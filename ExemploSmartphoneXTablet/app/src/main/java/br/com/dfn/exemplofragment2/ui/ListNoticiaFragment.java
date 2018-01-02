package br.com.dfn.exemplofragment2.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.dfn.exemplofragment2.R;
import br.com.dfn.exemplofragment2.model.Noticia;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnSelectNoticiaListener} interface
 * to handle interaction events.
 */
public class ListNoticiaFragment extends Fragment implements AdapterView.OnItemClickListener {

    private OnSelectNoticiaListener mListener;
    private ListView rootView;
    private List<Noticia> noticiaList;

    public ListNoticiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = (ListView) inflater.inflate(R.layout.fragment_list_noticia, container, false);
        init();

        return rootView;
    }

    private void init() {
        noticiaList = new ArrayList<>();
        noticiaList.add(new Noticia(
                "Conta do Governo Federal no Twitter publica acidentalmente."
                , "Sabe quando você faz um post no Twitter, compartilhando uma notícia para os seus 500 mil seguidores, e sem querer cola o link para um arquivo que contém todas as suas senhas? Não? Pois o Governo Federal sabe."
                , "http://imagens2.ne10.uol.com.br/blogsne10/mundobit/uploads//2017/01/Twit.jpg"));
        noticiaList.add(new Noticia(
                "Duplo atentado talibã em Cabul deixa ao menos 30 mortos e 80 feridos"
                , "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
                , "http://imagens4.ne10.uol.com.br/ne10/imagem/noticia/2017/01/10/normal/fe82a682f6162ad26d8cf4e1272ad1ad.jpg"));
        noticiaList.add(new Noticia(
                "Divulgado o gabarito do segundo dia do SSA 1 da UPE"
                , "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"
                , "http://imagens4.ne10.uol.com.br/vestibular/2017/01/DSCN2554.jpg"));

        NoticiasAdapter adapter = new NoticiasAdapter(getContext(), noticiaList);
        rootView.setAdapter(adapter);
        rootView.setOnItemClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSelectNoticiaListener) {
            mListener = (OnSelectNoticiaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSelectNoticiaListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        mListener.onSelectNoticia(noticiaList.get(position));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSelectNoticiaListener {
        void onSelectNoticia(Noticia obj);
    }
}
