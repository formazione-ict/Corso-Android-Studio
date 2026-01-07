# Naming Convention: Package e Classi

Queste convenzioni seguono gli standard ufficiali di Google per Android e le best practices della community Kotlin, garantendo codice leggibile, manutenibile e professionale.

## Convenzioni per Package Names

I nomi dei package seguono le convenzioni Java standard, essendo Kotlin compatibile con la JVM.

### Regole Generali

```kotlin
// ✅ CORRETTO - tutto minuscolo, senza underscore
com.example.myweatherapp
com.example.myweatherapp.data.repository
com.acmecorp.projectname.feature.home

// ❌ ERRATO - maiuscole, underscore, trattini
com.Example.MyWeatherApp          // No maiuscole
com.example.my_weather_app        // No underscore
com.example.my-weather-app        // No trattini
com.companyXYZ.Camera             // Sembra un nome di classe
```

### Struttura Tipica Android

```kotlin
// Struttura base dominio inverso
com.<company>.<app_name>
com.<company>.<app_name>.<feature>
com.<company>.<app_name>.<layer>.<component>

// Esempi pratici
com.example.myweatherapp                          // Root package
com.example.myweatherapp.data                     // Data layer
com.example.myweatherapp.data.local               // Local data source
com.example.myweatherapp.data.local.dao           // DAOs
com.example.myweatherapp.data.local.entity        // Database entities
com.example.myweatherapp.data.remote              // Remote data source
com.example.myweatherapp.data.remote.api          // API interfaces
com.example.myweatherapp.data.remote.dto          // Data Transfer Objects
com.example.myweatherapp.data.repository          // Repositories

com.example.myweatherapp.domain                   // Domain layer
com.example.myweatherapp.domain.model             // Domain models
com.example.myweatherapp.domain.repository        // Repository interfaces
com.example.myweatherapp.domain.usecase           // Use cases
com.example.myweatherapp.domain.usecase.weather   // Weather use cases
com.example.myweatherapp.domain.usecase.location  // Location use cases

com.example.myweatherapp.presentation             // Presentation layer
com.example.myweatherapp.presentation.ui          // UI components
com.example.myweatherapp.presentation.ui.home     // Home feature
com.example.myweatherapp.presentation.ui.details  // Details feature
com.example.myweatherapp.presentation.component   // Custom views

com.example.myweatherapp.di                       // Dependency Injection
com.example.myweatherapp.util                     // Utilities
com.example.myweatherapp.service                  // Services
com.example.myweatherapp.receiver                 // Broadcast Receivers
```

### Package Names con Domini Personali

```kotlin
// Con dominio aziendale
com.google.android.maps
com.spotify.music
com.adobe.reader

// Senza dominio (alternative valide)
io.github.username.appname        // GitHub account
dev.username.appname              // Generic dev namespace
me.username.appname               // Personal projects

// Per progetti educativi/test
it.university.department.project  // Università italiana
edu.mit.csail.project            // Università straniera
```

### Gestione Caratteri Speciali

```kotlin
// Domini con caratteri speciali o numeri
// Regola: aggiungere underscore '_' come prefisso

// Dominio: 3cookies.com
com.cookies._3                    // Il numero diventa _3

// Dominio: for-you.com (keyword riservata 'for')
com.you._for                      // Keyword diventa _for

// Dominio: my-app.io (trattino non permesso)
io.myapp                          // Rimuovi il trattino
```

## Convenzioni per Classi e Interfacce

### Android Components

Le classi che estendono componenti Android devono terminare con il nome del componente:

```kotlin
// ✅ ACTIVITY
MainActivity.kt
LoginActivity.kt
WeatherDetailActivity.kt
UserProfileActivity.kt

// ✅ FRAGMENT
HomeFragment.kt
DetailsFragment.kt
SettingsFragment.kt
SearchResultFragment.kt

// ✅ SERVICE
WeatherSyncService.kt
LocationTrackingService.kt
MusicPlayerService.kt
DownloadService.kt

// ✅ BROADCAST RECEIVER
NetworkChangeReceiver.kt
BootCompletedReceiver.kt
AlarmReceiver.kt
SmsReceiver.kt

// ✅ CONTENT PROVIDER
WeatherDataProvider.kt
ContactsProvider.kt

// ✅ ADAPTER
WeatherAdapter.kt
LocationListAdapter.kt
ForecastRecyclerAdapter.kt
PagerAdapter.kt

// ✅ VIEW HOLDER
WeatherViewHolder.kt
LocationViewHolder.kt
HeaderViewHolder.kt

// ✅ DIALOG
PermissionRationaleDialog.kt
ConfirmDeleteDialog.kt
DatePickerDialog.kt

// ✅ APPLICATION CLASS
WeatherApplication.kt
MyApplication.kt
```

