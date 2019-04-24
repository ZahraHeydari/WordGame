package com.android.wordgame.di.module

import android.content.Context
import com.android.wordgame.data.model.GameRepository
import com.android.wordgame.data.source.local.DataHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [AppModule::class])
class DataModule {

    @Singleton
    @Provides
    fun provideGameRepository(dataHelper: DataHelper):GameRepository{
        return GameRepository(dataHelper)
    }

    @Singleton
    @Provides
    fun provideDataHelper(context: Context):DataHelper{
        return DataHelper(context)
    }
}