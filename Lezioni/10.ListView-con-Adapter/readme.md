# Introduzione: ListView e il pattern Adapter
ListView è un componente Android (ora parzialmente superato da RecyclerView) che visualizza una lista scrollabile di elementi.

Per popolare una ListView è necessario un Adapter, che agisce come "traduttore" tra i dati (Array, ArrayList, Database) e la UI (View della lista).
## Concetti chiave:
-	L'Adapter converte ogni elemento dei dati in una View visualizzabile nella lista.
-	Esistono Adapter predefiniti come ArrayAdapter per liste semplici, e Adapter personalizzati per esigenze più complesse.
-	A differenza di RecyclerView, ListView non ricicla automaticamente i View, quindi è meno efficiente con liste grandi.

Nota: Anche se RecyclerView è oggi consigliato per nuovi progetti, ListView rimane utile per insegnare i fondamenti del pattern Adapter e per mantenere app legacy.
# Vantaggi e svantaggi: ListView vs RecyclerView
## ListView
Vantaggi:
-	Sintassi semplice e intuitiva.
-	Adapter predefiniti pronti all'uso (ArrayAdapter, SimpleAdapter).
-	Built-in OnItemClickListener facile da usare.
-	Base per ExpandableListView.

Svantaggi:
-	Non implementa il pattern ViewHolder automaticamente.
-	Meno efficiente con liste grandi (non ricicla View).
-	Meno flessibile rispetto a RecyclerView.
## RecyclerView
Vantaggi:
-	Ricicla i View per migliore performance.
-	Supporta grid, liste verticali e orizzontali.
-	Integra animazioni per add/update/remove.
-	Supporta DiffUtil per aggiornamenti efficienti.

Svantaggi:
-	Sintassi più complessa.
-	Richiede implementazione esplicita di ViewHolder.
-	Non ha OnItemClickListener built-in.

# ArrayAdapter: il più semplice per liste di stringhe
ArrayAdapter è l'adapter più semplice, ideale per liste di testo o oggetti omogenei.
## Esempio 1 – Lista semplice di stringhe
Layout (activity_main.xml)

MainActivity.java

Concetti chiave:
-	android.R.layout.simple_list_item_1 è un layout Android predefinito con un solo TextView.
-	OnItemClickListener cattura il click su un elemento con posizione e ID.
-	L'adapter aggiorna automaticamente la ListView quando i dati cambiano (se usa ArrayList invece di array statico).
