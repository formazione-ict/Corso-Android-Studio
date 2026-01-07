# Linee Guida Complete per Layout e Risorse Android

Queste convenzioni seguono le best practices ufficiali Android e sono ampiamente adottate dalla community, garantendo progetti ben organizzati, manutenibili e facilmente comprensibili da qualsiasi sviluppatore.

## Layout Files - Convenzioni di Naming

I file di layout devono seguire il pattern `<COMPONENT>_<DESCRIPTOR>.xml` dove il nome del componente Android viene spostato all'inizio.

### Activity Layouts

```xml
<!-- Pattern: activity_<name>.xml -->

activity_main.xml              <!-- MainActivity -->
activity_login.xml             <!-- LoginActivity -->
activity_user_profile.xml      <!-- UserProfileActivity -->
activity_weather_detail.xml    <!-- WeatherDetailActivity -->
activity_settings.xml          <!-- SettingsActivity -->
activity_splash.xml            <!-- SplashActivity -->
activity_onboarding.xml        <!-- OnboardingActivity -->

<!-- ❌ EVITARE -->
main_activity.xml              <!-- Ordine errato -->
MainActivity.xml               <!-- Maiuscole non permesse -->
main.xml                       <!-- Troppo generico -->
```

### Fragment Layouts

```xml
<!-- Pattern: fragment_<name>.xml -->

fragment_home.xml              <!-- HomeFragment -->
fragment_details.xml           <!-- DetailsFragment -->
fragment_search.xml            <!-- SearchFragment -->
fragment_settings.xml          <!-- SettingsFragment -->
fragment_weather_list.xml      <!-- WeatherListFragment -->
fragment_user_profile.xml      <!-- UserProfileFragment -->
fragment_map.xml               <!-- MapFragment -->

<!-- ❌ EVITARE -->
home_fragment.xml              <!-- Ordine errato -->
HomeFragment.xml               <!-- Maiuscole non permesse -->
frag_home.xml                  <!-- Abbreviazione non chiara -->
```

### Dialog Layouts

```xml
<!-- Pattern: dialog_<name>.xml -->

dialog_confirm_delete.xml      <!-- Dialog di conferma eliminazione -->
dialog_permission_rationale.xml <!-- Dialog spiegazione permessi -->
dialog_date_picker.xml         <!-- Dialog selezione data -->
dialog_loading.xml             <!-- Dialog di caricamento -->
dialog_error.xml               <!-- Dialog errore generico -->
dialog_language_selector.xml   <!-- Dialog scelta lingua -->
dialog_location_picker.xml     <!-- Dialog scelta località -->
```

### RecyclerView/ListView Items

```xml
<!-- Pattern: item_<name>.xml -->

item_weather.xml               <!-- Item meteo in lista -->
item_location.xml              <!-- Item località -->
item_forecast.xml              <!-- Item previsione -->
item_forecast_hourly.xml       <!-- Item previsione oraria -->
item_header.xml                <!-- Item header di sezione -->
item_footer.xml                <!-- Item footer -->
item_separator.xml             <!-- Item separatore -->
item_weather_compact.xml       <!-- Item compatto -->
item_weather_expanded.xml      <!-- Item espanso -->

<!-- ViewType diversi per stesso Adapter -->
item_message_sent.xml          <!-- Messaggio inviato -->
item_message_received.xml      <!-- Messaggio ricevuto -->
item_message_date_separator.xml <!-- Separatore data -->
```

### Layout Parziali (Include/Merge)

```xml
<!-- Pattern: partial_<name>.xml o include_<name>.xml -->

partial_toolbar.xml            <!-- Toolbar riutilizzabile -->
partial_loading.xml            <!-- Stato di caricamento -->
partial_error.xml              <!-- Stato di errore -->
partial_empty_state.xml        <!-- Stato vuoto -->
partial_weather_card.xml       <!-- Card meteo riutilizzabile -->
include_bottom_navigation.xml  <!-- Bottom navigation -->
include_app_bar.xml            <!-- App bar -->
merge_weather_details.xml      <!-- Merge layout per ottimizzazione -->
```

#### Widget Layouts

