**Obiettivi di questa lezione**:
•	Comprendere come Activity e Fragment possano scambiarsi dati.  
•	Imparare a inviare informazioni da un’Activity a un Fragment.  
•	Imparare a inviare informazioni da un Fragment all’Activity.  
•	Fornire esempi pratici con codice commentato e esercizi per gli studenti.  

**Concetti chiave**:
•	Activity: gestisce il ciclo di vita e ospita i Fragment.  
•	Fragment: componente riutilizzabile che vive dentro un’Activity.  
•	La comunicazione è necessaria per sincronizzare UI e logica (es. un Fragment con una lista che invia al dettaglio nell’Activity).  

#Esempio 1: Comunicazione da Activity → Fragment
**Passaggio di dati con Bundle (java)**
```
// Activity
Bundle bundle = new Bundle();
bundle.putString("username", "Marino");

// Creazione Fragment con argomenti
MyFragment fragment = new MyFragment();
fragment.setArguments(bundle);

// Aggiunta del Fragment
getSupportFragmentManager()
    .beginTransaction()
    .replace(R.id.fragment_container, fragment)
    .commit();
```

**Ricezione nel Fragment (java)**
```
public class MyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        // Recupero dati dal Bundle
        if (getArguments() != null) {
            String username = getArguments().getString("username");
            Toast.makeText(getContext(), "Ciao " + username, Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}
```
**Nota**: il Bundle è il metodo standard per passare dati iniziali al Fragment.  

#Esempio 2: Comunicazione da Fragment → Activity
Uso di un’interfaccia (pattern consigliato) in java
```
// Fragment
public class MyFragment extends Fragment {

    // Definizione dell’interfaccia
    public interface OnDataPass {
        void onDataPass(String data);
    }

    private OnDataPass dataPasser;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context; // Activity deve implementare l’interfaccia
    }

    private void sendData() {
        dataPasser.onDataPass("Messaggio dal Fragment");
    }
}
```
Implementazione nell’Activity in java
```
public class MainActivity extends AppCompatActivity implements MyFragment.OnDataPass {

    @Override
    public void onDataPass(String data) {
        Toast.makeText(this, "Ricevuto: " + data, Toast.LENGTH_SHORT).show();
    }
}
```
**Nota**: questo approccio mantiene il Fragment indipendente e riutilizzabile.  

# Esempio 3: Comunicazione bidirezionale con ViewModel condiviso
Con **Android Jetpack** si può usare un `ViewModel` condiviso tra Activity e Fragment (java)

SharedViewModel.java
```
public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> message = new MutableLiveData<>();

    public void setMessage(String msg) { message.setValue(msg); }
    public LiveData<String> getMessage() { return message; }
}

Activity
SharedViewModel viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
viewModel.getMessage().observe(this, msg -> {
    Toast.makeText(this, "Activity riceve: " + msg, Toast.LENGTH_SHORT).show();
});

Fragment
SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
viewModel.setMessage("Ciao dall’Fragment!");
```
Nota: il `ViewModel` è il metodo moderno e consigliato per comunicazioni complesse.  

# Esercizi pratici
1. Creare un’Activity con due Fragment: uno con un campo di input e un pulsante, l’altro che mostra il testo ricevuto.  
2. Implementare la comunicazione via interfaccia.  
3. Rifare lo stesso esercizio usando un ViewModel condiviso.  
4. Estendere l’esempio: aggiungere un terzo Fragment che riceve lo stesso dato e lo mostra in modo diverso.  

**Errori comuni da anticipare**
•	Cast errato in `onAttach`→ se l’Activity non implementa l’interfaccia, si ottiene `ClassCastException`.  
•	Uso improprio di `getActivity() → può restituire `null` se il Fragment non è attaccato.  
•	Non osservare LiveData → il dato viene impostato ma non aggiornato in UI.  

**Spunti di approfondimento**
•	Comunicazione tra più Fragment usando Navigation Component.  
•	Uso di EventBus (meno consigliato, ma utile per capire alternative).  
•	Gestione di dati complessi (oggetti serializzabili o Parcelable).  

# Esempio 4: Progetto Android Studio che mostra la comunicazione tra Activity e Fragment in entrambe le direzioni, includendo anche l’approccio moderno con **ViewModel condiviso**. 
**Struttura del progetto**

MyActivityFragmentCommApp/
 ├── app/
 │   ├── src/
 │   │   ├── main/
 │   │   │   ├── java/com/example/myactivityfragmentcommapp/
 │   │   │   │   ├── MainActivity.java
 │   │   │   │   ├── InputFragment.java
 │   │   │   │   ├── DisplayFragment.java
 │   │   │   │   └── SharedViewModel.java
 │   │   │   ├── res/
 │   │   │   │   ├── layout/activity_main.xml
 │   │   │   │   ├── layout/fragment_input.xml
 │   │   │   │   ├── layout/fragment_display.xml
 │   │   │   │   ├── values/strings.xml
 │   │   │   │   └── values/themes.xml

activity_main.xml
```
//Layout con due container per i Fragment.

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <FrameLayout
        android:id="@+id/input_container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="1"/>

    <FrameLayout
        android:id="@+id/display_container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="1"/>

</LinearLayout>

fragment_input.xml
Fragment con campo di input e pulsante.

<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:padding="16dp"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <EditText
        android:id="@+id/editTextMessage"
        android:hint="Scrivi un messaggio"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"/>

    <Button
        android:id="@+id/buttonSend"
        android:text="Invia"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
</LinearLayout>

fragment_display.xml
Fragment che mostra il messaggio ricevuto.
<FrameLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <TextView
        android:id="@+id/textViewMessage"
        android:text="Nessun messaggio"
        android:textSize="18sp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
</FrameLayout>
```

SharedViewModel.java
```
//ViewModel condiviso per comunicazione bidirezionale.
package com.example.myactivityfragmentcommapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> message = new MutableLiveData<>();

    public void setMessage(String msg) {
        message.setValue(msg);
    }

    public LiveData<String> getMessage() {
        return message;
    }
}
```
MainActivity.java
```
//Carica i Fragment e osserva i dati.

package com.example.myactivityfragmentcommapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    SharedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        // Aggiunta dei Fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.input_container, new InputFragment());
        ft.replace(R.id.display_container, new DisplayFragment());
        ft.commit();
    }
}
```
InputFragment.java - Invia dati al ViewModel (Fragment → Activity → altri Fragment).
```
package com.example.myactivityfragmentcommapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class InputFragment extends Fragment {

    SharedViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        EditText editText = view.findViewById(R.id.editTextMessage);
        Button button = view.findViewById(R.id.buttonSend);

        button.setOnClickListener(v -> {
            String msg = editText.getText().toString();
            viewModel.setMessage(msg);
        });

        return view;
    }
}
```
DisplayFragment.java - Riceve dati dal ViewModel (Activity → Fragment).
```
package com.example.myactivityfragmentcommapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class DisplayFragment extends Fragment {

    SharedViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        TextView textView = view.findViewById(R.id.textViewMessage);

        // Osserva i cambiamenti
        viewModel.getMessage().observe(getViewLifecycleOwner(), msg -> {
            textView.setText(msg);
        });

        return view;
    }
}
```
**Esercizi**
1. Modificare il progetto per inviare un **oggetto Parcelable** invece di una stringa.  
2. Aggiungere un terzo Fragment che mostra il messaggio in maiuscolo.  
3. Implementare la comunicazione usando l’approccio con **interfaccia** e confrontarlo con il ViewModel.  
