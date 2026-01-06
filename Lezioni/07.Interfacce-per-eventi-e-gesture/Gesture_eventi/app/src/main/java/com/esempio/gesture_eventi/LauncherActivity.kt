package com.esempio.gesture_eventi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this).apply {
            text = "Test di avvio..."
            textSize = 24f
        }
        setContentView(textView)
    }
}
