package com.abir.assistant.ai

import android.content.Context
import com.abir.assistant.chat.AnswerHistoryManager

/**
 * Central AI interface. Replace the placeholder with a real LLM call later.
 */
class AIAssistant(private val context: Context) {

    private val history = AnswerHistoryManager(context)

    fun ask(question: String, onResult: (String) -> Unit) {
        // Placeholder response
        val answer = when {
            question.contains("counter", ignoreCase = true) -> "Suggested counters: [Hero A, Hero B]. Focus on early game."
            question.contains("build", ignoreCase = true) -> "Recommended build: Boots \u2192 Core item \u2192 Situational."
            question.contains("study", ignoreCase = true) -> "Here's a practice question based on your notes..."
            else -> "I received: \"$question\". (Connect a real AI model to get full answers.)"
        }

        history.addAnswer(question, answer)
        onResult(answer)
    }

    fun askWithScreen(question: String, screenshotBase64: String?, onResult: (String) -> Unit) {
        val prefix = if (screenshotBase64 != null) "[Looking at your screen] " else ""
        ask(prefix + question, onResult)
    }
}
