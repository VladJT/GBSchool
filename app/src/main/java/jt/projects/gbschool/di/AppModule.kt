package jt.projects.gbschool.di

import dagger.Module
import dagger.Provides
import jt.projects.gbschool.App
import javax.inject.Singleton


@Module
class AppModule(val app: App) {

    @Singleton
    @Provides
    fun app(): App {
        return app
    }
}