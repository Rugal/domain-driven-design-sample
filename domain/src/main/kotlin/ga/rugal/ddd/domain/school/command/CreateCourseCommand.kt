package ga.rugal.ddd.domain.school.command

import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.aggregation.Course
import ga.rugal.ddd.domain.school.repository.CourseRepository
import org.springframework.stereotype.Component

data class CreateCourseCommand(
  val teacherId: Int,
  val name: String,
  val credit: Int,
)

@Component
class CreateCourseCommandHandler(
  private val repository: CourseRepository,
  private val queue: EventQueue,
) {

  fun handle(command: CreateCourseCommand): Course {
    // repository should return course id
    return this.repository.save(Course(
      name = command.name,
      credit = command.credit,
      teacherId = command.teacherId,
    )).also {
      it.handle(this.queue, command)
    }
  }
}
