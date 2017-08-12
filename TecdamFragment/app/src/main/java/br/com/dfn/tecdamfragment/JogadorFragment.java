package br.com.dfn.tecdamfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class JogadorFragment extends Fragment {

    TextView textView;
    TextView textView2;
    Jogador j;
    OnSelectJogador mOnSelectJogador;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnSelectJogador = (OnSelectJogador) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("The OnSelectJogador interface must be implemented !");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnSelectJogador = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout, container, false);
        textView = root.findViewById(R.id.textView);
        textView2 = root.findViewById(R.id.textView2);
        root.findViewById(R.id.btnSalvar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j = new Jogador();
                j.nome = "Diego Souza";
                j.club = "Sport";
                textView.setText(j.nome);
                textView2.setText(j.club);

                mOnSelectJogador.onJogadorChange(j);
            }
        });

        if (j != null) {
            textView.setText(j.nome);
            textView2.setText(j.club);
        }

        return root;
    }

    public interface OnSelectJogador {
        void onJogadorChange(Jogador j);
    }
}
