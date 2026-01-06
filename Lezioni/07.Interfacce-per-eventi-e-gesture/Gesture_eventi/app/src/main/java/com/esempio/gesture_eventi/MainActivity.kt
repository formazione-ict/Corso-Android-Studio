package com.esempio.gesture_eventi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
        }

        val titleText = TextView(this).apply {
            text = "Esempi Gesture ed Eventi"
            textSize = 24f
            setPadding(0, 0, 0, 32)
        }

        val subtitleText = TextView(this).apply {
            text = "Seleziona un esempio da eseguire:"
            textSize = 16f
            setPadding(0, 0, 0, 24)
        }

        // Bottone Esempio 1
        val example1Button = Button(this).apply {
            text = "Esempio 1: Canvas Disegno"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 16)
            }
            setOnClickListener {
                startActivity(Intent(this@MainActivity, DrawingActivity::class.java))
            }
        }

        val desc1 = TextView(this).apply {
            text = "Touch events base: onTouchEvent, MotionEvent"
            textSize = 12f
            setPadding(16, 0, 0, 24)
            alpha = 0.7f
        }

        // Bottone Esempio 2
        val example2Button = Button(this).apply {
            text = "Esempio 2: Listener Multipli"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 16)
            }
            setOnClickListener {
                startActivity(Intent(this@MainActivity, ListenerDemoActivity::class.java))
            }
        }

        val desc2 = TextView(this).apply {
            text = "OnClickListener, OnLongClickListener, OnTouchListener"
            textSize = 12f
            setPadding(16, 0, 0, 24)
            alpha = 0.7f
        }

        // Bottone Esempio 3
        val example3Button = Button(this).apply {
            text = "Esempio 3: GestureDetector"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 16)
            }
            setOnClickListener {
                startActivity(Intent(this@MainActivity, GestureDetectorActivity::class.java))
            }
        }

        val desc3 = TextView(this).apply {
            text = "Tap, DoubleTap, LongPress, Scroll, Fling"
            textSize = 12f
            setPadding(16, 0, 0, 24)
            alpha = 0.7f
        }

        // Bottone Esempio 4
        val example4Button = Button(this).apply {
            text = "Esempio 4: Zoom e Rotazione"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 16)
            }
            setOnClickListener {
                startActivity(Intent(this@MainActivity, ZoomRotateActivity::class.java))
            }
        }

        val desc4 = TextView(this).apply {
            text = "ScaleGestureDetector, Multi-touch gestures"
            textSize = 12f
            setPadding(16, 0, 0, 24)
            alpha = 0.7f
        }

        layout.addView(titleText)
        layout.addView(subtitleText)
        layout.addView(example1Button)
        layout.addView(desc1)
        layout.addView(example2Button)
        layout.addView(desc2)
        layout.addView(example3Button)
        layout.addView(desc3)
        layout.addView(example4Button)
        layout.addView(desc4)

        setContentView(layout)
    }
}