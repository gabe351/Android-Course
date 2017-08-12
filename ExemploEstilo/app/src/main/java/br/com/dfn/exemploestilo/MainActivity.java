package br.com.dfn.exemploestilo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_PESSOA = "KEY_PESSOA";
    Pessoa p = new Pessoa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            p = (Pessoa) savedInstanceState
                    .getSerializable(KEY_PESSOA);
            ((TextView)findViewById(R.id.textView)).setText(p.name);
        }

        Toast.makeText(getApplicationContext(),
                "nome -> " + p.name,
                Toast.LENGTH_SHORT).show();
    }

    public void salvar(View v) {
        p.name = "TECDAM - 2017";
        ((TextView)findViewById(R.id.textView)).setText(p.name);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_PESSOA,  p);
        super.onSaveInstanceState(outState);
    }
}
