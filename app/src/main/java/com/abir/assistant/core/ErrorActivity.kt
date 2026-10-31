package com.abir.assistant.core

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Error screen with Retry button.
 */
class ErrorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val message = intent.getStringExtra("message") ?: "Something went wrong"

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val text = TextView(this).apply {
            text = message
            textSize = 18f
        }

        val retry = Button(this).apply {
            text = "Retry"
            setOnClickListener { finish() } // Caller can restart the action
        }

        layout.addView(text)
        layout.addView(retry)
        setContentView(layout)
    }
}
