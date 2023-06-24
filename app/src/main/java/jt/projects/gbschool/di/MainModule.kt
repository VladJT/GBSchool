package jt.projects.gbschool.di

import dagger.Module
import dagger.Provides
import jt.projects.gbschool.repository.HomeworkFakeRepo
import jt.projects.gbschool.repository.IHomeworkRepo
import jt.projects.gbschool.repository.ILessonsRepo
import jt.projects.gbschool.repository.LessonsFakeRepo


@Module
class MainModule {

    @Provides
    fun lessonRepo(): ILessonsRepo = LessonsFakeRepo()

    @Provides
    fun homeworkRepo(): IHomeworkRepo = HomeworkFakeRepo()

//        single<LessonInteractor> { LessonInteractor(repo = get()) }
//    single<HomeworkInteractor> { HomeworkInteractor(repo = get()) }
//

}