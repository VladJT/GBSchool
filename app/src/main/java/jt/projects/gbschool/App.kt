package jt.projects.gbschool

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import jt.projects.gbschool.di.AppModule
import jt.projects.gbschool.di.DaggerAppComponent
import javax.inject.Inject

/** Обратите внимание на dispatchingAndroidInjector и интерфейс Dagger'а
HasAndroidInjector: мы переопределяем его метод androidInjector. Они
нужны для внедрения зависимостей в Activity. По своей сути — это вспомогательные
методы для разработчиков под Андроид для эффективного внедрения компонентов
платформы, таких как Активити, Сервис и т. п.
 */
class App : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

     //   DaggerAppComponent.builder().appModule(AppModule(this)).build()

        DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }
}