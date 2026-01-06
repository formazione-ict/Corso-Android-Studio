package com.example.contatore_digitale

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

        // Riferimenti ai componenti XML
        val textView = findViewById<TextView>(R.id.text_counter)
        val button = findViewById<Button>(R.id.button_increment)

        button.setOnClickListener {
            count++
            textView.text = count.toString()
        }
    }
}