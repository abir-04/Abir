package com.abir.assistant

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abir.assistant.account.AccountActivity
import com.abir.assistant.ai.AIAssistant
import com.abir.assistant.chat.ChatActivity
import com.abir.assistant.core.NetworkUtils
import com.abir.assistant.mlbb.HeroPickerActivity
import com.abir.assistant.overlay.BubbleSettingsActivity
import com.abir.assistant.overlay.OverlayService
import com.abir.assistant.privacy.PrivacyActivity
import com.abir.assistant.screen.ScreenCaptureActivity
import com.abir.assistant.settings.ThemeActivity
import com.abir.assistant.settings.VoiceSettingsActivity
import com.abir.assistant.social.GiftsActivity
import com.abir.assistant.social.RankingsActivity
import com.abir.assistant.study.StudyModeActivity
import com.abir.assistant.titles.AvatarBoardActivity
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

        val startOverlay = Button(this).apply {
            text = "Start Overlay Bubble"
            setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this@MainActivity)) {
                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
                    startActivity(intent)
                    Toast.makeText(this@MainActivity, "Grant overlay permission then try again", Toast.LENGTH_LONG).show()
                } else {
                    startService(Intent(this@MainActivity, OverlayService::class.java))
                    Toast.makeText(this@MainActivity, "Overlay started", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val stopOverlay = Button(this).apply {
            text = "Stop Overlay Bubble"
            setOnClickListener {
                stopService(Intent(this@MainActivity, OverlayService::class.java))
                Toast.makeText(this@MainActivity, "Overlay stopped", Toast.LENGTH_SHORT).show()
            }
        }

        layout.addView(startOverlay)
        layout.addView(stopOverlay)

        addButton("Overlay Settings", BubbleSettingsActivity::class.java)
        addButton("Hero Picker", HeroPickerActivity::class.java)
        addButton("Study Mode", StudyModeActivity::class.java)
        addButton("Titles", TitlesActivity::class.java)
        addButton("Avatar Boards", AvatarBoardActivity::class.java)
        addButton("Chat", ChatActivity::class.java)
        addButton("Screen Capture", ScreenCaptureActivity::class.java)
        addButton("Themes", ThemeActivity::class.java)
        addButton("Voice Settings", VoiceSettingsActivity::class.java)
        addButton("Rankings", RankingsActivity::class.java)
        addButton("Gifts", GiftsActivity::class.java)
        addButton("Account & Sync", AccountActivity::class.java)
        addButton("Privacy", PrivacyActivity::class.java)

        val testAi = Button(this).apply {
            text = "Test AI"
            setOnClickListener {
                if (NetworkUtils.isOffline(this@MainActivity)) {
                    Toast.makeText(this@MainActivity, "You are offline. Some features limited.", Toast.LENGTH_SHORT).show()
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
