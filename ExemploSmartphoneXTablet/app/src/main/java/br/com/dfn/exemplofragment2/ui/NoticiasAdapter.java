package br.com.dfn.exemplofragment2.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.dfn.exemplofragment2.R;
import br.com.dfn.exemplofragment2.model.Noticia;

/**
 * Created by Diego on 10/01/2017.
 */

public class NoticiasAdapter extends ArrayAdapter<Noticia>{

    public NoticiasAdapter(Context context, List<Noticia> noticiaList) {
        super(context,0,noticiaList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Noticia obj = getItem(position);

        if(convertView == null){
            convertView =
                    LayoutInflater.from(getContext())
                    .inflate(R.layout.noticia_adp,parent,false);
        }

        TextView txtTitulo = (TextView)convertView.findViewById(R.id.txtTitulo);
        ImageView img = (ImageView)convertView.findViewById(R.id.img);

        txtTitulo.setText(obj.titulo);
        Picasso.with(getContext())
                .load(obj.urlImg)
                .into(img);


        return convertView;
    }
}
