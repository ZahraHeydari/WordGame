package com.android.wordgame.data.model

import com.android.wordgame.data.source.local.DataHelper
import kotlin.collections.ArrayList


/**
 * This repository is responsible for
 * fetching data[Word] from json
 * */
class GameRepository(private val dataHelper: DataHelper) {

    private val QUESTION_NUMBER = 10//it can even be more than current value

    /**
     * based on my mind`s scenario
     * */
    fun getRandomWordList(): List<Word> {
        val generateAllWords = dataHelper.generateAllWords()
        // create a temporary list for storing selected element
        val randomWords = ArrayList<Word>(QUESTION_NUMBER)
        generateAllWords.shuffle()// to shuffle list each time we wanna generate a new random list
        for (i in 0 until QUESTION_NUMBER) {
            // add element in temporary list
            randomWords.add(generateAllWords[i])
        }
        return randomWords
    }

}