package com.android.wordgame.ui.game

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import com.android.wordgame.data.model.GameRepository
import com.android.wordgame.data.model.Word
import java.util.*
import javax.inject.Inject

/**
 * This class is responsible for storing & managing UI-related data in lifecycle conscious way
 * it also allows data to survive configuration changes such as screen rotation
 *
 * @author ZARA
 * */
class GameViewModel @Inject constructor(repository: GameRepository) : ViewModel() {

    var randomWords: ObservableArrayList<Word> = ObservableArrayList()
    var question = MutableLiveData<Word>()
    var answer = MutableLiveData<Word>()
    var isAnswerCorrect = MutableLiveData<Boolean>()
    var point = MutableLiveData<Int>()
    var isGameEnded = MutableLiveData<Boolean>()
    private var QUESTION_COUNT: Int = 10

    init {
        if (randomWords.isEmpty()) randomWords.addAll(repository.getRandomWordList())
        QUESTION_COUNT = 10
        point.value = 0
        isGameEnded.value = false
    }

    fun getPoints(): Int {
        return point.value!!
    }

    fun getRandomQuestion() {
        if (isGameOver()) return
        val random = Random()
        val index = random.nextInt(randomWords.size)
        question.value = randomWords[index]
        QUESTION_COUNT -= 1
        getRandomAnswer()

    }

    fun isGameOver(): Boolean {
        if (QUESTION_COUNT == -1) {
            isGameEnded.value = true
        }
        return isGameEnded.value!!
    }


    private fun getRandomAnswer() {
        if (isGameOver()) return
        val random = Random()
        val index = random.nextInt(randomWords.size)
        answer.value = randomWords[index]
    }


    fun checkUserAnswer(isCorrect: Boolean) {
        val isMatched = answer.value?.translation == question.value?.translation//status of question
        this.isAnswerCorrect.value = isMatched == isCorrect
        if (isAnswerCorrect.value == true) {
            point.value = getPoints() + 1
        }
    }

}


