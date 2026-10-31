package com.abir.assistant.chat

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * Basic chat with saved history, search placeholder, and pin/copy ready.
 */
class ChatActivity : AppCompatActivity() {

    private lateinit var historyManager: AnswerHistoryManager
    private lateinit var listView: ListView
    private val messages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyManager = AnswerHistoryManager(this)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        listView = ListView(this)
        val input = EditText(this).apply { hint = "Ask something..." }
        val send = Button(this).apply { text = "Send" }

        send.setOnClickListener {
            val question = input.text.toString()
            if (question.isNotBlank()) {
                val answer = "AI response for: $question" // TODO: real AI call
                historyManager.addAnswer(question, answer)
                messages.add(0, "$question \u2192 $answer")
                refreshList()
                input.text.clear()
            }
        }

        layout.addView(listView, LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f)
        layout.addView(input)
        layout.addView(send)
        setContentView(layout)

        messages.addAll(historyManager.getHistory())
        refreshList()
    }

    private fun refreshList() {
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, messages)
    }
}
