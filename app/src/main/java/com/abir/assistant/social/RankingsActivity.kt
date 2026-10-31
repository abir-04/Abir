package com.abir.assistant.social

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

/**
 * Rankings, friends, and gifts skeleton (Firebase later).
 */
class RankingsActivity : AppCompatActivity() {

    private val rankings = listOf(
        "1. You \u2014 1250 pts",
        "2. FriendA \u2014 980 pts",
        "3. FriendB \u2014 870 pts",
        "4. FriendC \u2014 650 pts"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listView = ListView(this)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, rankings)

        // TODO: Load real rankings from Firebase
        // TODO: Friends list + send gifts

        setContentView(listView)
    }
}
