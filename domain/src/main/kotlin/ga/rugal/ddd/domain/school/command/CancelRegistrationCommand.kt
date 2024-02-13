package ga.rugal.ddd.domain.school.command

import ga.rugal.ddd.domain.common.event.EventQueue
import ga.rugal.ddd.domain.school.aggregation.Registration
import ga.rugal.ddd.domain.school.event.RegistrationCancelled
import ga.rugal.ddd.domain.school.exception.RegistrationAlreadyGradedException
import ga.rugal.ddd.domain.school.exception.RegistrationNotFoundException
import ga.rugal.ddd.domain.school.repository.RegistrationRepository
import org.springframework.stereotype.Component

data class CancelRegistrationCommand(
  val registrationId: Int,
)

@Component
class CancelRegistrationCommandHandler(
  private val repository: RegistrationRepository,
  private val queue: EventQueue,
) {

  @Throws(RegistrationNotFoundException::class, RegistrationAlreadyGradedException::class)
  fun handle(command: CancelRegistrationCommand): Registration {
    // must already exist
    val registration = this.repository.findByIdOrNull(command.registrationId) ?: throw RegistrationNotFoundException()

    registration.handle(this.queue, command)

    this.repository.delete(registration)

    return registration
  }
}
