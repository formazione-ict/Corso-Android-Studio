# Documentazione tecnica di applicazioni Android:
- Strutturare la documentazione tecnica di un'app Android: descrizione, funzionalità, componenti
- Uso di Javadoc/KDoc e convenzioni di commento del codice
- Documentare flussi di navigazione, interazioni e componenti nel Manifest
- Creazione di README, changelog e schede di versione
- Preparazione alla pubblicazione su repository e app store

Questa lezione fornisce una guida completa per documentare professionalmente un'applicazione Android, coprendo tutti gli aspetti dalla struttura del codice alla pubblicazione su Google Play Store. Gli esempi pratici mostrano convenzioni moderne e best practices utilizzate nell'industria.

## Introduzione

La documentazione tecnica è fondamentale per garantire la manutenibilità, la scalabilità e la collaborazione efficace nello sviluppo di applicazioni Android. Una documentazione ben strutturata facilita l'onboarding di nuovi sviluppatori, riduce il debito tecnico e prepara l'applicazione per la pubblicazione su repository pubblici e app store.

## 1. Strutturare la Documentazione Tecnica

### 1.1 Descrizione dell'Applicazione

La descrizione deve fornire una panoramica chiara e concisa dell'app, includendo:

**Esempio pratico:**

```markdown
# MyWeatherApp

## Descrizione
MyWeatherApp è un'applicazione Android nativa che fornisce previsioni meteorologiche 
in tempo reale per città italiane ed europee. L'app integra API REST per recuperare 
dati meteo aggiornati e utilizza Material Design 3 per un'interfaccia moderna e intuitiva.

## Scopo
Fornire agli utenti informazioni meteorologiche accurate con un'interfaccia semplice 
e personalizzabile, permettendo il salvataggio di località preferite e notifiche per 
allerte meteo.

## Target
- Utenti Android (minSDK 24 - Android 7.0)
- Fascia d'età: 18-65 anni
- Utenti italiani ed europei interessati a previsioni dettagliate
```

### 1.2 Funzionalità Principali

Elencare le funzionalità core con descrizioni dettagliate:

```markdown
## Funzionalità

### 🌤️ Previsioni in Tempo Reale
- Temperatura attuale, minima e massima
- Condizioni meteo (soleggiato, nuvoloso, pioggia, neve)
- Umidità, pressione atmosferica e velocità del vento
- Previsioni orarie (24h) e giornaliere (7 giorni)

### 📍 Gestione Località
- Ricerca città tramite nome o coordinate GPS
- Salvataggio fino a 10 località preferite
- Geolocalizzazione automatica
- Rilevamento automatico della posizione corrente

### 🔔 Notifiche e Allerte
- Notifiche push per cambiamenti meteo significativi
- Allerte meteo (temporali, neve, vento forte)
- Notifiche personalizzabili per orari specifici

### 🎨 Personalizzazione
- Temi chiaro/scuro con cambio automatico
- Scelta unità di misura (Celsius/Fahrenheit, km/h, mph)
- Widget per la schermata home
```

### 1.3 Componenti Architetturali

Documentare l'architettura seguendo i pattern moderni Android:

```markdown
## Architettura

L'applicazione segue l'architettura **MVVM (Model-View-ViewModel)** raccomandata 
da Google, con separazione in moduli:

### Struttura dei Moduli

```
/app
├── /data                    # Layer di dati
│   ├── /repository          # Repository pattern
│   ├── /datasource          # Remote e Local datasources
│   │   ├── /remote          # API client (Retrofit)
│   │   └── /local           # Database (Room)
│   └── /model               # Data models
│
├── /domain                  # Business logic
│   ├── /usecase             # Use cases
│   └── /model               # Domain models
│
├── /presentation            # UI Layer
│   ├── /ui
│   │   ├── /home            # HomeFragment + HomeViewModel
│   │   ├── /details         # DetailsFragment + DetailsViewModel
│   │   └── /settings        # SettingsFragment + SettingsViewModel
│   ├── /adapter             # RecyclerView Adapters
│   └── /widget              # Custom views e widget
│
└── /di                      # Dependency Injection (Hilt/Koin)
```

### Componenti Principali

#### Data Layer
- **WeatherRepository**: Gestisce il recupero dati da API e cache locale
- **WeatherApi**: Interface Retrofit per chiamate REST
- **WeatherDatabase**: Database Room per persistenza offline
- **SharedPreferencesManager**: Gestione preferenze utente

#### Domain Layer
- **GetWeatherUseCase**: Recupera previsioni meteo per località
- **SaveLocationUseCase**: Salva località preferite
- **GetAlertsUseCase**: Gestisce logica delle allerte meteo

#### Presentation Layer
- **HomeViewModel**: Gestisce stato UI della schermata principale
- **WeatherAdapter**: Adapter per RecyclerView delle previsioni
- **LocationService**: Service per geolocalizzazione
```

### 1.4 Tecnologie e Librerie

