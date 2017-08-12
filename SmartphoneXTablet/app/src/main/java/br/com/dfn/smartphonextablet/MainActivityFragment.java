package br.com.dfn.smartphonextablet;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivityFragment extends Fragment
        implements AdapterView.OnItemClickListener {

    private List<Jogador> list = new ArrayList();
    private JogadorAdapter adapter;
    private ListView lstView;
    private OnSelectedJogador mOnSelectedJogador;

    public MainActivityFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnSelectedJogador = (OnSelectedJogador) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(" must implement OnSelectedJogador");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnSelectedJogador = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        lstView = root.findViewById(R.id.lst);

        list.add(new Jogador("Neymar",
                "PSG",
                "https://pbs.twimg.com/profile_images/606753846531104770/cWznug_i.png",
                getString(R.string.bio)));

        list.add(new Jogador("Tiago SIlva",
                "PSG",
                "https://pbs.twimg.com/profile_images/606753846531104770/cWznug_i.png",
                getString(R.string.bio)));

        list.add(new Jogador("Daniel Alves",
                "PSG",
                "https://pbs.twimg.com/profile_images/606753846531104770/cWznug_i.png",
                getString(R.string.bio)));

        list.add(new Jogador("Pastore",
                "PSG",
                "https://pbs.twimg.com/profile_images/606753846531104770/cWznug_i.png",
                getString(R.string.bio)));

        adapter = new JogadorAdapter(getContext(), R.layout.jogado_item, list);

        lstView.setAdapter(adapter);
        lstView.setOnItemClickListener(this);
        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Jogador j = list.get(position);
        mOnSelectedJogador.onSelect(j);
    }

    public interface OnSelectedJogador {
        void onSelect(Jogador j);
    }
}
