package ga.rugal.ddd.infrastructure.domain

import java.util.LinkedList
import ga.rugal.ddd.domain.common.event.DomainEvent
import ga.rugal.ddd.domain.common.event.EventQueue

class SimpleEventQueue : EventQueue {
  private val queue: MutableList<DomainEvent> = LinkedList()

  override fun enqueue(e: DomainEvent) {
    queue += e
  }

  override fun queue(): List<DomainEvent> = queue
}
