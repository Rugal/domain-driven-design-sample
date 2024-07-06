package ga.rugal.ddd.domain.school.command

import ga.rugal.ddd.domain.common.command.Command
import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.aggregation.Course
import ga.rugal.ddd.domain.school.mapper.CourseMapper
import ga.rugal.ddd.domain.school.repository.CourseRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

data class CreateCourseCommand(
  val teacherId: Int,
  val name: String,
  val credit: Int,
) : Command

@Component
class CreateCourseCommandHandler(
  private val repository: CourseRepository,
  private val queue: EventQueue,
) {

  fun handle(command: CreateCourseCommand): Mono<Course> {
    return this.repository.save(CourseMapper.I.to(command))
      .doOnNext { it.handle(this.queue, command) }
  }
}