#### ViewModels e State

```kotlin
// ✅ VIEW MODEL
HomeViewModel.kt
DetailsViewModel.kt
SearchViewModel.kt
SettingsViewModel.kt
SharedViewModel.kt

// ✅ UI STATE (data class o sealed class)
HomeUiState.kt
DetailsUiState.kt
WeatherUiState.kt

// ✅ UI EVENT (sealed class)
HomeUiEvent.kt
DetailsUiEvent.kt
WeatherEvent.kt

// ✅ UI EFFECT (sealed class per eventi one-time)
HomeUiEffect.kt
NavigationEffect.kt
```

### Repository Pattern

```kotlin
// ✅ REPOSITORY INTERFACE (Domain layer)
WeatherRepository.kt
LocationRepository.kt
UserRepository.kt
SettingsRepository.kt

// ✅ REPOSITORY IMPLEMENTATION (Data layer)
WeatherRepositoryImpl.kt
LocationRepositoryImpl.kt
UserRepositoryImpl.kt
SettingsRepositoryImpl.kt

// Alternativa con suffisso più specifico
WeatherRepositoryImpl.kt → WeatherDataRepository.kt
WeatherRepository.kt → WeatherDomainRepository.kt
```

### Use Cases

```kotlin
// Pattern: <Verb><Noun>UseCase
// Verbo: Get, Save, Delete, Update, Fetch, Load, Send, etc.

// ✅ USE CASES
GetCurrentWeatherUseCase.kt
GetForecastUseCase.kt
SaveLocationUseCase.kt
DeleteLocationUseCase.kt
UpdateUserPreferencesUseCase.kt
FetchWeatherDataUseCase.kt
ValidateEmailUseCase.kt
SendNotificationUseCase.kt

// Per operazioni semplici, può essere omesso "UseCase"
GetWeather.kt
SaveLocation.kt
DeleteLocation.kt
```

### Data Layer Classes

```kotlin
// ✅ ENTITIES (Database - Room)
WeatherEntity.kt
LocationEntity.kt
ForecastEntity.kt
UserEntity.kt

// ✅ DTO (Data Transfer Objects - API Response)
WeatherResponse.kt
WeatherDto.kt
ForecastResponse.kt
CityResponse.kt
ErrorResponse.kt

// ✅ DAO (Data Access Object)
WeatherDao.kt
LocationDao.kt
ForecastDao.kt

// ✅ MAPPER (conversione tra layer)
WeatherMapper.kt
LocationMapper.kt
WeatherEntityMapper.kt
WeatherDtoMapper.kt

// ✅ DATA SOURCE
WeatherLocalDataSource.kt
WeatherRemoteDataSource.kt
WeatherCacheDataSource.kt
```

### Domain Models

```kotlin
// ✅ DOMAIN MODELS (pure Kotlin classes, no Android dependencies)
Weather.kt
Location.kt
Forecast.kt
User.kt
WeatherCondition.kt
Temperature.kt

// ✅ SEALED CLASS per stati/risultati
Result.kt
NetworkResult.kt
UiState.kt
LoadingState.kt

// Esempio Result.kt
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
```

### Utility Classes

```kotlin
// ✅ UTILITIES - suffisso "Utils" o "Helper"
DateUtils.kt
NetworkUtils.kt
PermissionUtils.kt
ValidationUtils.kt
StringHelper.kt
ImageHelper.kt

// ✅ MANAGER - per gestione di risorse/stato
SharedPreferencesManager.kt
DatabaseManager.kt
NetworkManager.kt
SessionManager.kt
NotificationManager.kt

// ✅ EXTENSIONS - suffisso "Extensions" o "Ext"
ContextExtensions.kt
ViewExtensions.kt
StringExtensions.kt
FlowExtensions.kt

// Alternativa
ContextExt.kt
ViewExt.kt
```

### Custom Views

```kotlin
// ✅ CUSTOM VIEW - suffisso "View" o tipo specifico
WeatherCardView.kt
TemperatureGraphView.kt
CircularProgressView.kt
CustomButton.kt
RoundedImageView.kt

// ✅ WIDGET
WeatherWidget.kt
WeatherWidgetProvider.kt
ClockWidget.kt
```

### Dependency Injection

