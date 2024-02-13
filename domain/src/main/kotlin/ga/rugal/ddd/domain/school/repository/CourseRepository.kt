package ga.rugal.ddd.domain.school.repository

import ga.rugal.ddd.domain.common.repository.Repository
import ga.rugal.ddd.domain.school.aggregation.Course

interface CourseRepository : Repository {

  fun findByIdOrNull(id: Int): Course?

  fun save(input: Course): Course
}
