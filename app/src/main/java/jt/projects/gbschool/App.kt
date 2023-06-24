package jt.projects.gbschool

import android.app.Application
import jt.projects.gbschool.di.AppComponent
import jt.projects.gbschool.di.AppModule
import jt.projects.gbschool.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

    }
}
