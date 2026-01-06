package com.example.a4lifecycle

import android.os.Bundle
import android.util.Log // Import fondamentale
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val TAG = "LIFECYCLE_LOG"
    private var counter = 0 // Questa variabile andrebbe persa senza il salvataggio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate chiamato")

        // 1. Recupero il riferimento alla TextView tramite ID
        val textView = findViewById<TextView>(R.id.counterTextView)

        // 2. RIPRISTINO: Se c'è uno stato salvato, recupero il counter
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("KEY_COUNTER")
            Log.d(TAG, "onCreate: Stato ripristinato. Counter = $counter")
        }

        // 3. Incremento per dimostrare che il ciclo è avvenuto
        counter++

        // 4. AGGIORNAMENTO UI: Mostro il valore a schermo
        val baseText = textView.text.toString() // Prende "MODALITÀ LANDSCAPE ATTIVA" dall'XML
        textView.text = "$baseText \n Rotazioni: $counter"
        Log.d(TAG, "onCreate: Counter aggiornato a $counter")
        Log.d(TAG, "Valore attuale del counter: $counter")
    }

    // SALVATAGGIO: Chiamato prima che l'Activity venga distrutta
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Inseriamo i dati nel Bundle (una sorta di mappa chiave-valore)
        outState.putInt("KEY_COUNTER", counter)
        Log.d(TAG, "onSaveInstanceState: Salvataggio counter = $counter")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity visibile")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Focus ottenuto (interattiva)")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Focus perso")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Activity non più visibile")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Risorse rilasciate e istanza distrutta")
    }
}
