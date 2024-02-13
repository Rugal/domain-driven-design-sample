package ga.rugal.ddd.domain.school.aggregation

import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.command.CreateTeacherCommand
import ga.rugal.ddd.domain.school.event.TeacherCreated

data class Teacher(
  val id: Int,
  val name: String,
  val faculty: String,
) {
  fun handle(queue: EventQueue, command: CreateTeacherCommand) {
    // event queue
    queue.enqueue(TeacherCreated(
      id = this.id,
      name = this.name,
      faculty = this.faculty,
    ))
  }
}
