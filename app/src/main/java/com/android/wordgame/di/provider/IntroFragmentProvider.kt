package com.android.wordgame.di.provider

import com.android.wordgame.ui.intro.IntroFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class IntroFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideIntroFragment(): IntroFragment

}