# Struttura Cartelle e File: Esempi Pratici

Questa struttura completa con esempi pratici fornisce una guida chiara per organizzare un progetto Android professionale, seguendo le best practices moderne e facilitando la collaborazione del team, il testing e la manutenibilità del codice.

## Struttura Progetto Completa (Vista Generale)

Ecco la struttura completa di un progetto Android professionale con architettura MVVM e Clean Architecture:

```
MyWeatherApp/
├── .github/                           # GitHub workflows e templates
│   ├── workflows/
│   │   ├── ci.yml                     # Continuous Integration
│   │   └── release.yml                # Automated releases
│   ├── ISSUE_TEMPLATE/
│   │   ├── bug_report.md
│   │   └── feature_request.md
│   └── pull_request_template.md
│
├── .idea/                             # Android Studio config (gitignored)
│
├── app/                               # Modulo principale dell'app
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/myweatherapp/
│   │   │   │   ├── data/              # DATA LAYER
│   │   │   │   │   ├── local/         # Database locale
│   │   │   │   │   │   ├── dao/
│   │   │   │   │   │   │   ├── WeatherDao.kt
│   │   │   │   │   │   │   └── LocationDao.kt
│   │   │   │   │   │   ├── entity/
│   │   │   │   │   │   │   ├── WeatherEntity.kt
│   │   │   │   │   │   │   └── LocationEntity.kt
│   │   │   │   │   │   └── WeatherDatabase.kt
│   │   │   │   │   │
│   │   │   │   │   ├── remote/        # API e networking
│   │   │   │   │   │   ├── api/
│   │   │   │   │   │   │   └── WeatherApi.kt
│   │   │   │   │   │   ├── dto/       # Data Transfer Objects
│   │   │   │   │   │   │   ├── WeatherResponse.kt
│   │   │   │   │   │   │   ├── ForecastResponse.kt
│   │   │   │   │   │   │   └── CityResponse.kt
│   │   │   │   │   │   └── interceptor/
│   │   │   │   │   │       ├── AuthInterceptor.kt
│   │   │   │   │   │       └── LoggingInterceptor.kt
│   │   │   │   │   │
│   │   │   │   │   ├── repository/    # Implementazioni repository
│   │   │   │   │   │   ├── WeatherRepositoryImpl.kt
│   │   │   │   │   │   └── LocationRepositoryImpl.kt
│   │   │   │   │   │
│   │   │   │   │   ├── mapper/        # Conversioni DTO <-> Domain
│   │   │   │   │   │   ├── WeatherMapper.kt
│   │   │   │   │   │   └── LocationMapper.kt
│   │   │   │   │   │
│   │   │   │   │   └── datasource/    # DataSource abstractions
│   │   │   │   │       ├── WeatherLocalDataSource.kt
│   │   │   │   │       └── WeatherRemoteDataSource.kt
│   │   │   │   │
│   │   │   │   ├── domain/            # DOMAIN LAYER (Business Logic)
│   │   │   │   │   ├── model/         # Domain models (entità pure)
│   │   │   │   │   │   ├── Weather.kt
│   │   │   │   │   │   ├── Location.kt
│   │   │   │   │   │   ├── Forecast.kt
│   │   │   │   │   │   └── WeatherCondition.kt
│   │   │   │   │   │
│   │   │   │   │   ├── repository/    # Repository interfaces
│   │   │   │   │   │   ├── WeatherRepository.kt
│   │   │   │   │   │   └── LocationRepository.kt
│   │   │   │   │   │
│   │   │   │   │   └── usecase/       # Use cases (business logic)
│   │   │   │   │       ├── weather/
│   │   │   │   │       │   ├── GetCurrentWeatherUseCase.kt
│   │   │   │   │       │   ├── GetForecastUseCase.kt
│   │   │   │   │       │   └── RefreshWeatherUseCase.kt
│   │   │   │   │       ├── location/
│   │   │   │   │       │   ├── GetSavedLocationsUseCase.kt
│   │   │   │   │       │   ├── SaveLocationUseCase.kt
│   │   │   │   │       │   ├── DeleteLocationUseCase.kt
│   │   │   │   │       │   └── GetCurrentLocationUseCase.kt
│   │   │   │   │       └── alert/
│   │   │   │   │           └── GetWeatherAlertsUseCase.kt
│   │   │   │   │
│   │   │   │   ├── presentation/      # PRESENTATION LAYER (UI)
│   │   │   │   │   ├── ui/
│   │   │   │   │   │   ├── splash/
│   │   │   │   │   │   │   ├── SplashFragment.kt
│   │   │   │   │   │   │   └── splash_fragment.xml
│   │   │   │   │   │   │
│   │   │   │   │   │   ├── home/
│   │   │   │   │   │   │   ├── HomeFragment.kt
│   │   │   │   │   │   │   ├── HomeViewModel.kt
│   │   │   │   │   │   │   ├── HomeUiState.kt
│   │   │   │   │   │   │   ├── HomeUiEvent.kt
│   │   │   │   │   │   │   ├── adapter/
│   │   │   │   │   │   │   │   ├── WeatherAdapter.kt
│   │   │   │   │   │   │   │   └── LocationAdapter.kt
│   │   │   │   │   │   │   └── fragment_home.xml
│   │   │   │   │   │   │
│   │   │   │   │   │   ├── details/
│   │   │   │   │   │   │   ├── DetailsFragment.kt
│   │   │   │   │   │   │   ├── DetailsViewModel.kt
│   │   │   │   │   │   │   ├── DetailsUiState.kt
│   │   │   │   │   │   │   ├── adapter/
│   │   │   │   │   │   │   │   └── ForecastAdapter.kt
│   │   │   │   │   │   │   └── fragment_details.xml
│   │   │   │   │   │   │
│   │   │   │   │   │   ├── search/
│   │   │   │   │   │   │   ├── SearchFragment.kt
│   │   │   │   │   │   │   ├── SearchViewModel.kt
│   │   │   │   │   │   │   ├── SearchUiState.kt
│   │   │   │   │   │   │   └── fragment_search.xml
│   │   │   │   │   │   │
│   │   │   │   │   │   ├── settings/
│   │   │   │   │   │   │   ├── SettingsFragment.kt
│   │   │   │   │   │   │   ├── SettingsViewModel.kt
│   │   │   │   │   │   │   └── fragment_settings.xml
│   │   │   │   │   │   │
│   │   │   │   │   │   └── MainActivity.kt
│   │   │   │   │   │
│   │   │   │   │   ├── component/     # Custom views e widget
│   │   │   │   │   │   ├── WeatherCardView.kt
│   │   │   │   │   │   ├── TemperatureGraphView.kt
│   │   │   │   │   │   └── widget/
│   │   │   │   │   │       ├── WeatherWidget.kt
│   │   │   │   │   │       └── WeatherWidgetProvider.kt
│   │   │   │   │   │
│   │   │   │   │   └── util/         # UI utilities
│   │   │   │   │       ├── ViewExtensions.kt
│   │   │   │   │       ├── BindingAdapters.kt
│   │   │   │   │       └── UiText.kt  # Sealed class per stringhe
│   │   │   │   │
│   │   │   │   ├── di/                # DEPENDENCY INJECTION
│   │   │   │   │   ├── AppModule.kt
│   │   │   │   │   ├── DatabaseModule.kt
│   │   │   │   │   ├── NetworkModule.kt
│   │   │   │   │   ├── RepositoryModule.kt
│   │   │   │   │   └── UseCaseModule.kt
│   │   │   │   │
│   │   │   │   ├── service/           # SERVICES
│   │   │   │   │   ├── WeatherSyncService.kt
│   │   │   │   │   ├── LocationService.kt
│   │   │   │   │   └── fcm/
│   │   │   │   │       └── MyFirebaseMessagingService.kt
│   │   │   │   │
│   │   │   │   ├── receiver/          # BROADCAST RECEIVERS
│   │   │   │   │   ├── ConnectivityReceiver.kt
│   │   │   │   │   └── BootReceiver.kt
│   │   │   │   │
│   │   │   │   ├── util/              # UTILITIES GENERALI
│   │   │   │   │   ├── Constants.kt
│   │   │   │   │   ├── Result.kt      # Sealed class per risultati
│   │   │   │   │   ├── NetworkUtils.kt
│   │   │   │   │   ├── DateUtils.kt
│   │   │   │   │   ├── PermissionUtils.kt
│   │   │   │   │   └── extension/
│   │   │   │   │       ├── ContextExtensions.kt
│   │   │   │   │       ├── FlowExtensions.kt
│   │   │   │   │       └── StringExtensions.kt
│   │   │   │   │
│   │   │   │   └── WeatherApplication.kt  # Application class
│   │   │   │
│   │   │   ├── res/                   # RESOURCES
│   │   │   │   ├── anim/              # Animazioni XML
│   │   │   │   │   ├── fade_in.xml
│   │   │   │   │   ├── fade_out.xml
│   │   │   │   │   ├── slide_in_left.xml
│   │   │   │   │   ├── slide_in_right.xml
│   │   │   │   │   ├── slide_out_left.xml
│   │   │   │   │   └── slide_out_right.xml
│   │   │   │   │
│   │   │   │   ├── drawable/          # Drawables e vettori
│   │   │   │   │   ├── ic_launcher_background.xml
│   │   │   │   │   ├── ic_sun.xml
│   │   │   │   │   ├── ic_cloud.xml
│   │   │   │   │   ├── ic_rain.xml
│   │   │   │   │   ├── ic_snow.xml
│   │   │   │   │   ├── ic_location.xml
│   │   │   │   │   ├── ic_search.xml
│   │   │   │   │   ├── ic_settings.xml
│   │   │   │   │   ├── bg_card.xml
│   │   │   │   │   ├── bg_button.xml
│   │   │   │   │   └── selector_item.xml
│   │   │   │   │
│   │   │   │   ├── drawable-hdpi/     # Immagini bitmap (multi-density)
│   │   │   │   ├── drawable-mdpi/
│   │   │   │   ├── drawable-xhdpi/
│   │   │   │   ├── drawable-xxhdpi/
│   │   │   │   └── drawable-xxxhdpi/
│   │   │   │   │
│   │   │   │   ├── font/              # Custom fonts
│   │   │   │   │   ├── roboto_regular.ttf
│   │   │   │   │   ├── roboto_bold.ttf
│   │   │   │   │   └── fonts.xml
│   │   │   │   │
│   │   │   │   ├── layout/            # Layout XML
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── fragment_home.xml
│   │   │   │   │   ├── fragment_details.xml
│   │   │   │   │   ├── fragment_search.xml
│   │   │   │   │   ├── fragment_settings.xml
│   │   │   │   │   ├── item_weather.xml
│   │   │   │   │   ├── item_location.xml
│   │   │   │   │   ├── item_forecast.xml
│   │   │   │   │   ├── dialog_permission_rationale.xml
│   │   │   │   │   └── widget_weather.xml
│   │   │   │   │
│   │   │   │   ├── menu/              # Menu XML
│   │   │   │   │   ├── menu_main.xml
│   │   │   │   │   └── menu_bottom_nav.xml
│   │   │   │   │
│   │   │   │   ├── mipmap-hdpi/       # App icons (multi-density)
│   │   │   │   ├── mipmap-mdpi/
│   │   │   │   ├── mipmap-xhdpi/
│   │   │   │   ├── mipmap-xxhdpi/
│   │   │   │   ├── mipmap-xxxhdpi/
│   │   │   │   │
│   │   │   │   ├── navigation/        # Navigation graphs
│   │   │   │   │   └── nav_graph.xml
│   │   │   │   │
│   │   │   │   ├── raw/               # Raw assets (JSON animations, ecc.)
│   │   │   │   │   ├── sunny_animation.json
│   │   │   │   │   ├── rainy_animation.json
│   │   │   │   │   └── loading_animation.json
│   │   │   │   │
│   │   │   │   ├── values/            # Valori di default
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   ├── dimens.xml
│   │   │   │   │   ├── styles.xml
│   │   │   │   │   ├── themes.xml
│   │   │   │   │   └── attrs.xml      # Custom attributes
│   │   │   │   │
│   │   │   │   ├── values-night/      # Tema scuro
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   │
│   │   │   │   ├── values-it/         # Localizzazione italiana
│   │   │   │   │   └── strings.xml
│   │   │   │   │
│   │   │   │   ├── values-es/         # Localizzazione spagnola
│   │   │   │   │   └── strings.xml
│   │   │   │   │
│   │   │   │   └── xml/               # XML configs
│   │   │   │       ├── backup_rules.xml
│   │   │   │       ├── data_extraction_rules.xml
│   │   │   │       ├── file_paths.xml
│   │   │   │       ├── network_security_config.xml
│   │   │   │       └── shortcuts.xml
│   │   │   │
│   │   │   └── AndroidManifest.xml
│   │   │
│   │   ├── test/                      # UNIT TESTS
│   │   │   └── java/com/example/myweatherapp/
│   │   │       ├── data/
│   │   │       │   ├── repository/
│   │   │       │   │   └── WeatherRepositoryImplTest.kt
│   │   │       │   └── mapper/
│   │   │       │       └── WeatherMapperTest.kt
│   │   │       ├── domain/
│   │   │       │   └── usecase/
│   │   │       │       ├── GetCurrentWeatherUseCaseTest.kt
│   │   │       │       └── GetForecastUseCaseTest.kt
│   │   │       ├── presentation/
│   │   │       │   └── viewmodel/
│   │   │       │       ├── HomeViewModelTest.kt
│   │   │       │       └── DetailsViewModelTest.kt
│   │   │       └── util/
│   │   │           └── TestUtils.kt
│   │   │
│   │   └── androidTest/               # INSTRUMENTED TESTS
│   │       └── java/com/example/myweatherapp/
│   │           ├── data/
│   │           │   └── local/
│   │           │       └── WeatherDaoTest.kt
│   │           ├── ui/
│   │           │   ├── HomeFragmentTest.kt
│   │           │   └── DetailsFragmentTest.kt
│   │           └── ExampleInstrumentedTest.kt
│   │
│   ├── build.gradle.kts               # Build configuration modulo app
│   └── proguard-rules.pro             # ProGuard/R8 rules
│
├── buildSrc/                          # Gradle build logic (opzionale)
│   ├── src/main/kotlin/
│   │   ├── Dependencies.kt            # Centralized dependencies
│   │   ├── Versions.kt                # Version management
│   │   └── BuildPlugins.kt
│   └── build.gradle.kts
│
├── gradle/                            # Gradle wrapper
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
│
├── screenshots/                       # Screenshots per README
│   ├── home_light.png
│   ├── home_dark.png
│   ├── details.png
│   └── search.png
│
├── docs/                              # Documentazione extra
│   ├── API.md
│   ├── ARCHITECTURE.md
│   └── CONTRIBUTING.md
│
├── .gitignore                         # Git ignore rules
├── .gitattributes                     # Git attributes
├── build.gradle.kts                   # Root build file
├── settings.gradle.kts                # Settings & modules
├── gradle.properties                  # Gradle properties
├── local.properties                   # Local config (gitignored)
├── gradlew                            # Gradle wrapper script (Unix)
├── gradlew.bat                        # Gradle wrapper script (Windows)
├── README.md                          # Main documentation
├── CHANGELOG.md                       # Version history
├── LICENSE                            # License file
└── CONTRIBUTING.md                    # Contribution guidelines
```