```xml
<!-- Pattern: widget_<name>_<size>.xml -->

widget_weather.xml             <!-- Widget generico -->
widget_weather_small.xml       <!-- Widget piccolo (2x2) -->
widget_weather_medium.xml      <!-- Widget medio (4x2) -->
widget_weather_large.xml       <!-- Widget grande (4x4) -->
widget_clock.xml               <!-- Widget orologio -->
```

### Custom View Layouts

```xml
<!-- Pattern: view_<name>.xml -->

view_temperature_graph.xml     <!-- Custom view grafico temperatura -->
view_weather_card.xml          <!-- Custom card meteo -->
view_circular_progress.xml     <!-- Progress circolare personalizzato -->
view_weather_icon.xml          <!-- Icona meteo animata -->
```

### Bottom Sheet Layouts

```xml
<!-- Pattern: bottom_sheet_<name>.xml -->

bottom_sheet_filter.xml        <!-- Bottom sheet filtri -->
bottom_sheet_location.xml      <!-- Bottom sheet località -->
bottom_sheet_details.xml       <!-- Bottom sheet dettagli -->
```

## Drawable Resources - Convenzioni Dettagliate

I drawable devono seguire il pattern `<PREFIX>_<DESCRIPTOR>_<STATE>.xml`.

### Icons (Prefisso: ic_)

```xml
<!-- Pattern: ic_<name>_<size>dp.xml -->

<!-- Weather Icons -->
ic_sun.xml
ic_sun_24dp.xml                <!-- Dimensione specifica -->
ic_cloud.xml
ic_rain.xml
ic_snow.xml
ic_storm.xml
ic_fog.xml
ic_wind.xml

<!-- Navigation Icons -->
ic_home.xml
ic_search.xml
ic_settings.xml
ic_location.xml
ic_menu.xml
ic_arrow_back.xml
ic_arrow_forward.xml
ic_close.xml

<!-- Action Icons -->
ic_add.xml
ic_edit.xml
ic_delete.xml
ic_save.xml
ic_share.xml
ic_favorite.xml
ic_favorite_filled.xml         <!-- Stato pieno -->
ic_favorite_outline.xml        <!-- Stato vuoto -->

<!-- State Icons -->
ic_check_circle.xml            <!-- Successo -->
ic_error_outline.xml           <!-- Errore -->
ic_warning.xml                 <!-- Avviso -->
ic_info.xml                    <!-- Informazione -->

<!-- Social Icons -->
ic_facebook.xml
ic_twitter.xml
ic_instagram.xml
ic_whatsapp.xml

<!-- ❌ EVITARE -->
icon_sun.xml                   <!-- Usa 'ic_' non 'icon_' -->
sun_icon.xml                   <!-- Prefisso deve essere primo -->
IC_SUN.xml                     <!-- No maiuscole -->
ic-sun.xml                     <!-- Usa underscore non trattini -->
```

### Background Drawables (Prefisso: bg_)

```xml
<!-- Pattern: bg_<descriptor>_<properties>.xml -->

bg_button.xml                  <!-- Background generico button -->
bg_button_rounded.xml          <!-- Button con angoli arrotondati -->
bg_button_rounded_primary.xml  <!-- Button arrotondato primario -->
bg_card.xml                    <!-- Background card -->
bg_card_elevated.xml           <!-- Card con elevazione -->
bg_gradient_primary.xml        <!-- Gradient primario -->
bg_gradient_sunset.xml         <!-- Gradient personalizzato -->
bg_ripple.xml                  <!-- Effetto ripple -->
bg_splash.xml                  <!-- Background splash screen -->
bg_toolbar.xml                 <!-- Background toolbar -->

<!-- Con stati -->
bg_button_selector.xml         <!-- Selector con stati multipli -->
bg_button_pressed.xml          <!-- Stato pressed -->
bg_button_disabled.xml         <!-- Stato disabled -->
bg_button_focused.xml          <!-- Stato focused -->
```

### Shape Drawables (Prefisso: shape_)

