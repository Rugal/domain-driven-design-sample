package ga.rugal.ddd.domain.school.repository

import ga.rugal.ddd.domain.common.repository.Repository
import ga.rugal.ddd.domain.school.aggregation.Student

interface StudentRepository : Repository {

  fun findByIdOrNull(id: Int): Student?

  fun save(input: Student): Student
}
