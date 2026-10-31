package com.abir.assistant.overlay

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

/**
 * Settings for bubble size, opacity, and auto-hide delay.
 */
class BubbleSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: SeekBars for size (32-96), opacity (0.3-1.0), auto-hide (2-15s)
        // Save to SharedPreferences and notify OverlayService
    }
}
