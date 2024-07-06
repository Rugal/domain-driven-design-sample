package ga.rugal.ddd.domain.school.aggregation

import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.command.CancelRegistrationCommand
import ga.rugal.ddd.domain.school.command.GradeStudentCommand
import ga.rugal.ddd.domain.school.command.RegisterCourseCommand
import ga.rugal.ddd.domain.school.exception.AccessDeniedException
import ga.rugal.ddd.domain.school.exception.GradeFormatException
import ga.rugal.ddd.domain.school.exception.RegistrationAlreadyGradedException
import ga.rugal.ddd.domain.school.mapper.RegistrationMapper
import ga.rugal.ddd.domain.school.repository.CourseRepository

data class Registration(
  val id: Int? = null,
  val studentId: Int,
  val courseId: Int,
  var grade: Int? = null,
) {
  val isGraded: Boolean
    get() = this.grade != null

  @Throws(GradeFormatException::class, RegistrationAlreadyGradedException::class, AccessDeniedException::class)
  fun handle(queue: EventQueue, command: GradeStudentCommand, courseRepository: CourseRepository): Registration {
    // determine if this course is created by this teacher
    val isCourseOwner = courseRepository
      .findByIdAndTeacherId(this.courseId, command.teacherId)
      .hasElement()
      .block()!!
    // this teacher can not grade for this very course
    if (!isCourseOwner) throw AccessDeniedException()

    // must not yet grade
    if (this.isGraded) throw RegistrationAlreadyGradedException()

    // grade must between 0-100
    if (command.grade !in 0..100) throw GradeFormatException()

    this.grade = command.grade

    RegistrationMapper.I.grade(this).also(queue::enqueue)
    return this
  }

  @Throws(RegistrationAlreadyGradedException::class)
  fun handle(queue: EventQueue, command: CancelRegistrationCommand): Registration {
    // must not yet been graded
    if (this.isGraded) throw RegistrationAlreadyGradedException()

    RegistrationMapper.I.cancel(this).also(queue::enqueue)
    return this
  }

  fun handle(queue: EventQueue, command: RegisterCourseCommand): Registration {
    RegistrationMapper.I.create(this).also(queue::enqueue)
    return this
  }
}
