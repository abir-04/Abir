package com.abir.assistant.settings

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Themes for the whole app.
 */
class ThemeActivity : AppCompatActivity() {

    private val themes = listOf("Dark", "Light", "MLBB Purple", "Study Green", "Classic")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listView = ListView(this)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, themes)

        listView.setOnItemClickListener { _, _, position, _ ->
            val theme = themes[position]
            getSharedPreferences("settings", MODE_PRIVATE)
                .edit()
                .putString("theme", theme)
                .apply()
            Toast.makeText(this, "Theme set to $theme", Toast.LENGTH_SHORT).show()
            // TODO: Apply theme (recreate activities or use AppCompatDelegate)
        }

        setContentView(listView)
    }
}
