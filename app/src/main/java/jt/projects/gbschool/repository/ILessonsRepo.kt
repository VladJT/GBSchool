package jt.projects.gbschool.repository

import jt.projects.gbschool.model.Lesson
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface ILessonsRepo {
    fun getLessonsByDate(date: LocalDate): Flow<List<Lesson>>
}