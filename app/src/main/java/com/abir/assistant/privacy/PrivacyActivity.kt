package com.abir.assistant.privacy

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abir.assistant.chat.AnswerHistoryManager
import com.abir.assistant.core.SettingsManager

class PrivacyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val settings = SettingsManager(this)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val memorySwitch = Switch(this).apply {
            text = "Enable Memory"
            isChecked = settings.memoryEnabled
            setOnCheckedChangeListener { _, isChecked ->
                settings.memoryEnabled = isChecked
            }
        }

        val clearHistory = Button(this).apply { text = "Clear Answer History" }
        val clearAll = Button(this).apply { text = "Delete All Local Data" }

        clearHistory.setOnClickListener {
            AnswerHistoryManager(this).clear()
            Toast.makeText(this, "History cleared", Toast.LENGTH_SHORT).show()
        }

        clearAll.setOnClickListener {
            getSharedPreferences("mlbb", MODE_PRIVATE).edit().clear().apply()
            getSharedPreferences("answer_history", MODE_PRIVATE).edit().clear().apply()
            getSharedPreferences("app_settings", MODE_PRIVATE).edit().clear().apply()
            Toast.makeText(this, "All local data deleted", Toast.LENGTH_SHORT).show()
        }

        layout.addView(memorySwitch)
        layout.addView(clearHistory)
        layout.addView(clearAll)
        setContentView(layout)
    }
}
