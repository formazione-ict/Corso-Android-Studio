package com.example.a2localizzazione_e_risorse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Imposta il layout principale
        setContentView(R.layout.activity_main)

        // Trova il TextView e imposta il testo localizzato
        val welcomeTextView = findViewById<TextView>(R.id.welcome_text)
        welcomeTextView.text = getString(R.string.welcome_msg)
    }
}
