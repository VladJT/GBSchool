package jt.projects.gbschool.di

import dagger.Module
import dagger.Provides
import jt.projects.gbschool.App
import javax.inject.Singleton


@Module
class AppModule(app: App) {
    private val application: App

    init {
        this.application = app
    }

    @Provides
    @Singleton
    fun application(): App {
        return application
    }
}