## 8.2 Struttura Multi-Modulo Avanzata

Per progetti più grandi, è consigliato suddividere l'app in moduli separati:

```
MyWeatherApp/
├── app/                               # Modulo app principale
│   ├── src/main/
│   │   ├── java/com/example/myweatherapp/
│   │   │   ├── MainActivity.kt
│   │   │   └── WeatherApplication.kt
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
│
├── core/                              # Modulo core utilities
│   ├── common/                        # Utilities comuni
│   │   ├── src/main/java/com/example/core/common/
│   │   │   ├── Result.kt
│   │   │   ├── Constants.kt
│   │   │   └── extension/
│   │   │       ├── ContextExtensions.kt
│   │   │       └── FlowExtensions.kt
│   │   └── build.gradle.kts
│   │
│   ├── network/                       # Networking module
│   │   ├── src/main/java/com/example/core/network/
│   │   │   ├── NetworkModule.kt
│   │   │   ├── RetrofitBuilder.kt
│   │   │   └── interceptor/
│   │   │       ├── AuthInterceptor.kt
│   │   │       └── LoggingInterceptor.kt
│   │   └── build.gradle.kts
│   │
│   ├── database/                      # Database module
│   │   ├── src/main/java/com/example/core/database/
│   │   │   ├── WeatherDatabase.kt
│   │   │   ├── DatabaseModule.kt
│   │   │   └── converter/
│   │   │       └── Converters.kt
│   │   └── build.gradle.kts
│   │
│   └── ui/                            # UI components condivisi
│       ├── src/main/java/com/example/core/ui/
│       │   ├── component/
│       │   │   ├── LoadingView.kt
│       │   │   ├── ErrorView.kt
│       │   │   └── EmptyStateView.kt
│       │   └── theme/
│       │       ├── Color.kt
│       │       ├── Theme.kt
│       │       └── Type.kt
│       ├── res/
│       │   ├── values/
│       │   │   ├── colors.xml
│       │   │   ├── themes.xml
│       │   │   └── dimens.xml
│       │   └── drawable/
│       └── build.gradle.kts
│
├── feature/                           # Feature modules
│   ├── home/
│   │   ├── src/main/java/com/example/feature/home/
│   │   │   ├── data/
│   │   │   │   ├── repository/
│   │   │   │   │   └── HomeRepositoryImpl.kt
│   │   │   │   └── remote/
│   │   │   │       └── HomeApi.kt
│   │   │   ├── domain/
│   │   │   │   ├── model/
│   │   │   │   │   └── Weather.kt
│   │   │   │   ├── repository/
│   │   │   │   │   └── HomeRepository.kt
│   │   │   │   └── usecase/
│   │   │   │       └── GetWeatherUseCase.kt
│   │   │   └── presentation/
│   │   │       ├── HomeFragment.kt
│   │   │       ├── HomeViewModel.kt
│   │   │       └── HomeUiState.kt
│   │   └── build.gradle.kts
│   │
│   ├── details/
│   │   ├── src/main/java/com/example/feature/details/
│   │   │   ├── data/
│   │   │   ├── domain/
│   │   │   └── presentation/
│   │   └── build.gradle.kts
│   │
│   ├── search/
│   │   ├── src/main/java/com/example/feature/search/
│   │   │   ├── data/
│   │   │   ├── domain/
│   │   │   └── presentation/
│   │   └── build.gradle.kts
│   │
│   └── settings/
│       ├── src/main/java/com/example/feature/settings/
│       │   └── presentation/
│       └── build.gradle.kts
│
├── data/                              # Shared data module
│   ├── weather/
│   │   ├── src/main/java/com/example/data/weather/
│   │   │   ├── repository/
│   │   │   │   └── WeatherRepositoryImpl.kt
│   │   │   ├── local/
│   │   │   │   ├── dao/
│   │   │   │   └── entity/
│   │   │   ├── remote/
│   │   │   │   ├── api/
│   │   │   │   └── dto/
│   │   │   └── mapper/
│   │   └── build.gradle.kts
│   │
│   └── location/
│       ├── src/main/java/com/example/data/location/
│       └── build.gradle.kts
│
├── domain/                            # Shared domain module
│   ├── src/main/java/com/example/domain/
│   │   ├── model/
│   │   │   ├── Weather.kt
│   │   │   ├── Location.kt
│   │   │   └── Forecast.kt
│   │   └── repository/
│   │       ├── WeatherRepository.kt
│   │       └── LocationRepository.kt
│   └── build.gradle.kts
│
├── buildSrc/
│   ├── src/main/kotlin/
│   │   ├── Dependencies.kt
│   │   ├── Versions.kt
│   │   └── Modules.kt
│   └── build.gradle.kts
│
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

## File Chiave con Contenuto di Esempio

### settings.gradle.kts (Multi-modulo)

```kotlin
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyWeatherApp"

