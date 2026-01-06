# Esercizio 1: Implementazione del Reset

Aggiungi un secondo pulsante (sotto quello di incremento) etichettato "Reset". Al clic, il contatore deve tornare a `0`.

Obiettivo: Comprendere la manipolazione dello stato da più sorgenti.

1. Modifica al layout (activity_main.xml)

Dobbiamo aggiungere il nuovo tag <Button> e vincolarlo sotto il precedente.
   
```
<Button
    android:id="@+id/button_reset"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Reset"
    style="@style/Widget.Material3.Button.OutlinedButton"
    app:layout_constraintTop_toBottomOf="@id/button_increment"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    android:layout_marginTop="8dp" />
```
2. Modifica alla logica (MainActivity.kt)

Dobbiamo trovare il riferimento al nuovo pulsante e istruirlo esplicitamente.

```
// ... dentro onCreate ...
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
```
## Analisi Tecnica dell'Esercizio
L'obiettivo di questa aggiunta era comprendere la manipolazione da più sorgenti.

Sincronizzazione dei dati: In XML, se avessi dimenticato la riga *textView.text = count.toString()* nel listener del Reset, avresti avuto un bug di sincronizzazione: la variabile interna sarebbe stata 0, ma l'utente avrebbe continuato a vedere il valore precedente.

Accoppiamento: In XML, il codice della logica è "accoppiato" strettamente all'ID dell'XML (*R.id.button_reset*). In Compose, il codice è "accoppiato" solo allo stato (count).

# Esercizio 2: Vincoli di Stato

Modifica la funzione `CounterApp` affinché il contatore non possa superare il valore `10`. Se l'utente clicca dopo il 10, mostra un messaggio (Toast) o disabilita il pulsante.

Suggerimento tecnico: Usa la proprietà `enabled` nel composable `Button`.

Dobbiamo gestire lo stato del pulsante manualmente ogni volta che il valore cambia.

```Kotlin

// Dentro onCreate o nel listener del pulsante
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

// Ricordati di riabilitarlo nel listener del Reset!
btnReset.setOnClickListener {
    count = 0
    textView.text = "0"
    btnIncrement.isEnabled = true
}
```
# Esercizio 3: Persistenza di base

Attualmente, se ruoti lo schermo, il contatore potrebbe resettarsi (a seconda della configurazione). Sostituisci `remember` con `rememberSaveable`.

Il problema tecnico: Perché remember fallisce?

In Android, quando ruoti lo schermo, il sistema operativo distrugge e ricrea l'intera Activity per caricarne le risorse (ad esempio un layout diverso per il landscape).
- remember: memorizza il valore nella "composizione". Quando l'Activity viene distrutta, la composizione viene cancellata e il valore va perduto.
- rememberSaveable (appartiene esclusivamente a Jetpack Compose): salva il valore in un Bundle. Il Bundle è una struttura dati che Android conserva in memoria anche durante la distruzione e ricreazione dell'Activity, permettendo di ripristinare lo stato tramite *onSaveInstanceState*.

**Approfondimento: Cosa può essere salvato?**

Non tutti i dati possono essere salvati automaticamente con rememberSaveable.
- Tipi primitivi: String, Int, Boolean, ecc., vengono salvati automaticamente nel Bundle.
- Oggetti complessi: Se hai una classe personalizzata (es. User), devi renderla Parcelable o definire un Saver personalizzato.
- Limite di dati: Il Bundle ha un limite fisico (circa 1MB per intero processo). Non è adatto per salvare grandi liste di dati o immagini; per quello si usano i database (Room) o i ViewModel.
