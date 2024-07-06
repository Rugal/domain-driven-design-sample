package ga.rugal.ddd.domain.school.aggregation

import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.command.CreateTeacherCommand
import ga.rugal.ddd.domain.school.mapper.TeacherMapper

data class Teacher(
  val id: Int,
  val name: String,
  val faculty: String,
) {
  fun handle(queue: EventQueue, command: CreateTeacherCommand): Teacher {
    TeacherMapper.I.create(this).also(queue::enqueue)
    return this
  }
}
