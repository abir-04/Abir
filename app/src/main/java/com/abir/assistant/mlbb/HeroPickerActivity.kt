package com.abir.assistant.mlbb

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HeroPickerActivity : AppCompatActivity() {

    private val heroes = listOf(
        // Marksman
        "Layla", "Miya", "Bruno", "Clint", "Moskov", "Irithel", "Karrie", "Hanabi", "Claude", "Granger",
        // Assassin
        "Fanny", "Hayabusa", "Lancelot", "Gusion", "Helcurt", "Saber", "Karina", "Natalia", "Ling", "Benedetta",
        // Tank
        "Tigreal", "Franco", "Akai", "Grock", "Hylos", "Johnson", "Minotaur", "Lolita", "Belerick", "Khufra",
        // Support
        "Angela", "Estes", "Rafaela", "Nana", "Diggie", "Mathilda", "Floryn", "Carmilla",
        // Mage
        "Eudora", "Gord", "Kagura", "Cyclops", "Aurora", "Vexana", "Harley", "Lunox", "Chang'e", "Valir",
        // Fighter
        "Balmond", "Alucard", "Bane", "Zilong", "Freya", "Chou", "Sun", "Alpha", "Ruby", "Martis"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listView = ListView(this)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, heroes)

        listView.setOnItemClickListener { _, _, position, _ ->
            val hero = heroes[position]
            getSharedPreferences("mlbb", MODE_PRIVATE)
                .edit()
                .putString("selected_hero", hero)
                .apply()

            Toast.makeText(this, "Selected: $hero", Toast.LENGTH_SHORT).show()
            finish()
        }

        setContentView(listView)
    }
}
