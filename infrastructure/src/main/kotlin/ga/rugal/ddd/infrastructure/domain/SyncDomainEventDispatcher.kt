package ga.rugal.ddd.infrastructure.domain

import ga.rugal.ddd.domain.common.event.DomainEventListener
import ga.rugal.ddd.domain.common.event.EventQueue
import org.springframework.stereotype.Component

interface DomainEventDispatcher {
  fun dispatchNow(eventQueue: EventQueue)
}

@Component
class SyncDomainEventDispatcher(
  private val listeners: List<DomainEventListener>
) : DomainEventDispatcher {
  override fun dispatchNow(eventQueue: EventQueue) {
    eventQueue.queue.forEach { event ->
      listeners.forEach { listener ->
        listener.onEvent(event)
      }
    }
  }
}
