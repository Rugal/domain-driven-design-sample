package ga.rugal.ddd.domain.school.event

import ga.rugal.ddd.domain.common.event.DomainEvent

data class CourseCreated(
  val courseId: Int,
  val name: String,
  val credit: Int,
  val teacherId: Int,
) : DomainEvent
