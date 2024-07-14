package com.carlos_patito.apiLibros.model.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;



    public Integer nacimiento;
    public Integer fallecimiento;
    public String nombre;

    public Autor( Integer nacimiento, Integer fallecimiento, String nombre) {
        this.fallecimiento = fallecimiento;
        this.nacimiento = nacimiento;
        this.nombre = nombre;
    }

    public Autor() {
    }

    @Override
    public String toString() {
        return "Autor{" +
                "  nacimiento='" + nacimiento + '\'' +
                ", fallecimiento='" + fallecimiento + '\'' +
                ", nombre='" + nombre + "\'}";
    }

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Libro> libros;
}
