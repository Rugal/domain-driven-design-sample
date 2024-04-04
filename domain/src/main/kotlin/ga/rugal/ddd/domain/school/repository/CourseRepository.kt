package ga.rugal.ddd.domain.school.repository

import ga.rugal.ddd.domain.common.repository.Repository
import ga.rugal.ddd.domain.school.aggregation.Course
import reactor.core.publisher.Mono

interface CourseRepository : Repository {

  fun findById(id: Int): Mono<Course>

  fun save(input: Course): Mono<Course>
}