```xml
<!-- Pattern: shape_<type>_<properties>.xml -->

shape_rectangle.xml            <!-- Rettangolo base -->
shape_rectangle_rounded.xml    <!-- Rettangolo angoli arrotondati -->
shape_circle.xml               <!-- Cerchio -->
shape_oval.xml                 <!-- Ovale -->
shape_line.xml                 <!-- Linea -->
shape_ring.xml                 <!-- Anello -->
shape_rounded_8dp.xml          <!-- Corner radius specifico -->
```

### Selector Drawables (Prefisso: selector_)

```xml
<!-- Pattern: selector_<name>.xml -->

selector_button.xml            <!-- Selector per button -->
selector_button_primary.xml    <!-- Selector button primario -->
selector_tab.xml               <!-- Selector per tab -->
selector_item.xml              <!-- Selector per item lista -->
selector_text_color.xml        <!-- Selector colore testo -->
```

### Layer List Drawables (Prefisso: layer_)

```xml
<!-- Pattern: layer_<name>.xml -->

layer_card_shadow.xml          <!-- Layer con ombra -->
layer_button_elevated.xml      <!-- Button con elevazione -->
layer_border.xml               <!-- Bordo composito -->
```

### Vector Drawables

```xml
<!-- Pattern: <prefix>_<name>_<variant>.xml -->

<!-- Animated Vector Drawables -->
avd_sun_to_moon.xml            <!-- Animazione sole->luna -->
avd_menu_to_close.xml          <!-- Animazione menu->close -->
avd_loading_spinner.xml        <!-- Spinner animato -->

<!-- Vector Drawables -->
vd_weather_pattern.xml         <!-- Pattern vettoriale complesso -->
vd_logo.xml                    <!-- Logo vettoriale -->
```

### State-specific Drawables

```xml
<!-- Con suffissi di stato -->
ic_notification_active.xml
ic_notification_inactive.xml
ic_toggle_on.xml
ic_toggle_off.xml
ic_visibility_visible.xml
ic_visibility_hidden.xml
btn_bg_pressed.xml
btn_bg_normal.xml
btn_bg_disabled.xml
```

## String Resources - Convenzioni Strutturate

Le stringhe seguono il pattern `<CONTEXT>_<DESCRIPTOR>`.

