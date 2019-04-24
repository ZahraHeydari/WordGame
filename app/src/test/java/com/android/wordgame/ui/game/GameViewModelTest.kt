package com.android.wordgame.ui.game

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.wordgame.data.model.GameRepository
import com.android.wordgame.data.model.Word
import org.junit.Test

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GameViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var gameRepository: GameRepository
    lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this) //for initialization
        gameRepository = mock(GameRepository::class.java)
        viewModel = GameViewModel(gameRepository)
    }

    @Test
    fun verifyGetWordList() {
        verify(gameRepository).getRandomWordList()
    }

    @Test
    fun getWordList() {
        val words = mock<List<Word>>()
        Mockito.`when`(gameRepository.getRandomWordList()).thenReturn(words)
        val result = gameRepository.getRandomWordList()
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$words] must be matches on each other!",
            result,
            CoreMatchers.`is`(words)
        )
    }

    @Test
    fun checkPoints() {
        viewModel.getPoints()
        assert(viewModel.point.value == 0)
    }

    @Test
    fun checkIsGameOver(){
        viewModel.isGameOver()
        assert(!viewModel.isGameOver())
    }




}