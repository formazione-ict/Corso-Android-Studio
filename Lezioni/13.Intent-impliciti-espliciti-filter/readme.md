# Gli Intent impliciti, espliciti e Intent-filter

**Schema riassuntivo:**
- Spiegazione concettuale: cos’è un Intent e perché si usa.
- Intent espliciti:
  -	Esempio: MainActivity → DetailActivity con passaggio di dati (`putExtra`).
- Intent impliciti:
  -	Esempio: aprire una pagina web (ACTION_VIEW + URL).
  -	Esempio: condividere testo (ACTION_SEND + chooser).
-	Intent filter nel Manifest:
  -	Esempio: Activity principale (MAIN + LAUNCHER).
  -	Esempio avanzato: schema URI personalizzato e deep link con ACTION_VIEW e `<data>`.

## Spiegazione concettuale
In Android gli Intent servono per richiedere ad altre componenti (Activity, Service, BroadcastReceiver) di eseguire un’azione.

Possono essere espliciti (indicano una classe precisa) o impliciti (descrivono un’azione da svolgere) e gli intent-filter dichiarano quali Intent una Activity può ricevere.

**Concetti di base sugli Intent**
- Un Intent rappresenta una “intenzione” di eseguire un’azione (es. aprire una Activity, condividere testo, aprire la fotocamera).
- Componenti tipici coinvolti: Activity (schermate), Service (lavoro in background), BroadcastReceiver (ricezione eventi di sistema o app).
- Due categorie principali per le Activity: Intent espliciti (specificano la classe di destinazione) e Intent impliciti (specificano cosa si vuole fare, non chi lo farà).

## Intent espliciti
Gli Intent espliciti vengono usati quando si conosce esattamente quale Activity (o Service) aprire all’interno della propria app.library

#### Esempio 1 – Passare da MainActivity a DetailActivity

Obiettivo didattico: mostrare come passare da una schermata all’altra e come passare dati extra.

Layout (activity_main.xml)
```
//Bottone per aprire il dettaglio.
<Button
    android:id="@+id/button_open_detail"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Apri dettaglio" />
```

MainActivity.java

```
public class MainActivity extends AppCompatActivity {

    private Button buttonOpenDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOpenDetail = findViewById(R.id.button_open_detail);

        buttonOpenDetail.setOnClickListener(v -> {
            // Intent esplicito: dalla MainActivity alla DetailActivity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);

            // Aggiungo dati extra
            intent.putExtra("USERNAME", "Mario Rossi");
            intent.putExtra("AGE", 25);

            startActivity(intent);
        });
    }
}
```

DetailActivity.java

```
public class DetailActivity extends AppCompatActivity {

    private TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewInfo = findViewById(R.id.text_view_info);

        // Recupero degli extra dall'Intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");
        int age = intent.getIntExtra("AGE", -1);

        String message = "Utente: " + username + " - Età: " + age;
        textViewInfo.setText(message);
    }
}
```

**Punti chiave:**
-	Il costruttore dell’Intent esplicito riceve il Context e la classe della destinazione.
- *putExtra* e *getExtra* permettono di passare dati tra Activity in modo semplice.

## Intent impliciti
Gli Intent impliciti descrivono un’azione da compiere (es. “mostra questa pagina web”, “condividi questo testo”), lasciando ad Android la scelta dell’app più adatta.

### Esempio 2 – Aprire una pagina web nel browser
Obiettivo: mostrare un Intent implicito con azione ACTION_VIEW e un URI in java.

```
public class MainActivity extends AppCompatActivity {

    private Button buttonOpenWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOpenWeb = findViewById(R.id.button_open_web);

        buttonOpenWeb.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.google.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

            // Verifica che esista almeno un'app in grado di gestire l'Intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });
    }
}
```

*Concetti da sottolineare:*
- ACTION_VIEW indica che si vuole “visualizzare” il contenuto di un URI (URL, contatto, mappa, ecc.).
- *resolveActivity* è una buona pratica per evitare crash se non esistono app compatibili.

### Esempio 3 – Condividere testo con altre app
Obiettivo: mostrare Intent implicito con *ACTION_SEND* e *Intent.createChooser* in java.

```
private void shareText(String textToShare) {
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
    sendIntent.setType("text/plain");

    Intent shareIntent = Intent.createChooser(sendIntent, "Condividi tramite");
    startActivity(shareIntent);
}
```

*Spiegazione:*
- *ACTION_SEND* indica un’operazione di invio/condivisione.
- *createChooser* forza la comparsa del dialog di scelta app, non usa direttamente l’ultima app usata.

## Intent filter: come “dichiarare” cosa può fare una Activity

Gli intent-filter si dichiarano nel AndroidManifest.xml e definiscono quali Intent impliciti una Activity (o altro componente) è in grado di gestire.

*Elementi principali di un intent-filter:*
- *<action>*: l’azione (es. *android.intent.action.VIEW*).
•	*<category>*: categorie aggiuntive (es. DEFAULT, BROWSABLE).
•	*<data>*: il tipo di dati supportati (MIME type, schema di URI, host, path).

### Esempio 4 – Activity avviata all’avvio app (MAIN + LAUNCHER)
Questo esempio mostra il tipico intent-filter della Activity principale in xml.

```
<activity
    android:name=".MainActivity"
    android:exported="true">

    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>

</activity>
```

*Note:*
- MAIN indica il punto di ingresso principale.
- LAUNCHER indica che la Activity deve comparire nel launcher.

### Esempio completo: Activity che riceve un Intent implicito VIEW su URL personalizzato

**Scenario**: l’app dichiara un proprio schema URI (myapp://details/...) e una Activity che si apre quando un’altra app invia un Intent implicito ACTION_VIEW con quello schema.

**Manifest**: intent filter per schema personalizzato in xml.

```
<activity
    android:name=".DeepLinkActivity"
    android:exported="true">

    <intent-filter>
        <action android:name="android.intent.action.VIEW" />

        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />

        <data
            android:scheme="myapp"
            android:host="details" />
    </intent-filter>

</activity>
```

*Significato:*
- L’Activity risponde a Intent ACTION_VIEW indirizzati a URI come myapp://details.library
- BROWSABLE permette di aprire l’Activity anche da browser o link di terze parti.library

*Activity che legge i dati dal deep link in java*

```
public class DeepLinkActivity extends AppCompatActivity {

    private TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deeplink);

        textViewInfo = findViewById(R.id.text_view_info);

        Intent intent = getIntent();
        Uri data = intent.getData();

        if (data != null) {
            String scheme = data.getScheme();     // "myapp"
            String host = data.getHost();         // "details"
            String id = data.getQueryParameter("id"); // es. myapp://details?id=123

            String msg = "Schema: " + scheme +
                         "\nHost: " + host +
                         "\nID: " + id;
            textViewInfo.setText(msg);
        } else {
            textViewInfo.setText("Nessun dato ricevuto");
        }
    }
}
```
### Esempio di Intent che può aprire questa Activity da un’altra Activity (Intent implicito) in java:

```
Uri uri = Uri.parse("myapp://details?id=123");
Intent intent = new Intent(Intent.ACTION_VIEW, uri);
startActivity(intent);
```

**Messaggi chiave:**
- L’associazione tra Intent implicito e Activity avviene grazie all’intent-filter nel Manifest.
- Le parti dell’URI (schema, host, query) possono essere usate per personalizzare il comportamento.
