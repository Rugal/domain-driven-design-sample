package ga.rugal.ddd.infrastructure.repository

import ga.rugal.ddd.domain.school.repository.StudentRepository
import ga.rugal.ddd.infrastructure.dao.StudentDao
import ga.rugal.ddd.infrastructure.mapper.StudentMapper
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import ga.rugal.ddd.domain.school.aggregation.Student as Aggregation

@Repository
class StudentRepositoryImpl(
  private val dao: StudentDao,
) : StudentRepository {
  override fun findById(id: Int): Mono<Aggregation> = this.dao
    .findById(id)
    .map(StudentMapper.I::to)

  override fun save(input: Aggregation): Mono<Aggregation> = this.dao
    .save(StudentMapper.I.from(input))
    .map(StudentMapper.I::to)
}
