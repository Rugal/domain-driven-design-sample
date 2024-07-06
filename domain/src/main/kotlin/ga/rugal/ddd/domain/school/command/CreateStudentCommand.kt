package ga.rugal.ddd.domain.school.command

import ga.rugal.ddd.domain.common.command.Command
import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.aggregation.Student
import ga.rugal.ddd.domain.school.exception.DuplicatedStudentException
import ga.rugal.ddd.domain.school.mapper.StudentMapper
import ga.rugal.ddd.domain.school.repository.StudentRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

data class CreateStudentCommand(
  val id: Int,
  val name: String,
  val faculty: String,
) : Command

@Component
class CreateStudentCommandHandler(
  private val repository: StudentRepository,
  private val queue: EventQueue,
) {

  fun handle(command: CreateStudentCommand): Mono<Student> = this.repository
    .findById(command.id)
    .hasElement()
    .flatMap {
      if (it) Mono.error(DuplicatedStudentException(command.id)) else this.repository.save(StudentMapper.I.to(command))
    }
    .doOnNext { it.handle(queue, command) }
}
