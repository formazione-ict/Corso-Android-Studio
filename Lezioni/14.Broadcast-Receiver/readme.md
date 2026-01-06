# I Broadcast Receivers
I Broadcast Receiver sono uno dei meccanismi fondamentali di Android per reagire a eventi di sistema o eventi personalizzati generati da altre app. Sono perfetti quando vuoi “ascoltare” qualcosa che accade nel sistema senza dover tenere attiva un’intera Activity o un Service.

È un componente Android che intercetta broadcast, cioè messaggi inviati dal sistema o da altre app. Esempi di broadcast di sistema:
-	🔋 ACTION_BATTERY_CHANGED – variazione livello batteria
-	📶 CONNECTIVITY_ACTION – cambiamento stato rete
-	⏰ ACTION_TIME_TICK – ogni minuto
-	📱 BOOT_COMPLETED – dispositivo avviato

Puoi anche creare broadcast personalizzati per far comunicare parti della tua app.

## Come si usano
Ci sono due modi:
1.	Statici (nel Manifest) - Il Receiver viene attivato anche se l’app è chiusa. Ideale per eventi di sistema come BOOT_COMPLETED.
2.	Dinamici (registrati nel codice) - Si registrano e deregistrano in Activity/Fragment. Ideali per eventi che servono solo quando l’utente usa l’app.

