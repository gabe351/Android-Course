package br.com.dfn.exemplolistview;

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

/**
 * Created by Diego on 09/01/2017.
 */

public class FilmesAdapter extends ArrayAdapter<Filme>{

    public FilmesAdapter(Context context, List<Filme> filmes) {
        super(context,0,filmes);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Filme obj = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.adp_filmes, parent, false);
        }

        ImageView img = (ImageView)convertView.findViewById(R.id.img);
        TextView txtNome = (TextView)convertView.findViewById(R.id.txtNome);
        TextView txtAno = (TextView)convertView.findViewById(R.id.txtAno);

        txtNome.setText(obj.nome);
        txtAno.setText(obj.ano);

        Picasso.with(getContext())
                .load(obj.urlImg)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(img);

        return convertView;
    }
}
