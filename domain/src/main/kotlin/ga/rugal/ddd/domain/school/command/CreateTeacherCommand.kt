package ga.rugal.ddd.domain.school.command

import ga.rugal.ddd.domain.common.command.Command
import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.aggregation.Teacher
import ga.rugal.ddd.domain.school.exception.DuplicatedTeacherException
import ga.rugal.ddd.domain.school.repository.TeacherRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

data class CreateTeacherCommand(
  val id: Int,
  val name: String,
  val faculty: String,
) : Command

@Component
class CreateTeacherCommandHandler(
  private val repository: TeacherRepository,
  private val queue: EventQueue,
) {

  fun handle(command: CreateTeacherCommand): Mono<Teacher> = this.repository
    .findById(command.id)
    .hasElement()
    .filter { it == false }
    .switchIfEmpty { Mono.error(DuplicatedTeacherException()) }
    .flatMap {
      this.repository.save(Teacher(
        id = command.id,
        name = command.name,
        faculty = command.faculty,
      )).doOnNext {
        it.handle(this.queue, command)
      }
    }
}
