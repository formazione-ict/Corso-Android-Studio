Questa lezione esplora l'architettura fondamentale di Android: il **Ciclo di Vita delle Activity**. 

Ti aiuta a comprendere come il sistema gestisce la memoria e gli stati dell'interfaccia. È cruciale per creare app fluide, che non perdono dati e che non consumano batteria inutilmente.

# 1. Introduzione: Che cos'è il Ciclo di Vita?

In Android, un'Activity non ha il controllo diretto della propria istanziazione o distruzione. È il sistema operativo (tramite l'**Activity Manager Service**) a gestire queste transizioni in base alle azioni dell'utente o alle risorse disponibili.

Il ciclo di vita è rappresentato da una **macchina a stati**. Ogni volta che un'Activity cambia stato, il sistema invia una notifica tramite una serie di metodi "callback".

# 2. Analisi Dettagliata dei Metodi di Callback

## A. La Fase di Creazione

* **`onCreate()`**: È il metodo più importante. Viene chiamato una sola volta quando l'Activity viene creata.

*Operazioni:* Inizializzazione dei componenti statici (binding delle view, creazione del ViewModel, setup del layout tramite `setContentView`).

*Approfondimento:* Riceve un oggetto `Bundle` chiamato `savedInstanceState`, che contiene i dati salvati se l'Activity è stata precedentemente distrutta dal sistema.

* **`onStart()`**: L'Activity sta per diventare visibile all'utente. Qui si inizializzano le funzionalità che aggiornano l'interfaccia.

## B. La Fase di Interazione

* **`onResume()`**: L'Activity entra in primo piano (foreground) ed è pronta a ricevere input dall'utente.
*Best Practice:* È il posto ideale per avviare animazioni, aggiornamenti della fotocamera o sensori.

* **`onPause()`**: L'Activity è ancora parzialmente visibile (es. un dialog trasparente è sopra di essa) ma l'utente la sta lasciando.
*Nota tecnica:* Questo metodo deve essere estremamente veloce. Non usarlo per salvare dati nel database o fare chiamate di rete.

## C. La Fase di Chiusura

* **`onStop()`**: L'Activity non è più visibile. L'utente ha aperto un'altra app o è tornato alla Home.
*Operazioni:* Rilasciare risorse pesanti, interrompere thread di background, salvare dati persistenti.

* **`onDestroy()`**: L'ultima chiamata prima che l'Activity venga rimossa dalla memoria. Può avvenire perché l'utente ha chiuso l'app (`finish()`) o perché il sistema ha bisogno di RAM.

# 3. Approfondimento: Il Cambio di Configurazione

Uno degli aspetti più complessi è la **rotazione dello schermo**. Quando lo schermo ruota, Android per default:

1. Chiama `onPause()` -> `onStop()` -> `onDestroy()`.
2. Ricrea l'Activity chiamando `onCreate()` -> `onStart()` -> `onResume()`.

> **Perché?** Per permettere all'app di caricare risorse alternative (es. un layout diverso per il formato "landscape").
> **Soluzione moderna:** Per evitare la perdita di dati complessi durante questo ciclo, si utilizzano i **ViewModel**, che sopravvivono alla distruzione dell'Activity.

# 4. Esempio Pratico: Logging del Ciclo di Vita

Ecco come implementare un'Activity per monitorare cosa succede nel **Logcat**:

```kotlin
class LifecycleActivity : AppCompatActivity() {

    private val TAG = "LifecycleDemo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate chiamato")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart chiamato")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume chiamato - L'utente interagisce")
    }

    override fun onPause() {
        super.onPause()
        Log.w(TAG, "onPause chiamato - Interazione interrotta")
    }

    override fun onStop() {
        super.onStop()
        Log.w(TAG, "onStop chiamato - Activity invisibile")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy chiamato - Risorse liberate")
    }
}

```

---

# 5. Esercitazione

## Esercizio 1: Analisi del Logcat

1. Crea un nuovo progetto Android Studio.
2. Copia il codice di logging sopra indicato.
3. Esegui l'app e osserva il Logcat filtrando per "LifecycleDemo".
4. **Azione:** Premi il tasto Home. Quali metodi vengono chiamati?
5. **Azione:** Riapri l'app dai task recenti. Cosa succede?
6. **Azione:** Ruota lo smartphone. Quante volte vedi `onDestroy` e `onCreate`?

### Esercizio 2: Gestione dello Stato (Challenge)

Aggiungi un `EditText` nel layout. Scrivi del testo, ruota lo schermo. Il testo rimane?
Ora aggiungi una variabile `var counter = 0` che incrementa ogni secondo.

* **Domanda:** Cosa succede al contatore quando ruoti lo schermo?
* **Task:** Prova a salvare il valore del contatore usando il metodo `onSaveInstanceState(outState: Bundle)` e a recuperarlo in `onCreate`.

---

### Approfondimento consigliato

Ti suggerisco di studiare la classe **`ProcessLifecycleOwner`** per monitorare quando l'intera applicazione (non solo la singola Activity) entra in background o foreground, una tecnica fondamentale per la gestione delle notifiche o della sicurezza (es. app bancarie che si bloccano quando esci).
