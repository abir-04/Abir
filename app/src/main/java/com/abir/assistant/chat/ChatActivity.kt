package com.abir.assistant.chat

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.abir.assistant.ai.AIAssistant

class ChatActivity : AppCompatActivity() {

    private lateinit var historyManager: AnswerHistoryManager
    private lateinit var listView: ListView
    private val messages = mutableListOf<String>()
    private lateinit var ai: AIAssistant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyManager = AnswerHistoryManager(this)
        ai = AIAssistant(this)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val search = EditText(this).apply { hint = "Search history..." }
        listView = ListView(this)
        val input = EditText(this).apply { hint = "Ask something..." }
        val send = Button(this).apply { text = "Send" }
        val pin = Button(this).apply { text = "Pin selected (coming)" }

        search.setOnEditorActionListener { _, _, _ ->
            val query = search.text.toString()
            val filtered = if (query.isBlank()) messages else messages.filter { it.contains(query, ignoreCase = true) }
            listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filtered)
            true
        }

        send.setOnClickListener {
            val question = input.text.toString()
            if (question.isNotBlank()) {
                ai.ask(question) { answer ->
                    messages.add(0, "$question \u2192 $answer")
                    refreshList()
                }
                input.text.clear()
            }
        }

        layout.addView(search)
        layout.addView(listView, LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f)
        layout.addView(input)
        layout.addView(send)
        layout.addView(pin)
        setContentView(layout)

        messages.addAll(historyManager.getHistory())
        refreshList()
    }

    private fun refreshList() {
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, messages)
    }
}
