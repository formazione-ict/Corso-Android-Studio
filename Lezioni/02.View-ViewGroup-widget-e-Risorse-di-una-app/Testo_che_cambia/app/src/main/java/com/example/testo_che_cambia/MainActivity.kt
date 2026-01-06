package com.example.testo_che_cambia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflating del layout: trasforma l'XML in oggetti di memoria
        setContentView(R.layout.activity_main)

        // Referenziamento delle View tramite ID
        val myButton: Button = findViewById(R.id.action_button)
        val myTextView: TextView = findViewById(R.id.welcome_text)

        // Listener per l'evento click
        myButton.setOnClickListener {
            // Accediamo a una risorsa stringa dinamicamente
            myTextView.text = getString(R.string.button_clicked_message)

            // Logica tecnica: forziamo un aggiornamento della UI se necessario
            myTextView.invalidate()
        }
    }
}