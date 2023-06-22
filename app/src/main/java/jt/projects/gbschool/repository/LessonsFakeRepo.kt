package jt.projects.gbschool.repository

import jt.projects.gbschool.model.Lesson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
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
                    teacher = "Mrs. Barros",
                    image = "https://static.tildacdn.com/tild3364-3461-4464-b561-613032346536/_.png"
                ),
                Lesson(
                    timeStart = LocalTime.of(11, 50),
                    timeEnd = LocalTime.of(13, 0),
                    name = "GermanLanguage",
                    teacher = "Mrs. Keneth",
                    isOpenIn = true,
                    image = "https://static.tildacdn.com/tild3364-3461-4464-b561-613032346536/_.png"
                ),
                Lesson(
                    timeStart = LocalTime.of(10, 0),
                    timeEnd = LocalTime.of(11, 35),
                    name = "Physical Education",
                    teacher = "Mrs. Barros",
                    description = "Intensive preparation for the Junior World Championship in Los Angeles.",
                    isAdditional = true,
                    image = "https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-basketball-icon-set-of-basketball-balls-isolated-on-white-background-png-image_1855711.jpg"
                ),
                Lesson(
                    timeStart = LocalTime.of(8, 0),
                    timeEnd = LocalTime.of(8, 45),
                    name = "History",
                    teacher = "Mrs. Thomas",
                    isOpenIn = true,
                    image = "https://static.vecteezy.com/system/resources/previews/000/424/166/original/bow-icon-vector-illustration.jpg"
                )
            )
        return flowOf(lessons).onStart { delay(500L) }
    }

}