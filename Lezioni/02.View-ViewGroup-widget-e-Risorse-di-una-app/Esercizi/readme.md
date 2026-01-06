# 2.5 Esercizi Proposti
## Esercizio 1: Il Calcolatore di Margini
Crea un layout con un LinearLayout verticale. Inserisci tre View di colori diversi.
-	Obiettivo: Sperimenta la differenza tra android:layout_margin (distanza esterna tra View) e android:padding (distanza tra il bordo della View e il suo contenuto interno).
-	Domanda tecnica: Se una View ha **layout_width="match_parent"** e un margine di 16dp, quanto spazio totale occupa effettivamente rispetto al parent?
### Esperimento suggerito
- Aumentare il margin della View 1 e osservare come si allontana dalle altre View.
- Aumentare il padding della View 2 e vedere come il testo si sposta verso il centro.
- Combinare margin + padding nella View 3 per capire come interagiscono.
###❓ Domanda tecnica
Se una View ha layout_width="match_parent" e un margine di 16dp, quanto spazio totale occupa rispetto al parent?

✅ Risposta chiara e didattica

Quando una View ha:
- layout_width="match_parent"
- android:layout_margin="16dp"
allora la View non occupa tutta la larghezza del parent, ma:
- si restringe di 16dp a sinistra
- si restringe di 16dp a destra

📐 Calcolo dello spazio totale occupato

La View occupa:
      
    larghezza effettiva = larghezza del parent
                          - marginLeft
                          - marginRight
Con margin = 16dp:

    larghezza effettiva = parentWidth - 32dp

🧠 Conclusione

La View non “esce” dal parent, ma si ritira verso l’interno di 32dp totali (16dp per lato).

Lo spazio occupato rispetto al parent è quindi:
- la View stessa (match_parent → riempie lo spazio disponibile meno i margini)
- 16dp di spazio vuoto a sinistra
- 16dp di spazio vuoto a destra

## Esercizio 2: Localizzazione e Risorse
1.	Crea una risorsa stringa nel file strings.xml chiamata welcome_msg.
2.	Crea una variante della risorsa per una seconda lingua (es. Spagnolo) aggiungendo un file strings.xml (es).
3.	Cambia la lingua del simulatore/dispositivo.

Verifica: L'app cambia il testo automaticamente? Come gestisce Android il fallback se una stringa manca nella lingua secondaria?

### Esercizio 3: Implementazione di una **Chain**
Crea tre Button in un ConstraintLayout.
1.	Connettili tra loro orizzontalmente (il primo al secondo, il secondo al terzo, ecc.).
2.	Applica l'attributo app:layout_constraintHorizontal_chainStyle="spread_inside".
3.	Osserva come lo spazio viene distribuito automaticamente tra i widget.

**Approfondimento Tecnico**

Dinamiche delle Chain: Quando utilizzi *spread_inside*, il motore di ConstraintLayout esegue un calcolo basato sulla risoluzione dei vincoli lineari.

Il Calcolo dello Spazio: A differenza dello stile *spread* (che distribuisce uniformemente lo spazio tra i widget e i margini), *spread_inside* assegna uno spazio nullo tra i widget esterni e i bordi del parent. Lo spazio libero residuo ($S_{free} = W_{parent} - \sum W_{widgets}$) viene diviso equamente solo negli intervalli tra i widget.

L'importanza della Chain Head: Solo gli attributi definiti nel primo elemento (Button 1 in questo caso) controllano l'intera catena. Se provassi a inserire *chainStyle* nel Button 2, verrebbe ignorato.

Interazione con i pesi (Weights): Se uno dei Button avesse *android:layout_width="0dp"*, potresti usare *app:layout_constraintHorizontal_weight*. In quel caso, lo spazio non verrebbe più distribuito "vuoto", ma il widget stesso si espanderebbe per occupare la sua quota di $S_{free}$, rendendo la catena una "Weighted Chain", simile al comportamento di LinearLayout con l'attributo weight.

**Analisi dei Margini**

In una configurazione *spread_inside*, se aggiungi un *android:layout_marginStart* al Button 1, questo margine verrà sommato allo spazio calcolato, permettendoti di staccare il widget dal bordo pur mantenendo la distribuzione automatica interna.
### Esercizio 4: il “Logger del Ciclo di Vita” (Lifecycle)###

Questo esercizio è fondamentale per interiorizzare il concetto di **Activity State Transition** ed è stato modificato per visualizzare i cambiamenti.

Quando il sistema operativo Android gestisce le risorse, non si limita a spostare le finestre, ma distrugge e ricrea le istanze delle classi per adattarsi ai cambiamenti di configurazione (come la rotazione).

Inserisci i log in tutti i principali callback. Nota l'uso di una costante TAG per mantenere il codice pulito e professionale, come previsto dalle best practice di sviluppo.

Per visualizzare concretamente come si comporta il sistema, implementa questo esercizio:
1.	Apri una tua Activity e inserisci un *Log.d* in ogni metodo del ciclo di vita:

    	override fun onPause() {
         super.onPause()
         Log.d("Lifecycle", "onPause chiamato")
        }
  	
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
