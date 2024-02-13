package ga.rugal.ddd.domain.school.event

import ga.rugal.ddd.domain.common.event.DomainEvent

data class TeacherCreated(
  val id: Int,
  val name: String,
  val faculty: String,
) : DomainEvent
