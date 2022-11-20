package com.example.json_parser.util

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(this@toast, message, length).show()
    }
}

fun Context.startActivity(cls: Class<*>) {
    Intent(this, cls).also { intent ->
        startActivity(intent)
    }
}