// Modulo principale
include(":app")

// Core modules
include(":core:common")
include(":core:network")
include(":core:database")
include(":core:ui")

// Feature modules
include(":feature:home")
include(":feature:details")
include(":feature:search")
include(":feature:settings")

// Data modules
include(":data:weather")
include(":data:location")

// Domain module
include(":domain")
```

### buildSrc/src/main/kotlin/Dependencies.kt

```kotlin
object Versions {
    const val kotlin = "1.9.22"
    const val gradle = "8.2.0"
    
    const val compileSdk = 34
    const val minSdk = 24
    const val targetSdk = 34
    
    const val versionCode = 12
    const val versionName = "1.2.0"
    
    // AndroidX
    const val coreKtx = "1.12.0"
    const val appCompat = "1.6.1"
    const val material = "1.10.0"
    const val constraintLayout = "2.1.4"
    
    // Architecture Components
    const val lifecycle = "2.6.2"
    const val navigation = "2.7.5"
    const val room = "2.6.0"
    
    // Dependency Injection
    const val hilt = "2.48"
    
    // Networking
    const val retrofit = "2.9.0"
    const val okHttp = "4.11.0"
    const val gson = "2.10.1"
    
    // Coroutines
    const val coroutines = "1.7.3"
    
    // Image Loading
    const val glide = "4.16.0"
    
