# Schema riassuntivo:
1.	Introduzione: Perché RecyclerView e il pattern ViewHolder.
2.	ViewHolder: Struttura e vantaggi (evita findViewById ripetuti).
3.	onBindViewHolder: Come associare i dati ai widget.
4.	Esempio pratico: Lista di contatti con ArrayList, onBindViewHolder e click listener.
5.	Strutture dati: ArrayList per ordine naturale, HashMap e conversione.
6.	Aggiornamenti dinamici: notifyItemChanged, notifyItemInserted, notifyDataSetChanged.
7.	Ottimizzazioni: ViewType, pulizia risorse, ListAdapter con DiffUtil.

# Introduzione

## Perché RecyclerView?
RecyclerView è il componente Android moderno per visualizzare liste di dati in modo efficiente. A differenza di ListView, ricicla i view scorrendo: quando un elemento esce dallo schermo, il suo view viene riutilizzato per un nuovo elemento, risparmiando memoria e migliorando le prestazioni.

**Concetti chiave:**
- Ogni riga di lista è rappresentata da un ViewHolder che contiene i riferimenti ai widget (TextView, ImageView, ecc.).
- L'Adapter converte i dati in ViewHolder tramite i metodi onCreateViewHolder e onBindViewHolder.
- I dati si gestiscono con strutture come ArrayList, HashMap, o List<Object>.

## Cos'è un ViewHolder?
Un ViewHolder è una classe che contiene i riferimenti a tutti i widget di una singola riga di lista.

**Vantaggi del pattern ViewHolder:**
- Evita chiamate ripetute a findViewById() (operazione costosa).
- Memorizza i widget una sola volta in memoria.
- Migliora drasticamente le prestazioni dello scroll.

# Struttura base di un ViewHolder
```
public static class MyViewHolder extends RecyclerView.ViewHolder {
public TextView textViewTitle;
public TextView textViewDescription;
public ImageView imageViewIcon;

public MyViewHolder(@NonNull View itemView) {
    super(itemView);
    // Riferimenti ai widget (fatto una sola volta)
    textViewTitle = itemView.findViewById(R.id.text_title);
    textViewDescription = itemView.findViewById(R.id.text_description);
    imageViewIcon = itemView.findViewById(R.id.image_icon);
    }

}
```

**Punti fondamentali:**
- Il ViewHolder riceve il View della riga (gonfiato da un layout XML).
- Tutti i findViewById() si fanno nel costruttore, non ogni volta che la riga viene visualizzata.
- I widget pubblici sono accessibili dall'Adapter per aggiornare i dati.

# Cos'è onBindViewHolder?
onBindViewHolder è il metodo dell'Adapter che associa i dati al ViewHolder.

Viene chiamato da RecyclerView ogni volta che:
- Un ViewHolder deve mostrare dati di una nuova posizione.
- L'utente fa scorrere la lista e un ViewHolder viene riutilizzato per un nuovo elemento.

**Firma del metodo**
```
@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
// holder = il ViewHolder da aggiornare
// position = la posizione nella lista dati
}
```
**Responsabilità di onBindViewHolder:**
- Recuperare i dati dalla struttura dati (ArrayList, HashMap, ecc.) alla posizione fornita.
- Aggiornare i widget del holder con i nuovi dati.
- Impostare listener (click, long click, ecc.) sui widget del holder.

# Esempio completo: Lista di Contatti con ArrayList
1. Modello dati (Contatto.java)

```
public class Contatto {
private String nome;
private String telefono;
private String email;
public Contatto(String nome, String telefono, String email) {
    this.nome = nome;
    this.telefono = telefono;
    this.email = email;
}

// Getter
public String getNome() { return nome; }
public String getTelefono() { return telefono; }
public String getEmail() { return email; }

}
```

2. Layout della riga (contact_item.xml)

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="vertical" android:padding="16dp">
<TextView
    android:id="@+id/text_nome"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:textSize="18sp"
    android:textStyle="bold" />

<TextView
    android:id="@+id/text_telefono"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:textSize="14sp"
    android:layout_marginTop="4dp" />

<TextView
    android:id="@+id/text_email"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:textSize="14sp"
    android:layout_marginTop="4dp" />

</LinearLayout>
```

3. Adapter con ViewHolder (ContattiAdapter.java)

```
public class ContattiAdapter extends RecyclerView.Adapter<ContattiAdapter.ContattoViewHolder> {
private ArrayList<Contatto> contattiList;
private Context context;

public ContattiAdapter(Context context, ArrayList<Contatto> contattiList) {
    this.context = context;
    this.contattiList = contattiList;
}

// ViewHolder inner class
public static class ContattoViewHolder extends RecyclerView.ViewHolder {
    public TextView textNome;
    public TextView textTelefono;
    public TextView textEmail;