```markdown
## Stack Tecnologico

### Linguaggi
- **Kotlin** 1.9.22 (linguaggio principale)
- **XML** (layout UI)

### Framework e Librerie

#### Networking
- **Retrofit** 2.9.0 - Client HTTP REST
- **OkHttp** 4.11.0 - Interceptor e logging
- **Gson** 2.10.1 - Parsing JSON

#### Database
- **Room** 2.6.0 - Persistenza locale
- **SQLite** - Database engine

#### UI/UX
- **Material Design Components** 1.10.0
- **Glide** 4.16.0 - Image loading
- **Lottie** 6.1.0 - Animazioni JSON

#### Dependency Injection
- **Hilt** 2.48 - DI framework

#### Testing
- **JUnit** 4.13.2 - Unit testing
- **Espresso** 3.5.1 - UI testing
- **Mockito** 5.5.0 - Mocking

#### Altri
- **Coroutines** 1.7.3 - Programmazione asincrona
- **LiveData/Flow** - Gestione dati reattivi
- **Navigation Component** 2.7.5 - Navigazione tra schermate
```

## 2. Javadoc e KDoc: Documentazione del Codice

### 2.1 Convenzioni KDoc per Kotlin

KDoc è il sistema di documentazione per Kotlin, equivalente a Javadoc per Java.

**Sintassi base KDoc:**

```kotlin
/**
 * Rappresenta una previsione meteorologica per una specifica località.
 *
 * Questa classe contiene tutti i dati necessari per visualizzare le condizioni
 * meteo attuali e le previsioni future. I dati vengono recuperati dall'API
 * OpenWeatherMap e memorizzati nel database locale per l'accesso offline.
 *
 * @property cityName Nome della città (es. "Milano")
 * @property temperature Temperatura attuale in gradi Celsius
 * @property condition Condizione meteo corrente (SUNNY, CLOUDY, RAINY, SNOWY)
 * @property humidity Percentuale di umidità (0-100)
 * @property windSpeed Velocità del vento in km/h
 * @property timestamp Timestamp UTC dell'ultimo aggiornamento
 *
 * @constructor Crea una nuova istanza di WeatherData
 *
 * @throws IllegalArgumentException se la temperatura è fuori dal range valido
 *
 * @sample com.example.weather.samples.weatherDataSample
 *
 * @see WeatherRepository
 * @see WeatherApi
 *
 * @author Mario Rossi
 * @since 1.0.0
 */
data class WeatherData(
    val cityName: String,
    val temperature: Double,
    val condition: WeatherCondition,
    val humidity: Int,
    val windSpeed: Double,
    val timestamp: Long
) {
    init {
        require(temperature in -90.0..60.0) {
            "La temperatura deve essere compresa tra -90°C e 60°C"
        }
        require(humidity in 0..100) {
            "L'umidità deve essere compresa tra 0 e 100"
        }
    }
}
```

### 2.2 Documentazione di Funzioni

```kotlin
/**
 * Recupera le previsioni meteorologiche per una località specifica.
 *
 * Questa funzione sospesa recupera i dati meteo dall'API remota. Se la chiamata
 * fallisce o il dispositivo è offline, restituisce i dati dalla cache locale.
 * La funzione implementa una strategia di retry con backoff esponenziale.
 *
 * @param cityName Nome della città da cercare (non case-sensitive)
 * @param forceRefresh Se true, ignora la cache e forza il refresh dai server
 * @param units Unità di misura desiderate (METRIC per °C, IMPERIAL per °F)
 *
 * @return [Result] contenente [WeatherData] in caso di successo,
 *         o [WeatherError] in caso di fallimento
 *
 * @throws NetworkException se non c'è connessione Internet e la cache è vuota
 * @throws ApiException se l'API restituisce un errore (es. città non trovata)
 *
 * @see WeatherRepository.getCachedWeather
 * @see WeatherApi.fetchWeather
 */
suspend fun getWeatherForCity(
    cityName: String,
    forceRefresh: Boolean = false,
    units: TemperatureUnit = TemperatureUnit.METRIC
): Result<WeatherData> {
    // Implementazione
}
```

### 2.3 Documentazione di Classi ViewModel

```kotlin
/**
 * ViewModel per la schermata principale dell'applicazione.
 *
 * Gestisce lo stato UI, le interazioni utente e la logica di business per
 * la visualizzazione delle previsioni meteo. Utilizza StateFlow per esporre
 * lo stato UI in modo reattivo ai composable/fragment.
 *
 * Il ViewModel segue il pattern UDF (Unidirectional Data Flow):
 * 1. L'UI invia eventi tramite [onEvent]
 * 2. Il ViewModel processa gli eventi e aggiorna [uiState]
 * 3. L'UI osserva [uiState] e si aggiorna di conseguenza
 *
 * @property weatherRepository Repository per operazioni sui dati meteo
 * @property locationService Service per ottenere la posizione corrente
 * @property savedStateHandle Handle per salvare/ripristinare lo stato
 *
 * @constructor Iniettato da Hilt con le dipendenze necessarie
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationService: LocationService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    /**
     * Stato UI corrente della schermata principale.
     * 
     * Espone un flusso immutabile dello stato che l'UI può osservare.
     * Inizializzato con [HomeUiState.Loading] e aggiornato in risposta
     * agli eventi utente e ai risultati delle operazioni asincrone.
     */
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    // Implementazione...
}
```

### 2.4 Convenzioni Javadoc per Java

Per codice Java legacy o interoperabilità:

