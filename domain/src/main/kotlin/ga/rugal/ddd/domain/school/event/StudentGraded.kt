package ga.rugal.ddd.domain.school.event

import ga.rugal.ddd.domain.common.event.DomainEvent

data class StudentGraded(
  val registrationId: Int,
  val grade: Int,
) : DomainEvent
