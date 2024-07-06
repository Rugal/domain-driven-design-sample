package ga.rugal.ddd.domain.school.mapper

import ga.rugal.ddd.domain.school.command.CreateCourseCommand
import ga.rugal.ddd.domain.school.event.CourseCreated
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import ga.rugal.ddd.domain.school.aggregation.Course as Aggregation

/**
 * The Data Mapper For course aggregation.
 *
 * @author Rugal Bernstein
 */
@Mapper(
  config = CentralConfig::class,
)
interface CourseMapper {

  fun to(command: CreateCourseCommand): Aggregation

  @Mapping(source = "id", target = "courseId")
  fun create(e: Aggregation): CourseCreated

  companion object {
    @JvmField
    val I = Mappers.getMapper(CourseMapper::class.java)
  }
}
