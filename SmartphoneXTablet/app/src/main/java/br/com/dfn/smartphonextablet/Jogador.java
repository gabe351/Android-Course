package br.com.dfn.smartphonextablet;

import java.io.Serializable;

public class Jogador implements Serializable {
    public String nome;
    public String club;
    public String fotoUrl;
    public String biografia;

    public Jogador(String nome, String club, String fotoUrl, String biografia) {
        this.nome = nome;
        this.club = club;
        this.fotoUrl = fotoUrl;
        this.biografia = biografia;
    }
}
