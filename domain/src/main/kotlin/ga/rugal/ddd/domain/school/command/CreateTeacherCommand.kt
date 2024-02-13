package ga.rugal.ddd.domain.school.command

import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.aggregation.Teacher
import ga.rugal.ddd.domain.school.exception.DuplicatedTeacherException
import ga.rugal.ddd.domain.school.repository.TeacherRepository
import org.springframework.stereotype.Component

data class CreateTeacherCommand(
  val id: Int,
  val name: String,
  val faculty: String,
)

@Component
class CreateTeacherCommandHandler(
  private val repository: TeacherRepository,
  private val queue: EventQueue,
) {

  fun handle(command: CreateTeacherCommand): Teacher {
    // to make sure id is unique
    val teacher = this.repository.findByIdOrNull(command.id)
    if (null != teacher) throw DuplicatedTeacherException()
    // persist
    return this.repository.save(Teacher(
      id = command.id,
      name = command.name,
      faculty = command.faculty,
    )).also {
      it.handle(this.queue, command)
    }
  }
}
