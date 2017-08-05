package br.com.dfn.exemplofragment2;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import br.com.dfn.exemplofragment2.model.Noticia;
import br.com.dfn.exemplofragment2.ui.DetalheNoticiaActivity;
import br.com.dfn.exemplofragment2.ui.DetalheNoticiaFragment;
import br.com.dfn.exemplofragment2.ui.ListNoticiaFragment;

public class MainActivity extends AppCompatActivity implements ListNoticiaFragment.OnSelectNoticiaListener {

    FragmentManager fm;
    ListNoticiaFragment listNoticiaFragment;
    DetalheNoticiaFragment detalheNoticiaFragment;

    public static final String PARAM_NOTICIA = "noticia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();

        if (isTablet()) {
            buildTablet();
        } else {
            buildPhone();
        }
    }

    public void buildPhone() {
        if (fm.findFragmentById(R.id.container) != null) {
            listNoticiaFragment =
                    (ListNoticiaFragment)
                            fm.findFragmentById(R.id.container);
        } else {
            listNoticiaFragment = new ListNoticiaFragment();
            listNoticiaFragment.setRetainInstance(true);

            fm.beginTransaction()
                    .add(R.id.container, listNoticiaFragment)
                    .commit();
        }
    }

    public void buildTablet() {
        if (fm.findFragmentById(R.id.containerDetalhe) != null) {
            listNoticiaFragment =
                    (ListNoticiaFragment)
                            fm.findFragmentById(R.id.container);
            detalheNoticiaFragment =
                    (DetalheNoticiaFragment)
                            fm.findFragmentById(R.id.containerDetalhe);
            fm.beginTransaction()
                    .show(listNoticiaFragment)
                    .show(detalheNoticiaFragment)
                    .commit();
        } else {
            listNoticiaFragment = new ListNoticiaFragment();
            listNoticiaFragment.setRetainInstance(true);

            detalheNoticiaFragment = new DetalheNoticiaFragment();
            detalheNoticiaFragment.setRetainInstance(true);
            fm.beginTransaction()
                    .add(R.id.container, listNoticiaFragment)
                    .add(R.id.containerDetalhe, detalheNoticiaFragment)
                    .commit();
        }
    }

    @Override
    public void onSelectNoticia(Noticia obj) {
        if(isTablet()) {
            detalheNoticiaFragment.carregarNoticia(obj);
        }else{
            Intent it = new Intent(this, DetalheNoticiaActivity.class);
            it.putExtra(PARAM_NOTICIA,obj);
            startActivity(it);
        }
    }

    public boolean isTablet() {
        return findViewById(R.id.containerDetalhe) != null;
    }
}
