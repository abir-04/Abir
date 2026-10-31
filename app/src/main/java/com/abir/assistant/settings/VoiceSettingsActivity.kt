package com.abir.assistant.settings

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Voice settings: speed, volume, pitch.
 */
class VoiceSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        fun addSeek(label: String, key: String, max: Int = 100) {
            val text = TextView(this).apply { text = label }
            val seek = SeekBar(this).apply {
                this.max = max
                progress = getSharedPreferences("voice", MODE_PRIVATE).getInt(key, max / 2)
                setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        getSharedPreferences("voice", MODE_PRIVATE).edit().putInt(key, progress).apply()
                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
                })
            }
            layout.addView(text)
            layout.addView(seek)
        }

        addSeek("Speed", "speed")
        addSeek("Volume", "volume")
        addSeek("Pitch", "pitch")

        setContentView(layout)
    }
}
