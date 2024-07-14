package com.carlos_patito.apiLibros.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public Integer id_gutendex;
    public String titulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Autor autor;

    public String idioma;


    public Integer numeroDeDescargas;

    public Libro(Integer integer, String titulo, String s, Integer numeroDeDescargas) {
    }

    public Libro(Integer id_gutendex, String titulo, Autor autor, String idioma, Integer numeroDeDescargas) {
        this.id_gutendex = id_gutendex;
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Libro() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_gutendex() {
        return id_gutendex;
    }

    public void setId_gutendex(Integer id_gutendex) {
        this.id_gutendex = id_gutendex;
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

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

//    @Override
//    public String toString() {
//        return "Libro{" +
//                "id=" + id  +
//                "\n id_gutendex=" + id_gutendex +
//                "\n titulo='" + titulo + '\'' +
////                "\n autor=" + autor +
//                "\n idioma='" + idioma + '\'' +
//                "\n numeroDeDescargas=" + numeroDeDescargas +
//                '}';
//    }
}
