package ga.rugal.ddd.domain.school.event

import ga.rugal.ddd.domain.common.event.DomainEvent

data class RegistrationCancelled(
  val registrationId: Int,
  val courseId: Int,
  val studentId: Int,
) : DomainEvent
