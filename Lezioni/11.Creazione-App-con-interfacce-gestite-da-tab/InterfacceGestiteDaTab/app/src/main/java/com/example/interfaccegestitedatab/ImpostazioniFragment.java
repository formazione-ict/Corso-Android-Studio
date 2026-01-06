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
