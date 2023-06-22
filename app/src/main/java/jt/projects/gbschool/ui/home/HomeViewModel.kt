package jt.projects.gbschool.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jt.projects.gbschool.interactors.LessonInteractor
import jt.projects.gbschool.model.Lesson
import jt.projects.gbschool.utils.CURRENT_DATE
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeViewModel(private val lessonInteractor: LessonInteractor) : ViewModel() {

    private var job: Job? = null

    private val _resultRecycler = MutableStateFlow<List<Lesson>>(listOf())
    val resultRecycler get() = _resultRecycler.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading get() = _isLoading.asStateFlow()

    init {
        loadData(CURRENT_DATE)
    }

    private fun loadData(date: LocalDate) {
        job?.cancel()
        _isLoading.tryEmit(true)

        job = viewModelScope.launch {
            lessonInteractor.getLessonsByDate(date)
                .onEach {
                    _resultRecycler.tryEmit(it)
                    _isLoading.tryEmit(false)
                }.collect()
        }
    }

}