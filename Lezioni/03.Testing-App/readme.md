Benvenuto in questa lezione tecnica approfondita sul testing in Android. 

Questa lezione è strutturata analizzando l'architettura del testing in Android, le differenze hardware/software tra simulazione e realtà, e l'implementazione pratica di test strumentati.

# 1. Architettura del Testing in Android

In Android Studio, i test non sono tutti uguali. Esiste una distinzione fondamentale basata su dove il codice viene eseguito:

- **Local Unit Tests (`src/test`):** Girano sulla Java Virtual Machine (JVM) del tuo computer di sviluppo. Sono veloci ma non hanno accesso alle API di Android (se non tramite mocking con framework come **Mockito**).
- **Instrumented Tests (`src/androidTest`):** Girano su un dispositivo reale o un emulatore. Hanno accesso alle API di Android (`Context`, `Resources`, etc.) e sono fondamentali per testare l'integrità del framework e l'interfaccia utente (UI).

# 2. Testing su Emulatori vs Dispositivi Reali

## Android Emulator (AVD - Android Virtual Device)

L'emulatore moderno utilizza l'accelerazione hardware (Intel HAXM o Hypervisor su AMD) per eseguire un'immagine di sistema **x86_64** o **arm64**.

- **Vantaggi:** Scalabilità (puoi testare decine di combinazioni di API e schermi), Snapshot (salvataggio dello stato), simulazione di sensori (GPS, batteria, impronte digitali).
- **Deep Dive Tecnico:** L'emulatore comunica con ADB (Android Debug Bridge) tramite una connessione socket. Per il testing UI, è fondamentale disabilitare le animazioni di sistema nelle "Opzioni sviluppatore" dell'emulatore per evitare *test flakiness* (fallimenti casuali dovuti a ritardi nel rendering).

## Dispositivi Reali

Indispensabili per il testing delle performance reali e dei componenti hardware specifici.

**Criticità:** Gestione dei driver (specialmente su Windows), permessi di debug USB e, soprattutto, la frammentazione dei produttori (le personalizzazioni OEM come Samsung One UI o Xiaomi MIUI possono alterare il comportamento di background task e notifiche).

# 3. Implementazione: Espresso e UI Testing

Il framework standard per il testing strumentato è **Espresso**. La sua filosofia si basa su tre componenti:

1. **ViewMatchers:** Trovano l'elemento (es. `withId(R.id.button_submit)`).
2. **ViewActions:** Interagiscono con l'elemento (es. `click()`).
3. **ViewAssertions:** Verificano lo stato (es. `matches(isDisplayed())`).

## Esempio di test Form di login.

Implementeremo un esempio completo di **Instrumented UI Test** utilizzando il pattern **ActivityScenario**.

Per testare un form di login, non basta scrivere il codice del test; dobbiamo assicurarci che l'architettura dell'app sia testabile (usando ID univoci) e che l'ambiente di testing sia configurato correttamente.

### Esercizio per te:

Prova a modificare il test per gestire uno scenario di errore:

1. Lascia il campo password vuoto.
2. Clicca Login.
3. Verifica che la `EditText` della password mostri un errore (hint: usa `hasErrorText` o controlla la visibilità di un messaggio di errore dedicato).

---

## 4. Esercitazione
### Obiettivo: Testare la persistenza e l'UI di una "To-Do List".

**Task 1: Configurazione Ambiente**

1. Crea un emulatore con API 33 (Android 13).
2. Collega un dispositivo fisico tramite Wi-Fi Debugging (introdotto con Android 11).
3. Nel file `build.gradle`, aggiungi le dipendenze:
```gradle
androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
androidTestImplementation 'androidx.test:runner:1.5.2'

```

**Task 2: Scrittura del Test**
Scrivi un test strumentato che:

1. Digiti un'attività in una `EditText`.
2. Prema un tasto "Add".
3. Verifichi che l'elemento sia apparso in una `RecyclerView`.
* *Approfondimento:* Per le RecyclerView, dovrai usare `espresso-contrib` e il metodo `RecyclerViewActions.scrollToPosition()`.

**Task 3: Test su Dispositivo Reale**
Esegui lo stesso test sul dispositivo fisico. Nota le differenze di velocità e verifica se il test fallisce a causa della tastiera software che copre la vista (problema comune nei test UI).

---

## 5. Approfondimenti Avanzati

Per una padronanza completa, ti consiglio di esplorare:

* **Test Orchestrator:** Per eseguire ogni test in una propria istanza di strumentazione, garantendo che il crash di un test non influenzi gli altri.
* **Cloud Testing (Firebase Test Lab):** Per eseguire i tuoi test su centinaia di dispositivi reali situati nei data center di Google, utile per catturare bug specifici di determinati modelli hardware.
