package ga.rugal.ddd.domain.school.command

import ga.rugal.ddd.domain.common.command.Command
import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.aggregation.Registration
import ga.rugal.ddd.domain.school.exception.CourseNotFoundException
import ga.rugal.ddd.domain.school.exception.DuplicatedRegistrationException
import ga.rugal.ddd.domain.school.exception.StudentNotFoundException
import ga.rugal.ddd.domain.school.repository.CourseRepository
import ga.rugal.ddd.domain.school.repository.RegistrationRepository
import ga.rugal.ddd.domain.school.repository.StudentRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

data class RegisterCourseCommand(
  val courseId: Int,
  val studentId: Int,
) : Command

@Component
class RegisterCourseCommandHandler(
  private val courseRepository: CourseRepository,
  private val studentRepository: StudentRepository,
  private val repository: RegistrationRepository,
  private val queue: EventQueue,
) {

  @Throws(CourseNotFoundException::class, StudentNotFoundException::class, DuplicatedRegistrationException::class)
  fun handle(command: RegisterCourseCommand): Mono<Registration> {
    fun notExistsByStudentAndCourse(): Mono<Boolean> =
      this.repository.findByStudentIdAndCourseId(command.studentId, command.courseId).hasElement().map { !it }

    return Flux
      .merge(
        notExistsByStudentAndCourse(), // return true/false
        studentRepository.findById(command.studentId).hasElement(), // will emit error if wrong
        courseRepository.findById(command.courseId).hasElement(),   // will emit error if wrong
      )
      .reduce { t, u -> t && u }
      .filter { it } // either true or some error
      .switchIfEmpty { Mono.error(DuplicatedRegistrationException()) }
      .flatMap {
        this.repository.save(Registration(
          courseId = command.courseId,
          studentId = command.studentId,
        )).doOnNext {
          it.handle(this.queue, command)
        }
      }
  }
}
