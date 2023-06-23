package jt.projects.gbschool.model

import java.time.LocalDate
import java.time.LocalTime

data class Homework(
    val date: LocalDate = LocalDate.now().plusDays(2),
    val name: String,
    val description: String = "",
    val image: String = "https://thumbs.dreamstime.com/b/corel-pictogramm-141634577.jpg",
    var daysLeft : Int = 0
)
