package br.com.dfn.exemplofragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetalheFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetalheFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalheFragment extends Fragment implements View.OnClickListener {

    private TextView txtNome;
    private TextView txtClube;

    private String nome, clube;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalheFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_detalhe, container, false);
        txtNome = (TextView) root.findViewById(R.id.txtNome);
        txtClube = (TextView) root.findViewById(R.id.txtClube);
        if(nome != null) {
            txtNome.setText(nome);
            txtClube.setText(clube);
        }
        root.findViewById(R.id.btnAtualiza).setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAtualiza:
                nome = "Cristiano Ronaldo";
                clube = "Real Madrid";
                txtNome.setText(nome);
                txtClube.setText(clube);
                break;
        }
    }
}
