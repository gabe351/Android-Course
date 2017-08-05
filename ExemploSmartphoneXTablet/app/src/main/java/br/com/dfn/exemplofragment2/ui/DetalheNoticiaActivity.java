package br.com.dfn.exemplofragment2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.dfn.exemplofragment2.MainActivity;
import br.com.dfn.exemplofragment2.R;
import br.com.dfn.exemplofragment2.model.Noticia;

public class DetalheNoticiaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_noticia);
        TextView txtResumo = (TextView)findViewById(R.id.txtResumo);
        ImageView imagem = (ImageView) findViewById(R.id.imagem);

        if(getIntent().getExtras()!= null){
            Noticia noticia =
                    (Noticia)getIntent()
                            .getSerializableExtra(MainActivity.PARAM_NOTICIA);
            txtResumo.setText(noticia.resumo);
            Picasso.with(this)
                    .load(noticia.urlImg)
                    .into(imagem);
        }

    }
}