```kotlin
// ✅ HILT/KOIN MODULES - suffisso "Module"
AppModule.kt
NetworkModule.kt
DatabaseModule.kt
RepositoryModule.kt
UseCaseModule.kt
ViewModelModule.kt
```

### Testing

```kotlin
// ✅ TEST CLASSES - suffisso "Test"
WeatherRepositoryTest.kt
GetWeatherUseCaseTest.kt
HomeViewModelTest.kt
WeatherMapperTest.kt

// ✅ INSTRUMENTED TEST - suffisso "Test"
WeatherDaoTest.kt
HomeFragmentTest.kt
MainActivityTest.kt

// ✅ FAKE/MOCK - prefisso "Fake" o "Mock"
FakeWeatherRepository.kt
MockWeatherApi.kt
FakeLocationDataSource.kt
```

## Convenzioni per Variabili e Funzioni

### Variabili

```kotlin
// ✅ CAMEL CASE - inizio minuscolo
val userName: String
val weatherData: WeatherData
val temperatureCelsius: Double
var isLoading: Boolean

// ✅ COSTANTI - ALL_CAPS con underscore
const val MAX_RETRY_COUNT = 3
const val API_BASE_URL = "https://api.example.com/"
const val DEFAULT_TIMEOUT = 30_000L
const val DATABASE_NAME = "weather_database"

// ✅ COMPANION OBJECT CONSTANTS
companion object {
    private const val TAG = "HomeFragment"
    const val EXTRA_CITY_ID = "extra_city_id"
    const val REQUEST_CODE_LOCATION = 100
}

// ✅ BACKING PROPERTY - underscore come prefisso
private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
val uiState: StateFlow<UiState> = _uiState.asStateFlow()

private var _isLoggedIn = false
val isLoggedIn: Boolean
    get() = _isLoggedIn

// ❌ EVITARE prefissi ungheresi (m, s, etc.)
// Old style Android (deprecato in Kotlin)
private var mUserName: String  // ❌ No 'm' prefix
private val sInstance: String  // ❌ No 's' prefix

// Modern Kotlin style
private var userName: String   // ✅ Corretto
private val instance: String   // ✅ Corretto
```

### Funzioni

```kotlin
// ✅ CAMEL CASE - inizio minuscolo, verbo + sostantivo
fun getCurrentWeather()
fun saveLocation(location: Location)
fun deleteUserData()
fun updateUserProfile(user: User)
fun calculateTemperature(value: Double): Double
fun isNetworkAvailable(): Boolean

// ✅ FUNZIONI BOOLEAN - prefisso "is", "has", "can", "should"
fun isUserLoggedIn(): Boolean
fun hasLocationPermission(): Boolean
fun canAccessInternet(): Boolean
fun shouldShowOnboarding(): Boolean

// ✅ FUNZIONI SUSPEND (coroutines)
suspend fun fetchWeatherData(): Result<Weather>
suspend fun loadUserProfile(): User?

// ✅ FACTORY FUNCTIONS - stesso nome del tipo di ritorno
fun Weather(json: JSONObject): Weather
fun Location(latitude: Double, longitude: Double): Location

// ✅ EXTENSION FUNCTIONS
fun Context.showToast(message: String)
fun View.visible()
fun View.gone()
fun String.isValidEmail(): Boolean
```

### Lambda Parameters

```kotlin
// ✅ NOMI DESCRITTIVI per lambda parameters
weatherList.filter { weather -> weather.temperature > 20 }
locations.map { location -> location.name }

// ✅ USA 'it' solo se il contesto è chiaro
weatherList.filter { it.temperature > 20 }  // OK, chiaro
list.forEach { it.process() }              // OK, breve

// ❌ EVITA 'it' in lambda complesse
weatherList.filter { 
    it.temperature > 20 && it.humidity < 50 && it.city != null
}  // ❌ Poco leggibile

weatherList.filter { weather ->
    weather.temperature > 20 && 
    weather.humidity < 50 && 
    weather.city != null
}  // ✅ Più chiaro
```

## Convenzioni per File XML

### Layout Files

```xml
<!-- Pattern: <type>_<descriptor>.xml -->

<!-- ✅ ACTIVITIES -->
activity_main.xml
activity_login.xml
activity_weather_detail.xml
activity_user_profile.xml

<!-- ✅ FRAGMENTS -->
fragment_home.xml
fragment_details.xml
fragment_settings.xml
fragment_search_result.xml

<!-- ✅ DIALOG -->
dialog_confirm_delete.xml
dialog_permission_rationale.xml
dialog_date_picker.xml

<!-- ✅ LIST ITEMS -->
item_weather.xml
item_location.xml
item_forecast_hourly.xml
item_header.xml

<!-- ✅ PARTIAL LAYOUTS (include) -->
partial_toolbar.xml
partial_loading.xml
partial_error.xml
include_weather_card.xml

<!-- ✅ WIDGET -->
widget_weather_small.xml
widget_weather_large.xml

<!-- ✅ CUSTOM VIEW -->
view_temperature_graph.xml
view_weather_card.xml
```

