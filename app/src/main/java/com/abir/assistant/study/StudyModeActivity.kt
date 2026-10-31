package com.abir.assistant.study

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abir.assistant.ai.AIAssistant

class StudyModeActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null
    private var isRunning = false
    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        status = TextView(this).apply { text = "Focus Timer: 25:00" }
        val startBtn = Button(this).apply { text = "Start Focus" }
        val photoBtn = Button(this).apply { text = "Take Textbook Photo" }
        val practiceBtn = Button(this).apply { text = "Generate Practice Question" }

        startBtn.setOnClickListener {
            if (!isRunning) startFocusTimer() else stopTimer()
        }

        photoBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, 100)
            } else {
                Toast.makeText(this, "No camera app", Toast.LENGTH_SHORT).show()
            }
        }

        practiceBtn.setOnClickListener {
            AIAssistant(this).ask("Generate a practice question from my notes") { answer ->
                status.text = answer
            }
        }

        layout.addView(status)
        layout.addView(startBtn)
        layout.addView(photoBtn)
        layout.addView(practiceBtn)
        setContentView(layout)
    }

    private fun startFocusTimer() {
        isRunning = true
        timer = object : CountDownTimer(25 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val min = millisUntilFinished / 60000
                val sec = (millisUntilFinished % 60000) / 1000
                status.text = "Focus: %02d:%02d".format(min, sec)
            }
            override fun onFinish() {
                status.text = "Focus complete! Take a break."
                isRunning = false
            }
        }.start()
    }

    private fun stopTimer() {
        timer?.cancel()
        isRunning = false
        status.text = "Focus Timer: 25:00"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            status.text = "Photo captured! (AI analysis coming soon)"
            // TODO: Send image to AI
        }
    }
}
