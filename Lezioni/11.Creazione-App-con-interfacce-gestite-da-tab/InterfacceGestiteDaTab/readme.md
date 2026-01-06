#Esempio completo: App con 3 Tab (Home, Profilo, Impostazioni)
**Step 1: Dipendenze Gradle**

Aggiungi in build.gradle.kts (Module: app):
```
dependencies {
implementation("androidx.appcompat:appcompat:1.6.1")
implementation("com.google.android.material:material:1.11.0") // Per TabLayout
implementation("androidx.viewpager2:viewpager2:1.0.0") // Per ViewPager2
implementation("androidx.fragment:fragment:1.6.1") // Per Fragment
}
```
**Step 2: Layout principale (activity_main.xml)**
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <!-- TabLayout con le linguette -->
    <com.google.android.material.tabs.TabLayout
    android:id="@+id/tab_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="?attr/colorPrimary"
    app:tabTextColor="@android:color/white"
    app:tabSelectedTextColor="@android:color/white"
    app:tabIndicatorColor="@android:color/white"
    android:contentDescription="@string/descrizione_tab_layout" />

    <!-- ViewPager2 che contiene i Fragment -->
    <androidx.viewpager2.widget.ViewPager2
    android:id="@+id/view_pager"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1" />

    </LinearLayout>
```
Nota: Il TabLayout ha layout_weight="0" (dimensione minima) e ViewPager2 ha layout_weight="1" (occupa lo spazio rimanente).

**Step 3: Creare i Fragment**

HomeFragment.java
```
package com.example.interfaccegestitedatab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.text_home);
        textView.setText("Benvenuto nella schermata Home!");
    }
}
```
ProfiloFragment.java
```
package com.example.interfaccegestitedatab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfiloFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profilo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.text_profilo);
        textView.setText("Profilo Utente\n\nNome: Mario Rossi\nEmail: mariorossi@example.com");
    }
}
```
ImpostazioniFragment.java
```
package com.example.interfaccegestitedatab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ImpostazioniFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_impostazioni, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Switch switchNotifiche = view.findViewById(R.id.switch_notifiche);
        switchNotifiche.setChecked(true);

        switchNotifiche.setOnCheckedChangeListener((buttonView, isChecked) ->
                Toast.makeText(getContext(),
                        "Notifiche " + (isChecked ? "abilitate" : "disabilitate"),
                        Toast.LENGTH_SHORT).show()
        );
    }

}
```
Step 4: Layout dei Fragment

fragment_home.xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent" android:layout_height="match_parent" android:orientation="vertical" android:padding="16dp" android:gravity="center">
    <TextView
        android:id="@+id/text_home"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="24sp"
        android:textStyle="bold" />

</LinearLayout>
fragment_profilo.xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent" android:layout_height="match_parent" android:orientation="vertical" android:padding="16dp" android:gravity="center">
    <TextView
        android:id="@+id/text_profilo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="18sp"
        android:textAlignment="center" />

</LinearLayout>
fragment_impostazioni.xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Impostazioni"
        android:textSize="20sp"
        android:textStyle="bold"
        android:layout_marginBottom="16dp" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:gravity="center_vertical"
        android:minHeight="48dp">  <!-- Opzionale: garantisce altezza minima per l'intera riga -->

        <TextView
            android:id="@+id/label_notifiche"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Abilita notifiche"
            android:textSize="16sp" />

        <Switch
            android:id="@+id/switch_notifiche"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:minHeight="48dp"
            android:contentDescription="Attiva o disattiva le notifiche dell'app" />

    </LinearLayout>

</LinearLayout>
```
Step 5: FragmentStateAdapter per collegare Fragment a ViewPager2 (IGNORATO)
```
public class TabPagerAdapter extends FragmentStateAdapter {
public TabPagerAdapter(FragmentActivity activity) {
    super(activity);
}

@NonNull
@Override
public Fragment createFragment(int position) {
    switch (position) {
        case 0:
            return new HomeFragment();
        case 1:
            return new ProfiloFragment();
        case 2:
            return new ImpostazioniFragment();
        default:
            return new HomeFragment();
    }
}

@Override
public int getItemCount() {
    return 3; // 3 tab
}

}
````
Punto: createFragment(int position) crea il Fragment corretto in base alla posizione del tab.

**Step 6: MainActivity che configura TabLayout e ViewPager2**
```
public class MainActivity extends AppCompatActivity {
private TabLayout tabLayout;
private ViewPager2 viewPager;
private TabPagerAdapter adapter;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    tabLayout = findViewById(R.id.tab_layout);
    viewPager = findViewById(R.id.view_pager);

    // Crea l'adapter
    adapter = new TabPagerAdapter(this);
    viewPager.setAdapter(adapter);

    // Collega TabLayout a ViewPager2
    new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
        switch (position) {
            case 0:
                tab.setText("Home");
                break;
            case 1:
                tab.setText("Profilo");
                break;
            case 2:
                tab.setText("Impostazioni");
                break;
        }
    }).attach();
}

}
```
**Concetti chiave:**
•	TabLayoutMediator sincronizza il TabLayout con il ViewPager2: quando l'utente tocca un tab, il ViewPager2 va a quella posizione, e viceversa.
•	Il callback (tab, position) assegna il testo a ogni tab in base alla sua posizione.
