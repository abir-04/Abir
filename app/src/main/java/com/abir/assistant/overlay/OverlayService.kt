package com.abir.assistant.overlay

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.abir.assistant.ai.AIAssistant
import com.abir.assistant.core.BatterySaver
import com.abir.assistant.core.SettingsManager

class OverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private var overlayView: View? = null
    private val handler = Handler(Looper.getMainLooper())
    private var autoHideRunnable: Runnable? = null
    private lateinit var ai: AIAssistant
    private lateinit var settings: SettingsManager

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        if (BatterySaver.shouldPauseOverlay(this)) {
            stopSelf()
            return
        }
        ai = AIAssistant(this)
        settings = SettingsManager(this)
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        createOverlay()
    }

    private fun createOverlay() {
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = 100
            y = 200
        }

        overlayView = LayoutInflater.from(this).inflate(R.layout.overlay_bubble, null)
        val icon = overlayView?.findViewById<ImageView>(R.id.bubble_icon)
        val quickButtons = overlayView?.findViewById<LinearLayout>(R.id.quick_buttons)
        val history = overlayView?.findViewById<TextView>(R.id.history_preview)

        val size = settings.bubbleSize
        icon?.layoutParams?.width = size
        icon?.layoutParams?.height = size
        overlayView?.alpha = settings.bubbleOpacity

        val selectedHero = getSharedPreferences("mlbb", MODE_PRIVATE).getString("selected_hero", null)

        fun ask(question: String) {
            val full = if (selectedHero != null) "$question for $selectedHero" else question
            ai.ask(full) { answer ->
                history?.text = answer
                history?.visibility = View.VISIBLE
            }
        }

        overlayView?.findViewById<Button>(R.id.btn_counter)?.setOnClickListener { ask("Counter this hero") }
        overlayView?.findViewById<Button>(R.id.btn_build)?.setOnClickListener { ask("Build tips") }
        overlayView?.findViewById<Button>(R.id.btn_tips)?.setOnClickListener { ask("General tips") }

        var initialX = 0
        var initialY = 0
        var initialTouchX = 0f
        var initialTouchY = 0f

        overlayView?.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialX = params.x
                    initialY = params.y
                    initialTouchX = event.rawX
                    initialTouchY = event.rawY
                    quickButtons?.visibility = View.VISIBLE
                    scheduleAutoHide()
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    params.x = initialX + (event.rawX - initialTouchX).toInt()
                    params.y = initialY + (event.rawY - initialTouchY).toInt()
                    windowManager.updateViewLayout(overlayView, params)
                    true
                }
                else -> false
            }
        }

        windowManager.addView(overlayView, params)
        scheduleAutoHide()
    }

    private fun scheduleAutoHide() {
        autoHideRunnable?.let { handler.removeCallbacks(it) }
        autoHideRunnable = Runnable {
            overlayView?.findViewById<LinearLayout>(R.id.quick_buttons)?.visibility = View.GONE
            overlayView?.findViewById<TextView>(R.id.history_preview)?.visibility = View.GONE
        }
        handler.postDelayed(autoHideRunnable!!, settings.autoHideDelay)
    }

    override fun onDestroy() {
        autoHideRunnable?.let { handler.removeCallbacks(it) }
        overlayView?.let { windowManager.removeView(it) }
        super.onDestroy()
    }
}