```java
/**
 * Adapter per visualizzare una lista di previsioni meteo in RecyclerView.
 * 
 * <p>Questo adapter supporta diverse tipologie di view holder per mostrare:
 * <ul>
 *   <li>Previsioni orarie (vista compatta)</li>
 *   <li>Previsioni giornaliere (vista espansa)</li>
 *   <li>Header con informazioni città</li>
 * </ul>
 * 
 * <p><b>Utilizzo:</b>
 * <pre>{@code
 * WeatherAdapter adapter = new WeatherAdapter(weatherList, clickListener);
 * recyclerView.setAdapter(adapter);
 * adapter.updateData(newWeatherList);
 * }</pre>
 * 
 * @author Mario Rossi
 * @version 1.2.0
 * @since 1.0.0
 * @see RecyclerView.Adapter
 * @see WeatherViewHolder
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    /**
     * Lista delle previsioni meteo da visualizzare.
     * Non deve essere null ma può essere vuota.
     */
    private List<WeatherData> weatherList;
    
    /**
     * Listener per gestire i click sugli elementi della lista.
     */
    private OnWeatherItemClickListener clickListener;

    /**
     * Costruisce un nuovo WeatherAdapter.
     *
     * @param weatherList Lista iniziale di dati meteo (non null)
     * @param clickListener Listener per eventi di click (può essere null)
     * @throws NullPointerException se weatherList è null
     */
    public WeatherAdapter(
        @NonNull List<WeatherData> weatherList,
        @Nullable OnWeatherItemClickListener clickListener
    ) {
        this.weatherList = Objects.requireNonNull(weatherList);
        this.clickListener = clickListener;
    }

    /**
     * Aggiorna la lista di previsioni e notifica i cambiamenti.
     * 
     * <p>Utilizza {@link DiffUtil} per calcolare le differenze ed eseguire
     * animazioni fluide. Operazione eseguita su thread background.
     *
     * @param newWeatherList Nuova lista di previsioni (non null)
     * @see DiffUtil
     */
    public void updateData(@NonNull List<WeatherData> newWeatherList) {
        // Implementazione
    }
}
```

## 3. Documentare Flussi di Navigazione

### 3.1 Navigation Graph Documentation

Documentare i flussi di navigazione dell'app è essenziale per comprendere l'architettura UI.

```markdown
## Flusso di Navigazione

L'applicazione utilizza il **Navigation Component** di Jetpack per gestire
la navigazione tra schermate.

### Navigation Graph

```xml
<!-- res/navigation/nav_graph.xml -->
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_graph"
    app:startDestination="@id/splashFragment">

    <!-- Splash Screen: Schermata iniziale con logo animato -->
    <fragment
        android:id="@+id/splashFragment"
        android:name="com.example.weather.ui.splash.SplashFragment"
        android:label="Splash">
        <action
            android:id="@+id/action_splash_to_home"
            app:destination="@id/homeFragment"
            app:popUpTo="@id/splashFragment"
            app:popUpToInclusive="true"
            app:enterAnim="@anim/fade_in"
            app:exitAnim="@anim/fade_out" />
    </fragment>

    <!-- Home Screen: Schermata principale con previsioni -->
    <fragment
        android:id="@+id/homeFragment"
        android:name="com.example.weather.ui.home.HomeFragment"
        android:label="Meteo">
        
        <!-- Navigazione verso i dettagli della città -->
        <action
            android:id="@+id/action_home_to_details"
            app:destination="@id/detailsFragment"
            app:enterAnim="@anim/slide_in_right"
            app:exitAnim="@anim/slide_out_left"
            app:popEnterAnim="@anim/slide_in_left"
            app:popExitAnim="@anim/slide_out_right" />
        
        <!-- Navigazione verso le impostazioni -->
        <action
            android:id="@+id/action_home_to_settings"
            app:destination="@id/settingsFragment" />
        
        <!-- Navigazione verso ricerca località -->
        <action
            android:id="@+id/action_home_to_search"
            app:destination="@id/searchFragment" />
    </fragment>

    <!-- Details Screen: Dettagli previsioni città -->
    <fragment
        android:id="@+id/detailsFragment"
        android:name="com.example.weather.ui.details.DetailsFragment"
        android:label="Dettagli">
        
        <!-- Argomenti richiesti -->
        <argument
            android:name="cityId"
            app:argType="long"
            android:defaultValue="-1L" />
        <argument
            android:name="cityName"
            app:argType="string" />
    </fragment>

    <!-- Search Screen: Ricerca e aggiunta località -->
    <fragment
        android:id="@+id/searchFragment"
        android:name="com.example.weather.ui.search.SearchFragment"
        android:label="Cerca Città" />

    <!-- Settings Screen: Impostazioni applicazione -->
    <fragment
        android:id="@+id/settingsFragment"
        android:name="com.example.weather.ui.settings.SettingsFragment"
        android:label="Impostazioni" />

</navigation>
```

### Mappa Visuale dei Flussi

```
┌─────────────┐
│   Splash    │ (2 sec)
└──────┬──────┘
       │ automatic
       ▼
┌─────────────┐
│    Home     │◄──────────┐
│  (Start)    │           │
└──────┬──────┘           │
       │                  │
       ├─────────────┐    │
       │             │    │
       ▼             ▼    │
┌─────────────┐ ┌─────────────┐
│   Details   │ │   Search    │
│             │ │             │
└─────────────┘ └──────┬──────┘
                       │
                       └──────┘

       ┌─────────────┐
       │  Settings   │
       │   (Bottom   │
       │   NavView)  │
       └─────────────┘
```

### Descrizione Flussi

#### Flusso Principale (Happy Path)
1. **Splash → Home**: Avvio automatico dopo 2 secondi
2. **Home → Details**: Click su città salvata mostra dettagli completi
3. **Home → Search**: FAB "+" per aggiungere nuova località
4. **Search → Home**: Località selezionata viene salvata e mostrata

#### Flussi Secondari
- **Home → Settings**: Menu hamburger o bottom nav
- **Settings → Home**: Back button
- **Details → Home**: Back button (animazione slide)
```

