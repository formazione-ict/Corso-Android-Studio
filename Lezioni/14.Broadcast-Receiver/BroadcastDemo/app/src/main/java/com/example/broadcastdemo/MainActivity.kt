package com.example.broadcastdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    // View per lo stato della rete
    private lateinit var txtNetworkStatus: TextView
    private lateinit var networkStatusManager: NetworkStatusManager

    // View per lo stato del broadcast personalizzato
    private lateinit var txtCustomBroadcastStatus: TextView

    // --- Questo è il ricevitore DINAMICO per aggiornare la UI ---
    // Sarà registrato quando l'activity è visibile.
    private val customReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Controlliamo che l'azione sia quella giusta
            if (intent.action == "com.example.broadcastdemo.CUSTOM_BROADCAST") {
                val message = intent.getStringExtra("message") ?: "Nessun messaggio"
                // Aggiorna la TextView!
                txtCustomBroadcastStatus.text = "Stato: $message"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inizializzazione di tutte le View
        txtNetworkStatus = findViewById(R.id.txtNetworkStatus)
        txtCustomBroadcastStatus = findViewById(R.id.txtCustomBroadcastStatus) // <-- Inizializza la nuova TextView
        val btnSendBroadcast: Button = findViewById(R.id.btnSendCustomBroadcast)

        // 2. Impostazione del Click Listener
        btnSendBroadcast.setOnClickListener {
            sendCustomBroadcast()
        }

        // 3. Inizializzazione del Network Manager
        networkStatusManager = NetworkStatusManager(this) { status ->
            runOnUiThread {
                txtNetworkStatus.text = "Stato rete: $status"
            }
        }

       // --- 4. REGISTRAZIONE DEL RICEVITORE DINAMICO ---
       // Specifichiamo per quale azione deve mettersi in ascolto
        val filter = IntentFilter("com.example.broadcastdemo.CUSTOM_BROADCAST")

       // Utilizziamo ContextCompat per una registrazione retrocompatibile e sicura
        ContextCompat.registerReceiver(
            this, // Context
            customReceiver, // Il tuo receiver
            filter, // L'intent filter
            ContextCompat.RECEIVER_NOT_EXPORTED // Il flag di esportazione
        )

    }

    private fun sendCustomBroadcast() {
        val intent = Intent("com.example.broadcastdemo.CUSTOM_BROADCAST")
        // --- MODIFICA CONSIGLIATA ---
        // Rende il broadcast esplicito, specificando che è destinato solo
        // alla nostra app. È più sicuro ed efficiente.
        // "this.packageName" ottiene dinamicamente l'ID della tua applicazione (es. "com.example.broadcastdemo").
        intent.setPackage(this.packageName)

        intent.putExtra("message", "Broadcast personalizzato ricevuto!")
        sendBroadcast(intent)
    }


    override fun onResume() {
        super.onResume()
        // Registra il receiver per lo stato della rete
        networkStatusManager.register()
    }

    override fun onPause() {
        super.onPause()
        // De-registra il receiver per lo stato della rete
        networkStatusManager.unregister()
    }

    override fun onDestroy() {
        super.onDestroy()
        // --- DE-REGISTRAZIONE DEL RICEVITORE DINAMICO ---
        // È fondamentale per evitare memory leak!
        unregisterReceiver(customReceiver)
    }
}
