package com.android.wordgame.di.builder

import android.arch.lifecycle.ViewModel
import com.android.wordgame.di.ViewModelKey
import com.android.wordgame.ui.game.GameViewModel
import com.android.wordgame.ui.intro.IntroViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun bindGameViewModel(gameViewModel: GameViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(IntroViewModel::class)
    abstract fun bindIntroViewModel(introViewModel: IntroViewModel): ViewModel

}