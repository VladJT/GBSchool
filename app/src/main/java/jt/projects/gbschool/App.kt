package jt.projects.gbschool

import android.app.Application
import android.content.Context
import android.content.res.Resources
import jt.projects.gbschool.di.AppComponent
import jt.projects.gbschool.di.AppModule
import jt.projects.gbschool.di.DaggerAppComponent

class App : Application() {

    companion object {
        private var mContext: Context? = null

        fun getResources(): Resources {
            return mContext!!.resources
        }

        fun getContext(): Context {
            return mContext!!
        }

        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext

        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

//        startKoin {
//            androidContext(this@App)
//            modules(
//                listOf(
//                    appModule, repoModule, vmModule
//                )
//            )
//        }
    }
}
