package ga.rugal.ddd.domain.school.repository

import ga.rugal.ddd.domain.common.repository.Repository
import ga.rugal.ddd.domain.school.aggregation.Student
import reactor.core.publisher.Mono

interface StudentRepository : Repository {

  fun findById(id: Int): Mono<Student>

  fun save(input: Student): Mono<Student>
}
