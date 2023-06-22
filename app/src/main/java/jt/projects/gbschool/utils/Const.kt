package jt.projects.gbschool.utils

import java.time.LocalDate
import java.time.LocalTime

/**
 * USER SETTINGS
 */
const val USER_NAME = "Mike"
val CURRENT_DATE = LocalDate.now()
val CURRENT_TIME = LocalTime.of(9, 35)


/**
 * LOGS
 */
const val LOG_TAG = "TAG"

/**
 * EXCEPTIONS
 */
val ViewModelNotInitException = IllegalStateException("The ViewModel should be initialised first")