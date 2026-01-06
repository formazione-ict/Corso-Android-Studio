package com.esempio.gesture_eventi

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ZoomRotateActivity : AppCompatActivity() {

    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f

    private lateinit var targetView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        targetView = View(this).apply {
            setBackgroundColor(Color.MAGENTA)
            layoutParams = LinearLayout.LayoutParams(300, 300)
        }

        val layout = LinearLayout(this).apply {
            gravity = android.view.Gravity.CENTER
            addView(targetView)
        }

        setContentView(layout)

        scaleGestureDetector = ScaleGestureDetector(this, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scaleFactor *= detector.scaleFactor
                scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f)) // Limita lo zoom
                targetView.scaleX = scaleFactor
                targetView.scaleY = scaleFactor
                return true
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }
}