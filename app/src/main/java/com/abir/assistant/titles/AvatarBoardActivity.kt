package com.abir.assistant.titles

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Avatar boards and name effects unlocked by titles.
 */
class AvatarBoardActivity : AppCompatActivity() {

    private val boards = listOf(
        "Default Board" to true,
        "Warrior Board" to false,
        "Legend Board" to false,
        "Mythic Board" to false,
        "Study Board" to false
    )

    private val effects = listOf(
        "None",
        "Glow",
        "Sparkle",
        "Rainbow",
        "Fire"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val items = boards.map { "${it.first} ${if (it.second) "\u2713 Unlocked" else "\uD83D\uDD12 Locked"}" } +
                effects.map { "Effect: $it" }

        val listView = ListView(this)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)

        listView.setOnItemClickListener { _, _, position, _ ->
            if (position < boards.size && boards[position].second) {
                Toast.makeText(this, "Equipped ${boards[position].first}", Toast.LENGTH_SHORT).show()
            } else if (position >= boards.size) {
                Toast.makeText(this, "Equipped effect: ${effects[position - boards.size]}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Unlock by progressing titles", Toast.LENGTH_SHORT).show()
            }
        }

        setContentView(listView)
    }
}
