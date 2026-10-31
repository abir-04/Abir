package com.abir.assistant.core

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager

/**
 * Battery saver: pause the overlay bubble when battery is low.
 */
object BatterySaver {

    private const val LOW_BATTERY_THRESHOLD = 15

    fun isBatteryLow(context: Context): Boolean {
        val batteryStatus = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val level = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        val percent = if (level >= 0 && scale > 0) (level * 100 / scale) else 100
        return percent <= LOW_BATTERY_THRESHOLD
    }

    fun shouldPauseOverlay(context: Context): Boolean {
        // Can be expanded with user setting later
        return isBatteryLow(context)
    }
}
