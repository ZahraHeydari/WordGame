package com.android.wordgame.data.repository


import androidx.test.InstrumentationRegistry
import com.android.wordgame.data.model.GameRepository
import com.android.wordgame.data.source.local.DataHelper
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class GameRepositoryTest {

    private lateinit var gameRepository: GameRepository
    private lateinit var dataHelper: DataHelper

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        dataHelper = DataHelper(context)
        gameRepository = GameRepository(dataHelper)
    }

    @Test
    fun nullRandomWordList(){
        val randomWordList = gameRepository.getRandomWordList()
        Assert.assertNotNull(randomWordList)
    }

    @Test
    fun checkRandomWordListSize(){
        val randomWordList = gameRepository.getRandomWordList()
        Assert.assertNotEquals(0,randomWordList.size)
    }

    @Test
    fun getRandomWordList() {
        val list = gameRepository.getRandomWordList()
        assertThat(list.size, `is`(10))
    }

}