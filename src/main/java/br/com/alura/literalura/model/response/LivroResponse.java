package br.com.alura.literalura.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class LivroResponse implements Serializable {

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<AutorResponse> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private int downloands;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<AutorResponse> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorResponse> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public int getDownloands() {
        return downloands;
    }

    public void setDownloands(int downloands) {
        this.downloands = downloands;
    }

    public String toString() {
        return "LivroResponse{" +
            "Titulo='" + titulo + '\'' +
            ", Autores=" + autores +
            ", Idiomas=" + idiomas +
            ", Downloands=" + downloands +
            '}';
    }
}


