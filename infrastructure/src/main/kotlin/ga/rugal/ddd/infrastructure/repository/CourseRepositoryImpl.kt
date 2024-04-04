package ga.rugal.ddd.infrastructure.repository

import ga.rugal.ddd.domain.school.aggregation.Course
import ga.rugal.ddd.domain.school.repository.CourseRepository
import ga.rugal.ddd.infrastructure.dao.CourseDao
import ga.rugal.ddd.infrastructure.entity.CourseTable
import org.springframework.stereotype.Component

// FIXME: change to reactive
@Component
class CourseRepositoryImpl(
  private val dao: CourseDao,
) : CourseRepository {
  override fun findByIdOrNull(id: Int): Course? {
    this.dao.findById(id)
    return null
  }

  override fun save(input: Course): Course {
    this.dao.save(
      CourseTable(
        id = input.id,
        name = input.name,
        teacherId = input.teacherId,
        credit = input.credit,
      )
    )
    return input
  }
}