    // Testing
    const val junit = "4.13.2"
    const val junitExt = "1.1.5"
    const val espresso = "3.5.1"
    const val mockito = "5.5.0"
}

object Dependencies {
    // Kotlin
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    
    // AndroidX Core
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    
    // Lifecycle
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    
    // Navigation
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    
    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    
    // Hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    
    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    
    // Gson
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    
    // Coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    
    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    
    // Testing
    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}

object Modules {
    // Core
    const val coreCommon = ":core:common"
    const val coreNetwork = ":core:network"
    const val coreDatabase = ":core:database"
    const val coreUi = ":core:ui"
    
    // Feature
    const val featureHome = ":feature:home"
    const val featureDetails = ":feature:details"
    const val featureSearch = ":feature:search"
    const val featureSettings = ":feature:settings"
    
    // Data
    const val dataWeather = ":data:weather"
    const val dataLocation = ":data:location"
    
    // Domain
    const val domain = ":domain"
}
```

### app/build.gradle.kts

```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.myweatherapp"
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.example.myweatherapp"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
        // API Keys da local.properties
        buildConfigField("String", "WEATHER_API_KEY", 
            "\"${project.findProperty("WEATHER_API_KEY") ?: ""}\"")
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
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlinOptions {
        jvmTarget = "17"
    }
    
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    // Moduli interni
    implementation(project(Modules.coreCommon))
    implementation(project(Modules.coreNetwork))
    implementation(project(Modules.coreDatabase))
    implementation(project(Modules.coreUi))
    implementation(project(Modules.featureHome))
    implementation(project(Modules.featureDetails))
    implementation(project(Modules.featureSearch))
    implementation(project(Modules.featureSettings))
    implementation(project(Modules.domain))
    
    // AndroidX
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)
    
    // Lifecycle
    implementation(Dependencies.lifecycleViewModelKtx)
    implementation(Dependencies.lifecycleLiveDataKtx)
    implementation(Dependencies.lifecycleRuntimeKtx)
    
    // Navigation
    implementation(Dependencies.navigationFragmentKtx)
    implementation(Dependencies.navigationUiKtx)
    
    // Hilt
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
    
    // Testing
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitExt)
    androidTestImplementation(Dependencies.espressoCore)
}
```

### core/network/build.gradle.kts

```kotlin
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.core.network"
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Core common
    implementation(project(Modules.coreCommon))
    
    // Networking
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitGsonConverter)
    implementation(Dependencies.okHttpLoggingInterceptor)
    implementation(Dependencies.gson)
    
    // Coroutines
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)
    
    // Hilt
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
    
    // Testing
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockitoCore)
    testImplementation(Dependencies.coroutinesTest)
}
```

## Organizzazione Package: Feature vs Layer

### Approccio per Layer (Tradizionale)

```
com.example.myweatherapp/
├── data/
│   ├── local/
│   ├── remote/
│   └── repository/
├── domain/
│   ├── model/
│   ├── repository/
│   └── usecase/
└── presentation/
    ├── home/
    ├── details/
    └── search/
