package jt.projects.gbschool.interactors

import jt.projects.gbschool.repository.IHomeworkRepo
import jt.projects.gbschool.utils.CURRENT_DATE
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.Period


class HomeworkInteractor(private val repo: IHomeworkRepo) {

    fun getHomeworkByDate(searchDate: LocalDate) = repo
        .getHomeworkByDate(searchDate)
        .map { it ->
            it.filter { it.date >= searchDate }
        }
        .map { it ->
            it.forEach {
                it.daysLeft = Period.between(CURRENT_DATE, it.date).days
            }
            it.sortedBy { it.date }
        }
}