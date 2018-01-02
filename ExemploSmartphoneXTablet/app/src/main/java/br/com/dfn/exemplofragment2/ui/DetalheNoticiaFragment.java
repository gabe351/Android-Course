package br.com.dfn.exemplofragment2.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import br.com.dfn.exemplofragment2.R;
import br.com.dfn.exemplofragment2.model.Noticia;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalheNoticiaFragment extends Fragment {

    private RelativeLayout rootView;
    private TextView txtResumo;
    private ImageView imageView;
    private Noticia mNoticia;



    public DetalheNoticiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (RelativeLayout)inflater.inflate(R.layout.activity_detalhe_noticia,null);
        txtResumo = (TextView)rootView.findViewById(R.id.txtResumo);
        imageView = (ImageView) rootView.findViewById(R.id.imagem);

        return rootView;
    }

    public void carregarNoticia(Noticia obj){
        if(obj != null){
            txtResumo.setText(obj.resumo);
            Picasso.with(getContext())
                    .load(obj.urlImg)
                    .into(imageView);
        }
        mNoticia = obj;
    }

    @Override
    public void onResume() {
        super.onResume();
        carregarNoticia(mNoticia);
    }
}
