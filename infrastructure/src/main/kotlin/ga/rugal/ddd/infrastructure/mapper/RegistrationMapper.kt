package ga.rugal.ddd.infrastructure.mapper

import ga.rugal.ddd.domain.school.mapper.CentralConfig
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ga.rugal.ddd.domain.school.aggregation.Registration as Aggregation
import ga.rugal.ddd.infrastructure.entity.RegistrationTable as Table

/**
 * The Data Mapper For registration aggregation.
 *
 * @author Rugal Bernstein
 */
@Mapper(
  config = CentralConfig::class,
)
interface RegistrationMapper {

  fun from(e: Aggregation): Table

  fun to(e: Table): Aggregation

  companion object {
    @JvmField
    val I = Mappers.getMapper(RegistrationMapper::class.java)
  }
}
