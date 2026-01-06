package com.example.arraylist_dinamico;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> frutti;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        editText = findViewById(R.id.edit_text_frutto);
        Button buttonAdd = findViewById(R.id.button_aggiungi);

        // ArrayList inizializzato con dati
        frutti = new ArrayList<>();
        frutti.add("Mela");
        frutti.add("Banana");
        frutti.add("Arancia");

        // Adapter collegato alla ArrayList
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                frutti
        );

        listView.setAdapter(adapter);

        // Bottone per aggiungere elementi
        buttonAdd.setOnClickListener(v -> {
            String nuovoFrutto = editText.getText().toString().trim();
            if (!nuovoFrutto.isEmpty()) {
                frutti.add(nuovoFrutto);
                adapter.notifyDataSetChanged(); // Aggiorna la UI
                editText.setText("");
            }
        });
    }

}
