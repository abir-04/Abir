package com.abir.assistant.overlay

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout

/**
 * Floating overlay bubble service for the AI Assistant.
 * Supports size, opacity, auto-hide, and quick question buttons.
 */
class OverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private var overlayView: View? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        createOverlay()
    }

    private fun createOverlay() {
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = 100
            y = 200
        }

        overlayView = LayoutInflater.from(this).inflate(R.layout.overlay_bubble, null)
        // TODO: Add size/opacity from settings
        // TODO: Quick question buttons
        // TODO: Auto-hide logic

        windowManager.addView(overlayView, params)
    }

    override fun onDestroy() {
        overlayView?.let { windowManager.removeView(it) }
        super.onDestroy()
    }
}
