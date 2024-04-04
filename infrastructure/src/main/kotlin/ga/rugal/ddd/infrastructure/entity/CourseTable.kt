package ga.rugal.ddd.infrastructure.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "course")
class CourseTable(
  @Id val id: Int,
  val name: String,
  val teacherId: Int,
  val credit: Int,
)
