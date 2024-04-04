package ga.rugal.ddd.infrastructure.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "student")
class StudentTable(
  @Id val id: Int,
  val name: String,
  val faculty: String,
)