```xml
<!-- strings.xml -->
<resources>
    <!-- ========== APP INFO ========== -->
    <string name="app_name">MyWeatherApp</string>
    <string name="app_description">Real-time weather forecast</string>
    <string name="app_version">Version %1$s</string>
    
    <!-- ========== SCREEN TITLES ========== -->
    <string name="title_home">Home</string>
    <string name="title_details">Weather Details</string>
    <string name="title_search">Search City</string>
    <string name="title_settings">Settings</string>
    <string name="title_about">About</string>
    
    <!-- ========== BUTTONS ========== -->
    <!-- Pattern: btn_<action> o action_<verb> -->
    <string name="btn_save">Save</string>
    <string name="btn_cancel">Cancel</string>
    <string name="btn_delete">Delete</string>
    <string name="btn_refresh">Refresh</string>
    <string name="btn_retry">Retry</string>
    <string name="btn_ok">OK</string>
    <string name="btn_got_it">Got it</string>
    <string name="action_search">Search</string>
    <string name="action_share">Share</string>
    <string name="action_edit">Edit</string>
    
    <!-- ========== LABELS ========== -->
    <!-- Pattern: label_<descriptor> -->
    <string name="label_temperature">Temperature</string>
    <string name="label_humidity">Humidity</string>
    <string name="label_wind_speed">Wind Speed</string>
    <string name="label_pressure">Pressure</string>
    <string name="label_feels_like">Feels like</string>
    <string name="label_location">Location</string>
    
    <!-- ========== HINTS ========== -->
    <!-- Pattern: hint_<descriptor> -->
    <string name="hint_search_city">Search city…</string>
    <string name="hint_enter_email">Enter your email</string>
    <string name="hint_enter_password">Enter password</string>
    <string name="hint_city_name">City name</string>
    
    <!-- ========== MESSAGES ========== -->
    <!-- Pattern: msg_<context>_<descriptor> -->
    <string name="msg_loading">Loading…</string>
    <string name="msg_loading_weather">Fetching weather data…</string>
    <string name="msg_success_saved">Location saved successfully</string>
    <string name="msg_success_deleted">Location deleted</string>
    <string name="msg_no_results">No results found</string>
    <string name="msg_try_again">Please try again</string>
    
    <!-- ========== ERRORS ========== -->
    <!-- Pattern: error_<context> -->
    <string name="error_network">No internet connection</string>
    <string name="error_network_timeout">Connection timeout</string>
    <string name="error_server">Server error occurred</string>
    <string name="error_unknown">Unknown error</string>
    <string name="error_location_not_found">City not found</string>
    <string name="error_invalid_email">Invalid email format</string>
    <string name="error_empty_field">This field cannot be empty</string>
    <string name="error_permission_denied">Permission denied</string>
    
    <!-- ========== DIALOGS ========== -->
    <!-- Pattern: dialog_<name>_<element> -->
    <string name="dialog_delete_title">Delete Location?</string>
    <string name="dialog_delete_message">This will remove the location from your favorites</string>
    <string name="dialog_permission_title">Location Permission</string>
    <string name="dialog_permission_message">We need location access to show weather for your current position</string>
    
    <!-- ========== EMPTY STATES ========== -->
    <!-- Pattern: empty_<context>_<element> -->
    <string name="empty_locations_title">No Saved Locations</string>
    <string name="empty_locations_message">Add your first location to get started</string>
    <string name="empty_search_title">No Results</string>
    <string name="empty_search_message">Try searching for a different city</string>
    
    <!-- ========== CONTENT DESCRIPTIONS (Accessibility) ========== -->
    <!-- Pattern: cd_<descriptor> -->
    <string name="cd_weather_icon">Weather condition icon</string>
    <string name="cd_menu">Menu</string>
    <string name="cd_close">Close</string>
    <string name="cd_back">Navigate back</string>
    <string name="cd_location_icon">Location marker</string>
    <string name="cd_favorite">Add to favorites</string>
    
    <!-- ========== FEATURE SPECIFIC ========== -->
    <!-- Pattern: <feature>_<element> -->
    
    <!-- Home Screen -->
    <string name="home_greeting">Welcome back!</string>
    <string name="home_current_location">Current Location</string>
    <string name="home_saved_locations">Saved Locations</string>
    <string name="home_no_data">Pull down to refresh</string>
    
    <!-- Settings Screen -->
    <string name="settings_general">General</string>
    <string name="settings_units">Units</string>
    <string name="settings_theme">Theme</string>
    <string name="settings_theme_light">Light</string>
    <string name="settings_theme_dark">Dark</string>
    <string name="settings_theme_auto">Auto (System)</string>
    <string name="settings_notifications">Notifications</string>
    <string name="settings_about">About</string>
    
    <!-- Details Screen -->
    <string name="details_hourly_forecast">Hourly Forecast</string>
    <string name="details_weekly_forecast">7-Day Forecast</string>
    <string name="details_today">Today</string>
    <string name="details_tomorrow">Tomorrow</string>
    
    <!-- ========== FORMATS (Placeholders) ========== -->
    <!-- Pattern: format_<descriptor> -->
    <string name="format_temperature">%1$d°C</string>
    <string name="format_humidity">%1$d%%</string>
    <string name="format_wind_speed">%1$.1f km/h</string>
    <string name="format_last_updated">Updated %1$s ago</string>
    <string name="format_date_time">%1$s at %2$s</string>
    
    <!-- Plurals -->
    <plurals name="format_days">
        <item quantity="one">%d day</item>
        <item quantity="other">%d days</item>
    </plurals>
    
    <!-- ========== VALIDATION ========== -->
    <!-- Pattern: validation_<context> -->
    <string name="validation_email_required">Email is required</string>
    <string name="validation_password_too_short">Password must be at least 8 characters</string>
    <string name="validation_passwords_dont_match">Passwords don\'t match</string>
    
</resources>
```

## Color Resources - Sistema Semantico

