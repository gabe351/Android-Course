package br.com.dfn.smartphonextablet;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class JogadorAdapter extends ArrayAdapter<Jogador> {

    public JogadorAdapter(@NonNull Context context,
                          @LayoutRes int resource,
                          @NonNull List<Jogador> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Jogador j = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.jogado_item,parent,false);
        }

        TextView txtNome = convertView.findViewById(R.id.txtNome);
        TextView txtClub = convertView.findViewById(R.id.txtClub);

        txtNome.setText(j.nome);
        txtClub.setText(j.club);

        return convertView;
    }
}
