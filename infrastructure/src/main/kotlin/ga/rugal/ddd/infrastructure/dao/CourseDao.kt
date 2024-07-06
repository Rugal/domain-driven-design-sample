package ga.rugal.ddd.infrastructure.dao

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import ga.rugal.ddd.infrastructure.entity.CourseTable as Table

interface CourseDao : ReactiveCrudRepository<Table, Int> {
  fun findByIdAndTeacherId(id: Int, teacherId: Int): Mono<Table>
}