```xml
<!-- colors.xml -->
<resources>
    <!-- ========== PRIMARY PALETTE (Material Design 3) ========== -->
    <color name="primary">#6750A4</color>
    <color name="primary_container">#EADDFF</color>
    <color name="on_primary">#FFFFFF</color>
    <color name="on_primary_container">#21005E</color>
    
    <color name="secondary">#625B71</color>
    <color name="secondary_container">#E8DEF8</color>
    <color name="on_secondary">#FFFFFF</color>
    <color name="on_secondary_container">#1E192B</color>
    
    <color name="tertiary">#7D5260</color>
    <color name="tertiary_container">#FFD8E4</color>
    <color name="on_tertiary">#FFFFFF</color>
    <color name="on_tertiary_container">#31101D</color>
    
    <!-- ========== SURFACE COLORS ========== -->
    <color name="surface">#FFFBFE</color>
    <color name="surface_variant">#E7E0EC</color>
    <color name="on_surface">#1C1B1F</color>
    <color name="on_surface_variant">#49454E</color>
    
    <color name="background">#FFFBFE</color>
    <color name="on_background">#1C1B1F</color>
    
    <!-- ========== OUTLINE COLORS ========== -->
    <color name="outline">#79747E</color>
    <color name="outline_variant">#CAC4D0</color>
    
    <!-- ========== STATE COLORS ========== -->
    <color name="error">#B3261E</color>
    <color name="error_container">#F9DEDC</color>
    <color name="on_error">#FFFFFF</color>
    <color name="on_error_container">#410E0B</color>
    
    <color name="success">#4CAF50</color>
    <color name="success_light">#81C784</color>
    <color name="success_dark">#388E3C</color>
    
    <color name="warning">#FF9800</color>
    <color name="warning_light">#FFB74D</color>
    <color name="warning_dark">#F57C00</color>
    
    <color name="info">#2196F3</color>
    <color name="info_light">#64B5F6</color>
    <color name="info_dark">#1976D2</color>
    
    <!-- ========== TEXT COLORS ========== -->
    <color name="text_primary">#000000</color>
    <color name="text_secondary">#757575</color>
    <color name="text_disabled">#BDBDBD</color>
    <color name="text_hint">#9E9E9E</color>
    
    <!-- ========== SEMANTIC COLORS (Branded) ========== -->
    <color name="weather_sunny">#FFA726</color>
    <color name="weather_cloudy">#90A4AE</color>
    <color name="weather_rainy">#42A5F5</color>
    <color name="weather_snowy">#E1F5FE</color>
    <color name="weather_stormy">#5C6BC0</color>
    
    <!-- ========== COMPONENT SPECIFIC ========== -->
    <color name="divider">#E0E0E0</color>
    <color name="ripple">#1F000000</color>
    <color name="scrim">#80000000</color>
    <color name="shadow">#1A000000</color>
    
    <!-- ========== TRANSPARENT OVERLAYS ========== -->
    <color name="black_overlay_10">#1A000000</color>
    <color name="black_overlay_30">#4D000000</color>
    <color name="black_overlay_50">#80000000</color>
    <color name="black_overlay_70">#B3000000</color>
    
    <color name="white_overlay_10">#1AFFFFFF</color>
    <color name="white_overlay_30">#4DFFFFFF</color>
    <color name="white_overlay_50">#80FFFFFF</color>
    
    <!-- ========== PURE COLORS (When needed) ========== -->
    <color name="black">#000000</color>
    <color name="white">#FFFFFF</color>
    <color name="transparent">#00000000</color>
    
</resources>

<!-- colors_night.xml (Tema scuro) -->
<resources>
    <!-- Dark theme equivalents -->
    <color name="primary">#D0BCFF</color>
    <color name="primary_container">#4F378B</color>
    <color name="on_primary">#371E73</color>
    <color name="on_primary_container">#EADDFF</color>
    
    <color name="surface">#1C1B1F</color>
    <color name="on_surface">#E6E1E5</color>
    <color name="background">#1C1B1F</color>
    <color name="on_background">#E6E1E5</color>
    
    <!-- ... Altri colori dark theme ... -->
</resources>
```

## Dimension Resources

