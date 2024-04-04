package ga.rugal.ddd.domain.school.command

import ga.rugal.ddd.domain.common.command.Command
import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.aggregation.Registration
import ga.rugal.ddd.domain.school.exception.GradeFormatException
import ga.rugal.ddd.domain.school.exception.GradeOutsideException
import ga.rugal.ddd.domain.school.exception.RegistrationAlreadyGradedException
import ga.rugal.ddd.domain.school.exception.RegistrationNotFoundException
import ga.rugal.ddd.domain.school.repository.CourseRepository
import ga.rugal.ddd.domain.school.repository.RegistrationRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

data class GradeStudentCommand(
  val registrationId: Int,
  val teacherId: Int,
  val grade: Int,
) : Command

@Component
class GradeStudentCommandHandler(
  private val repository: RegistrationRepository,
  private val courseRepository: CourseRepository,
  private val queue: EventQueue,
) {

  @Throws(RegistrationNotFoundException::class, RegistrationAlreadyGradedException::class, GradeFormatException::class)
  fun handle(command: GradeStudentCommand): Mono<Registration> = this.repository
    .findById(command.registrationId)
    .switchIfEmpty { Mono.error(RegistrationNotFoundException()) }
    .filterWhen { r ->
      this.courseRepository
        .findById(r.courseId)
        .map { it.teacherId == command.teacherId }
    }
    .switchIfEmpty { Mono.error(GradeOutsideException()) }
    .map {
      it.handle(this.queue, command)
      this.repository.save(
        it.copy(
          grade = command.grade
        )
      )
      it
    }
}
