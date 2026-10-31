package com.abir.assistant.study

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudyModeActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null
    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val status = TextView(this).apply { text = "Focus Timer: 25:00" }
        val startBtn = Button(this).apply { text = "Start Focus" }
        val photoBtn = Button(this).apply { text = "Take Textbook Photo" }

        startBtn.setOnClickListener {
            if (!isRunning) startFocusTimer(status) else stopTimer(status)
        }

        photoBtn.setOnClickListener {
            // TODO: Launch camera intent for textbook page
            status.text = "Camera coming soon..."
        }

        layout.addView(status)
        layout.addView(startBtn)
        layout.addView(photoBtn)
        setContentView(layout)
    }

    private fun startFocusTimer(status: TextView) {
        isRunning = true
        timer = object : CountDownTimer(25 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val min = millisUntilFinished / 60000
                val sec = (millisUntilFinished % 60000) / 1000
                status.text = "Focus: %02d:%02d".format(min, sec)
            }
            override fun onFinish() {
                status.text = "Focus complete!"
                isRunning = false
            }
        }.start()
    }

    private fun stopTimer(status: TextView) {
        timer?.cancel()
        isRunning = false
        status.text = "Focus Timer: 25:00"
    }
}
