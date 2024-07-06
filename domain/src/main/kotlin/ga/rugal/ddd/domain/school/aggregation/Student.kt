package ga.rugal.ddd.domain.school.aggregation

import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.command.CreateStudentCommand
import ga.rugal.ddd.domain.school.mapper.StudentMapper

data class Student(
  val id: Int,
  val name: String,
  val faculty: String,
) {
  fun handle(queue: EventQueue, command: CreateStudentCommand): Student {
    StudentMapper.I.create(this).also(queue::enqueue)
    return this
  }
}
