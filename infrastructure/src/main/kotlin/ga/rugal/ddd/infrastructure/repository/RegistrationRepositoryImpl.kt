package ga.rugal.ddd.infrastructure.repository

import ga.rugal.ddd.domain.school.aggregation.Registration
import ga.rugal.ddd.domain.school.repository.RegistrationRepository
import ga.rugal.ddd.infrastructure.dao.RegistrationDao
import ga.rugal.ddd.infrastructure.entity.RegistrationTable
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class RegistrationRepositoryImpl(
  private val dao: RegistrationDao,
) : RegistrationRepository {
  override fun findById(id: Int): Mono<Registration> = this.dao
    .findById(id)
    .map {
      Registration(
        id = it.id,
        courseId = it.courseId,
        studentId = it.studentId,
        grade = it.grade,
      )
    }

  override fun findByStudentIdAndCourseId(studentId: Int, courseId: Int): Mono<Registration> = this.dao
    .findByStudentIdAndCourseId(studentId, courseId)
    .map {
      Registration(
        id = it.id,
        courseId = it.courseId,
        studentId = it.studentId,
        grade = it.grade,
      )
    }

  override fun save(input: Registration): Mono<Registration> = this.dao
    .save(
      RegistrationTable(
        id = input.id!!,
        courseId = input.courseId,
        studentId = input.studentId,
        grade = input.grade,
      )
    )
    .map {
      Registration(
        id = it.id,
        courseId = it.courseId,
        studentId = it.studentId,
        grade = it.grade,
      )
    }

  override fun delete(input: Registration) {
    this.dao.deleteById(input.id!!)
  }
}
