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
