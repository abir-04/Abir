package com.abir.assistant.chat

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abir.assistant.ai.AIAssistant
import java.util.Locale

class ChatActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var historyManager: AnswerHistoryManager
    private lateinit var listView: ListView
    private val messages = mutableListOf<String>()
    private lateinit var ai: AIAssistant
    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyManager = AnswerHistoryManager(this)
        ai = AIAssistant(this)
        tts = TextToSpeech(this, this)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val search = EditText(this).apply { hint = "Search history..." }
        listView = ListView(this)
        val input = EditText(this).apply { hint = "Ask something..." }
        val send = Button(this).apply { text = "Send" }
        val copy = Button(this).apply { text = "Copy last" }
        val share = Button(this).apply { text = "Share last" }
        val read = Button(this).apply { text = "Read aloud" }

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

        copy.setOnClickListener {
            if (messages.isNotEmpty()) {
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(ClipData.newPlainText("answer", messages[0]))
                Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
            }
        }

        share.setOnClickListener {
            if (messages.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, messages[0])
                }
                startActivity(Intent.createChooser(intent, "Share answer"))
            }
        }

        read.setOnClickListener {
            if (messages.isNotEmpty()) {
                tts?.speak(messages[0], TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        layout.addView(search)
        layout.addView(listView, LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f)
        layout.addView(input)
        layout.addView(send)
        layout.addView(copy)
        layout.addView(share)
        layout.addView(read)
        setContentView(layout)

        messages.addAll(historyManager.getHistory())
        refreshList()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.US
        }
    }

    override fun onDestroy() {
        tts?.shutdown()
        super.onDestroy()
    }

    private fun refreshList() {
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, messages)
    }
}