### 3.2 Documentazione Interazioni Utente

```markdown
## Interazioni Utente

### Gesture Supportate

#### Home Screen
- **Swipe Down**: Pull-to-refresh per aggiornare previsioni
- **Swipe Left/Right su card**: Elimina località dai preferiti
- **Click su card**: Naviga ai dettagli città
- **Long Press su card**: Mostra dialog opzioni (rinomina, elimina, sposta)
- **FAB Click**: Apre schermata ricerca

#### Details Screen
- **Scroll verticale**: Visualizza previsioni orarie e settimanali
- **Click su grafico**: Mostra tooltip con valori dettagliati
- **Swipe tra tab**: Cambia tra "Oggi", "Domani", "Settimana"

#### Search Screen
- **Type in SearchView**: Filtra risultati in tempo reale
- **Click risultato**: Aggiunge località e torna a Home
- **Click GPS icon**: Usa posizione corrente
```

## 4. Documentare il Manifest

### 4.1 AndroidManifest.xml Commentato

Il file Manifest dichiara componenti, permessi e configurazioni dell'app.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.example.myweatherapp">

    <!-- ========== PERMESSI ========== -->
    
    <!-- Permesso per accedere a Internet (richiesto per API meteo) -->
    <uses-permission android:name="android.permission.INTERNET" />
    
    <!-- Permesso per verificare lo stato della connessione -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    
    <!-- Permessi per geolocalizzazione (opzionali) -->
    <!-- COARSE: posizione approssimativa (città) -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <!-- FINE: posizione precisa (GPS) -->
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    
    <!-- Permesso per ricevere notifiche push (Android 13+) -->
    <uses-permission android:name="android.permission.POST_NOTIFICATIONS"
        android:minSdkVersion="33" />
    
    <!-- Permesso per vibrare con le notifiche -->
    <uses-permission android:name="android.permission.VIBRATE" />

    <!-- ========== FEATURES RICHIESTE ========== -->
    
    <!-- GPS opzionale: l'app funziona anche senza -->
    <uses-feature
        android:name="android.hardware.location.gps"
        android:required="false" />
    
    <!-- Touchscreen richiesto -->
    <uses-feature
        android:name="android.hardware.touchscreen"
        android:required="true" />

    <application
        android:name=".WeatherApplication"
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.MyWeatherApp"
        android:usesCleartextTraffic="false"
        tools:targetApi="31">

        <!-- ========== MAIN ACTIVITY ========== -->
        
        <!-- Activity principale: entry point dell'applicazione -->
        <activity
            android:name=".ui.MainActivity"
            android:exported="true"
            android:launchMode="singleTop"
            android:screenOrientation="portrait"
            android:windowSoftInputMode="adjustResize">
            
            <!-- Intent filter per rendere l'app lanciabile -->
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
            
            <!-- Deep link per aprire città specifica -->
            <!-- Esempio: myweather://city/milano -->
            <intent-filter android:autoVerify="true">
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data
                    android:scheme="myweather"
                    android:host="city"
                    android:pathPrefix="/" />
            </intent-filter>
        </activity>

        <!-- ========== SERVICES ========== -->
        
        <!-- Service per aggiornamento periodico meteo in background -->
        <service
            android:name=".service.WeatherSyncService"
            android:enabled="true"
            android:exported="false"
            android:permission="android.permission.BIND_JOB_SERVICE" />
        
        <!-- Firebase Cloud Messaging per notifiche push -->
        <service
            android:name=".service.MyFirebaseMessagingService"
            android:exported="false">
            <intent-filter>
                <action android:name="com.google.firebase.MESSAGING_EVENT" />
            </intent-filter>
        </service>

        <!-- ========== BROADCAST RECEIVERS ========== -->
        
        <!-- Receiver per gestire cambio di connettività -->
        <receiver
            android:name=".receiver.ConnectivityReceiver"
            android:enabled="true"
            android:exported="false">
            <intent-filter>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
            </intent-filter>
        </receiver>

        <!-- ========== CONTENT PROVIDERS ========== -->
        
        <!-- FileProvider per condivisione sicura di screenshot meteo -->
        <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="${applicationId}.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths" />
        </provider>

        <!-- ========== METADATA ========== -->
        
        <!-- API Key per servizio meteo (da nascondere in produzione) -->
        <meta-data
            android:name="com.example.weather.API_KEY"
            android:value="${WEATHER_API_KEY}" />
        
        <!-- Configurazione Google Maps (se usata) -->
        <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="${MAPS_API_KEY}" />

    </application>

</manifest>
```

### 4.2 Documentazione Permessi Runtime

```markdown
## Gestione Permessi

### Permessi Pericolosi (Runtime)

L'app richiede i seguenti permessi runtime con rationale chiare:

#### ACCESS_FINE_LOCATION
- **Quando**: Al primo avvio o quando l'utente clicca "Usa posizione corrente"
- **Rationale**: "Per fornirti previsioni meteo accurate per la tua posizione attuale"
- **Fallback**: L'utente può cercare manualmente la città
- **Codice**:

