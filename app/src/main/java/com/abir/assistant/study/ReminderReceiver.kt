package com.abir.assistant.study

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Receives study reminders.
 * In a full version this would show a notification.
 */
class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra("message") ?: "Time to study!"
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        // TODO: Show proper Notification
    }
}
