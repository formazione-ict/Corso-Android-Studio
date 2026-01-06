package com.example.custom_view_interattiva

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class InteractingView @JvmOverloads constructor(
    context: Context, 
    attrs: AttributeSet? = null, 
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var circleX = 100f
    private var circleY = 100f
    private val paint = Paint().apply { color = Color.BLUE }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(circleX, circleY, 50f, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                paint.color = Color.RED
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                circleX = x
                circleY = y
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                paint.color = Color.BLUE
                invalidate()
            }
        }
        return super.onTouchEvent(event)
    }
}