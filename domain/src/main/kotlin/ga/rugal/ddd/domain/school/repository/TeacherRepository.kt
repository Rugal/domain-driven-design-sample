package ga.rugal.ddd.domain.school.repository

import ga.rugal.ddd.domain.common.repository.Repository
import ga.rugal.ddd.domain.school.aggregation.Teacher
import reactor.core.publisher.Mono

//import reactor.core.publisher.Mono

interface TeacherRepository : Repository {

  fun findById(id: Int): Mono<Teacher>

  fun save(input: Teacher): Mono<Teacher>
}
