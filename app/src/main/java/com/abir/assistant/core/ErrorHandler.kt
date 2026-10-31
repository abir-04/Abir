package com.abir.assistant.core

import android.content.Context
import android.widget.Toast

/**
 * Simple error screens / retry helper.
 */
object ErrorHandler {

    fun showError(context: Context, message: String, onRetry: (() -> Unit)? = null) {
        Toast.makeText(context, "Error: $message", Toast.LENGTH_LONG).show()
        // TODO: Full error Activity with Retry button
        onRetry?.invoke()
    }

    fun isOffline(): Boolean {
        // TODO: Check connectivity
        return false
    }
}
