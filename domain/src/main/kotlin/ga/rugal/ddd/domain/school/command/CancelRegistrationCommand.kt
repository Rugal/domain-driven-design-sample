package ga.rugal.ddd.domain.school.command

import ga.rugal.ddd.domain.common.command.Command
import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.aggregation.Registration
import ga.rugal.ddd.domain.school.exception.RegistrationAlreadyGradedException
import ga.rugal.ddd.domain.school.exception.RegistrationNotFoundException
import ga.rugal.ddd.domain.school.repository.RegistrationRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

data class CancelRegistrationCommand(
  val registrationId: Int,
) : Command

@Component
class CancelRegistrationCommandHandler(
  private val repository: RegistrationRepository,
  private val queue: EventQueue,
) {

  @Throws(RegistrationNotFoundException::class, RegistrationAlreadyGradedException::class)
  fun handle(command: CancelRegistrationCommand): Mono<Registration> = this.repository
    .findById(command.registrationId)
    .switchIfEmpty { Mono.error(RegistrationNotFoundException()) }
    .map {
      it.handle(this.queue, command) // will throw exception if already graded
      this.repository.delete(it)
      it
    }
}
