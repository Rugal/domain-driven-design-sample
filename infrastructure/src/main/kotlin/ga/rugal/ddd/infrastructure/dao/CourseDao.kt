package ga.rugal.ddd.infrastructure.dao

import ga.rugal.ddd.infrastructure.entity.CourseTable
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CourseDao : ReactiveCrudRepository<CourseTable, Int>
