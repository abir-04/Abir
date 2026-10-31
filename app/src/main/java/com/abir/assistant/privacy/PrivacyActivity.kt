package com.abir.assistant.privacy

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abir.assistant.chat.AnswerHistoryManager

/**
 * Privacy settings: clear history, turn off memory, delete data.
 */
class PrivacyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val clearHistory = Button(this).apply { text = "Clear Answer History" }
        val clearAll = Button(this).apply { text = "Delete All Local Data" }
        val toggleMemory = Button(this).apply { text = "Toggle Memory (coming soon)" }

        clearHistory.setOnClickListener {
            AnswerHistoryManager(this).clear()
            Toast.makeText(this, "History cleared", Toast.LENGTH_SHORT).show()
        }

        clearAll.setOnClickListener {
            getSharedPreferences("mlbb", MODE_PRIVATE).edit().clear().apply()
            getSharedPreferences("answer_history", MODE_PRIVATE).edit().clear().apply()
            Toast.makeText(this, "All local data deleted", Toast.LENGTH_SHORT).show()
        }

        layout.addView(clearHistory)
        layout.addView(clearAll)
        layout.addView(toggleMemory)
        setContentView(layout)
    }
}
