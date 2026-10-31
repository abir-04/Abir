package com.abir.assistant.study

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock

/**
 * Simple study reminders (can be expanded).
 */
class ReminderManager(private val context: Context) {

    fun scheduleReminder(minutesFromNow: Int, message: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra("message", message)
        }
        val pending = PendingIntent.getBroadcast(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val trigger = SystemClock.elapsedRealtime() + minutesFromNow * 60 * 1000L
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, trigger, pending)
    }
}

// Note: ReminderReceiver would be a BroadcastReceiver that shows a notification.
// Add it to the manifest when you implement notifications.