    public ContattoViewHolder(@NonNull View itemView) {
        super(itemView);
        // Riferimenti unici, fatti una volta
        textNome = itemView.findViewById(R.id.text_nome);
        textTelefono = itemView.findViewById(R.id.text_telefono);
        textEmail = itemView.findViewById(R.id.text_email);
    }
}

// Crea il ViewHolder (gonfia il layout)
@NonNull
@Override
public ContattoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.contact_item, parent, false);
    return new ContattoViewHolder(view);
}

// Associa i dati al ViewHolder
@Override
public void onBindViewHolder(@NonNull ContattoViewHolder holder, int position) {
    Contatto contatto = contattiList.get(position);

    holder.textNome.setText(contatto.getNome());
    holder.textTelefono.setText("Tel: " + contatto.getTelefono());
    holder.textEmail.setText("Email: " + contatto.getEmail());

    // Opcional: click listener
    holder.itemView.setOnClickListener(v ->
            Toast.makeText(context, "Cliccato: " + contatto.getNome(), Toast.LENGTH_SHORT).show()
    );
}

// Numero totale di elementi
@Override
public int getItemCount() {
    return contattiList.size();
}

// Metodo per aggiornare i dati (utile per refresh dinamici)
public void updateList(ArrayList<Contatto> newList) {
    this.contattiList = newList;
    notifyDataSetChanged(); // Aggiorna tutta la lista
}

}
```

4. Uso in MainActivity

```
public class MainActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private ContattiAdapter adapter;
private ArrayList<Contatto> contattiList;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = findViewById(R.id.recycler_view);

    // Inizializza i dati
    contattiList = new ArrayList<>();
    contattiList.add(new Contatto("Mario Rossi", "3331234567", "mario@example.com"));
    contattiList.add(new Contatto("Laura Bianchi", "3339876543", "laura@example.com"));
    contattiList.add(new Contatto("Giovanni Verdi", "3325551234", "giovanni@example.com"));

    // Configura RecyclerView
    adapter = new ContattiAdapter(this, contattiList);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);
}

}
```

**Concetti chiave:**
- *onCreateViewHolder* crea il ViewHolder gonfiando il layout XML.
- *onBindViewHolder* preleva il dato dalla posizione e lo assegna ai widget del ViewHolder.
- I dati sono memorizzati in una struttura Java (ArrayList, HashMap, ecc.).
- *notifyDataSetChanged()* avvisa RecyclerView che i dati sono cambiati.

# Gestione Strutture Dati: ArrayList vs HashMap

## ArrayList di oggetti
Quando usare: Quando i dati hanno un ordine naturale e si accedono per posizione.

### Esempio: lista di contatti ordinata per nome.

```
ArrayList<Contatto> contattiList = new ArrayList<>();
contattiList.add(new Contatto("Mario", "123", "mario@example.com"));
contattiList.add(new Contatto("Laura", "456", "laura@example.com"));
// Accesso per indice
Contatto c = contattiList.get(0); // Mario
Adapter corrispondente:
@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
Contatto contatto = contattiList.get(position);
holder.textNome.setText(contatto.getNome());
}
```

## HashMap per dati key-value
Quando usare: Quando i dati sono coppie chiave-valore (es. ID→Nome).

### Esempio: dizionario di traduzioni o catalogo di prodotti con ID univoco.

```
HashMap<String, String> traduzioni = new HashMap<>();
traduzioni.put("hello", "ciao");
traduzioni.put("goodbye", "arrivederci");
// Accesso per chiave
String traduzione = traduzioni.get("hello"); // "ciao"
```

**Problema**: HashMap non ha un ordine di iterazione garantito, quindi usarlo direttamente in RecyclerView è complicato.

**Soluzione**: Convertire HashMap in ArrayList prima di passarla all'Adapter.

### Esempio: Convertire HashMap in ArrayList

```
HashMap<String, String> produzioniMap = new HashMap<>();
produzioniMap.put("1", "Mela");
produzioniMap.put("2", "Banana");
produzioniMap.put("3", "Arancia");
// Conversione a ArrayList per RecyclerView
ArrayList<String> produzioniList = new ArrayList<>(produzioniMap.values());
// Oppure, se serve accesso alla chiave:
ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>(produzioniMap.entrySet());
// Nel Adapter:
@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
Map.Entry<String, String> entry = entryList.get(position);
String id = entry.getKey();
String nome = entry.getValue();
holder.textId.setText(id);
holder.textNome.setText(nome);
}
```

# Aggiornamenti dinamici dei dati

**Aggiornare singolo elemento**

```
// Modifica il dato
contattiList.get(posizione).setNome("Nuovo Nome");
// Notifica RecyclerView
adapter.notifyItemChanged(posizione);
```
**Aggiornare una fascia di elementi**

```
adapter.notifyItemRangeChanged(posizioneMio, 5); // 5 elementi a partire da posizioneMio
```

**Inserire un nuovo elemento**

```
contattiList.add(nuovoContatto);
adapter.notifyItemInserted(contattiList.size() - 1);
```

**Rimuovere un elemento**

```
contattiList.remove(posizione);
adapter.notifyItemRemoved(posizione);
```

**Aggiornare intera lista**
```
contattiList.clear();
contattiList.addAll(nuoviContatti);
adapter.notifyDataSetChanged();
```

# Esempio avanzato: RecyclerView con ClickListener

Spesso è necessario reagire ai click sugli elementi della lista.[web:35][web:39]

**Interfaccia callback**

```
public interface OnContattoClickListener {
void onContattoClick(Contatto contatto, int posizione);
}
```

**Adapter aggiornato**

```
public class ContattiAdapter extends RecyclerView.Adapter<ContattiAdapter.ContattoViewHolder> {
private ArrayList<Contatto> contattiList;
private OnContattoClickListener clickListener;

public ContattiAdapter(ArrayList<Contatto> contattiList, OnContattoClickListener clickListener) {
    this.contattiList = contattiList;
    this.clickListener = clickListener;
}

@Override
public void onBindViewHolder(@NonNull ContattoViewHolder holder, int position) {
    Contatto contatto = contattiList.get(position);

    holder.textNome.setText(contatto.getNome());
    holder.textTelefono.setText(contatto.getTelefono());

    // Click listener
    holder.itemView.setOnClickListener(v -> {
        if (clickListener != null) {
            clickListener.onContattoClick(contatto, position);
        }
    });
}

@Override
public int getItemCount() {
    return contattiList.size();
}

// ... resto del codice

}
```

**Uso in MainActivity**

```
adapter = new ContattiAdapter(contattiList, (contatto, posizione) -> {
Toast.makeText(this, "Cliccato: " + contatto.getNome() + " (posizione " + posizione + ")", Toast.LENGTH_SHORT).show();
// Puoi aprire una Activity di dettaglio, eliminare l'elemento, ecc.
});
recyclerView.setAdapter(adapter);
```

# Ottimizzazioni e Best Practice

## 1. ViewType per righe diverse

Se la lista contiene più tipi di layout (es. header, item normale, footer):

```
@Override
public int getItemViewType(int position) {
if (position == 0) return 0; // Header
if (position == contattiList.size() - 1) return 2; // Footer
return 1; // Item normale
}
@Override
public ContattoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
if (viewType == 0) {
View view = LayoutInflater.from(parent.getContext())
.inflate(R.layout.header_item, parent, false);
return new HeaderViewHolder(view);
} else if (viewType == 2) {
// ... footer layout
}
// Default: normal item
View view = LayoutInflater.from(parent.getContext())
.inflate(R.layout.contact_item, parent, false);
return new ContattoViewHolder(view);
}
```

## 2. Cleanup risorse nel ViewHolder

Per evitare leak di memoria, pulire risorse costose (Bitmap, thread, listener) quando il ViewHolder esce dalla vista:

```
@Override
public void onViewRecycled(@NonNull ContattoViewHolder holder) {
super.onViewRecycled(holder);
// Pulire risorse
holder.imageViewIcon.setImageBitmap(null);
}
```

## 3. Usar ListAdapter (variante moderna)

ListAdapter semplifica gli aggiornamenti dinamici usando DiffUtil (calcola le differenze):

```
public class ContattiAdapter extends ListAdapter<Contatto, ContattiAdapter.ContattoViewHolder> {
private static final DiffUtil.ItemCallback<Contatto> DIFF_CALLBACK =
        new DiffUtil.ItemCallback<Contatto>() {
    @Override
    public boolean areItemsTheSame(@NonNull Contatto oldItem, @NonNull Contatto newItem) {
        return oldItem.getNome().equals(newItem.getNome()); // Confronto ID logico
    }

    @Override
    public boolean areContentsTheSame(@NonNull Contatto oldItem, @NonNull Contatto newItem) {
        return oldItem.equals(newItem); // Confronto totale
    }
};

public ContattiAdapter() {
    super(DIFF_CALLBACK);
}

// ... resto codice

}
```

Poi in MainActivity:

```
adapter.submitList(nuovaLista); // Calcola differenze automaticamente
```
