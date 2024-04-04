package ga.rugal.ddd.infrastructure.repository

import ga.rugal.ddd.domain.school.aggregation.Course
import ga.rugal.ddd.domain.school.repository.CourseRepository
import ga.rugal.ddd.infrastructure.dao.CourseDao
import ga.rugal.ddd.infrastructure.entity.CourseTable
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CourseRepositoryImpl(
  private val dao: CourseDao,
) : CourseRepository {
  override fun findById(id: Int): Mono<Course> = this.dao
    .findById(id)
    .map {
      Course(
        id = it.id,
        name = it.name,
        credit = it.credit,
        teacherId = it.teacherId,
      )
    }

  override fun save(input: Course): Mono<Course> = this.dao
    .save(
      CourseTable(
        id = input.id,
        name = input.name,
        teacherId = input.teacherId,
        credit = input.credit,
      )
    )
    .map {
      Course(
        id = it.id,
        name = it.name,
        teacherId = it.teacherId,
        credit = it.credit,
      )
    }
}
