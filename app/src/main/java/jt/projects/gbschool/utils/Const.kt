package jt.projects.gbschool.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * USER SETTINGS
 */
const val USER_NAME = "Mike"
val CURRENT_DATE = LocalDate.now()
val CURRENT_TIME = LocalTime.of(9, 35)
val CURRENT_DATE_TIME = LocalDateTime.of(CURRENT_DATE, CURRENT_TIME)
val EXAMS_DATE = LocalDateTime.of(CURRENT_DATE.plusDays(0), LocalTime.of(12, 30))


/**
 * LOGS
 */
const val LOG_TAG = "TAG"

/**
 * EXCEPTIONS
 */
val ViewModelNotInitException = IllegalStateException("The ViewModel should be initialised first")