```xml
<!-- dimens.xml -->
<resources>
    <!-- ========== SPACING (8dp grid system) ========== -->
    <dimen name="spacing_none">0dp</dimen>
    <dimen name="spacing_tiny">2dp</dimen>
    <dimen name="spacing_extra_small">4dp</dimen>
    <dimen name="spacing_small">8dp</dimen>
    <dimen name="spacing_medium">16dp</dimen>
    <dimen name="spacing_large">24dp</dimen>
    <dimen name="spacing_extra_large">32dp</dimen>
    <dimen name="spacing_huge">48dp</dimen>
    <dimen name="spacing_massive">64dp</dimen>
    
    <!-- ========== MARGINS ========== -->
    <dimen name="margin_tiny">4dp</dimen>
    <dimen name="margin_small">8dp</dimen>
    <dimen name="margin_medium">16dp</dimen>
    <dimen name="margin_large">24dp</dimen>
    <dimen name="margin_xlarge">32dp</dimen>
    
    <!-- ========== PADDING ========== -->
    <dimen name="padding_tiny">4dp</dimen>
    <dimen name="padding_small">8dp</dimen>
    <dimen name="padding_medium">16dp</dimen>
    <dimen name="padding_large">24dp</dimen>
    <dimen name="padding_xlarge">32dp</dimen>
    
    <!-- ========== TEXT SIZES (Scale tipografica) ========== -->
    <dimen name="text_size_tiny">10sp</dimen>
    <dimen name="text_size_small">12sp</dimen>
    <dimen name="text_size_body">14sp</dimen>
    <dimen name="text_size_medium">16sp</dimen>
    <dimen name="text_size_large">18sp</dimen>
    <dimen name="text_size_title">20sp</dimen>
    <dimen name="text_size_headline">24sp</dimen>
    <dimen name="text_size_display">34sp</dimen>
    
    <!-- Material Design 3 Type Scale -->
    <dimen name="text_display_large">57sp</dimen>
    <dimen name="text_display_medium">45sp</dimen>
    <dimen name="text_display_small">36sp</dimen>
    <dimen name="text_headline_large">32sp</dimen>
    <dimen name="text_headline_medium">28sp</dimen>
    <dimen name="text_headline_small">24sp</dimen>
    <dimen name="text_title_large">22sp</dimen>
    <dimen name="text_title_medium">16sp</dimen>
    <dimen name="text_title_small">14sp</dimen>
    <dimen name="text_body_large">16sp</dimen>
    <dimen name="text_body_medium">14sp</dimen>
    <dimen name="text_body_small">12sp</dimen>
    <dimen name="text_label_large">14sp</dimen>
    <dimen name="text_label_medium">12sp</dimen>
    <dimen name="text_label_small">11sp</dimen>
    
    <!-- ========== COMPONENT DIMENSIONS ========== -->
    <!-- Buttons -->
    <dimen name="button_height">48dp</dimen>
    <dimen name="button_height_small">36dp</dimen>
    <dimen name="button_height_large">56dp</dimen>
    <dimen name="button_corner_radius">8dp</dimen>
    <dimen name="button_corner_radius_small">4dp</dimen>
    
    <!-- Cards -->
    <dimen name="card_elevation">2dp</dimen>
    <dimen name="card_elevation_raised">8dp</dimen>
    <dimen name="card_corner_radius">12dp</dimen>
    <dimen name="card_corner_radius_small">8dp</dimen>
    
    <!-- Icons -->
    <dimen name="icon_size_tiny">16dp</dimen>
    <dimen name="icon_size_small">20dp</dimen>
    <dimen name="icon_size_medium">24dp</dimen>
    <dimen name="icon_size_large">32dp</dimen>
    <dimen name="icon_size_xlarge">48dp</dimen>
    
    <!-- Toolbar -->
    <dimen name="toolbar_height">56dp</dimen>
    <dimen name="toolbar_elevation">4dp</dimen>
    
    <!-- FAB -->
    <dimen name="fab_size">56dp</dimen>
    <dimen name="fab_size_mini">40dp</dimen>
    <dimen name="fab_margin">16dp</dimen>
    
    <!-- List Items -->
    <dimen name="list_item_height">56dp</dimen>
    <dimen name="list_item_height_large">72dp</dimen>
    <dimen name="list_item_height_small">48dp</dimen>
    
    <!-- Dividers -->
    <dimen name="divider_height">1dp</dimen>
    <dimen name="divider_inset">72dp</dimen>
    
    <!-- Ripple -->
    <dimen name="ripple_size">48dp</dimen>
    
    <!-- Bottom Sheet -->
    <dimen name="bottom_sheet_corner_radius">16dp</dimen>
    <dimen name="bottom_sheet_peek_height">64dp</dimen>
    
</resources>

<!-- dimens.xml (sw600dp - Tablet) -->
<resources>
    <dimen name="margin_medium">24dp</dimen>
    <dimen name="margin_large">32dp</dimen>
    <dimen name="text_size_body">16sp</dimen>
    <!-- Dimensioni maggiorate per tablet -->
</resources>
```

