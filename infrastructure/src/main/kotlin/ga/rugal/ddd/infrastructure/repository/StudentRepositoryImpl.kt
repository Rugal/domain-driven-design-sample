package ga.rugal.ddd.infrastructure.repository

import ga.rugal.ddd.domain.school.aggregation.Student
import ga.rugal.ddd.domain.school.repository.StudentRepository
import ga.rugal.ddd.infrastructure.dao.StudentDao
import ga.rugal.ddd.infrastructure.entity.StudentTable
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class StudentRepositoryImpl(
  private val dao: StudentDao,
) : StudentRepository {
  override fun findById(id: Int): Mono<Student> = this.dao
    .findById(id)
    .map {
      Student(
        id = it.id,
        name = it.name,
        faculty = it.faculty,
      )
    }

  override fun save(input: Student): Mono<Student> = this.dao
    .save(
      StudentTable(
        id = input.id,
        name = input.name,
        faculty = input.faculty,
      )
    )
    .map {
      Student(
        id = it.id,
        name = it.name,
        faculty = it.faculty,
      )
    }
}
