package com.esempio.gesture_eventi

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class DrawingActivity : AppCompatActivity() {
    private lateinit var drawingView: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        drawingView = DrawingView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        }

        val buttonLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(16, 16, 16, 16)
        }

        val clearButton = Button(this).apply {
            text = "Cancella"
            setOnClickListener { drawingView.clearCanvas() }
        }

        val redButton = Button(this).apply {
            text = "Rosso"
            setOnClickListener { drawingView.changeColor(Color.RED) }
        }

        val blueButton = Button(this).apply {
            text = "Blu"
            setOnClickListener { drawingView.changeColor(Color.BLUE) }
        }

        buttonLayout.addView(clearButton)
        buttonLayout.addView(redButton)
        buttonLayout.addView(blueButton)

        layout.addView(drawingView)
        layout.addView(buttonLayout)

        setContentView(layout)
    }
}