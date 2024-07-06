package ga.rugal.ddd.domain.school.mapper

import ga.rugal.ddd.domain.school.command.RegisterCourseCommand
import ga.rugal.ddd.domain.school.event.CourseRegistered
import ga.rugal.ddd.domain.school.event.RegistrationCancelled
import ga.rugal.ddd.domain.school.event.StudentGraded
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import ga.rugal.ddd.domain.school.aggregation.Registration as Aggregation

/**
 * The Data Mapper For registration aggregation.
 *
 * @author Rugal Bernstein
 */
@Mapper(
  config = CentralConfig::class,
)
interface RegistrationMapper {

  fun to(command: RegisterCourseCommand): Aggregation

  @Mapping(source = "id", target = "registrationId")
  fun create(e: Aggregation): CourseRegistered

  @Mapping(source = "id", target = "registrationId")
  fun cancel(e: Aggregation): RegistrationCancelled

  @Mapping(source = "id", target = "registrationId")
  fun grade(e: Aggregation): StudentGraded

  companion object {
    @JvmField
    val I = Mappers.getMapper(RegistrationMapper::class.java)
  }
}
