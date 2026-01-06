# Guida all'Architettura delle App Android

## Introduzione

Un'architettura ben definita è fondamentale per creare app Android scalabili, gestibili e adattabili a diversi dispositivi (smartphone, tablet, pieghevoli, ChromeOS, dispositivi auto e XR).

L'architettura fornisce una base solida per applicazioni di alta qualità che possono crescere ed evolversi nel tempo.

## Composizione di un'App Android

Un'applicazione Android moderna è composta da:
- **Componenti dell'app**: servizi, provider di contenuti, ricevitori di trasmissione dichiarati nel manifest
- **Architettura a singola Activity**: una sola `Activity` che funge da contenitore per le schermate implementate con Fragment o Jetpack Compose
- **UI adattiva**: gestione di diversi fattori di forma e orientamenti senza presupporre configurazioni specifiche

### Sfide dello Sviluppo Android

- **Modifiche alla configurazione**: rotazione dispositivo, apertura/chiusura pieghevoli richiedono ricomposizione UI
- **Risorse limitate**: il sistema può terminare processi per liberare memoria
- **Avvio non sequenziale**: componenti possono essere avviati singolarmente e distrutti in qualsiasi momento

## Principi Architettonici Fondamentali

<img width="1725" height="1005" alt="Diagramma di una tipica architettura dell'app." src="https://github.com/user-attachments/assets/dd019e91-71bf-499b-a2f5-a4038c4ae66e" />


### 1. Separazione delle Responsabilità (Separation of Concerns)

Non scrivere tutto il codice in `Activity` o `Fragment`. Il loro ruolo principale è ospitare la UI, mentre il sistema Android ne controlla il ciclo di vita distruggendoli e ricreandoli frequentemente.

Separa la logica di business, la gestione dati e la UI in componenti distinti.

### 2. UI Guidata da Modelli di Dati

Basa la UI su modelli di dati persistenti, indipendenti dal ciclo di vita dei componenti UI. I vantaggi includono:
- Gli utenti non perdono dati quando il sistema distrugge l'app
- L'app funziona anche con connessione intermittente o assente
- Architettura più robusta e testabile

### 3. Single Source of Truth (SSOT)

Ogni tipo di dati deve avere un'unica fonte attendibile che possiede e modifica i dati. L'SSOT espone dati immutabili e fornisce funzioni per modificarli. Vantaggi:
- Centralizza tutte le modifiche in un unico punto
- Protegge i dati da manomissioni
- Facilita il tracciamento e debugging

### 4. Flusso di Dati Unidirezionale (UDF)

Lo **stato** scorre in una direzione (dal componente principale al secondario), mentre gli **eventi** fluiscono nella direzione opposta. 

In Android: i dati scorrono dalle origini dati alla UI, mentre gli eventi utente (pressione pulsanti) risalgono fino all'SSOT.

## Architettura Consigliata a Livelli

### Livello UI (Presentation Layer)

<img width="1697" height="1503" alt="Il ruolo del livello UI nell'architettura dell'app." src="https://github.com/user-attachments/assets/ebd3378c-e7fa-426b-9c15-b5b94e19b011" />

Visualizza i dati dell'applicazione sullo schermo. Comprende:
- **Elementi UI**: creati con Jetpack Compose per layout adattabili
- **Contenitori di stato**: `ViewModel` che contengono dati, li espongono alla UI e gestiscono la logica
- **UI adattive**: utilizzano `currentWindowAdaptiveInfo()` e componenti come `NavigationSuiteScaffold` per adattarsi a diverse dimensioni schermo.

### Livello Dati (Data Layer)

<img width="1693" height="1511" alt="Il ruolo del data layer nell'architettura dell'app" src="https://github.com/user-attachments/assets/382599f4-bc20-4fa4-99c5-36587a0582ae" />


Contiene la **logica di business** che definisce come l'app crea, archivia e modifica i dati. È costituito da:
- **Repository**: una classe per ogni tipo di dati (es. `MoviesRepository`, `PaymentsRepository`)
- **Origini dati**: ciascuna gestisce una singola origine (file, rete, database locale)

Responsabilità dei Repository:
- Esporre dati al resto dell'app
- Centralizzare modifiche ai dati
- Risolvere conflitti tra origini multiple
- Astrarre le origini dati
- Contenere logica di business

### Livello Dominio (Domain Layer) - Opzionale

<img width="1687" height="979" alt="Il ruolo del livello del dominio nell'architettura dell'app." src="https://github.com/user-attachments/assets/5ab78e09-eff3-48ac-ab63-0885d33ea04f" />


Livello facoltativo tra UI e dati per incapsulare logica di business complessa o riutilizzabile.

Contiene **casi d'uso** (use cases) o **interattori**, ognuno responsabile di una singola funzionalità (es. `GetTimeZoneUseCase`).

## Gestione delle Dipendenze

Utilizza pattern di progettazione per gestire le dipendenze:
- **Dependency Injection (DI)**: le classi definiscono dipendenze senza costruirle; fornite a runtime
- **Service Locator**: registro dove le classi ottengono dipendenze

Questi pattern permettono scalabilità, evitano duplicazioni di codice e facilitano il passaggio tra implementazioni di test e produzione.

## Best Practice Generali
1. **Non archiviare dati nei componenti dell'app**: Activity, Service e BroadcastReceiver non devono essere origini dati
2. **Riduci dipendenze da classi Android**: solo componenti dell'app devono usare API SDK Android (`Context`, `Toast`)
3. **Definisci confini chiari**: non distribuire codice (es. caricamento rete) su più classi
4. **Limita esposizione interna**: non creare scorciatoie che espongono dettagli implementativi
5. **Concentrati sull'unicità**: usa librerie Jetpack per boilerplate, dedica tempo a ciò che rende unica la tua app
6. **Layout canonici**: utilizza pattern di design adattivi per ottimizzare l'esperienza su più fattori di forma
7. **Preserva stato UI**: mantieni lo stato durante modifiche configurazione (rotazione, ridimensionamento)
8. **Componenti riutilizzabili**: crea componenti UI componibili per design adattivo
9. **Testabilità**: progetta ogni parte testabile in isolamento con API ben definite
10. **Policy di concorrenza**: ogni tipo deve gestire il proprio threading ed essere main-thread-safe
11. **Persistenza dati**: conserva il massimo dei dati per supportare modalità offline

## Vantaggi di una Buona Architettura
- **Manutenibilità**: migliora qualità e robustezza dell'app
- **Scalabilità**: più team possono contribuire con conflitti minimi
- **Onboarding efficiente**: nuovi membri si integrano rapidamente grazie alla coerenza
- **Testabilità**: tipi più semplici sono più facili da testare
- **Debugging metodico**: bug esaminabili con processi ben definiti
- **Esperienza utente**: app più stabile e team più produttivo

## Tecnologie dell'Architettura Moderna
- Architettura adattiva e multi-livello
- Flusso di dati unidirezionale (UDF) in tutti i livelli
- Contenitori di stato (`ViewModel`) per gestire complessità UI
- Coroutine e Flow per operazioni asincrone
- Dependency Injection
