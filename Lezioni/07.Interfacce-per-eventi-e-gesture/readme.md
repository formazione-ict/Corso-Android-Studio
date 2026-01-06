Questa lezione approfondisce il sistema di gestione degli input in **Android Studio**.

Passeremo dalla fisica del tocco fino all'implementazione di gesture complesse, analizzando come il sistema operativo Android interpreta i segnali elettrici dello schermo.

# 1. L'Anatomia di un tocco: La classe `MotionEvent`

In Android, ogni singola interazione dell'utente viene impacchettata in un oggetto **`MotionEvent`**. Questo oggetto non contiene solo le coordinate , ma un set completo di metadati storici e fisici.

## Azioni Fondamentali (Action Codes)

L'evento è definito da un "Action Code" che ne descrive lo stato nel ciclo di vita:

* **`ACTION_DOWN`**: Il primo contatto con lo schermo. È l'evento più importante: se non viene "consumato" (restituendo `true`), la View non riceverà più alcun evento per quella sequenza.
* **`ACTION_MOVE`**: Generato ogni volta che la posizione cambia. Android raggruppa questi movimenti in "batch" per ottimizzare le performance.
* **`ACTION_UP`**: L'utente solleva l'ultimo dito.
* **`ACTION_CANCEL`**: L'evento viene annullato dal sistema (es. un dialogo si apre sopra l'app o un genitore ha intercettato il tocco).

## Coordinate: `getX()` vs `getRawX()`

* **`getX()`**: Restituisce la coordinata relativa al bordo della View che ha ricevuto il tocco.
* **`getRawX()`**: Restituisce la coordinata assoluta riferita all'intero schermo del dispositivo.

# 2. Il Flusso di Distribuzione (Event Dispatching)

Android utilizza un sistema di "Responsabilità a Catena". Immagina una gerarchia di View (es. un `ConstraintLayout` che contiene un `Button`). Quando tocchi il bottone, l'evento attraversa tre fasi:

1. **`dispatchTouchEvent()`**: Il sistema chiama questo metodo partendo dalla Root (Activity) verso il basso.
2. **`onInterceptTouchEvent()`** (Solo per i `ViewGroup`): Qui il genitore può decidere di "rubare" l'evento al figlio. Se un `ScrollView` rileva che il dito si muove verticalmente, intercetta l'evento per scorrere, impedendo al bottone interno di cliccarsi.
3. **`onTouchEvent()`**: È il metodo finale dove la logica viene eseguita. Se restituisci `true`, la catena si ferma (evento consumato).

# 3. Gestire le Gesture con `GestureDetector`

Calcolare manualmente la velocità di uno "swipe" o distinguere un "double tap" è matematicamente complesso. Per questo Android fornisce la classe **`GestureDetector`**.

## Esempio Pratico: Custom View con Gesture

In questo esempio, creiamo una View che riconosce il **Long Press** e il **Fling** (lo scorrimento veloce).

```kotlin
class MyGestureView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    // 1. Definiamo il listener che interpreta i segnali
    private val listener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean = true // Obbligatorio!

        override fun onDoubleTap(e: MotionEvent): Boolean {
            Log.d("GESTURE", "Doppio tocco a: ${e.x}, ${e.y}")
            return true
        }

        override fun onFling(
            e1: MotionEvent?, e2: MotionEvent, 
            velocityX: Float, velocityY: Float
        ): Boolean {
            // e1: punto di inizio, e2: punto di fine
            Log.d("GESTURE", "Lancio rilevato! Velocità: $velocityX px/s")
            return true
        }
    }

    // 2. Inizializziamo il detector
    private val detector = GestureDetector(context, listener)

    // 3. Colleghiamo il tocco della View al detector
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return detector.onTouchEvent(event) || super.onTouchEvent(event)
    }
}

```

# 4. Multi-Touch e Pointer ID

Quando l'utente usa più dita, Android assegna a ogni dito un **Pointer ID**.
Mentre l'indice dell'array di tocchi può cambiare (se il dito 0 si alza, il dito 1 diventa il nuovo 0), l'ID rimane costante.

**Matematica del Pinch-to-Zoom:**
Per calcolare lo zoom, dobbiamo monitorare la distanza euclidea tra due pointer ( p_1 e p_2):

$$dist = \sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}$$

Android semplifica questo calcolo con la classe **`ScaleGestureDetector`**.

# 5. Approfondimento Tecnico: VelocityTracker

Nelle interfacce ad alte prestazioni (come un elenco che scorre), non basta sapere dove si trova il dito; serve sapere quanto corre. La classe **`VelocityTracker`** analizza gli ultimi 100-200ms di movimento e applica un filtro di smoothing per calcolare i pixel al secondo.

```kotlin
private var velocityTracker: VelocityTracker? = null

override fun onTouchEvent(event: MotionEvent): Boolean {
    when (event.actionMasked) {
        MotionEvent.ACTION_DOWN -> {
            velocityTracker = VelocityTracker.obtain()
            velocityTracker?.addMovement(event)
        }
        MotionEvent.ACTION_MOVE -> {
            velocityTracker?.addMovement(event)
            velocityTracker?.computeCurrentVelocity(1000) // Calcola px al secondo
            Log.d("VELOCITY", "Velocità X: ${velocityTracker?.xVelocity}")
        }
        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
            velocityTracker?.recycle() // Importante per evitare memory leak
            velocityTracker = null
        }
    }
    return true
}

```

---

# Esercitazioni Pratiche

## Esercizio 1: Il quadrato "sfuggente"

Crea una View con un quadrato disegnato al centro. Implementa `onScroll` nel `GestureDetector` in modo che il quadrato si sposti seguendo esattamente il dito dell'utente.

**Suggerimento:** Usa `canvas.drawRect()` e aggiorna le coordinate chiamando `invalidate()`.

## Esercizio 2: Rilevatore di Angolo (Avanzato)

Implementa una gesture che rilevi la rotazione di due dita.

**Obiettivo:** Calcolare l'arcotangente della pendenza tra i due punti per determinare l'angolo di rotazione in gradi:

## Esercizio 3: Gestione Conflitti

Inserisci una `MyGestureView` dentro un `ScrollView`. Nota come lo scroll "ruba" i movimenti verticali. Prova a usare `requestDisallowInterceptTouchEvent(true)` per impedire allo ScrollView di intervenire quando l'utente tocca la tua View.

**Puoi approfondire con le animazioni fisiche (Fling Animation) collegate a questi eventi**
