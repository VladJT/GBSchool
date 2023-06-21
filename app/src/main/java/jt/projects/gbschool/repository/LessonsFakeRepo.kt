package jt.projects.gbschool.repository

import jt.projects.gbschool.model.Lesson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import java.time.LocalDate
import java.time.LocalTime

class LessonsFakeRepo : ILessonsRepo {

    override fun getLessonsByDate(date: LocalDate): Flow<List<Lesson>> {
        val lessons =
            listOf<Lesson>(
                Lesson(
                    timeStart = LocalTime.of(9, 0),
                    timeEnd = LocalTime.of(9, 45),
                    name = "Literature",
                    teacher = "Mrs. Barros"
                ),
                Lesson(
                    timeStart = LocalTime.of(10, 0),
                    timeEnd = LocalTime.of(11, 35),
                    name = "Physical Education",
                    teacher = "Mrs. Barros",
                    description = "Intensive preparation for the Junior World Championship in Los Angeles.",
                    isAdditional = true
                ),
                Lesson(
                    timeStart = LocalTime.of(8, 0),
                    timeEnd = LocalTime.of(8, 45),
                    name = "History",
                    teacher = "Mrs. Thomas",
                    isOpenIn = true
                )
            )
        return flowOf(lessons)
    }

}