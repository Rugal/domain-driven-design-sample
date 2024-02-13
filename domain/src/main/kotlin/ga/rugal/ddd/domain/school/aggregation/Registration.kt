package ga.rugal.ddd.domain.school.aggregation

import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.command.CancelRegistrationCommand
import ga.rugal.ddd.domain.school.command.GradeStudentCommand
import ga.rugal.ddd.domain.school.command.RegisterCourseCommand
import ga.rugal.ddd.domain.school.event.CourseRegistered
import ga.rugal.ddd.domain.school.event.RegistrationCancelled
import ga.rugal.ddd.domain.school.event.StudentGraded
import ga.rugal.ddd.domain.school.exception.GradeFormatException
import ga.rugal.ddd.domain.school.exception.RegistrationAlreadyGradedException

data class Registration(
  val id: Int? = null,
  val studentId: Int,
  val courseId: Int,
  var grade: Int? = null,
) {
  val isGraded: Boolean
    get() = this.grade != null

  @Throws(GradeFormatException::class, RegistrationAlreadyGradedException::class)
  fun handle(queue: EventQueue, command: GradeStudentCommand) {
    // must not yet grade
    if (this.isGraded) throw RegistrationAlreadyGradedException()
    // grade must between 0-100
    if (command.grade !in 0..100) throw GradeFormatException()

    this.grade = command.grade

    queue.enqueue(StudentGraded(
      registrationId = this.id!!,
      grade = command.grade,
    ))
  }

  @Throws(RegistrationAlreadyGradedException::class)
  fun handle(queue: EventQueue, command: CancelRegistrationCommand) {
    // must not yet grade
    if (this.isGraded) throw RegistrationAlreadyGradedException()

    queue.enqueue(RegistrationCancelled(
      registrationId = this.id!!,
      courseId = this.courseId,
      studentId = this.studentId,
    ))
  }

  fun handle(queue: EventQueue, command: RegisterCourseCommand) {
    // event queue
    queue.enqueue(CourseRegistered(
      registrationId = this.id!!,
      courseId = this.courseId,
      studentId = this.studentId
    ))
  }
}
