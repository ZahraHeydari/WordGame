package com.android.wordgame.data.source.local

import androidx.test.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class DataHelperTest {

    private lateinit var dataHelper: DataHelper

    @Before
    fun setup(){
        val context = InstrumentationRegistry.getTargetContext()
        dataHelper = DataHelper(context)
    }

    @Test
    fun nullDirectory() {
        val readJson = dataHelper.generateAllWords()
        Assert.assertNotNull(readJson)
    }

    @Test
    fun nullWordList(){
        val words = dataHelper.generateAllWords()
        Assert.assertNotNull(words)
        Assert.assertNotEquals(0, words.size)
    }

    @Test
    fun count() {
        val words = dataHelper.generateAllWords()
        Assert.assertNotNull(words)
        Assert.assertEquals(297, words.size)
    }

    @Test
    fun oneWord() {
        val words = dataHelper.generateAllWords()
        val word = words[0]
        Assert.assertNotNull(word)
        Assert.assertEquals("primary school", word.translation)
        Assert.assertEquals("escuela primaria", word.native)
    }


}