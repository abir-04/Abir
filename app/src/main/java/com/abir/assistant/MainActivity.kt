package com.abir.assistant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abir.assistant.ai.AIAssistant
import com.abir.assistant.chat.ChatActivity
import com.abir.assistant.core.NetworkUtils
import com.abir.assistant.mlbb.HeroPickerActivity
import com.abir.assistant.overlay.BubbleSettingsActivity
import com.abir.assistant.privacy.PrivacyActivity
import com.abir.assistant.screen.ScreenCaptureActivity
import com.abir.assistant.settings.ThemeActivity
import com.abir.assistant.settings.VoiceSettingsActivity
import com.abir.assistant.study.StudyModeActivity
import com.abir.assistant.titles.TitlesActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 24)
        }

        fun addButton(text: String, activity: Class<*>) {
            val btn = Button(this).apply {
                this.text = text
                setOnClickListener { startActivity(Intent(this@MainActivity, activity)) }
            }
            layout.addView(btn)
        }

        addButton("Overlay Settings", BubbleSettingsActivity::class.java)
        addButton("Hero Picker", HeroPickerActivity::class.java)
        addButton("Study Mode", StudyModeActivity::class.java)
        addButton("Titles", TitlesActivity::class.java)
        addButton("Chat", ChatActivity::class.java)
        addButton("Screen Capture", ScreenCaptureActivity::class.java)
        addButton("Themes", ThemeActivity::class.java)
        addButton("Voice Settings", VoiceSettingsActivity::class.java)
        addButton("Privacy", PrivacyActivity::class.java)

        val testAi = Button(this).apply {
            text = "Test AI"
            setOnClickListener {
                if (NetworkUtils.isOffline(this@MainActivity)) {
                    Toast.makeText(this@MainActivity, "Offline mode", Toast.LENGTH_SHORT).show()
                }
                AIAssistant(this@MainActivity).ask("Counter this hero") { answer ->
                    Toast.makeText(this@MainActivity, answer, Toast.LENGTH_LONG).show()
                }
            }
        }
        layout.addView(testAi)

        setContentView(layout)
    }
}
