package jt.projects.gbschool.di

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import jt.projects.gbschool.interactors.HomeworkInteractor
import jt.projects.gbschool.interactors.LessonInteractor
import jt.projects.gbschool.repository.HomeworkFakeRepo
import jt.projects.gbschool.repository.IHomeworkRepo
import jt.projects.gbschool.repository.ILessonsRepo
import jt.projects.gbschool.repository.LessonsFakeRepo
import jt.projects.gbschool.ui.MainActivity
import jt.projects.gbschool.ui.classes.ClassesFragment
import jt.projects.gbschool.ui.home.HomeFragment
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
}

/**
 * Модуль для Activity. Так как мы используем дополнительную библиотеку поддержки для Android, то все
становится гораздо проще при помощи ContributesAndroidInjector. Он позволяет внедрять
зависимости в Activity (нашу ViewModel) благодаря простому AndroidInjection.inject(this) в методе
onCreate
 */
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun binClassesFragment(): ClassesFragment
}