## Style Resources

```xml
<!-- styles.xml -->
<resources>
    <!-- ========== TEXT STYLES ========== -->
    <style name="TextAppearance.App" parent="TextAppearance.Material3.BodyMedium">
        <item name="android:textColor">@color/text_primary</item>
    </style>
    
    <style name="TextAppearance.App.Headline">
        <item name="android:textSize">@dimen/text_size_headline</item>
        <item name="android:textStyle">bold</item>
    </style>
    
    <style name="TextAppearance.App.Title">
        <item name="android:textSize">@dimen/text_size_title</item>
        <item name="android:textStyle">bold</item>
    </style>
    
    <style name="TextAppearance.App.Body">
        <item name="android:textSize">@dimen/text_size_body</item>
    </style>
    
    <style name="TextAppearance.App.Caption">
        <item name="android:textSize">@dimen/text_size_small</item>
        <item name="android:textColor">@color/text_secondary</item>
    </style>
    
    <!-- ========== WIDGET STYLES ========== -->
    <style name="Widget.App.Button" parent="Widget.Material3.Button">
        <item name="android:minHeight">@dimen/button_height</item>
        <item name="cornerRadius">@dimen/button_corner_radius</item>
    </style>
    
    <style name="Widget.App.Button.Outlined" parent="Widget.Material3.Button.OutlinedButton">
        <item name="android:minHeight">@dimen/button_height</item>
    </style>
    
    <style name="Widget.App.Card" parent="Widget.Material3.CardView.Elevated">
        <item name="cardElevation">@dimen/card_elevation</item>
        <item name="cardCornerRadius">@dimen/card_corner_radius</item>
        <item name="contentPadding">@dimen/padding_medium</item>
    </style>
    
    <!-- ========== COMPONENT STYLES ========== -->
    <style name="Widget.App.Toolbar" parent="Widget.Material3.Toolbar">
        <item name="android:elevation">@dimen/toolbar_elevation</item>
    </style>
    
</resources>
```

## View IDs - Convenzioni Complete

