package com.abir.assistant.games

import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Build

/**
 * Auto-detect games besides Mobile Legends (skeleton).
 * Requires PACKAGE_USAGE_STATS permission.
 */
object GameDetector {

    private val knownGames = mapOf(
        "com.mobile.legends" to "Mobile Legends",
        "com.tencent.ig" to "PUBG Mobile",
        "com.garena.game.codm" to "Call of Duty Mobile",
        "com.supercell.clashofclans" to "Clash of Clans"
    )

    fun getCurrentGame(context: Context): String? {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return null

        val usm = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val time = System.currentTimeMillis()
        val stats = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 60, time)

        val recent = stats.maxByOrNull { it.lastTimeUsed }
        return recent?.packageName?.let { knownGames[it] }
    }
}
