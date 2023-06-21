package jt.projects.gbschool.interactors

import jt.projects.gbschool.repository.ILessonsRepo
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class LessonInteractor(private val repo: ILessonsRepo) {

    fun getLessonsByDate(searchDate: LocalDate) = repo
        .getLessonsByDate(searchDate)
        .map { it ->
            it.filter { it.date == searchDate }
        }
        .map { it ->
            it.sortedBy { it.timeStart }
        }
}