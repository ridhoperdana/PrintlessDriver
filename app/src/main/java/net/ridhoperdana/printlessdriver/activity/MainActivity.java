package net.ridhoperdana.printlessdriver.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.ridhoperdana.printlessdriver.kelas_penampung.Client;
import net.ridhoperdana.printlessdriver.recycler_view.CustomAdapter;
import net.ridhoperdana.printlessdriver.R;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView;
        LinearLayoutManager linearLayoutManager;
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        ArrayList<Client> list = new ArrayList<>();
        Client client = new Client("John", "airlangga", "proposal_ta" ,-7.2724699, 112.7485652, "dicetak hitam putih, sekalian dijilid ya", 0);
        Client client2 = new Client("Ridho", "airlangga", "buku_kp" ,-7.2701809, 112.8094324, "dicetak ukuran A5, jilid cover transparan", 1);
        list.add(client);
        list.add(client2);
        CustomAdapter adapter = new CustomAdapter(list, this);
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
