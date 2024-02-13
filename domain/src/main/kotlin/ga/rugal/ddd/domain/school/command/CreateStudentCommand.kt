package ga.rugal.ddd.domain.school.command

import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.aggregation.Student
import ga.rugal.ddd.domain.school.event.StudentCreated
import ga.rugal.ddd.domain.school.exception.DuplicatedStudentException
import ga.rugal.ddd.domain.school.repository.StudentRepository
import org.springframework.stereotype.Component

data class CreateStudentCommand(
  val id: Int,
  val name: String,
  val faculty: String,
)

@Component
class CreateStudentCommandHandler(
  private val repository: StudentRepository,
  private val queue: EventQueue,
) {

  fun handle(command: CreateStudentCommand): Student {
    // to make sure id is unique
    val student = this.repository.findByIdOrNull(command.id)
    if (null != student) throw DuplicatedStudentException()
    // persist
    return this.repository.save(Student(
      id = command.id,
      name = command.name,
      faculty = command.faculty,
    )).also {
      it.handle(this.queue, command)
    }
  }
}
