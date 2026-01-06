# 2. Panoramica su View, ViewGroup, widget e risorse di un’app 
Benvenuto in questa lezione tecnica approfondita sull'architettura dell'interfaccia utente (UI) in Android. In questa lezione analizzeremo non solo i concetti base, ma anche i meccanismi interni che governano il rendering e la gestione delle risorse.

## 2.1 La Gerarchia dell'Interfaccia: View e ViewGroup

In Android, ogni elemento dell'interfaccia è un oggetto della classe **android.view.View**. L'architettura segue il Composite Design Pattern, dove gli oggetti sono organizzati in una struttura ad albero chiamata View Hierarchy.

### View (L'atomo)
La View è il mattoncino base. Occupa un'area rettangolare sullo schermo ed è responsabile del disegno di sé stessa e della gestione degli eventi (touch, click).

**Ciclo di vita del rendering.**

Ogni View attraversa tre fasi principali: 
-	*onMeasure()* (quanto spazio serve?)
-	*onLayout()* (dove mi posiziono?)
-	*onDraw()* (disegno dei pixel).

### ViewGroup (La molecola)
Un ViewGroup è una sottoclasse invisibile di View che funge da contenitore. Può contenere altre View o altri ViewGroup.

**Common Layouts**:
-	LinearLayout
-	ConstraintLayout (il più efficiente per gerarchie piatte)
-	FrameLayout.

## 2.2 Widget comuni e Attributi XML

I widget sono View pre-configurate fornite dal framework Android.

**Esempio: Struttura di un Layout**

Analizziamo un file XML tipico (*res/layout/activity_main.xml*):
```
    <androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/welcome_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_world"
        android:textSize="18sp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/action_button"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Invia"
        app:layout_constraintTop_toBottomOf="@id/welcome_text"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    </androidx.constraintlayout.widget.ConstraintLayout>
```
Note Tecniche:
-	*match_parent* vs *wrap_content*: Il primo espande la View fino ai limiti del genitore; il secondo la ridimensiona strettamente attorno al suo contenuto.
-	Unità di misura: Usa dp (density-independent pixels) per le dimensioni e sp (scalable pixels) per il testo, per garantire coerenza su schermi con diverse densità di pixel.

## 2.3 Il sistema delle risorse (res/)
Le risorse sono file esterni al codice Java/Kotlin che permettono di supportare diverse configurazioni (lingue, dimensioni schermo).
-	drawable/: Immagini (PNG, WebP) e file vettoriali (VectorDrawables).
-	layout/: Definizioni XML della UI.
-	values/: Contiene strings.xml (localizzazione), colors.xml (palette) e themes.xml.

**Approfondimento**:

La classe R

Quando compili l'app, Android Studio genera la classe R. È un indice di ID interi che puntano alle risorse.
-	In XML: **@string/nome_risorsa**
-	In Kotlin/Java: **R.string.nome_risorsa**
## 2.4 Esempio Testo che cambia (Kotlin)
Colleghiamo la logica alla UI. Supponiamo di voler cambiare il testo della TextView al click del Button.
```
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
```
## 2.5 Esercizi Proposti

### Esercizio 1: Il Calcolatore di Margini
Crea un layout con un LinearLayout verticale. Inserisci tre View di colori diversi.
-	Obiettivo: Sperimenta la differenza tra android:layout_margin (distanza esterna tra View) e android:padding (distanza tra il bordo della View e il suo contenuto interno).
-	Domanda tecnica: Se una View ha **layout_width="match_parent"** e un margine di 16dp, quanto spazio totale occupa effettivamente rispetto al parent?

### Esercizio 2: Localizzazione e Risorse
1.	Crea una risorsa stringa nel file strings.xml chiamata welcome_msg.
2.	Crea una variante della risorsa per una seconda lingua (es. Spagnolo) aggiungendo un file strings.xml (es).
3.	Cambia la lingua del simulatore/dispositivo.

Verifica: L'app cambia il testo automaticamente? Come gestisce Android il fallback se una stringa manca nella lingua secondaria?

**Approfondimento suggerito**

