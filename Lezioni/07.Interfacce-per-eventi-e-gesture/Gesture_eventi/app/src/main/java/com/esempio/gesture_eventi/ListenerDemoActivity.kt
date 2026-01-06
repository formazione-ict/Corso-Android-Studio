package com.esempio.gesture_eventi

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ListenerDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val clickableBox = View(this).apply {
            setBackgroundColor(Color.CYAN)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                300
            ).apply {
                setMargins(48,48,48,48)
            }

            setOnClickListener {
                Toast.makeText(this@ListenerDemoActivity, "Click!", Toast.LENGTH_SHORT).show()
            }

            setOnLongClickListener {
                Toast.makeText(this@ListenerDemoActivity, "Long Click!", Toast.LENGTH_SHORT).show()
                true
            }

            setOnTouchListener {
                    _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    alpha = 0.5f
                }
                if (event.action == MotionEvent.ACTION_UP) {
                    alpha = 1.0f
                }
                false
            }
        }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            addView(clickableBox)
        }

        setContentView(layout)
    }
}