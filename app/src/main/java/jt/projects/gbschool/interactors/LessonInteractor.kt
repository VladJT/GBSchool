package jt.projects.gbschool.interactors

import jt.projects.gbschool.repository.ILessonsRepo
import jt.projects.gbschool.utils.CURRENT_TIME
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
        .map {
            var index = it
                .filter { lesson -> lesson.timeStart <= CURRENT_TIME }
                .lastIndex
            if (index == -1) index = 0

            it[index].isCurrent = true
            it
        }
}