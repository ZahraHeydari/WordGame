package com.android.wordgame.di.provider

import com.android.wordgame.ui.game.GameFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GameFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideGameFragment(): GameFragment

}