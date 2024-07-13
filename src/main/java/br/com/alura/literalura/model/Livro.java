package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TB_LIVRO")
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String idioma;

    private int downloands;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getDownloands() {
        return downloands;
    }

    public void setDownloands(int downloands) {
        this.downloands = downloands;
    }

    @Override
    public String toString() {
        return "**********LIVRO********\n" +
            " Titulo = " + titulo + "\n" +
            " Autor = " + autor + "\n" +
            " Idioma = " + idioma + "\n" +
            " Número de downloands = " + downloands;
    }
}