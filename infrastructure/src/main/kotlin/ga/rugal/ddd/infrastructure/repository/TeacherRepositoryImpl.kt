package ga.rugal.ddd.infrastructure.repository

import ga.rugal.ddd.domain.school.aggregation.Teacher
import ga.rugal.ddd.domain.school.repository.TeacherRepository
import ga.rugal.ddd.infrastructure.dao.TeacherDao
import ga.rugal.ddd.infrastructure.entity.TeacherTable
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class TeacherRepositoryImpl(
  private val dao: TeacherDao,
) : TeacherRepository {
  override fun findById(id: Int): Mono<Teacher> = this.dao
    .findById(id)
    .map {
      Teacher(
        id = it.id,
        name = it.name,
        faculty = it.faculty,
      )
    }

  override fun save(input: Teacher): Mono<Teacher> = this.dao
    .save(
      TeacherTable(
        id = input.id,
        name = input.name,
        faculty = input.faculty,
      )
    )
    .map {
      Teacher(
        id = it.id,
        name = it.name,
        faculty = it.faculty,
      )
    }
}