### Drawable Resources

```xml
<!-- Pattern: <type>_<descriptor>_<state>.xml -->

<!-- ✅ ICONS - prefisso 'ic_' -->
ic_sun.xml
ic_cloud.xml
ic_rain.xml
ic_location.xml
ic_search.xml
ic_settings_24dp.xml

<!-- ✅ BACKGROUND - prefisso 'bg_' -->
bg_button.xml
bg_card.xml
bg_card_rounded.xml
bg_gradient_primary.xml

<!-- ✅ SELECTOR - prefisso 'selector_' -->
selector_button.xml
selector_item.xml
selector_tab.xml

<!-- ✅ SHAPE - prefisso 'shape_' -->
shape_circle.xml
shape_rounded_rectangle.xml
shape_line.xml

<!-- ✅ STATE-SPECIFIC -->
ic_favorite_filled.xml
ic_favorite_outline.xml
bg_button_pressed.xml
bg_button_disabled.xml
```

### Values Resources

```xml
<!-- strings.xml -->
<resources>
    <!-- ✅ APP STRINGS -->
    <string name="app_name">MyWeatherApp</string>
    <string name="app_description">Weather forecast app</string>
    
    <!-- ✅ SCREEN TITLES -->
    <string name="title_home">Home</string>
    <string name="title_settings">Settings</string>
    <string name="title_details">Weather Details</string>
    
    <!-- ✅ BUTTON LABELS - prefisso 'btn_' o 'action_' -->
    <string name="btn_save">Save</string>
    <string name="action_search">Search</string>
    <string name="action_delete">Delete</string>
    
    <!-- ✅ HINTS - prefisso 'hint_' -->
    <string name="hint_search_city">Search city…</string>
    <string name="hint_enter_email">Enter your email</string>
    
    <!-- ✅ MESSAGES - prefisso 'msg_' -->
    <string name="msg_loading">Loading weather data…</string>
    <string name="msg_success">Location saved successfully</string>
    
    <!-- ✅ ERRORS - prefisso 'error_' -->
    <string name="error_network">No internet connection</string>
    <string name="error_invalid_email">Invalid email format</string>
    <string name="error_location_not_found">City not found</string>
    
    <!-- ✅ LABELS - prefisso 'label_' -->
    <string name="label_temperature">Temperature</string>
    <string name="label_humidity">Humidity</string>
    
    <!-- ✅ FEATURE-SPECIFIC - prefisso con feature -->
    <string name="home_greeting">Welcome back!</string>
    <string name="settings_theme">Theme</string>
    <string name="details_forecast_title">7-Day Forecast</string>
</resources>

<!-- colors.xml -->
<resources>
    <!-- ✅ PALETTE COLORS -->
    <color name="primary">#6200EE</color>
    <color name="primary_dark">#3700B3</color>
    <color name="primary_light">#BB86FC</color>
    <color name="accent">#03DAC6</color>
    
    <!-- ✅ SEMANTIC COLORS -->
    <color name="background">#FFFFFF</color>
    <color name="background_dark">#121212</color>
    <color name="text_primary">#000000</color>
    <color name="text_secondary">#757575</color>
    
    <!-- ✅ STATE COLORS -->
    <color name="success">#4CAF50</color>
    <color name="error">#F44336</color>
    <color name="warning">#FF9800</color>
    <color name="info">#2196F3</color>
</resources>

<!-- dimens.xml -->
<resources>
    <!-- ✅ MARGINS -->
    <dimen name="margin_tiny">4dp</dimen>
    <dimen name="margin_small">8dp</dimen>
    <dimen name="margin_medium">16dp</dimen>
    <dimen name="margin_large">24dp</dimen>
    <dimen name="margin_xlarge">32dp</dimen>
    
    <!-- ✅ PADDING -->
    <dimen name="padding_small">8dp</dimen>
    <dimen name="padding_medium">16dp</dimen>
    
    <!-- ✅ TEXT SIZES -->
    <dimen name="text_size_small">12sp</dimen>
    <dimen name="text_size_medium">14sp</dimen>
    <dimen name="text_size_large">18sp</dimen>
    <dimen name="text_size_xlarge">24sp</dimen>
    
    <!-- ✅ COMPONENT-SPECIFIC -->
    <dimen name="card_elevation">4dp</dimen>
    <dimen name="card_corner_radius">8dp</dimen>
    <dimen name="button_height">48dp</dimen>
</resources>
```

