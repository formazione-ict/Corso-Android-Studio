package com.example.broadcastdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


/**
 * Questo è un BroadcastReceiver registrato staticamente nel manifest.
 * Si attiva quando riceve un Intent con l'azione "com.example.broadcastdemo.CUSTOM_BROADCAST".
 */
class CustomBroadcastReceiver : BroadcastReceiver() {
    /**
     * Questo metodo viene chiamato quando il BroadcastReceiver riceve un Intent
     * che corrisponde al suo intent-filter.
     */
    override fun onReceive(context: Context, intent: Intent) {
        // Controlla che l'azione sia quella che ci aspettiamo
        if (intent.action == "com.example.broadcastdemo.CUSTOM_BROADCAST") {
            val message = intent.getStringExtra("message") ?: "Nessun messaggio"

            // Poiché questo receiver non è legato a una UI specifica (potrebbe attivarsi
            // anche ad app chiusa), il modo più semplice per mostrare un output
            // è usare un Toast.
            // Mostra un messaggio a schermo (Toast)
            Toast.makeText(context, "Broadcast Ricevuto: $message", Toast.LENGTH_LONG).show()

            // NOTA: Aggiornare la UI da qui è complesso perché il receiver
            // non ha un riferimento diretto all'Activity. Per aggiornare la UI,
            // è meglio usare la registrazione dinamica (Opzione B).
        }
    }
}

