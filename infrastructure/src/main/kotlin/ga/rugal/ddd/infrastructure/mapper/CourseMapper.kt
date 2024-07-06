package ga.rugal.ddd.infrastructure.mapper

import ga.rugal.ddd.domain.school.mapper.CentralConfig
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ga.rugal.ddd.domain.school.aggregation.Course as Aggregation
import ga.rugal.ddd.infrastructure.entity.CourseTable as Table

/**
 * The Data Mapper For course aggregation.
 *
 * @author Rugal Bernstein
 */
@Mapper(
  config = CentralConfig::class,
)
interface CourseMapper {

  fun from(e: Aggregation): Table

  fun to(e: Table): Aggregation

  companion object {
    @JvmField
    val I = Mappers.getMapper(CourseMapper::class.java)
  }
}
