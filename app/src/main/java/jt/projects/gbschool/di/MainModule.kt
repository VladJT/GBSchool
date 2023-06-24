package jt.projects.gbschool.di

import dagger.Module
import dagger.Provides
import jt.projects.gbschool.interactors.HomeworkInteractor
import jt.projects.gbschool.interactors.LessonInteractor
import jt.projects.gbschool.repository.HomeworkFakeRepo
import jt.projects.gbschool.repository.IHomeworkRepo
import jt.projects.gbschool.repository.ILessonsRepo
import jt.projects.gbschool.repository.LessonsFakeRepo
import javax.inject.Singleton


@Module
class MainModule {

    @Provides
    @Singleton
    fun lessonRepo(): ILessonsRepo = LessonsFakeRepo()

    @Provides
    @Singleton
    fun homeworkRepo(): IHomeworkRepo = HomeworkFakeRepo()

    @Provides
    @Singleton
    fun lessonInteractor(repo: ILessonsRepo): LessonInteractor = LessonInteractor(repo)

    @Provides
    @Singleton
    fun homeworkInteractor(repo: IHomeworkRepo): HomeworkInteractor = HomeworkInteractor(repo)

//        single<LessonInteractor> { LessonInteractor(repo = get()) }
//    single<HomeworkInteractor> { HomeworkInteractor(repo = get()) }
//

}