Per una comprensione avanzata, ti consiglio di studiare come il Choreographer di Android coordina i tempi di measure, layout e draw con il refresh rate dello schermo (solitamente 60Hz o 120Hz) per evitare il "jank" (scatti nell'interfaccia).
## 2.6. ConstraintLayout e l'ottimizzazione della gerarchia
Il ConstraintLayout è stato introdotto per risolvere un problema tecnico specifico: le performance dei layout annidati.

In Android, ogni volta che inserisci un LinearLayout dentro un altro LinearLayout (o RelativeLayout), aumenti la profondità dell'albero delle View. Durante la fase di *onMeasure()*, il sistema deve calcolare ricorsivamente le dimensioni di ogni figlio. Se la gerarchia è profonda, questo calcolo può richiedere più di 16ms, causando la perdita di frame (jank).

Il vantaggio tecnico: ConstraintLayout permette di creare interfacce complesse con una gerarchia piatta (un solo livello di profondità), riducendo drasticamente i tempi di calcolo del layout.

In Android, una "Chain" viene creata quando due o più elementi hanno connessioni bidirezionali tra loro. Il primo elemento della catena è definito **Chain Head** ed è quello che ospita l'attributo *layout_constraintHorizontal_chainStyle*.
```
    app:layout_constraintHorizontal_chainStyle="spread_inside"
```
**Esempio: Il concetto di "Bias" e "Chains"**
Invece di usare margini fissi, puoi usare il bias per posizionare un widget in modo proporzionale:
```    
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintHorizontal_bias="0.3" 
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent" />
```
Il tasto sarà posizionato al 30% della larghezza dello schermo partendo da sinistra, rendendo il layout estremamente flessibile su diverse risoluzioni.

## 2.7 Gestione avanzata delle risorse: Qualificatori e Densità
Le risorse non sono solo "cartelle", ma un sistema di risoluzione basato su "Qualificatori di configurazione".

Quando il sistema cerca una risorsa (es. un'immagine), segue questa logica:
1.	Controlla la densità dello schermo (**Density Bucket**): *mdpi*, *hdpi*, *xhdpi*, *xxhdpi*, *xxxhdpi*.
2.	Controlla l'orientamento (*port*, *land*).
3.	Controlla la versione minima di Android (es. *v24*).

**Approfondimento sui Vector Drawables**:

A differenza dei file PNG (raster), Android preferisce l'uso di XML vettoriali. Perché un file vettoriale è definito matematicamente (percorsi di linee e curve). Questo significa che una sola risorsa può scalare su qualsiasi densità di schermo senza perdere qualità e senza aumentare il peso dell'APK.

## 2.8 Problema tecnico: L'Overdraw (Sovradisegno)
L'Overdraw si verifica quando l'app disegna lo stesso pixel più volte in un singolo frame. Immagina di avere:
1.	Uno sfondo blu per l'Activity.
2.	Sopra, un LinearLayout con sfondo bianco.
3.	Sopra, una TextView con sfondo grigio.
Il sistema disegnerà tre volte lo stesso pixel. Questo spreca cicli di clock della GPU.

**Strumento di Debug:**

In Android Studio o sul dispositivo (opzioni Sviluppatore), puoi attivare “**Debug Overdraw**”.
-	Blu: 1x overdraw (accettabile).
-	Verde: 2x overdraw.
-	Rosso: 4x o più (critico).

**Soluzione tecnica:**

Rimuovere gli attributi Android: background non necessari dai contenitori padri se sono già coperti dai figli.
### Verifica: Analisi della gerarchia
1.	Apri un tuo progetto in Android Studio.
2.	Avvia l'app sull'emulatore.
3.	Vai su **Tools** > **Layout Inspector**.
4.	Esamina l'albero delle View.
- Sfida: Riesci a trovare un modo per eliminare almeno un ViewGroup intermedio (ad esempio un FrameLayout o un LinearLayout inutile) per appiattire la struttura?

### Esercizio 3: Implementazione di una **Chain**
Crea tre Button in un ConstraintLayout.
1.	Connettili tra loro orizzontalmente (il primo al secondo, il secondo al terzo, ecc.).
2.	Applica l'attributo app:layout_constraintHorizontal_chainStyle="spread_inside".
3.	Osserva come lo spazio viene distribuito automaticamente tra i widget.
## 2.9 Il ciclo di vita dell'Activity
Il ciclo di vita dell'Activity è uno dei concetti più critici per un programmatore Android. Non è solo una sequenza di eventi, ma un sistema di gestione delle risorse di sistema. Android è un sistema "aggressivo": se la memoria scarseggia, il sistema operativo ucciderà i processi in background senza preavviso. Capire il ciclo di vita serve a prevenire **memory leak** e **crash**.

Concetto di **Activity State Transition**: Quando il sistema operativo Android gestisce le risorse, non si limita a spostare le finestre, ma distrugge e ricrea le istanze delle classi per adattarsi ai cambiamenti di configurazione (come la rotazione).

**Lo Stack delle Activity e il Backstack**

Le Activity sono gestite in un Last-In, First-Out (LIFO) stack. Quando avvii una nuova Activity, quella corrente viene messa in pausa e spinta nello stack. Quando l'utente preme il tasto "Indietro", l'Activity corrente viene distrutta (*finish()*) e quella precedente torna in primo piano.

### 2.9.1 I Metodi di Callback (Deep Dive)
Il sistema invia segnali all'Activity attraverso metodi di callback. Eccoli in ordine di esecuzione:

A. La fase di Creazione: *onCreate()* e *onStart()*

- *onCreate()*: Viene chiamato una sola volta quando l'Activity viene istanziata. Qui avviene l'Inflating del layout (*setContentView*) e l'inizializzazione dei dati.
    Nota Tecnica: Se ricevi un *savedInstanceState: Bundle?* non nullo, significa che l'Activity è stata ricreata dopo una distruzione dal sistema (es. rotazione schermo).
- *onStart()*: L'Activity diventa visibile all'utente, ma non è ancora interattiva. È il momento ideale per registrare listener che monitorano i cambiamenti della UI.

B. La fase Interattiva: onResume()

- *onResume()*: L'Activity è in cima allo stack e l'utente può interagire.
	Best Practice: Qui dovresti far ripartire animazioni o aggiornamenti della posizione GPS che avevi interrotto in *onPause()*.

C. La fase di Pausa e Arresto:
- *onPause()*: L'Activity è ancora parzialmente visibile (es. un dialogo semi-trasparente è sopra di essa). È un metodo critico: deve essere estremamente veloce. Non usarlo per salvare dati nel DB, altrimenti rallenterai la transizione verso la nuova Activity.
- *onStop()*: L'Activity non è più visibile. Qui puoi eseguire operazioni più pesanti come il salvataggio dei dati sul disco o lo scollegamento di servizi pesanti.

D. La fase di Distruzione:
- *onDestroy()*: L'ultima chiamata prima che l'oggetto Activity venga rimosso dalla memoria. Viene chiamato o perché hai invocato *finish()* o perché il sistema ha bisogno di RAM.

**Gestione della configurazione: il problema della rotazione**
Quando ruoti lo schermo, Android per impostazione predefinita distrugge e ricrea completamente l'Activity.
- Perché? Per permettere al sistema di ricaricare le risorse corrette (es. un layout diverso in *layout-land/*).
- Il rischio: se hai variabili semplici (come un contatore), i dati andranno persi.

**Soluzione Tecnica: *onSaveInstanceState***

Per dati piccoli (pochi KB), usa il Bundle:
```
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("CONTATORE", valoreCorrente)
        super.onSaveInstanceState(outState)
    }
    // Nel onCreate puoi recuperarlo:
    val valore = savedInstanceState?.getInt("CONTATORE") ?: 0
```
Per dati complessi o pesanti, l'approccio moderno prevede l'uso dei ViewModel (parte dei Jetpack Architecture Components), che sopravvivono ai cambiamenti di configurazione.
### Esercizio 4: il “Logger del Ciclo di Vita” (Lifecycle)###
Per visualizzare concretamente come si comporta il sistema, implementa questo esercizio:
1.	Apri una tua Activity e inserisci un *Log.d* in ogni metodo del ciclo di vita:

```
    	override fun onPause() {
         super.onPause()
         Log.d("Lifecycle", "onPause chiamato")
        }
``` 	
2.	Apri il tab Logcat in Android Studio e filtra per la parola "Lifecycle".
3.	Prova queste azioni e osserva il Logcat:
-	Premi il tasto Home.
-	Riapri l'app dal task manager.
-	Ruota lo schermo (assicurati che la rotazione automatica sia attiva).
-	Apri una seconda Activity e poi premi "Back".

**Approfondimento sulle risorse alternative**
Colleghiamoci al ciclo di vita: se crei una cartella res/layout-land/ e ci metti una versione orizzontale di activity_main.xml, Android farà tutto il lavoro di switch durante la fase di *onDestroy()* -> *onCreate()* causata dalla rotazione.

**Analisi tecnica**
Questo meccanismo è potente ma costoso. Se la tua Activity è "pesante", la rotazione risulterà scattosa. È qui che entra in gioco l'ottimizzazione del ViewBinding e dei ViewModel.
