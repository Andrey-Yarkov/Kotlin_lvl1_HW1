package com.example.homework1app

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat

class LocalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local)

        // Get values from intent by keys
        val textLabel = intent.getStringExtra(textKey)
        val textColor = intent.getIntExtra(colorKey, 0)

        // Show view with received parameters
        val textView : TextView = findViewById(R.id.local_rectangle)
        textView.text = textLabel
        textView.setBackgroundColor(textColor)
    }

    companion object {
        const val textKey = "RectText"
        const val colorKey = "RectColor"
    }
}