package com.android.wordgame.di.module

import android.app.Application
import android.content.Context
import com.android.wordgame.di.builder.ViewModelFactoryBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelFactoryBuilder::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}