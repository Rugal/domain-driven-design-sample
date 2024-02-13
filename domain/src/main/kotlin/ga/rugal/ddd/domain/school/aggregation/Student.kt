package ga.rugal.ddd.domain.school.aggregation

import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.command.CreateStudentCommand
import ga.rugal.ddd.domain.school.event.StudentCreated

data class Student(
  val id: Int,
  val name: String,
  val faculty: String,
) {
  fun handle(queue: EventQueue, command: CreateStudentCommand) {
    queue.enqueue(StudentCreated(
      id = this.id,
      name = this.name,
      faculty = this.faculty,
    ))
  }
}
