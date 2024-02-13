package ga.rugal.ddd.domain.school.repository

import ga.rugal.ddd.domain.common.repository.Repository
import ga.rugal.ddd.domain.school.aggregation.Registration

interface RegistrationRepository : Repository {

  fun findByIdOrNull(id: Int): Registration?

  fun findByStudentIdAndCourseId(studentId: Int, courseId: Int): Registration?

  fun save(input: Registration): Registration

  fun delete(input: Registration)
}
