package ga.rugal.ddd.infrastructure.repository

import ga.rugal.ddd.domain.school.aggregation.Registration
import ga.rugal.ddd.domain.school.repository.RegistrationRepository
import ga.rugal.ddd.infrastructure.dao.RegistrationDao
import ga.rugal.ddd.infrastructure.mapper.RegistrationMapper
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class RegistrationRepositoryImpl(
  private val dao: RegistrationDao,
) : RegistrationRepository {
  override fun findById(id: Int): Mono<Registration> = this.dao
    .findById(id)
    .map(RegistrationMapper.I::to)

  override fun findByStudentIdAndCourseId(studentId: Int, courseId: Int): Mono<Registration> = this.dao
    .findByStudentIdAndCourseId(studentId, courseId)
    .map(RegistrationMapper.I::to)

  override fun save(input: Registration): Mono<Registration> = this.dao
    .save(RegistrationMapper.I.from(input))
    .map(RegistrationMapper.I::to)

  override fun delete(input: Registration) {
    this.dao.deleteById(input.id!!)
  }
}
