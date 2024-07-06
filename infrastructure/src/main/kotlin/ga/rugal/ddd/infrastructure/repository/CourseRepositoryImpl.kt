package ga.rugal.ddd.infrastructure.repository

import ga.rugal.ddd.domain.school.repository.CourseRepository
import ga.rugal.ddd.infrastructure.dao.CourseDao
import ga.rugal.ddd.infrastructure.mapper.CourseMapper
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import ga.rugal.ddd.domain.school.aggregation.Course as Aggregation

@Repository
class CourseRepositoryImpl(
  private val dao: CourseDao,
) : CourseRepository {
  override fun findById(id: Int): Mono<Aggregation> = this.dao
    .findById(id)
    .map(CourseMapper.I::to)

  override fun findByIdAndTeacherId(id: Int, teacherId: Int): Mono<Aggregation> =
    this.dao
      .findByIdAndTeacherId(id, teacherId)
      .map(CourseMapper.I::to)

  override fun save(input: Aggregation): Mono<Aggregation> = this.dao
    .save(CourseMapper.I.from(input))
    .map(CourseMapper.I::to)
}
