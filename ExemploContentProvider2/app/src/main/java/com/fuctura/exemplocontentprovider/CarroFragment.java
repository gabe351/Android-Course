package com.fuctura.exemplocontentprovider;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by diego on 24/03/15.
 */
public class CarroFragment extends Fragment implements
        AdapterView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {


    CarroRepository mRepository;
    CarroCursorAdapter mAdapter;
    ListView lst;
    Button btnInsert;
    EditText edtNome;
    EditText edtPlaca;
    EditText edtAno;


    public CarroFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout root = (LinearLayout)
                inflater.inflate(R.layout.fragment_main, null);
        edtNome = (EditText) root.findViewById(R.id.edtNome);
        edtPlaca = (EditText) root.findViewById(R.id.edtPlaca);
        edtAno = (EditText) root.findViewById(R.id.edtAno);

        btnInsert = (Button)root.findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);


        lst = (ListView) root.findViewById(R.id.lst);
        mAdapter = new CarroCursorAdapter(getActivity(), null);
        lst.setAdapter(mAdapter);
        lst.setOnItemClickListener(this);
        getLoaderManager().initLoader(0, null, this);

        return root;


    }



    // AdapterView.OnItemClickListener -----------------------------------------------------------//
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cur = (Cursor) mAdapter.getItem(position);
        cur.moveToPosition(position);
       //long id = cur.getLong(cur.getColumnIndexOrThrow(MeuHelper.COLUMN_ID));
        mRepository.delete(new Carro(id));



    }


    // LoaderManager.LoaderCallbacks<Cursor> ----------------------------------------------------//
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        mRepository = new CarroRepository(getActivity());
        return mRepository.find(getActivity(), null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }



    // View.OnClickListener ----------------------------------------------------------------------//
    @Override
    public void onClick(View v) {
        Carro c = new Carro(0,edtNome.getText().toString(),
                edtPlaca.getText().toString(),
                edtAno.getText().toString());

        mRepository.insert(c);
    }
}
