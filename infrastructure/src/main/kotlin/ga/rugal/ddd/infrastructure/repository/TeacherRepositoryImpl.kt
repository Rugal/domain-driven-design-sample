package ga.rugal.ddd.infrastructure.repository

import ga.rugal.ddd.domain.school.aggregation.Teacher
import ga.rugal.ddd.domain.school.repository.TeacherRepository
import ga.rugal.ddd.infrastructure.dao.TeacherDao
import ga.rugal.ddd.infrastructure.mapper.TeacherMapper
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class TeacherRepositoryImpl(
  private val dao: TeacherDao,
) : TeacherRepository {
  override fun findById(id: Int): Mono<Teacher> = this.dao
    .findById(id)
    .map(TeacherMapper.I::to)

  override fun save(input: Teacher): Mono<Teacher> = this.dao
    .save(TeacherMapper.I.from(input))
    .map(TeacherMapper.I::to)
}
