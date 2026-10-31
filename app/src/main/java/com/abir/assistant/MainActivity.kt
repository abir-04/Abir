package com.abir.assistant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.abir.assistant.chat.ChatActivity
import com.abir.assistant.mlbb.HeroPickerActivity
import com.abir.assistant.overlay.BubbleSettingsActivity
import com.abir.assistant.privacy.PrivacyActivity
import com.abir.assistant.study.StudyModeActivity
import com.abir.assistant.titles.TitlesActivity

/**
 * Main launcher with buttons to all features.
 */
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

        addButton("Open Overlay Settings", BubbleSettingsActivity::class.java)
        addButton("Hero Picker (MLBB)", HeroPickerActivity::class.java)
        addButton("Study Mode", StudyModeActivity::class.java)
        addButton("Titles & Avatars", TitlesActivity::class.java)
        addButton("Chat", ChatActivity::class.java)
        addButton("Privacy", PrivacyActivity::class.java)

        // TODO: Start OverlayService button (needs permission)

        setContentView(layout)
    }
}
