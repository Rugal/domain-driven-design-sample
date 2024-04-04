package ga.rugal.ddd.infrastructure.dao

import ga.rugal.ddd.infrastructure.entity.StudentTable
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface StudentDao : ReactiveCrudRepository<StudentTable, Int>