```xml
<!-- Pattern: <component_abbreviation>_<descriptor> -->

<LinearLayout
    android:id="@+id/layout_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- TEXTVIEW - prefisso: text_ o tv_ -->
    <TextView
        android:id="@+id/text_title"
        android:id="@+id/tv_temperature"
        android:id="@+id/text_city_name"
        android:id="@+id/tv_description" />

    <!-- BUTTON - prefisso: button_ o btn_ -->
    <Button
        android:id="@+id/button_save"
        android:id="@+id/btn_refresh"
        android:id="@+id/button_submit" />

    <!-- IMAGEVIEW - prefisso: image_ o img_ o iv_ -->
    <ImageView
        android:id="@+id/image_weather_icon"
        android:id="@+id/img_profile"
        android:id="@+id/iv_background" />

    <!-- EDITTEXT - prefisso: edit_ o input_ ou et_ -->
    <EditText
        android:id="@+id/edit_city_name"
        android:id="@+id/input_email"
        android:id="@+id/et_password" />

    <!-- RECYCLERVIEW - prefisso: recycler_ o rv_ -->
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_weather_list"
        android:id="@+id/rv_locations" />

    <!-- CARDVIEW - prefisso: card_ -->
    <androidx.cardview.widget.CardView
        android:id="@+id/card_weather"
        android:id="@+id/card_location" />

    <!-- FAB - prefisso: fab_ -->
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab_add"
        android:id="@+id/fab_filter" />

    <!-- PROGRESSBAR - prefisso: progress_ -->
    <ProgressBar
        android:id="@+id/progress_loading"
        android:id="@+id/progress_download" />

    <!-- SWITCH - prefisso: switch_ -->
    <com.google.android.material.switchmaterial.SwitchMaterial
        android:id="@+id/switch_notifications"
        android:id="@+id/switch_theme" />

    <!-- CHECKBOX - prefisso: checkbox_ ou cb_ -->
    <CheckBox
        android:id="@+id/checkbox_terms"
        android:id="@+id/cb_remember_me" />

    <!-- RADIOBUTTON - prefisso: radio_ ou rb_ -->
    <RadioButton
        android:id="@+id/radio_celsius"
        android:id="@+id/rb_fahrenheit" />

    <!-- VIEWPAGER - prefisso: pager_ -->
    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/pager_onboarding"
        android:id="@+id/pager_forecast" />

    <!-- TABLAYOUT - prefisso: tab_layout_ -->
    <com.google.android.material.tabs.TabLayout
        android:id="@+id/tab_layout_main"
        android:id="@+id/tabs_forecast" />

    <!-- CONTAINER - prefisso: container_ ou frame_ -->
    <FrameLayout
        android:id="@+id/container_fragment"
        android:id="@+id/frame_content" />

    <!-- COORDINATORLAYOUT - prefisso: coordinator_ -->
    <androidx.coordinatorlayout.widget.CoordinatorLayout
        android:id="@+id/coordinator_main" />

</LinearLayout>
```

## Tabella Riassuntiva Completa

```markdown
## 📋 Quick Reference: Layout & Resources

### Layout Files
| Componente | Pattern | Esempio |
|------------|---------|---------|
| Activity | `activity_<name>.xml` | `activity_main.xml` |
| Fragment | `fragment_<name>.xml` | `fragment_home.xml` |
| Dialog | `dialog_<name>.xml` | `dialog_confirm.xml` |
| List Item | `item_<name>.xml` | `item_weather.xml` |
| Partial | `partial_<name>.xml` | `partial_toolbar.xml` |
| Widget | `widget_<name>_<size>.xml` | `widget_weather_small.xml` |
| Custom View | `view_<name>.xml` | `view_graph.xml` |
| Bottom Sheet | `bottom_sheet_<name>.xml` | `bottom_sheet_filter.xml` |

### Drawable Files
| Tipo | Pattern | Esempio |
|------|---------|---------|
| Icon | `ic_<name>.xml` | `ic_sun.xml` |
| Background | `bg_<name>.xml` | `bg_card_rounded.xml` |
| Shape | `shape_<type>.xml` | `shape_circle.xml` |
| Selector | `selector_<name>.xml` | `selector_button.xml` |
| Layer | `layer_<name>.xml` | `layer_shadow.xml` |

### String Resources
| Tipo | Pattern | Esempio |
|------|---------|---------|
| Screen Title | `title_<screen>` | `title_home` |
| Button | `btn_<action>` | `btn_save` |
| Label | `label_<name>` | `label_temperature` |
| Hint | `hint_<field>` | `hint_search` |
| Error | `error_<context>` | `error_network` |
| Message | `msg_<context>` | `msg_loading` |
| Content Desc | `cd_<element>` | `cd_menu` |
| Feature | `<feature>_<element>` | `home_greeting` |

### View IDs
| Widget | Prefix | Esempio |
|--------|--------|---------|
| TextView | `text_` o `tv_` | `text_title` |
| Button | `button_` o `btn_` | `btn_save` |
| ImageView | `image_` o `img_` | `image_icon` |
| EditText | `edit_` o `input_` | `edit_name` |
| RecyclerView | `recycler_` | `recycler_list` |
| CardView | `card_` | `card_weather` |
| FAB | `fab_` | `fab_add` |
| ProgressBar | `progress_` | `progress_loading` |
```
