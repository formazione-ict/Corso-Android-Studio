package com.example.a3persistenza_di_base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Button
import android.util.Log

class MainActivity : AppCompatActivity() {

    // 1. Stato della logica
    private var count = 0
    // 2. Riferimento globale alla UI
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Assegna alla variabile di classe
        textView = findViewById(R.id.text_counter)
        val btnIncrement = findViewById<Button>(R.id.button_increment)
        val btnReset = findViewById<Button>(R.id.button_reset) // Assicurati che l'ID sia corretto nell'XML

        // RIPRISTINO STATO: Se l'app viene ruotata, recuperiamo il valore dal Bundle
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("SAVED_COUNT", 0)
            textView?.text = count.toString()
            Log.d("DEBUG_APP", "Stato ripristinato: $count")
        }

        // Listener Incrementa
        btnIncrement.setOnClickListener {
            if (count < 10) {
                count++
                textView?.text = count.toString()
            }
            if (count >= 10) {
                btnIncrement.isEnabled = false
            }
        }

        // 2: Implementazione logica Reset
        btnReset.setOnClickListener {
            count = 0
            textView?.text = count.toString()
            btnIncrement.isEnabled = true // Riabilita il tasto se era bloccato a 10
            Log.d("DEBUG_APP", "Contatore resettato")
        }
    }

    // 3: Sostituto tecnico di 'rememberSaveable' per il sistema XML
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SAVED_COUNT", count)
        Log.d("DEBUG_APP", "Stato salvato nel Bundle prima della rotazione: $count")
    }
}