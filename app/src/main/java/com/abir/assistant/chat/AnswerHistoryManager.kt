package com.abir.assistant.chat

import android.content.Context
import android.content.SharedPreferences

/**
 * Simple local answer history for the overlay.
 * Later can be replaced with Room database.
 */
class AnswerHistoryManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("answer_history", Context.MODE_PRIVATE)

    fun addAnswer(question: String, answer: String) {
        val history = getHistory().toMutableList()
        history.add(0, "$question \u2192 $answer") // newest first
        if (history.size > 20) history.removeAt(history.size - 1)

        prefs.edit()
            .putStringSet("history", history.toSet())
            .apply()
    }

    fun getHistory(): List<String> {
        return prefs.getStringSet("history", emptySet())?.toList() ?: emptyList()
    }

    fun clear() {
        prefs.edit().remove("history").apply()
    }
}
