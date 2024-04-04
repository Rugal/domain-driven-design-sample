package ga.rugal.ddd.domain.common.event

interface DomainEventListener {
  fun onEvent(event: DomainEvent)
}
