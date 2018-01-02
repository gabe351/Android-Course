package br.com.dfn.exemplofragment2.model;

import java.io.Serializable;

/**
 * Created by Diego on 10/01/2017.
 */

public class Noticia implements Serializable{

    public String titulo;
    public String resumo;
    public String urlImg;

    public Noticia(String titulo, String resumo,
                   String urlImg){
        this.titulo = titulo;
        this.resumo = resumo;
        this.urlImg = urlImg;
    }
}
