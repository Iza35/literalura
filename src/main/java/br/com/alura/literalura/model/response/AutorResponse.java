package br.com.alura.literalura.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.io.Serializable;

public class AutorResponse implements Serializable {

    @JsonAlias("name")
    private String nome;

    @JsonAlias("birth_year")
    private int anoDeNascimento;

    @JsonAlias("death_year")
    private int anoDeFalecimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(int anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public int getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    public void setAnoDeFalecimento(int anoDeFalecimento) {
        this.anoDeFalecimento = anoDeFalecimento;
    }


    @Override
    public String toString() {
        return "" + nome  +
            ", Ano de Nascimento = " + anoDeNascimento +
            ", Ano de Falecimento = " + anoDeFalecimento;
    }
}



