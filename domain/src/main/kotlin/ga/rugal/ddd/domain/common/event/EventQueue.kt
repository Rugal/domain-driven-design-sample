package ga.rugal.ddd.domain.common.event

interface EventQueue {
  fun enqueue(e: DomainEvent)

  val queue: List<DomainEvent>
}
