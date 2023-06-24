package jt.projects.gbschool.di

import dagger.Component
import jt.projects.gbschool.ui.MainActivity
import jt.projects.gbschool.ui.classes.ClassesFragment
import jt.projects.gbschool.ui.classes.ClassesViewModel
import jt.projects.gbschool.ui.home.HomeFragment
import jt.projects.gbschool.ui.home.HomeViewModel
import javax.inject.Singleton

// если в компоненте есть хотя бы 1 Singleton, то и компонент обязаны объявить как Singleton
@Singleton
@Component(
    modules = [AppModule::class, MainModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(classesFragment: ClassesFragment)
}