package com.carlos_patito.apiLibros.repository;

import com.carlos_patito.apiLibros.model.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    @Query("SELECT l FROM Libro l WHERE id_gutendex = :idGutendex")
    Optional<Libro> findByGutendex(Integer idGutendex);

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> librosPorIdioma(String idioma);
}
