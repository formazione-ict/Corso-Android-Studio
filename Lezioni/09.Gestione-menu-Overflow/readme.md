# 10. La gestione dei menu Overflow
## Obiettivi:
-	Comprendere cosa sono i menu Overflow e quando utilizzarli.
-	Imparare a creare un file XML per il menu.
-	Gestire gli eventi di selezione degli item nel codice Java/Kotlin.
-	Fornire esempi pratici con codice commentato, pronti per l’uso in classe.
## 1. Cos’è il Menu Overflow?
Il menu Overflow è il menu che compare con i tre puntini verticali nella Toolbar/ActionBar. Serve per mostrare opzioni aggiuntive che non trovano spazio direttamente nella barra superiore (es. impostazioni, logout, info).
## 2. Creazione del file XML del menu
In Android Studio, i menu si definiscono in res/menu/

Esempio: res/menu/menu_main.xml

Nota:
-	showAsAction="ifRoom" → mostra l’icona se c’è spazio.
-	showAsAction="never" → finisce nel menu Overflow.
## 3. Collegare il menu all’Activity
Nel file MainActivity.java (o .kt se usi Kotlin)
## 4. Esercizio pratico
1.	Creare un progetto Android con una Toolbar.
2.	Aggiungere un file menu_main.xml con almeno 3 voci (una visibile, due in Overflow).
3.	Gestire i click mostrando un Toast.
4.	Estendere l’esempio: aprire una nuova Activity quando si clicca su “Impostazioni”.
## 5. Esercizio con test
1.	Aprire il progetto in Android Studio.  
2.	Avviare l’app e testare il menu Overflow.  
3.	Modificare il comportamento di “Impostazioni” per aprire una nuova Activity.  
4.	Aggiungere una voce “Logout” che mostra un messaggio di conferma.  
## 5. Errori comuni da anticipare
-	Dimenticare di creare la cartella menu/ → Android Studio non la genera automaticamente.
-	Usare showAsAction senza namespace → serve xmlns:app="http://schemas.android.com/apk/res-auto" se si lavora con AppCompat.
-	Non gestire il click → gli item compaiono ma non fanno nulla.
## 6. Spunti di approfondimento
-	Personalizzare l’icona del menu Overflow.
-	Aggiungere submenu.
-	Integrare il menu con Navigation Component.

