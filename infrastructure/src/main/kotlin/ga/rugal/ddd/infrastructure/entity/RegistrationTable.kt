package ga.rugal.ddd.infrastructure.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "registration")
class RegistrationTable(
  @Id val id: Int,
  val courseId: Int,
  val studentId: Int,
  val grade: Int? = null,
)
