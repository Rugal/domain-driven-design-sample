package ga.rugal.ddd.infrastructure.dao

import ga.rugal.ddd.infrastructure.entity.TeacherTable
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TeacherDao : ReactiveCrudRepository<TeacherTable, Int>
