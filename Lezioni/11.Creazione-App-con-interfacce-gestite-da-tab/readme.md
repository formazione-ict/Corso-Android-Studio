# Introduzione: Navigazione a Tab in Android
Le tab (linguette) permettono di organizzare più schermate (Fragment) in una singola Activity, migliorando l'esperienza utente senza necessità di navigare tra più Activity.

**Componenti principali:**
•	TabLayout (da AndroidX): contiene i tab visibili all'utente.
•	ViewPager2: contenitore che gestisce lo scorrimento tra Fragment associati ai tab.
•	Fragment: ogni tab mostra un Fragment diverso con la propria interfaccia e logica.
•	FragmentStateAdapter (o PagerAdapter): connette i Fragment al ViewPager2.

**Vantaggi rispetto a più Activity:**
•	Transizioni più fluide e rapide tra schermate.
•	Memoria condivisa più efficiente.
•	Interfaccia unificata con barra di tab sempre visibile.

# Concetti base: Fragment e ViewPager2

## Fragment: una "schermata" riutilizzabile

Un Fragment è una porzione di interfaccia che vive dentro un'Activity.

Diversamente da un'Activity:
•	Non ha un ciclo di vita completamente indipendente (dipende dall'Activity padre).
•	Può avere il proprio layout XML e logica Java/Kotlin.
•	Può comunicare con l'Activity e con altri Fragment attraverso ViewModel o callback.

## ViewPager2: scorrimento tra pagine

ViewPager2 è un contenitore che permette lo scorrimento orizzontale tra Fragment.

Quando l'utente scorre verso destra o sinistra, il ViewPager2 mostra il Fragment successivo/precedente e notifica il TabLayout per aggiornare il tab selezionato.

### Vedi Esempio completo: App con 3 Tab (Home, Profilo, Impostazioni)

### Vedi Esempio avanzato: Tab con RecyclerView

**Schema riassuntivo:**
1.	Concetti base: Tab, Fragment, ViewPager2, TabLayout.
2.	Step pratici:
o	Dipendenze Gradle.
o	Layout principale con TabLayout e ViewPager2.
o	Creare 3 Fragment (Home, Profilo, Impostazioni).
o	FragmentStateAdapter per collegare Fragment.
o	MainActivity con TabLayoutMediator per sincronizzazione.
3.	Ottimizzazioni:
o	Aggiungere icone ai tab.[web:51][web:52]
o	Comunicazione tra Fragment (callback, ViewModel).[web:51][web:52]
o	Animazioni durante cambio tab.[web:51][web:52]
o	Tab con RecyclerView.[web:51][web:52]
