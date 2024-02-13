package ga.rugal.ddd.domain.school.repository

import ga.rugal.ddd.domain.common.repository.Repository
import ga.rugal.ddd.domain.school.aggregation.Teacher

interface TeacherRepository : Repository {

  fun findByIdOrNull(id: Int): Teacher?

  fun save(input: Teacher): Teacher
}
