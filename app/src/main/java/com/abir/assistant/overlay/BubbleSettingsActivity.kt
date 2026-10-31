package com.abir.assistant.overlay

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abir.assistant.core.SettingsManager

class BubbleSettingsActivity : AppCompatActivity() {

    private lateinit var settings: SettingsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settings = SettingsManager(this)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        fun addSeek(label: String, current: Int, max: Int, onChange: (Int) -> Unit) {
            val text = TextView(this).apply { text = "$label: $current" }
            val seek = SeekBar(this).apply {
                this.max = max
                progress = current
                setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        text.text = "$label: $progress"
                        onChange(progress)
                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
                })
            }
            layout.addView(text)
            layout.addView(seek)
        }

        addSeek("Bubble Size", settings.bubbleSize, 96) { settings.bubbleSize = it }
        addSeek("Opacity %", (settings.bubbleOpacity * 100).toInt(), 100) {
            settings.bubbleOpacity = it / 100f
        }
        addSeek("Auto-hide (sec)", (settings.autoHideDelay / 1000).toInt(), 15) {
            settings.autoHideDelay = it * 1000L
        }

        setContentView(layout)
    }
}
