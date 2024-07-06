package ga.rugal.ddd.infrastructure.mapper

import ga.rugal.ddd.domain.school.mapper.CentralConfig
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ga.rugal.ddd.domain.school.aggregation.Student as Aggregation
import ga.rugal.ddd.infrastructure.entity.StudentTable as Table

/**
 * The Data Mapper For student aggregation.
 *
 * @author Rugal Bernstein
 */
@Mapper(
  config = CentralConfig::class,
)
interface StudentMapper {

  fun from(e: Aggregation): Table

  fun to(e: Table): Aggregation

  companion object {
    @JvmField
    val I = Mappers.getMapper(StudentMapper::class.java)
  }
}
