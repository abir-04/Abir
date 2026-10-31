package com.abir.assistant.social

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Gifts skeleton (Firebase later).
 */
class GiftsActivity : AppCompatActivity() {

    private val gifts = listOf(
        "Send coins to friend",
        "Send avatar board",
        "Send title boost",
        "Received gifts"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listView = ListView(this)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, gifts)

        listView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "${gifts[position]} (coming with Firebase)", Toast.LENGTH_SHORT).show()
        }

        setContentView(listView)
    }
}
