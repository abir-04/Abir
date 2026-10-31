package com.abir.assistant.titles

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class TitlesActivity : AppCompatActivity() {

    // Example titles with progress (0-100)
    private val titles = listOf(
        "Rookie" to 100,
        "Warrior" to 65,
        "Legend" to 20,
        "Mythic" to 0,
        "Study Master" to 40,
        "MLBB Expert" to 15
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val items = titles.map { "${it.first} \u2014 ${it.second}%" }
        val listView = ListView(this)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)

        listView.setOnItemClickListener { _, _, position, _ ->
            val title = titles[position]
            // TODO: Show avatar board / name effects unlock screen
            // if (title.second >= 100) unlock effect
        }

        setContentView(listView)
    }
}
