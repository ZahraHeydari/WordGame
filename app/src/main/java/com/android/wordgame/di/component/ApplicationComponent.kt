package com.android.wordgame.di.component

import android.app.Application
import com.android.wordgame.MainApplication
import com.android.wordgame.di.module.ActivityModule
import com.android.wordgame.di.module.AppModule
import com.android.wordgame.di.module.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        DataModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MainApplication)
}