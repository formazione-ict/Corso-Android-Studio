package com.esempio.gesture_eventi

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat

class GestureDetectorActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var feedbackText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48,48,48,48)
        }

        feedbackText = TextView(this).apply {
            text = "Interagisci con lo schermo"
            textSize = 20f
        }

        layout.addView(feedbackText)
        setContentView(layout)

        gestureDetector = GestureDetectorCompat(this, this)
        gestureDetector.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (gestureDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    override fun onDown(e: MotionEvent): Boolean {
        feedbackText.text = "onDown"
        return true
    }

    override fun onShowPress(e: MotionEvent) {
        feedbackText.text = "onShowPress"
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        feedbackText.text = "onSingleTapUp"
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        feedbackText.text = "onScroll"
        return true
    }

    override fun onLongPress(e: MotionEvent) {
        feedbackText.text = "onLongPress"
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        feedbackText.text = "onFling"
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        feedbackText.text = "onSingleTapConfirmed"
        return true
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
        feedbackText.text = "onDoubleTap"
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent): Boolean {
        feedbackText.text = "onDoubleTapEvent"
        return true
    }
}