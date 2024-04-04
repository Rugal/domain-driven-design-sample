package ga.rugal.ddd.infrastructure.dao

import ga.rugal.ddd.infrastructure.entity.RegistrationTable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface RegistrationDao : ReactiveCrudRepository<RegistrationTable, Int> {
  fun findByStudentId(id: Int): Flux<RegistrationTable>

  fun findByCourseId(id: Int): Flux<RegistrationTable>

  fun findByStudentIdAndCourseId(studentId: Int, courseId: Int): Mono<RegistrationTable>
}
