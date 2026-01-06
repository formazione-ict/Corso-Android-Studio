package com.esempio.gesture_eventi

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 10f
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }

    private val path = Path()
    private val paths = mutableListOf<Pair<Path, Int>>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Disegna tutti i percorsi precedenti
        paths.forEach { (savedPath, color) ->
            paint.color = color
            canvas.drawPath(savedPath, paint)
        }

        // Disegna il percorso corrente
        paint.color = Color.BLUE
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Inizia un nuovo percorso
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                // Continua il percorso
                path.lineTo(x, y)
                invalidate() // Richiede il ridisegno
            }
            MotionEvent.ACTION_UP -> {
                // Salva il percorso completato
                paths.add(Pair(Path(path), paint.color))
                path.reset()
            }
        }
        return super.onTouchEvent(event)
    }

    fun clearCanvas() {
        paths.clear()
        path.reset()
        invalidate()
    }

    fun changeColor(color: Int) {
        paint.color = color
    }
}
