package com.android.wordgame.di.module

import com.android.wordgame.di.provider.GameFragmentProvider
import com.android.wordgame.di.provider.IntroFragmentProvider
import com.android.wordgame.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivityModule {

    @ContributesAndroidInjector(
        modules = [
            GameFragmentProvider::class,
            IntroFragmentProvider::class
        ])
    fun baseActivityInjector(): MainActivity

}