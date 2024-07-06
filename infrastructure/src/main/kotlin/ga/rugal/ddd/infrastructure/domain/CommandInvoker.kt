package ga.rugal.ddd.infrastructure.domain

import ga.rugal.ddd.domain.common.event.EventQueue
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CommandInvoker {
  fun <R> invoke(run: (EventQueue) -> Mono<R>): Mono<R>

  fun <R> invoke(run: (EventQueue) -> Flux<R>): Flux<R>
}

@Component
class OneTransactionCommandInvoker(
  private val dispatcher: DomainEventDispatcher,
) : CommandInvoker {

  override fun <R> invoke(run: (EventQueue) -> Mono<R>): Mono<R> = SimpleEventQueue().let { queue ->
    run(queue) // execute command
      .doOnSuccess { dispatcher.dispatchNow(queue) } // after everything committed, now dispatch domain event
  }

  override fun <R> invoke(run: (EventQueue) -> Flux<R>): Flux<R> = SimpleEventQueue().let { queue ->
    run(queue) // execute command
      .doOnComplete { dispatcher.dispatchNow(queue) } // after everything committed, now dispatch domain event
  }
}
