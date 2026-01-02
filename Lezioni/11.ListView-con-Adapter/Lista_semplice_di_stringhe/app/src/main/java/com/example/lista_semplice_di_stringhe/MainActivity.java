package com.example.lista_semplice_di_stringhe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);

        // Dati: semplice array di stringhe
        String[] frutti = {
                "Mela",
                "Banana",
                "Arancia",
                "Fragola",
                "Uva"
        };

        // ArrayAdapter con layout predefinito (simple_list_item_1)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                frutti
        );

        // Assegna l'adapter alla ListView
        listView.setAdapter(adapter);

        // Click listener: reagire ai tap sugli elementi
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selected = frutti[position];
            Toast.makeText(MainActivity.this, "Selezionato: " + selected, Toast.LENGTH_SHORT).show();
        });
    }

}