```

**Vantaggi**: Separazione chiara dei layer, facile da comprendere per piccoli progetti.

**Svantaggi**: Difficile navigazione per feature specifiche in grandi progetti.

### Approccio per Feature (Moderno, Raccomandato)

```
com.example.myweatherapp/
├── feature/
│   ├── home/
│   │   ├── data/
│   │   │   ├── repository/
│   │   │   └── remote/
│   │   ├── domain/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── usecase/
│   │   └── presentation/
│   │       ├── HomeFragment.kt
│   │       └── HomeViewModel.kt
│   │
│   ├── details/
│   │   ├── data/
│   │   ├── domain/
│   │   └── presentation/
│   │
│   └── search/
│       ├── data/
│       ├── domain/
│       └── presentation/
│
└── core/
    ├── common/
    ├── network/
    └── database/
```

**Vantaggi**: Codice feature-specific raccolto insieme, più facile da mantenere e testare, modularizzazione semplificata.

**Svantaggi**: Possibile duplicazione di codice tra feature (risolvibile con moduli core).

## Documentazione della Struttura nel README

Aggiungi questa sezione al tuo README.md:

```markdown
## 📂 Struttura del Progetto

Il progetto segue un'architettura multi-modulo con separazione per feature, facilitando la scalabilità e la manutenibilità.

