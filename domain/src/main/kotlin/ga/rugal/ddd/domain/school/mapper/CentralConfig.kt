package ga.rugal.ddd.domain.school.mapper

import org.mapstruct.MapperConfig
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

/**
 * Central configuration for data mapper.
 *
 * @author Rugal Bernstein
 */
@MapperConfig(
  unmappedTargetPolicy = ReportingPolicy.IGNORE,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface CentralConfig
