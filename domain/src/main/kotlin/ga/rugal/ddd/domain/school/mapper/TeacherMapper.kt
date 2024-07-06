package ga.rugal.ddd.domain.school.mapper

import ga.rugal.ddd.domain.school.command.CreateTeacherCommand
import ga.rugal.ddd.domain.school.event.TeacherCreated
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import ga.rugal.ddd.domain.school.aggregation.Teacher as Aggregation

/**
 * The Data Mapper For teacher aggregation.
 *
 * @author Rugal Bernstein
 */
@Mapper(
  config = CentralConfig::class,
)
interface TeacherMapper {

  fun to(command: CreateTeacherCommand): Aggregation

  @Mapping(source = "id", target = "teacherId")
  fun create(e: Aggregation): TeacherCreated

  companion object {
    @JvmField
    val I = Mappers.getMapper(TeacherMapper::class.java)
  }
}
