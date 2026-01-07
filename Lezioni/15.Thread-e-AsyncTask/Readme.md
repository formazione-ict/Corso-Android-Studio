#Thread e AsyncTask

Servono per eseguire operazioni in background senza bloccare la UI; oggi AsyncTask è deprecato ma resta utile da spiegare come esempio storico e per leggere vecchi progetti.developer.android+1

## Concetti di base: Main Thread e UI
•	In Android tutta la UI gira sul Main thread (UI thread). 
•	Operazioni lunghe (rete, accesso disco, calcoli pesanti) sul Main thread causano ANR (App Not Responding) e blocchi dell’interfaccia. 
•	Soluzione: spostare il lavoro pesante su thread secondari e tornare sul Main thread solo per aggiornare la UI. 

## Thread in Android (Java)
Un Thread è un’unità di esecuzione concorrente; in Android si usano per eseguire codice in background. 

### Esempio 1 – Thread “puro” con Runnable
Esempio didattico: l’utente preme un bottone, parte un conteggio lento in background e la UI mostra un messaggio finale usando un Handler verso il Main thread. 

```
public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private TextView resultTextView;

    // Handler associato al Main thread
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start_button);
        resultTextView = findViewById(R.id.result_text_view);

        startButton.setOnClickListener(v -> startLongOperation());
    }

    private void startLongOperation() {
        resultTextView.setText("Operazione in corso...");

        // Nuovo thread in background
        new Thread(() -> {
            try {
                // Simulazione operazione lunga (3 secondi)
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Torno sul Main thread per aggiornare la UI
            handler.post(() ->
                    resultTextView.setText("Operazione completata!")
            );
        }).start();
    }
}
```

**Punti chiave:**
- new Thread(() -> {...}).start(); crea e avvia il thread in background. 
- Non si può aggiornare la UI direttamente dal thread secondario: si usa Handler legato al Looper.getMainLooper() per postare il codice sul Main thread. 

### Esempio 2 – Thread che aggiorna una ProgressBar
Esempio leggermente più evoluto: una ProgressBar viene aggiornata gradualmente da un thread in background. 

```
public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button startButton;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_progress);

        progressBar = findViewById(R.id.progress_bar);
        startButton = findViewById(R.id.start_button);

        startButton.setOnClickListener(v -> startProgress());
    }

    private void startProgress() {
        progressBar.setProgress(0);

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(500); // Simula lavoro
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int value = i * 10;
                handler.post(() -> progressBar.setProgress(value));
            }
        }).start();
    }
}
```

**Nota:**
- Separare sempre lavoro (loop, sleep, rete) dal codice che tocca la UI. 
- In progetti reali, Gestire la cancellazione del thread e la chiusura dell’Activity per evitare leak. 
________________________________________
## AsyncTask: struttura e uso (deprecato)
AsyncTask era una classe di supporto per eseguire operazioni in background e aggiornare la UI in modo più semplice, ora deprecata da Android 11 (API 30). 

**Caratteristiche principali:**
- Template con metodi chiave: onPreExecute(), doInBackground(), onProgressUpdate(), onPostExecute().
- doInBackground() gira in background; onPreExecute, onProgressUpdate e onPostExecute sul Main thread per interagire con la UI. 
- Deprecata per problemi di leak, gestione ciclo di vita e comportamento incoerente su versioni diverse. 

### Esempio 3 – AsyncTask semplice con ProgressBar/Testo

**Scenario**: bottone che avvia un conteggio in background, con aggiornamento del testo e della ProgressBar. 

```
public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private ProgressBar progressBar;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask_example);

        startButton = findViewById(R.id.start_button);
        progressBar = findViewById(R.id.progress_bar);
        statusText = findViewById(R.id.status_text);

        progressBar.setMax(100);

        startButton.setOnClickListener(v -> {
            // Avvio dell’AsyncTask
            new MyTask().execute(10); // Numero di passi
        });
    }

    // Parametri: Integer (input), Integer (progress), String (result)
    private class MyTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            statusText.setText("Inizio operazione...");
            progressBar.setProgress(0);
            startButton.setEnabled(false);
        }

        @Override
        protected String doInBackground(Integer... params) {
            int steps = params[0];

            for (int i = 1; i <= steps; i++) {
                try {
                    Thread.sleep(500); // Simula operazione lunga
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Calcolo progresso percentuale
                int progress = (i * 100) / steps;

                // Invoca onProgressUpdate sul Main thread
                publishProgress(progress);
            }

            return "Operazione completata con successo!";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int progress = values[0];
            progressBar.setProgress(progress);
            statusText.setText("Progresso: " + progress + "%");
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            statusText.setText(result);
            startButton.setEnabled(true);
        }
    }
}
```

**Osservazioni:**
- Parametri generici <Params, Progress, Result> e relativa corrispondenza nei metodi. 
- `execute(...)` avvia il task; `doInBackground()` non può aggiornare direttamente la UI, ma usa `publishProgress()` che chiama `onProgressUpdate()` sul Main thread. 
- `onPostExecute()` viene chiamato alla fine per aggiornare la UI con il risultato. 

## Perché AsyncTask è deprecato e alternative moderne

**Best practice attuali:**
- Problemi di memory leak: AsyncTask come inner class non statica mantiene un riferimento implicito all’Activity. 
- Non è lifecycle aware: se l’utente ruota lo schermo o chiude l’Activity, il task può continuare e provare ad aggiornare una UI distrutta, causando crash. 
- Gestione thread poco controllabile (pool condiviso, ordine di esecuzione non sempre prevedibile). 

**Alternative raccomandate oggi:**
- Per Java: ExecutorService e API java.util.concurrent per thread pool più controllabili. 
- Per Kotlin: coroutines (soprattutto con ViewModel e viewModelScope) per codice asincrono più leggibile e lifecycle aware. 
- Per lavori programmati o “lungi”: WorkManager o JobScheduler. 

**Suggerimenti:*
- Introduzione teorica: Main thread, ANR, perché servono thread/background. 
- Esempio 1: Thread + Handler con TextView. 
- Esempio 2: Thread + Handler con ProgressBar. 
- Esempio 3: AsyncTask completo (con discussione sui problemi e sulla deprecazione). 
•	Alternative moderne (Executor, coroutines, WorkManager) e indicazione che nei progetti nuovi AsyncTask non va più usato. 

