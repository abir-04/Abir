package com.abir.assistant.core

import android.content.Context

/**
 * Central place for app settings (bubble, theme, voice, etc.).
 */
class SettingsManager(context: Context) {

    private val prefs = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    var bubbleSize: Int
        get() = prefs.getInt("bubble_size", 48)
        set(value) = prefs.edit().putInt("bubble_size", value).apply()

    var bubbleOpacity: Float
        get() = prefs.getFloat("bubble_opacity", 0.8f)
        set(value) = prefs.edit().putFloat("bubble_opacity", value).apply()

    var autoHideDelay: Long
        get() = prefs.getLong("auto_hide", 5000L)
        set(value) = prefs.edit().putLong("auto_hide", value).apply()

    var theme: String
        get() = prefs.getString("theme", "Dark") ?: "Dark"
        set(value) = prefs.edit().putString("theme", value).apply()

    var memoryEnabled: Boolean
        get() = prefs.getBoolean("memory", true)
        set(value) = prefs.edit().putBoolean("memory", value).apply()
}