### Organizzazione Moduli

```
MyWeatherApp/
├── app/              # Modulo applicazione principale
├── core/             # Moduli core riutilizzabili
│   ├── common/       # Utilities e estensioni comuni
│   ├── network/      # Configurazione networking (Retrofit, OkHttp)
│   ├── database/     # Database Room e DAO
│   └── ui/           # Componenti UI condivisi e tema
├── feature/          # Feature modules isolati
│   ├── home/         # Schermata principale meteo
│   ├── details/      # Dettagli previsioni
│   ├── search/       # Ricerca città
│   └── settings/     # Impostazioni app
├── data/             # Layer dati condiviso
│   ├── weather/      # Repository e datasource meteo
│   └── location/     # Repository località
└── domain/           # Business logic condivisa
    ├── model/        # Domain models
    └── repository/   # Repository interfaces
```

### Convenzioni di Naming

#### File Kotlin
- **Activities**: `MainActivity.kt`, `DetailActivity.kt`
- **Fragments**: `HomeFragment.kt`, `DetailsFragment.kt`
- **ViewModels**: `HomeViewModel.kt`, `DetailsViewModel.kt`
- **Use Cases**: `GetWeatherUseCase.kt`, `SaveLocationUseCase.kt`
- **Repositories**: `WeatherRepository.kt` (interface), `WeatherRepositoryImpl.kt` (impl)
- **Entities**: `WeatherEntity.kt`, `LocationEntity.kt`
- **DTOs**: `WeatherResponse.kt`, `ForecastResponse.kt`
- **Models**: `Weather.kt`, `Location.kt`

#### File XML Layout
- **Activities**: `activity_main.xml`
- **Fragments**: `fragment_home.xml`, `fragment_details.xml`
- **List items**: `item_weather.xml`, `item_location.xml`
- **Dialogs**: `dialog_permission_rationale.xml`
- **Widgets**: `widget_weather.xml`

#### Risorse
- **Drawables**: `ic_sun.xml`, `bg_card.xml`, `selector_item.xml`
- **Strings**: `<string name="app_name">`, `<string name="error_network">`
- **Colors**: `<color name="primary_color">`, `<color name="background_light">`
- **Dimensions**: `<dimen name="margin_small">8dp</dimen>`

### Dipendenze tra Moduli

```
app → feature → data → domain
  ↓      ↓       ↓
  └──── core ────┘
```

- **app**: Dipende da tutti i moduli feature e core
- **feature**: Dipende da domain, data e core
- **data**: Dipende da domain e core
- **domain**: Nessuna dipendenza (pure Kotlin)
- **core**: Dipendenze minime (utility standalone)
```
