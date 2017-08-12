package com.fuctura.exemplocontentprovider;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by diego on 23/03/15.
 */
public class CarroCursorAdapter extends CursorAdapter {

    public CarroCursorAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.adp_carro,null);
    }

    @Override
    public long getItemId(int position) {
        Cursor cur = (Cursor) getItem(position);
        cur.moveToPosition(position);

        return cur.getLong(cur.getColumnIndexOrThrow(MeuHelper.COLUMN_ID));
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtNome = (TextView)view.findViewById(R.id.txtNome);
        TextView txtPlaca = (TextView)view.findViewById(R.id.txtPlaca);
        TextView txtAno = (TextView)view.findViewById(R.id.txtAno);

        txtNome.setText(
                cursor.getString(cursor.getColumnIndex(MeuHelper.COLUMN_NOME))
        );

        txtPlaca.setText(
                cursor.getString(cursor.getColumnIndex(MeuHelper.COLUMN_PLACA))
        );

        txtAno.setText(
                cursor.getString(cursor.getColumnIndex(MeuHelper.COLUMN_ANO))
        );
    }

    /*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v  = super.getView(position, convertView, parent);
        //ListView list = (ListView)parent;

        return v;
    }
    */
}
