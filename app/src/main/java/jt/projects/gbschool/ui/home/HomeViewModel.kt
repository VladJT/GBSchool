package jt.projects.gbschool.ui.home

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jt.projects.gbschool.interactors.HomeworkInteractor
import jt.projects.gbschool.interactors.LessonInteractor
import jt.projects.gbschool.model.Homework
import jt.projects.gbschool.model.Lesson
import jt.projects.gbschool.utils.CURRENT_DATE
import jt.projects.gbschool.utils.CURRENT_DATE_TIME
import jt.projects.gbschool.utils.EXAMS_DATE
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate

class HomeViewModel(
    private val lessonInteractor: LessonInteractor,
    private val homeworkInteractor: HomeworkInteractor
) : ViewModel() {

    // секция 1 - таймер
    private val _resultTimer = MutableStateFlow<String>("00:00:00")
    val resultTimer get() = _resultTimer.asStateFlow()

    // секция 2 - уроки
    private val _lessonRecycler = MutableStateFlow<List<Lesson>>(listOf())
    val lessonRecycler get() = _lessonRecycler.asStateFlow()
    private var jobLessons: Job? = null
    private val _isLoadingLessons = MutableStateFlow(true)
    val isLoadingLessons get() = _isLoadingLessons.asStateFlow()

    // секция 3 - домашние задания
    private val _homeworkRecycler = MutableStateFlow<List<Homework>>(listOf())
    val homeworkRecycler get() = _homeworkRecycler.asStateFlow()
    private var jobHomework: Job? = null
    private val _isLoadingHomework = MutableStateFlow(true)
    val isLoadingHomework get() = _isLoadingHomework.asStateFlow()

    init {
        initTimer()
        loadLessons(CURRENT_DATE)
        loadHomework(CURRENT_DATE)
    }

    private fun initTimer() {
        var different = Duration.between(CURRENT_DATE_TIME, EXAMS_DATE).seconds * 1000

        val countDownTimer = object : CountDownTimer(different, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                var diff = millisUntilFinished
                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60
                val hoursInMilli = minutesInMilli * 60
                val daysInMilli = hoursInMilli * 24

                val elapsedDays = diff / daysInMilli
                diff %= daysInMilli

                val elapsedHours = diff / hoursInMilli
                diff %= hoursInMilli

                val elapsedMinutes = diff / minutesInMilli
                diff %= minutesInMilli

                val elapsedSeconds = diff / secondsInMilli

                //   _resultTimer.tryEmit("$elapsedDays days $elapsedHours hs $elapsedMinutes min $elapsedSeconds sec")
                val s = String.format(
                    "%02d:%02d:%02d:%02d",
                    elapsedDays,
                    elapsedHours,
                    elapsedMinutes,
                    elapsedSeconds
                )
                _resultTimer.tryEmit(s)
            }

            override fun onFinish() {
                _resultTimer.tryEmit("00:00:00")
            }
        }.start()
    }

    private fun loadLessons(date: LocalDate) {
        jobLessons?.cancel()
        jobLessons = viewModelScope.launch {
            _isLoadingLessons.tryEmit(true)
            lessonInteractor.getLessonsByDate(date)
                .onEach {
                    _lessonRecycler.tryEmit(it)
                    _isLoadingLessons.tryEmit(false)
                }.collect()
        }
    }

    private fun loadHomework(date: LocalDate) {
        jobHomework?.cancel()
        jobHomework = viewModelScope.launch {
            _isLoadingHomework.tryEmit(true)
            homeworkInteractor.getHomeworkByDate(date)
                .onEach {
                    _homeworkRecycler.tryEmit(it)
                    _isLoadingHomework.tryEmit(false)
                }.collect()
        }
    }

}