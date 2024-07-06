package ga.rugal.ddd.domain.school.mapper

import ga.rugal.ddd.domain.school.command.CreateStudentCommand
import ga.rugal.ddd.domain.school.event.StudentCreated
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import ga.rugal.ddd.domain.school.aggregation.Student as Aggregation

/**
 * The Data Mapper For student aggregation.
 *
 * @author Rugal Bernstein
 */
@Mapper(
  config = CentralConfig::class,
)
interface StudentMapper {

  fun to(command: CreateStudentCommand): Aggregation

  @Mapping(source = "id", target = "studentId")
  fun create(e: Aggregation): StudentCreated

  companion object {
    @JvmField
    val I = Mappers.getMapper(StudentMapper::class.java)
  }
}
