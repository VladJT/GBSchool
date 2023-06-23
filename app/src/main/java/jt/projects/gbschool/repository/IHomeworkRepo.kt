package jt.projects.gbschool.repository

import jt.projects.gbschool.model.Homework
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface IHomeworkRepo {
    fun getHomeworkByDate(date: LocalDate): Flow<List<Homework>>
}