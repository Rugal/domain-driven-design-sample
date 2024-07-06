package ga.rugal.ddd.infrastructure.mapper

import ga.rugal.ddd.domain.school.mapper.CentralConfig
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ga.rugal.ddd.domain.school.aggregation.Teacher as Aggregation
import ga.rugal.ddd.infrastructure.entity.TeacherTable as Table

/**
 * The Data Mapper For teacher aggregation.
 *
 * @author Rugal Bernstein
 */
@Mapper(
  config = CentralConfig::class,
)
interface TeacherMapper {

  fun from(e: Aggregation): Table

  fun to(e: Table): Aggregation

  companion object {
    @JvmField
    val I = Mappers.getMapper(TeacherMapper::class.java)
  }
}
