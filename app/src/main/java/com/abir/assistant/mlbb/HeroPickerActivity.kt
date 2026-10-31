package com.abir.assistant.mlbb

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HeroPickerActivity : AppCompatActivity() {

    private val heroes = listOf(
        "Layla", "Miya", "Bruno", "Clint", "Moskov",
        "Fanny", "Hayabusa", "Lancelot", "Gusion", "Helcurt",
        "Tigreal", "Franco", "Akai", "Grock", "Hylos",
        "Angela", "Estes", "Rafaela", "Nana", "Diggie"
        // TODO: Expand full hero list + roles (Marksman, Assassin, Tank, Support, Mage, Fighter)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listView = ListView(this)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, heroes)

        listView.setOnItemClickListener { _, _, position, _ ->
            val hero = heroes[position]
            // Save selected hero (SharedPreferences for now)
            getSharedPreferences("mlbb", MODE_PRIVATE)
                .edit()
                .putString("selected_hero", hero)
                .apply()

            Toast.makeText(this, "Selected: $hero", Toast.LENGTH_SHORT).show()
            // TODO: Open quick questions or send to AI overlay
            finish()
        }

        setContentView(listView)
    }
}
