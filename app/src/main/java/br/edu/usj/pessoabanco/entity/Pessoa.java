package br.edu.usj.pessoabanco.entity;

import java.io.Serializable;

/**
 * Created by rafael on 22/11/17.
 */

public class Pessoa implements Serializable {

    private int id;
    private int idade;
    private String nome;

    public Pessoa(){}

    public Pessoa(int id, int idade, String nome){
        this.id = id;
        this.idade = idade;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
