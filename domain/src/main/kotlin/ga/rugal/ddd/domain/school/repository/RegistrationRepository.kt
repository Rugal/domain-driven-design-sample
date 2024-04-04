package ga.rugal.ddd.domain.school.repository

import ga.rugal.ddd.domain.common.repository.Repository
import ga.rugal.ddd.domain.school.aggregation.Registration
import reactor.core.publisher.Mono

interface RegistrationRepository : Repository {

  fun findById(id: Int): Mono<Registration>

  fun findByStudentIdAndCourseId(studentId: Int, courseId: Int): Mono<Registration>

  fun save(input: Registration): Mono<Registration>

  fun delete(input: Registration)
}
