package ga.rugal.ddd.domain.school.event

import ga.rugal.ddd.domain.common.event.DomainEvent

data class StudentCreated(
  val studentId: Int,
  val name: String,
  val faculty: String,
) : DomainEvent
