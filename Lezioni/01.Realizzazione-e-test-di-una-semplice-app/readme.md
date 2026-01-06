# Lezione: Realizzazione e test di una semplice app

In questa lezione analizzeremo la creazione di un'applicazione basata su **Jetpack Compose**, il toolkit moderno di Google per la costruzione di interfacce native, che ha sostituito il sistema basato su XML (View System).

## 1. Anatomia di un progetto Android Studio

Quando crei un nuovo progetto ("Empty Compose Activity"), Android Studio genera una struttura complessa. Ecco i componenti chiave su cui concentrarsi:

* **`MainActivity.kt`**: Il punto di ingresso (Entry Point). In Android, un'Activity rappresenta una singola schermata con cui l'utente può interagire.
* **`AndroidManifest.xml`**: Il file di metadati che descrive i componenti dell'app, i permessi richiesti e le versioni hardware supportate.
* **`build.gradle.kts` (Project & Module)**: Script di configurazione per **Gradle**, il sistema di automazione della compilazione. Qui si gestiscono le dipendenze e le versioni del compilatore Kotlin.

### Approfondimento Tecnico: Il ciclo di vita (Lifecycle)

Ogni Activity segue un ciclo di vita gestito dal sistema operativo. È fondamentale comprendere che un'app non "gira" semplicemente; viene creata, avviata, messa in pausa e distrutta in base alle risorse di sistema e alle azioni dell'utente (es. rotazione dello schermo).

---

## 2. Esempio Pratico: "Il Contatore Digitale" (Versione XML / Views)

Realizzeremo un'app con un testo e un pulsante che incrementa un valore.

In questo paradigma, il lavoro è diviso in due file distinti che vengono messi in comunicazione dal sistema di compilazione.

### 1. Definizione dell'Interfaccia (res/layout/activity_main.xml)

Il layout viene definito in un linguaggio dichiarativo statico (XML). Utilizziamo un *ConstraintLayout*, che è il manager di posizionamento più avanzato e performante per evitare gerarchie di viste troppo profonde (che rallenterebbero il rendering).

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:constraints="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/text_counter"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="0"
        android:textSize="48sp"
        constraints:layout_constraintBottom_toTopOf="@+id/button_increment"
        constraints:layout_constraintEnd_toEndOf="parent"
        constraints:layout_constraintStart_toStartOf="parent"
        constraints:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button_increment"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Incrementa"
        constraints:layout_constraintBottom_toBottomOf="parent"
        constraints:layout_constraintEnd_toEndOf="parent"
        constraints:layout_constraintStart_toStartOf="parent"
        constraints:layout_constraintTop_toBottomOf="@+id/text_counter" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### 2. Logica Applicativa (MainActivity.kt)
Qui dobbiamo "gonfiare" (inflate) il layout XML e collegare manualmente gli oggetti del codice agli elementi grafici tramite i loro ID.

```kotlin
package com.example.contatore_digitale

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Variabile di stato
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Collega il file Kotlin al file XML
        setContentView(R.layout.activity_main)

        // Riferimenti ai componenti XML
        val textView = findViewById<TextView>(R.id.text_counter)
        val button = findViewById<Button>(R.id.button_increment)

        button.setOnClickListener {
            count++
            textView.text = count.toString()
        }
    }
}
```
**Approfondimento: Perché il sistema XML è considerato "Imperativo"?**

Nel codice sopra, vedi l'istruzione textView.text = count.toString(). Questo è un comando imperativo: tu stai dicendo esplicitamente al sistema di cambiare una proprietà specifica di un oggetto specifico in un momento preciso.

Il limite tecnico: Se l'app diventa complessa (es. il contatore cambia da tre posti diversi), devi ricordarti di aggiornare la TextView in ognuno di quei tre posti. Se ne dimentichi uno, la UI e i dati non saranno più sincronizzati.

## 3. Test dell'applicazione

Il test non è un'attività opzionale, ma parte integrante del flusso di lavoro (TDD - Test Driven Development).

### A. Test Strumentali (UI Test)

Questi test girano su un dispositivo fisico o un emulatore. Si trovano nella cartella `androidTest`.

### B. Debugging con Logcat

Il **Logcat** è lo strumento di Android Studio per visualizzare i log di sistema in tempo reale.

* Usa `Log.d("TAG", "Messaggio")` per il debug.
* Filtra per il "Package Name" della tua app per isolare i tuoi messaggi dal rumore di fondo del sistema.

---

## Esercizi proposti

### Esercizio 1: Implementazione del Reset

Aggiungi un secondo pulsante (sotto quello di incremento) etichettato "Reset". Al clic, il contatore deve tornare a `0`.

* *Obiettivo:* Comprendere la manipolazione dello stato da più sorgenti.

### Esercizio 2: Vincoli di Stato

Implementare dei vincoli di stato è un passaggio fondamentale per imparare a rendere le interfacce reattive e consistenti.

In Android, disabilitare un pulsante non è solo una scelta estetica, ma una best practice di UX: informa l'utente che un'azione non è più disponibile prima ancora che provi a eseguirla.

### Esercizio 3: Persistenza di base (Sfida Avanzata)

Attualmente, se ruoti lo schermo, il contatore potrebbe resettarsi (a seconda della configurazione). 

Analizza i comandi `remember` e `rememberSaveable`.

* *Domanda di approfondimento:* Qual è la differenza tecnica tra il salvataggio in memoria (RAM) e il salvataggio nel `Bundle` dello stato dell'istanza?
