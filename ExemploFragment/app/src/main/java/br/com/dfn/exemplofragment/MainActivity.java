package br.com.dfn.exemplofragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DetalheFragment frgDetalhe;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();

        if(savedInstanceState == null){
            frgDetalhe = new DetalheFragment();
            frgDetalhe.setRetainInstance(true);

            fm.beginTransaction()
                    .add(R.id.container, frgDetalhe)
                    .commit();

        }else {
            frgDetalhe = (DetalheFragment)
                    fm.findFragmentById(R.id.container);

            fm.beginTransaction()
                    .show(frgDetalhe)
                    .commit();
        }
    }
}
