# Struttura di base di un’app Android
- Il codice può essere scritto in Kotlin, Java o C++ e viene compilato in un **APK** installabile o in un **Android App Bundle (AAB)** usato per la distribuzione su Play Store.
- Ogni app gira in una propria **sandbox**: ha un ID utente Linux dedicato, un proprio processo e una propria VM, così da isolare codice e dati dalle altre app.
- Android applica il **principio del privilegio minimo**: l’app vede solo ciò che le è stato esplicitamente consentito (permessi, feature hardware, ecc.).

# I quattro componenti fondamentali
- **Activity**: punto di ingresso per l’interazione con l’utente; rappresenta una singola schermata UI e gestisce il ciclo di vita e i flussi di navigazione dentro/fuori l’app.
- **Service**: esegue operazioni in **background** (musica, sync, download, ecc.); può essere “avviato” (startService) o “associato” (bindService) a un altro componente.
- **BroadcastReceiver**: riceve **eventi di sistema** o di altre app (batteria scarica, allarmi, schermo spento, download completati) e tipicamente delega il lavoro a servizi o altre componenti.
- **ContentProvider**: espone e gestisce dati strutturati (es. contatti) tramite URI; permette **condivisione controllata** dei dati tra app, con permessi granulari e grant temporanei.

# Intent e attivazione dei componenti
- Un **Intent** è un messaggio asincrono usato per **attivare** Activity, Service e BroadcastReceiver e per descrivere l’azione richiesta (es. “VIEW”, “SEND”) e i dati da usare.
- Intent **espliciti** indicano direttamente la classe target; gli intent **impliciti** descrivono solo l’azione e lasciano al sistema la scelta del componente compatibile tramite i relativi intent filter.
- I ContentProvider non usano intent ma vengono invocati tramite `ContentResolver` (`query()`, `insert()`, ecc.), che fa da strato di astrazione e sicurezza.

# AndroidManifest.xml
- Il **manifest** descrive all’OS la struttura dell’app: componenti (activity, service, receiver, provider), permessi, SDK min/target, feature hardware/software e librerie esterne.
- Ogni componente deve essere dichiarato con i tag `<activity>`, `<service>`, `<receiver>`, `<provider>` per poter essere avviato dal sistema.
- Gli **intent filter** dichiarati nel manifest (es. `ACTION_SEND`) rendono un componente disponibile anche ad altre app e permettono al sistema di risolvere gli intent impliciti.

# Requisiti di dispositivo e compatibilità
- I requisiti di versione Android si definiscono in Gradle tramite `minSdkVersion` e `targetSdkVersion`, che determinano su quali versioni l’app può essere installata.
- Le feature hardware/software (es. fotocamera) si dichiarano con `<uses-feature>` e si può indicare se sono **obbligatorie** (`required="true"`) o opzionali per controllare la distribuzione su Play Store.

## Risorse dell’app
- Oltre al codice, un’app include **risorse** (layout, stringhe, immagini, audio, stili) separate in `res/`, referenziate tramite ID generati (`R.drawable.logo`, `R.string.app_name`).
- Android supporta **risorse alternative** tramite directory qualificate (`values-fr/`, `layout-land/`, ecc.) per adattare UI e contenuti a lingua, orientamento e dimensioni schermo.

## Spunti didattici per il corso
- Introdurre il progetto Android mostrando **componenti** e **manifest** come “mappa” dell’app, prima ancora del codice.
- Proporre esercizi mirati:  
  - creare un’Activity con intent implicito `ACTION_SEND`,  
  - aggiungere un BroadcastReceiver per un evento di sistema,  
  - definire un `<uses-feature>` e mostrare l’effetto su dispositivi/emulatori diversi.
