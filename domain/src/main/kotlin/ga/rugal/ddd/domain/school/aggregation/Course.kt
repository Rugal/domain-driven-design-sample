package ga.rugal.ddd.domain.school.aggregation

import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.command.CreateCourseCommand
import ga.rugal.ddd.domain.school.event.CourseCreated

data class Course(
  val id: Int? = null,
  val name: String,
  val credit: Int,
  val teacherId: Int,
) {
  fun handle(queue: EventQueue, command: CreateCourseCommand) {
    queue.enqueue(CourseCreated(
      courseId = this.id!!,
      name = this.name,
      credit = this.credit,
      teacherId = this.teacherId,
    ))
  }
}
