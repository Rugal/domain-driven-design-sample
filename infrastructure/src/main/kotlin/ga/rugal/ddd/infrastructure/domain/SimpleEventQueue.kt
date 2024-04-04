package ga.rugal.ddd.infrastructure.domain

import java.util.LinkedList
import ga.rugal.ddd.domain.common.event.DomainEvent
import ga.rugal.ddd.domain.common.event.EventQueue

class SimpleEventQueue : EventQueue {
  private val q: MutableList<DomainEvent> = LinkedList()

  override fun enqueue(e: DomainEvent) {
    this.q += e
  }

  override val queue: List<DomainEvent>
    get() = this.q

}
