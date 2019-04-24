package com.android.wordgame.data.model

import com.android.wordgame.data.source.local.DataHelper
import kotlin.collections.ArrayList


/**
 * This repository is responsible for
 * fetching data[Word] from json
 * */
class GameRepository(private val dataHelper: DataHelper){

    fun getRandomWordList(): List<Word>{
        val generateAllWords = dataHelper.generateAllWords()
        // create a temporary list for storing selected element
        val randomWords = ArrayList<Word>(10)
        if(generateAllWords.size ==0) return ArrayList()
        generateAllWords.shuffle()// to shuffle list each time we wanna generate a new random list
        for (i in 0 until 10) {
            // add element in temporary list
            randomWords.add(generateAllWords[i])
        }
        return randomWords
    }

}