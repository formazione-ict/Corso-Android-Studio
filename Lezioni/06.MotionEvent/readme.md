Questa lezione affronta uno dei pilastri dell'interattività in Android: come il sistema traduce un contatto fisico sullo schermo in dati digitali manipolabili.

Passiamo dalla gestione ad alto livello (i semplici "Click") al basso livello (il flusso di pacchetti di dati).

# 1. Cos'è un MotionEvent?

Ogni volta che tocchi lo schermo, il kernel Linux genera un evento di interrupt che il sistema Android impacchetta in un oggetto **`MotionEvent`**. Questo oggetto non contiene solo la posizione, ma un'intera serie di metadati:

* **Action**: Cosa sta succedendo (giù, su, movimento).
* **Coordinates**: Dove sta succedendo ().
* **Pressure/Size**: Quanta forza viene applicata (se supportato dal sensore).
* **Time**: Quando è avvenuto l'evento (EventTime vs DownTime).

# 2. Le Azioni Fondamentali (Action Codes)

Il ciclo di vita di un tocco è una sequenza di azioni che inizia sempre con un `ACTION_DOWN` e finisce con un `ACTION_UP`.

1. **`ACTION_DOWN`**: Il primo dito tocca lo schermo. È l'evento più importante: se una View non ritorna `true` qui, non riceverà i successivi eventi della sequenza.
2. **`ACTION_MOVE`**: Il dito si sposta. Viene generato con un'alta frequenza (spesso legata al refresh rate dello schermo, es. 60Hz o 120Hz).
3. **`ACTION_UP`**: L'ultimo dito lascia lo schermo.
4. **`ACTION_CANCEL`**: L'evento viene annullato dal sistema (es. se un Parent ruba l'evento perché ha capito che l'utente sta facendo uno scroll).

# 3. Il Flusso di Propagazione (Event Dispatching)

Tecnicamente, Android usa un sistema a tre fasi per decidere chi deve gestire il tocco. Immagina una gerarchia di View (Layout -> Frame -> Button):

1. **`dispatchTouchEvent()`**: Il sistema chiama questo metodo partendo dalla Activity, poi scende nell'albero delle View.
2. **`onInterceptTouchEvent()`**: (Solo per i `ViewGroup`) Permette a un genitore di "origliare" e rubare l'evento ai figli. Se ritorna `true`, il figlio riceve un `ACTION_CANCEL`.
3. **`onTouchEvent()`**: Il metodo dove effettivamente scrivi la logica di gestione. Se ritorna `true`, dichiari: "Ho gestito io questo evento, non passarlo ad altri".

# 4. Esempio: Custom View Interattiva

Ecco come creare una View che segue il dito dell'utente e cambia colore durante il movimento.

```kotlin
...
class InteractingView @JvmOverloads constructor(
    context: Context, 
    attrs: AttributeSet? = null, 
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
...
    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(circleX, circleY, 50f, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                paint.color = Color.RED
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                circleX = x
                circleY = y
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                paint.color = Color.BLUE
                invalidate()
            }
        }
        return super.onTouchEvent(event)
    }
}

```

## Approfondimento

1. *InteractingView*: crea una nuova classe che estende View.
2. *onDraw*: esegua l'override di questo metodo per disegnare un cerchio sulla tela (Canvas). La posizione del cerchio è determinata dalle variabili circleX and circleY.
3. *onTouchEvent*: Qui gestisci l'input dell'utente.
- ACTION_DOWN: Quando l'utente tocca per la prima volta lo schermo, cambi il colore del cerchio in rosso. Restituendo true, comunichi al sistema che sei interessato a ricevere i successivi eventi di tocco (come il movimento).
- ACTION_MOVE: Mentre l'utente trascina il dito sullo schermo, aggiorni le coordinate del cerchio e richiami invalidate() per forzare un ridisegno della vista.
- ACTION_UP: Quando l'utente solleva il dito, il colore del cerchio torna blu.

Nota la differenza fondamentale:

* **`event.getX()`**: Coordinata relativa alla View (0,0 è l'angolo in alto a sinistra del componente).
* **`event.getRawX()`**: Coordinata relativa all'intero schermo (0,0 è l'angolo del display fisico).

Per calcoli di fisica o trascinamento tra View diverse, si usa quasi sempre il valore **Raw**.

## 5. Esercitazione Proposta

### Esercizio 1: Il "Rilevatore di Distanza"

Modifica l'esempio precedente per calcolare la distanza percorsa dal dito tra l' `ACTION_DOWN` e l' `ACTION_UP`.

* *Suggerimento:* Usa la formula della distanza euclidea tra due punti P1(x1, y1) e P2(x2, y2)

   d = sqrt{(x2 - x1)^2 + (y2 - y1)^2}

* Stampa il risultato nel Logcat o in una TextView.

### Esercizio 2: Multi-Touch (Challenge)

Il `MotionEvent` può gestire più dita contemporaneamente usando i **PointerIndex**.

1. Prova a intercettare `ACTION_POINTER_DOWN`.
2. Cerca di far apparire un secondo cerchio quando viene appoggiato un secondo dito.
3. Usa `event.getX(pointerIndex)` per distinguere le posizioni.

### Approfondimento per la prossima sessione

Oltre ai `MotionEvent` grezzi, Android offre i **GestureDetectors**. Queste classi analizzano sequenze di `MotionEvent` per te e ti dicono direttamente se l'utente ha fatto un "Double Tap", un "Long Press" o uno "Scroll".

**Ti piacerebbe vedere come implementare un `GestureDetector` per gestire lo "Swipe to Dismiss" personalizzato?**