```kotlin
/**
 * Richiede il permesso di geolocalizzazione con rationale educativa.
 * 
 * Se il permesso è già stato negato, mostra un dialog esplicativo
 * prima di richiedere nuovamente. Se negato permanentemente, apre
 * le impostazioni dell'app.
 */
private fun requestLocationPermission() {
    when {
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED -> {
            // Permesso già concesso
            getCurrentLocation()
        }
        
        shouldShowRequestPermissionRationale(
            Manifest.permission.ACCESS_FINE_LOCATION
        ) -> {
            // Mostra rationale prima di richiedere
            showLocationRationaleDialog()
        }
        
        else -> {
            // Richiedi permesso
            locationPermissionLauncher.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }
}
```

#### POST_NOTIFICATIONS (Android 13+)
- **Quando**: Dopo che l'utente ha salvato la prima località
- **Rationale**: "Ricevi allerte meteo importanti per le tue località preferite"
- **Fallback**: L'app funziona normalmente senza notifiche
```

## 5. README.md Strutturato

### 5.1 Template README Completo

```markdown
# 🌤️ MyWeatherApp

![Version](https://img.shields.io/badge/version-1.2.0-blue)
![API](https://img.shields.io/badge/API-24%2B-brightgreen)
![License](https://img.shields.io/badge/license-MIT-orange)
![Build](https://img.shields.io/badge/build-passing-success)

> Applicazione Android per previsioni meteorologiche in tempo reale con interfaccia Material Design 3

[Screenshots](#screenshots) • [Funzionalità](#funzionalità) • [Installazione](#installazione) • [Architettura](#architettura) • [Contribuire](#contribuire)

---

## 📸 Screenshots

<p align="center">
  <img src="screenshots/home_light.png" width="250" />
  <img src="screenshots/details_dark.png" width="250" />
  <img src="screenshots/search.png" width="250" />
</p>

## ✨ Funzionalità

- ✅ Previsioni in tempo reale per città italiane ed europee
- ✅ Previsioni orarie (24h) e settimanali (7 giorni)
- ✅ Geolocalizzazione automatica
- ✅ Salvataggio località preferite (fino a 10)
- ✅ Notifiche push per allerte meteo
- ✅ Temi chiaro/scuro con switch automatico
- ✅ Widget per home screen
- ✅ Modalità offline con cache locale
- ✅ Supporto tablet e split-screen
- ✅ Animazioni fluide con Material Motion

## 📋 Requisiti

- **minSDK**: 24 (Android 7.0 Nougat)
- **targetSDK**: 34 (Android 14)
- **Kotlin**: 1.9.22
- **Gradle**: 8.2.0
- **Android Studio**: Hedgehog | 2023.1.1 o superiore

## 🚀 Installazione

### Clone del Repository

```bash
git clone https://github.com/username/myweatherapp.git
cd myweatherapp
```

### Configurazione API Key

1. Registrati su [OpenWeatherMap](https://openweathermap.org/api)
2. Ottieni una API key gratuita
3. Crea il file `local.properties` nella root del progetto:

```properties
sdk.dir=/path/to/Android/sdk
WEATHER_API_KEY=your_api_key_here
```

4. (Opzionale) Per Google Maps, aggiungi:

```properties
MAPS_API_KEY=your_maps_key_here
```

### Build e Run

```bash
# Debug build
./gradlew assembleDebug

# Release build (richiede keystore configurato)
./gradlew assembleRelease

# Esegui test
./gradlew test

# Esegui test UI
./gradlew connectedAndroidTest
```

## 🏗️ Architettura

L'app segue **Clean Architecture** con pattern **MVVM**:

```
┌─────────────────────────────────────┐
│         Presentation Layer          │
│   (Activities, Fragments, ViewModels)│
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│          Domain Layer               │
│   (Use Cases, Domain Models)        │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│           Data Layer                │
│  (Repository, DataSources, API, DB) │
└─────────────────────────────────────┘
```

### Tecnologie Chiave

| Componente | Tecnologia |
|-----------|------------|
| **UI** | Material Design 3, ViewBinding |
| **Networking** | Retrofit, OkHttp, Gson |
| **Database** | Room, SQLite |
| **Async** | Kotlin Coroutines, Flow |
| **DI** | Hilt |
| **Navigation** | Navigation Component |
| **Immagini** | Glide |
| **Testing** | JUnit, Mockito, Espresso |

## 📁 Struttura del Progetto

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/myweatherapp/
│   │   │   ├── data/              # Repository, datasources, models
│   │   │   ├── domain/            # Use cases, business logic
│   │   │   ├── presentation/      # UI, ViewModels, Adapters
│   │   │   ├── di/                # Dependency Injection modules
│   │   │   ├── util/              # Helper classes, extensions
│   │   │   └── WeatherApplication.kt
│   │   ├── res/                   # Resources (layouts, drawables, strings)
│   │   └── AndroidManifest.xml
│   ├── test/                      # Unit tests
│   └── androidTest/               # Instrumented tests
├── build.gradle.kts
└── proguard-rules.pro
```

## 🧪 Testing

### Unit Tests

```bash
./gradlew test
```

- **Coverage attuale**: 78%
- Test per ViewModel, Repository, Use Cases

### UI Tests

```bash
./gradlew connectedAndroidTest
```

- Espresso per test UI
- Test su emulatore o device fisico

## 🌐 API

L'app utilizza [OpenWeatherMap API](https://openweathermap.org/api):

- **Current Weather**: Dati meteo attuali
- **5 Day Forecast**: Previsioni ogni 3 ore per 5 giorni
- **One Call API**: Previsioni dettagliate incluse allerte

## 🤝 Contribuire

I contributi sono benvenuti! Segui questi step:

1. Fork del repository
2. Crea un branch per la feature (`git checkout -b feature/AmazingFeature`)
3. Commit delle modifiche (`git commit -m 'Add AmazingFeature'`)
4. Push al branch (`git push origin feature/AmazingFeature`)
5. Apri una Pull Request

### Linee Guida

- Segui [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Aggiungi test per nuove funzionalità
- Aggiorna la documentazione
- Mantieni il code coverage sopra 70%

## 📄 Licenza

Questo progetto è rilasciato sotto licenza MIT. Vedi il file [LICENSE](LICENSE) per dettagli.

```
MIT License

Copyright (c) 2026 Mario Rossi

Permission is hereby granted, free of charge, to any person obtaining a copy...
```

## 👨‍💻 Autore

**Mario Rossi**
- GitHub: [@mariorossi](https://github.com/mariorossi)
- LinkedIn: [Mario Rossi](https://linkedin.com/in/mariorossi)
- Email: mario.rossi@example.com

## 🙏 Ringraziamenti

- [OpenWeatherMap](https://openweathermap.org/) per le API meteo
- [Material Design Icons](https://fonts.google.com/icons) per le icone
- [Lottie](https://lottiefiles.com/) per le animazioni

## 📝 Changelog

Vedi [CHANGELOG.md](CHANGELOG.md) per la cronologia delle versioni.

---

<p align="center">Fatto con ❤️ in Italia</p>
```

## 6. Changelog e Versioning

### 6.1 CHANGELOG.md

Il changelog documenta tutte le modifiche tra versioni seguendo [Keep a Changelog](https://keepachangelog.com/):

```markdown
# Changelog

Tutte le modifiche significative a questo progetto saranno documentate in questo file.

Il formato è basato su [Keep a Changelog](https://keepachangelog.com/it/1.0.0/),
e questo progetto aderisce al [Semantic Versioning](https://semver.org/lang/it/).

## [Unreleased]

### Da Aggiungere
- Integrazione radar meteo interattivo
- Supporto per Apple Watch
- Condivisione previsioni sui social

## [1.2.0] - 2026-01-05

### ✨ Aggiunto
- Widget per schermata home con aggiornamento automatico
- Grafici interattivi per temperatura e precipitazioni
- Supporto per notifiche push con Firebase Cloud Messaging
- Animazioni Lottie per condizioni meteo
- Modalità tablet con layout a due pannelli
- Export dati meteo in formato CSV

### 🔧 Modificato
- Migrazione da Glide 4.15.0 a 4.16.0
- Ottimizzazione consumo batteria per aggiornamenti in background
- Migliorata precisione geolocalizzazione con Fused Location Provider
- Ridotto size APK del 15% con R8 optimization
- Aggiornato Material Design 3 components a 1.10.0

### 🐛 Corretto
- Fix crash su Android 14 con permessi notifiche
- Risolto bug di cache che mostrava dati obsoleti
- Corretta animazione di transizione tra tema chiaro/scuro
- Fix memory leak in WeatherAdapter
- Risolto problema di sincronizzazione con database Room

### ⚠️ Deprecato
- `getWeatherLegacy()` sarà rimosso nella v2.0.0 (usare `getWeatherSuspend()`)

### 🗑️ Rimosso
- Supporto per Android 6.0 (API 23) e inferiori
- Libreria Apache Commons obsoleta

### 🔐 Sicurezza
- Implementato certificate pinning per API calls
- Aggiunta crittografia database con SQLCipher
- Fix vulnerabilità XSS in WebView delle policy

## [1.1.0] - 2025-11-20

### ✨ Aggiunto
- Tema scuro con switch automatico
- Salvataggio fino a 10 località preferite
- Previsioni settimanali dettagliate
- Ricerca città con autocompletamento

### 🔧 Modificato
- Redesign UI con Material Design 3
- Passaggio da MVP a MVVM architecture
- Migrazione database SQLite a Room

### 🐛 Corretto
- Fix crash all'avvio su dispositivi con Android 8.0
- Risolto problema di refresh infinito

## [1.0.0] - 2025-10-01

### ✨ Release Iniziale
- Previsioni meteo in tempo reale
- Geolocalizzazione automatica
- Supporto per città italiane
- UI Material Design base
- Modalità offline con cache

[Unreleased]: https://github.com/username/myweatherapp/compare/v1.2.0...HEAD
[1.2.0]: https://github.com/username/myweatherapp/compare/v1.1.0...v1.2.0
[1.1.0]: https://github.com/username/myweatherapp/compare/v1.0.0...v1.1.0
[1.0.0]: https://github.com/username/myweatherapp/releases/tag/v1.0.0
```

### 6.2 Semantic Versioning

```markdown
## Versioning Strategy

Questo progetto segue [Semantic Versioning 2.0.0](https://semver.org/):

```
MAJOR.MINOR.PATCH-PRERELEASE+BUILD

Esempio: 1.2.3-beta.1+20260105
```

### Incremento Versioni

| Tipo | Quando | Esempio |
|------|--------|---------|
| **MAJOR** | Breaking changes incompatibili | 1.0.0 → 2.0.0 |
| **MINOR** | Nuove funzionalità retrocompatibili | 1.1.0 → 1.2.0 |
| **PATCH** | Bug fixes retrocompatibili | 1.1.1 → 1.1.2 |
| **PRERELEASE** | Versioni alpha/beta/rc | 1.2.0-beta.1 |
| **BUILD** | Build metadata | 1.2.0+20260105 |

### Configurazione in build.gradle.kts

```kotlin
android {
    namespace = "com.example.myweatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myweatherapp"
        minSdk = 24
        targetSdk = 34
        
        // versionCode: incrementa ad ogni build per Play Store
        versionCode = 12  // Intero progressivo
        
        // versionName: versione semantica user-facing
        versionName = "1.2.0"
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
```

### Automazione con Git Tags

```bash
# Creare una release
git tag -a v1.2.0 -m "Release version 1.2.0"
git push origin v1.2.0

# Listare tutte le release
git tag -l "v*"

# Checkout di una versione specifica
git checkout tags/v1.2.0
```
```

## 7. Preparazione alla Pubblicazione

### 7.1 Checklist Pre-Pubblicazione

```markdown
## Checklist Pubblicazione Google Play Store

### 📋 Pre-Build

- [ ] **Version bump**: Aggiornato `versionCode` e `versionName`
- [ ] **Changelog**: Aggiornato `CHANGELOG.md` con nuove modifiche
- [ ] **Dependencies**: Verificate e aggiornate dipendenze obsolete
- [ ] **TODO**: Risolti tutti i TODO critici nel codice
- [ ] **Warnings**: Eliminati warning di compilazione
- [ ] **Lint**: Eseguito `./gradlew lint` senza errori critici
- [ ] **Tests**: Tutti i test passano (unit + UI)
  ```bash
  ./gradlew test
  ./gradlew connectedAndroidTest
  ```

### 🔐 Sicurezza

- [ ] **API Keys**: Rimosse chiavi hardcoded, spostate in `local.properties`
- [ ] **Logs**: Disabilitati log di debug in produzione
- [ ] **ProGuard/R8**: Configurato per obfuscation
- [ ] **Permissions**: Rimossi permessi non necessari
- [ ] **SSL Pinning**: Implementato certificate pinning
- [ ] **Database Encryption**: Database criptato (se contiene dati sensibili)

### 📱 Build Release

- [ ] **Keystore**: Generato keystore di release
  ```bash
  keytool -genkeypair -v -keystore release-keystore.jks \
    -alias my-key-alias -keyalg RSA -keysize 2048 \
    -validity 10000
  ```
- [ ] **Signing Config**: Configurato in `build.gradle.kts`
- [ ] **Build Type**: Release configurato correttamente
- [ ] **ProGuard Rules**: File `proguard-rules.pro` ottimizzato
- [ ] **Build AAB**: Generato Android App Bundle
  ```bash
  ./gradlew bundleRelease
  ```
- [ ] **Testing**: Testato file AAB/APK su dispositivi reali

### 📄 Documentazione

- [ ] **README.md**: Aggiornato e completo
- [ ] **LICENSE**: File licenza presente
- [ ] **Privacy Policy**: Creata e hostata online
- [ ] **Terms of Service**: Termini d'uso disponibili
- [ ] **GDPR Compliance**: Conformità al regolamento europeo

### 🎨 Assets Google Play

- [ ] **App Icon**: 512x512px, formato PNG 32-bit
- [ ] **Feature Graphic**: 1024x500px per header store
- [ ] **Screenshots**: Minimo 2, idealmente 4-8 per dispositivo
  - Phone: 16:9 o 9:16
  - Tablet 7": 16:9 o 9:16
  - Tablet 10": 16:9 o 9:16
- [ ] **Promo Video**: Link YouTube (opzionale ma consigliato)
- [ ] **Banner TV**: 1280x720px (se supporti Android TV)

### ✍️ Store Listing

- [ ] **Titolo**: Max 50 caratteri, descrittivo e SEO-friendly
- [ ] **Breve descrizione**: Max 80 caratteri, chiara e accattivante
- [ ] **Descrizione completa**: Max 4000 caratteri, formattata
  - Introduzione accattivante
  - Elenco puntato funzionalità
  - Call-to-action finale
- [ ] **Categoria**: Selezionata categoria appropriata
- [ ] **Tags**: Aggiunti fino a 5 tag pertinenti
- [ ] **Content Rating**: Completato questionario di rating

### 🧪 Testing

- [ ] **Internal Testing**: Deploy su track interno
- [ ] **Closed Testing**: Beta test con gruppo chiuso
- [ ] **Open Testing**: Beta pubblica (opzionale)
- [ ] **Feedback**: Raccolto e implementato feedback tester

### 🚀 Go Live

- [ ] **Production Release**: Promozione a produzione
- [ ] **Staged Rollout**: Iniziato con 5-10% utenti
- [ ] **Monitoring**: Configurato Firebase Crashlytics
- [ ] **Analytics**: Implementato Google Analytics for Firebase
- [ ] **Release Notes**: Pubblicate note di rilascio
```

### 7.2 Configurazione Release Build

```kotlin
// build.gradle.kts (app module)

android {
    // ...
    
    signingConfigs {
        create("release") {
            // Non committare queste informazioni!
            // Usa environment variables o keystore.properties
            storeFile = file(System.getenv("KEYSTORE_PATH") 
                ?: "release-keystore.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            isDebuggable = true
            isMinifyEnabled = false
        }
        
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            
            // Configurazioni specifiche release
            buildConfigField("String", "API_BASE_URL", 
                "\"https://api.openweathermap.org/data/2.5/\"")
            buildConfigField("boolean", "ENABLE_LOGGING", "false")
        }
    }
    
    // Split APK per ottimizzazione dimensioni
    splits {
        abi {
            isEnable = true
            reset()
            include("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
            isUniversalApk = true
        }
        density {
            isEnable = true
            reset()
            include("mdpi", "hdpi", "xhdpi", "xxhdpi", "xxxhdpi")
        }
    }
}
```

### 7.3 ProGuard Rules

```pro
# proguard-rules.pro

# Mantieni attributi per stack trace leggibili
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Retrofit
-keepattributes Signature
-keepattributes Exceptions
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Gson
-keep class com.google.gson.** { *; }
-keep class com.example.myweatherapp.data.model.** { *; }

# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# Rimuovi log in produzione
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
```

### 7.4 Store Listing Template

```markdown
## Google Play Store Listing

### 📱 Titolo (50 caratteri max)
```
MyWeatherApp - Previsioni Meteo
```

### 📝 Breve Descrizione (80 caratteri max)
```
Previsioni meteo accurate in tempo reale con interfaccia elegante e intuitiva
```

### 📖 Descrizione Completa (4000 caratteri max)

```
🌤️ Scopri MyWeatherApp: la tua app meteo affidabile e moderna!

Ottieni previsioni meteorologiche precise e aggiornate per qualsiasi città italiana ed europea. Con un'interfaccia elegante basata su Material Design 3, MyWeatherApp rende semplice e piacevole controllare il meteo.

✨ FUNZIONALITÀ PRINCIPALI

🌡️ Previsioni Dettagliate
-  Temperatura attuale, minima e massima
-  Condizioni meteo con icone animate
-  Umidità, pressione e velocità del vento
-  Previsioni orarie per le prossime 24 ore
-  Previsioni settimanali per i prossimi 7 giorni

📍 Località Multiple
-  Salva fino a 10 città preferite
-  Geolocalizzazione automatica
-  Ricerca rapida con autocompletamento
-  Swipe per eliminare località

🔔 Allerte e Notifiche
-  Notifiche push per cambiamenti improvvisi
-  Allerte meteo per temporali e neve
-  Personalizza orari delle notifiche

🎨 Design Moderno
-  Interfaccia Material Design 3
-  Tema chiaro e scuro automatico
-  Animazioni fluide e intuitive
-  Widget per schermata home

📊 Dati Accurati
-  API OpenWeatherMap affidabili
-  Aggiornamenti in tempo reale
-  Modalità offline con cache intelligente
-  Grafici interattivi temperatura/precipitazioni

🌐 PERCHÉ SCEGLIERE MYWEATHERAPP?

✓ Gratuita al 100% senza acquisti in-app
✓ Nessuna pubblicità invasiva
✓ Rispetta la tua privacy (no tracciamento)
✓ Leggera e veloce
✓ Supporto completo per tablet
✓ Aggiornamenti frequenti

📱 REQUISITI
-  Android 7.0 o superiore
-  Connessione Internet (per aggiornamenti)
-  Posizione GPS (opzionale)

🔐 PRIVACY
I tuoi dati meteo sono memorizzati solo sul tuo dispositivo. Non condividiamo né vendiamo informazioni personali.

💬 SUPPORTO
Hai domande o suggerimenti? Contattaci:
📧 support@myweatherapp.com

⭐ Se ti piace MyWeatherApp, lascia una recensione!
Il tuo feedback ci aiuta a migliorare continuamente l'app.

Scarica ora e non farti più sorprendere dal maltempo! ☔☀️
```

### 🏷️ Categorie e Tag
- **Categoria Principale**: Meteo
- **Categoria Secondaria**: Strumenti
- **Tags**: meteo, previsioni, tempo, clima, temperatura
```

### 7.5 Release Notes Template

```markdown
## Release Notes v1.2.0

### 🎉 Novità

**Widget Home Screen**
Aggiungi il nostro nuovo widget alla schermata home per vedere il meteo a colpo d'occhio, senza nemmeno aprire l'app!

**Grafici Interattivi**
Tocca i nuovi grafici di temperatura e precipitazioni per visualizzare dati dettagliati ora per ora.

**Notifiche Push**
Non perderti mai più un'allerta meteo importante. Ricevi notifiche intelligenti quando il tempo cambia.

### ⚡ Miglioramenti

- Ridotto il consumo di batteria del 30%
- App più leggera: dimensioni ridotte del 15%
- Caricamento dati più veloce
- Migliore precisione della geolocalizzazione

### 🐛 Correzioni

- Risolto crash su Android 14
- Fix problema cache dati obsoleti
- Corrette animazioni tema scuro

Grazie per usare MyWeatherApp! ❤️
```

***

## Risorse Aggiuntive

### 📚 Documentazione Ufficiale
- [Android Developers Documentation](https://developer.android.com/docs)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Material Design Guidelines](https://m3.material.io/)

### 🛠️ Tools Utili
- [Android Studio](https://developer.android.com/studio)
- [Dokka](https://github.com/Kotlin/dokka) - Generazione documentazione Kotlin
- [Keep a Changelog](https://keepachangelog.com/)
- [Semantic Versioning](https://semver.org/)

### 📖 Guide
- [Google Play Publishing Guide](https://developer.android.com/distribute/best-practices/launch)
- [App Bundle Guide](https://developer.android.com/guide/app-bundle)
- [Android Security Best Practices](https://developer.android.com/topic/security/best-practices)

***
