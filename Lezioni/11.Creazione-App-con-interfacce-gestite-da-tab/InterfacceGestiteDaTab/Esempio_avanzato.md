# Esempio avanzato: Tab con RecyclerView
Scenario: una tab che contiene una lista di contatti gestita con RecyclerView.

ContactsFragment.java
```
public class ContactsFragment extends Fragment {
private RecyclerView recyclerView;
private ContactsAdapter adapter;
private ArrayList<String> contactsList;

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_contacts, container, false);
}

@Override
public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    recyclerView = view.findViewById(R.id.recycler_view);

    // Dati di esempio
    contactsList = new ArrayList<>();
    contactsList.add("Alice Bianchi");
    contactsList.add("Bob Rossi");
    contactsList.add("Charlie Verdi");

    // Adapter semplice (assume che ContactsAdapter esista)
    adapter = new ContactsAdapter(contactsList);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(adapter);
}

}
```
fragment_contacts.xml
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent" android:layout_height="match_parent" android:orientation="vertical" android:padding="16dp">
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recycler_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />

</LinearLayout>
```
