package ga.rugal.ddd.domain.school.command

import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.aggregation.Registration
import ga.rugal.ddd.domain.school.exception.CourseNotFoundException
import ga.rugal.ddd.domain.school.exception.DuplicatedRegistrationException
import ga.rugal.ddd.domain.school.exception.StudentNotFoundException
import ga.rugal.ddd.domain.school.repository.CourseRepository
import ga.rugal.ddd.domain.school.repository.RegistrationRepository
import ga.rugal.ddd.domain.school.repository.StudentRepository
import org.springframework.stereotype.Component

data class RegisterCourseCommand(
  val courseId: Int,
  val studentId: Int,
)

@Component
class RegisterCourseCommandHandler(
  private val courseRepository: CourseRepository,
  private val studentRepository: StudentRepository,
  private val repository: RegistrationRepository,
  private val queue: EventQueue,
) {

  @Throws(CourseNotFoundException::class, StudentNotFoundException::class, DuplicatedRegistrationException::class)
  fun handle(command: RegisterCourseCommand): Registration {
    val course = this.courseRepository.findByIdOrNull(command.courseId) ?: throw CourseNotFoundException()
    val student = this.studentRepository.findByIdOrNull(command.studentId) ?: throw StudentNotFoundException()

    // check duplication
    if (null != this.repository.findByStudentIdAndCourseId(student.id, course.id!!)) {
      throw DuplicatedRegistrationException()
    }

    // persist
    return this.repository.save(Registration(
      courseId = command.courseId,
      studentId = command.studentId,
    )).also {
      it.handle(this.queue, command)
    }
  }
}
