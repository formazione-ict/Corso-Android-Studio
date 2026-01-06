package com.example.a1contatore_digitale_con_reset

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

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
            count++
            textView.text = count.toString()
        }

        btnReset.setOnClickListener {
            count = 0 // Logica: azzeramento variabile
            textView.text = count.toString() // UI: aggiornamento manuale obbligatorio
        }
    }
}