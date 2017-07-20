package br.com.dfn.exemplolistview;

/**
 * Created by Diego on 09/01/2017.
 */

public class Filme {

    public int id;
    public String nome;
    public String ano;
    public String urlImg;

    public Filme(int id, String nome,
                 String ano, String urlImg){
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.urlImg = urlImg;
    }
}
