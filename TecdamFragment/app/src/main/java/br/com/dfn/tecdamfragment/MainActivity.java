package br.com.dfn.tecdamfragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements JogadorFragment.OnSelectJogador {

    JogadorFragment fragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        if (fragmentManager
                .findFragmentById(R.id.container) != null) {
            fragment = (JogadorFragment) fragmentManager
                    .findFragmentById(R.id.container);
        } else {
            fragment = new JogadorFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }

    }

    @Override
    public void onJogadorChange(Jogador j) {
        Toast.makeText(getApplicationContext(),
                j.nome,
                Toast.LENGTH_SHORT).show();
    }
}
