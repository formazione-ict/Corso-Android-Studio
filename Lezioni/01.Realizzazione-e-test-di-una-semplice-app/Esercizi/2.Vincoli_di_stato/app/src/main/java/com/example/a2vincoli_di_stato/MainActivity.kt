package com.example.a2vincoli_di_stato

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    // Variabile di stato
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Collega il file Kotlin al file XML
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_counter)
        val btnIncrement = findViewById<Button>(R.id.button_increment)
        val btnReset = findViewById<Button>(R.id.button_reset) // Riferimento al nuovo ID

        btnIncrement.setOnClickListener {
            if (count < 10) {
                count++
                textView.text = count.toString()
            }

            // Controllo esplicito per disabilitare il componente
            if (count >= 10) {
                btnIncrement.isEnabled = false
                Toast.makeText(this, "Limite massimo raggiunto!", Toast.LENGTH_SHORT).show()
            }
        }
        btnReset.setOnClickListener {
            count = 0
            textView.text = "0"
            btnIncrement.isEnabled = true
        }
    }
}