package jt.projects.gbschool.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import jt.projects.gbschool.App
import javax.inject.Singleton


// если в компоненте есть хотя бы 1 Singleton, то и компонент обязаны объявить как Singleton
@Singleton
@Component(
    modules = [AppModule::class, MainModule::class, ActivityModule::class, FragmentModule::class, AndroidSupportInjectionModule::class]
)
interface AppComponent {
    // Этот билдер мы вызовем из класса App
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun appModule(appProviderModule: AppModule): Builder
        fun build(): AppComponent
    }

    // Наш кастомный Application
    fun inject(app: App)
}
