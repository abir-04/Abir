package com.abir.assistant.screen

import android.app.Activity
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Screen help: request screen capture permission so the AI can read the screen.
 */
class ScreenCaptureActivity : AppCompatActivity() {

    private val REQUEST_CODE = 1001
    private lateinit var projectionManager: MediaProjectionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        projectionManager = getSystemService(MEDIA_PROJECTION_SERVICE) as MediaProjectionManager

        val btn = Button(this).apply {
            text = "Start Screen Capture for AI"
            setOnClickListener {
                startActivityForResult(projectionManager.createScreenCaptureIntent(), REQUEST_CODE)
            }
        }
        setContentView(btn)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Toast.makeText(this, "Screen capture permission granted", Toast.LENGTH_SHORT).show()
            // TODO: Start MediaProjection and pass frames to AI
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }
}
