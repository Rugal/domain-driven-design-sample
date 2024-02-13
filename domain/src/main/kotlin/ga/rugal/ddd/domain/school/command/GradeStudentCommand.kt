package ga.rugal.ddd.domain.school.command

import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.aggregation.Registration
import ga.rugal.ddd.domain.school.event.StudentGraded
import ga.rugal.ddd.domain.school.exception.GradeOutsideException
import ga.rugal.ddd.domain.school.exception.GradeFormatException
import ga.rugal.ddd.domain.school.exception.RegistrationAlreadyGradedException
import ga.rugal.ddd.domain.school.exception.RegistrationNotFoundException
import ga.rugal.ddd.domain.school.repository.CourseRepository
import ga.rugal.ddd.domain.school.repository.RegistrationRepository
import org.springframework.stereotype.Component

data class GradeStudentCommand(
  val registrationId: Int,
  val teacherId: Int,
  val grade: Int,
)

@Component
class GradeStudentCommandHandler(
  private val repository: RegistrationRepository,
  private val courseRepository: CourseRepository,
  private val queue: EventQueue,
) {

  @Throws(RegistrationNotFoundException::class, RegistrationAlreadyGradedException::class, GradeFormatException::class)
  fun handle(command: GradeStudentCommand): Registration {
    // must already exist
    val registration = this.repository.findByIdOrNull(command.registrationId) ?: throw RegistrationNotFoundException()
    // must only grade student under
    if (this.courseRepository.findByIdOrNull(registration.courseId)!!.teacherId != command.teacherId) throw GradeOutsideException()

    registration.handle(this.queue, command)

    return this.repository.save(
      registration.copy(
        grade = command.grade
      )
    )
  }
}
