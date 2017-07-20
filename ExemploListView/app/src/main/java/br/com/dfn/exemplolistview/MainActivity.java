package br.com.dfn.exemplolistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Filme> filmes = new ArrayList<Filme>();
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFilmesFake();

        lstView = (ListView)findViewById(R.id.lstFilmes);
        FilmesAdapter adp
                = new FilmesAdapter(getApplicationContext(),filmes);
        lstView.setAdapter(adp);
    }

    private void loadFilmesFake(){
        filmes.add(new Filme(1,"Titanic","2001","https://upload.wikimedia.org/wikipedia/pt/e/ed/Tropa_de_Elite_2.jpg"));
        filmes.add(new Filme(2,"Panico","2002",""));
        filmes.add(new Filme(3,"Tropa de Elite","2007","https://upload.wikimedia.org/wikipedia/pt/e/ed/Tropa_de_Elite_2.jpg"));

    }

}
