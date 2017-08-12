package com.fuctura.applicationcontentprovider;

/**
 * Created by diego on 23/03/15.
 */
public class Carro {

    public long _id;
    public String nome;
    public String placa;
    public String ano;


    public Carro(long _id){
        this._id = _id;
    }
    public Carro(long _id, String nome, String placa, String ano) {
        this._id = _id;
        this.nome = nome;
        this.placa = placa;
        this.ano = ano;
    }
}
