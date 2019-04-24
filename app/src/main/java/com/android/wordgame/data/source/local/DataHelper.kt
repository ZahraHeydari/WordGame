package com.android.wordgame.data.source.local

import android.content.Context
import com.android.wordgame.R
import com.android.wordgame.data.model.Word
import org.json.JSONArray
import org.json.JSONException
import java.io.InputStream

class DataHelper(var context: Context){

    fun generateAllWords(): ArrayList<Word> {
        val wordList = ArrayList<Word>()
        val jsonArray = readJson()
        (0..(jsonArray.length() - 1)).forEach { i ->
            val jsonObject = jsonArray.getJSONObject(i)
            try {
                val native = jsonObject.getString("text_spa")
                val translation = jsonObject.getString("text_eng")
                wordList.add(Word(native,translation))
            } catch (ex: JSONException) {
                ex.printStackTrace()
            }
        }
        return wordList
    }

    private fun readJson(): JSONArray {
        var jsonArray = JSONArray()
        try {
            val inputStream = context.resources.openRawResource(R.raw.words_v2) ?: return jsonArray
            val wordsString = getWordsString(inputStream)
            jsonArray = JSONArray(wordsString)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonArray
    }

    private fun getWordsString(inputStream: InputStream): String {
        val stringBuilder = StringBuilder()
        inputStream.bufferedReader(Charsets.UTF_8).use {
            stringBuilder.append(it.readText())
        }
        return stringBuilder.toString()
    }

}