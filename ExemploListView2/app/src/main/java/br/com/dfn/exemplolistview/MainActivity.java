package br.com.dfn.exemplolistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    List<Jogador> jogadorList;
    ListView lst;
    JogadorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jogadorList = new ArrayList<>();
        jogadorList.add(new Jogador("Neymar", "PSG"));
        jogadorList.add(new Jogador("Daniel Alves", "PSG"));
        jogadorList.add(new Jogador("Lucas Moura", "PSG"));
        jogadorList.add(new Jogador("Messi", "Barcelona"));

        adapter = new JogadorAdapter(getApplicationContext(),
                R.layout.item_jogador,
                jogadorList);

        lst = (ListView) findViewById(R.id.lst);
        lst.setAdapter(adapter);
        lst.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Jogador j = jogadorList.get(position);
        Toast.makeText(getApplicationContext(), "Jogador: " + j.name, Toast.LENGTH_LONG).show();

    }
}
