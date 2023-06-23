package jt.projects.gbschool.di


import jt.projects.gbschool.App
import jt.projects.gbschool.interactors.HomeworkInteractor
import jt.projects.gbschool.interactors.LessonInteractor
import jt.projects.gbschool.repository.HomeworkFakeRepo
import jt.projects.gbschool.repository.IHomeworkRepo
import jt.projects.gbschool.repository.ILessonsRepo
import jt.projects.gbschool.repository.LessonsFakeRepo
import jt.projects.gbschool.ui.classes.ClassesViewModel
import jt.projects.gbschool.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // контекст приложения
    single<App> { androidApplication().applicationContext as App }
}

val repoModule = module {
    // interactors
    single<LessonInteractor> { LessonInteractor(repo = get()) }
    single<HomeworkInteractor> { HomeworkInteractor(repo = get()) }

    // data sources
    single<ILessonsRepo> { LessonsFakeRepo() }
    single<IHomeworkRepo> { HomeworkFakeRepo() }
}


val vmModule = module {

    viewModel { HomeViewModel(lessonInteractor = get(), homeworkInteractor = get()) }
    viewModel { ClassesViewModel(lessonInteractor = get()) }
}
