package com.abir.assistant.account

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Accounts & sync skeleton (Firebase later).
 */
class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val status = TextView(this).apply { text = "Not signed in" }
        val signIn = Button(this).apply { text = "Sign In (Firebase)" }
        val signOut = Button(this).apply { text = "Sign Out" }
        val sync = Button(this).apply { text = "Sync Now" }

        signIn.setOnClickListener {
            status.text = "Signed in (placeholder)"
            Toast.makeText(this, "Connect Firebase to enable real accounts", Toast.LENGTH_SHORT).show()
        }

        signOut.setOnClickListener {
            status.text = "Not signed in"
        }

        sync.setOnClickListener {
            Toast.makeText(this, "Sync complete (placeholder)", Toast.LENGTH_SHORT).show()
        }

        layout.addView(status)
        layout.addView(signIn)
        layout.addView(signOut)
        layout.addView(sync)
        setContentView(layout)
    }
}