### IDs nei Layout

```xml
<!-- Pattern: <component>_<descriptor> o <screen>_<component>_<descriptor> -->

<LinearLayout
    android:id="@+id/container_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- ✅ TEXT VIEWS - prefisso 'text_' o 'tv_' -->
    <TextView
        android:id="@+id/text_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
    
    <TextView
        android:id="@+id/text_temperature"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <!-- ✅ BUTTONS - prefisso 'button_' o 'btn_' -->
    <Button
        android:id="@+id/button_save"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <!-- ✅ IMAGE VIEWS - prefisso 'image_' o 'img_' -->
    <ImageView
        android:id="@+id/image_weather_icon"
        android:layout_width="48dp"
        android:layout_height="48dp" />

    <!-- ✅ EDIT TEXT - prefisso 'edit_' o 'input_' -->
    <EditText
        android:id="@+id/edit_city_name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <!-- ✅ RECYCLER VIEW - prefisso 'recycler_' -->
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_weather_list"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <!-- ✅ CARD VIEW - prefisso 'card_' -->
    <androidx.cardview.widget.CardView
        android:id="@+id/card_weather"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <!-- ✅ FAB - prefisso 'fab_' -->
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab_add_location"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</LinearLayout>
```

## 9.5 Riepilogo Completo

```markdown
## 📋 Quick Reference: Naming Conventions

### Packages
| Elemento | Convenzione | Esempio |
|----------|-------------|---------|
| Root | `com.company.app` | `com.example.myweatherapp` |
| Feature | `com.company.app.feature` | `com.example.myweatherapp.home` |
| Data | `com.company.app.data` | `com.example.myweatherapp.data.repository` |
| Domain | `com.company.app.domain` | `com.example.myweatherapp.domain.usecase` |
| Presentation | `com.company.app.presentation` | `com.example.myweatherapp.presentation.ui` |

### Classes
| Tipo | Suffisso | Esempio |
|------|----------|---------|
| Activity | `Activity` | `MainActivity`, `LoginActivity` |
| Fragment | `Fragment` | `HomeFragment`, `DetailsFragment` |
| ViewModel | `ViewModel` | `HomeViewModel`, `DetailsViewModel` |
| Adapter | `Adapter` | `WeatherAdapter`, `LocationAdapter` |
| ViewHolder | `ViewHolder` | `WeatherViewHolder` |
| Service | `Service` | `WeatherSyncService` |
| Receiver | `Receiver` | `NetworkChangeReceiver` |
| Repository | `Repository` | `WeatherRepository` |
| Repository Impl | `RepositoryImpl` | `WeatherRepositoryImpl` |
| Use Case | `UseCase` | `GetWeatherUseCase` |
| Entity | `Entity` | `WeatherEntity` |
| DTO | `Response` o `Dto` | `WeatherResponse`, `WeatherDto` |
| Mapper | `Mapper` | `WeatherMapper` |

### Variables & Functions
| Elemento | Convenzione | Esempio |
|----------|-------------|---------|
| Variable | `camelCase` | `userName`, `weatherData` |
| Constant | `ALL_CAPS` | `MAX_RETRY_COUNT`, `API_BASE_URL` |
| Function | `camelCase` (verb) | `getCurrentWeather()`, `saveLocation()` |
| Boolean | `is/has/can` | `isLoading`, `hasPermission` |
| Backing Property | `_camelCase` | `_uiState`, `_isLoggedIn` |

### XML Resources
| Tipo | Pattern | Esempio |
|------|---------|---------|
| Activity Layout | `activity_<name>` | `activity_main.xml` |
| Fragment Layout | `fragment_<name>` | `fragment_home.xml` |
| List Item | `item_<name>` | `item_weather.xml` |
| Icon | `ic_<name>` | `ic_sun.xml` |
| Background | `bg_<name>` | `bg_card.xml` |
| Selector | `selector_<name>` | `selector_button.xml` |
| View ID | `<component>_<desc>` | `text_title`, `button_save` |
| String | `<context>_<desc>` | `error_network`, `btn_save` |
| Color | Semantic name | `primary`, `text_secondary` |
| Dimen | `<type>_<size>` | `margin_small`, `text_size_large` |
```

***
