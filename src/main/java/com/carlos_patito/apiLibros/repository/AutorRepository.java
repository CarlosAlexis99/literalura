package com.carlos_patito.apiLibros.repository;

import com.carlos_patito.apiLibros.model.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

    Optional<Autor> findByNombre(String nombre);

    Optional<Autor> findFirstByNombre(String nombre);

    @Query("SELECT DISTINCT a FROM Autor a WHERE lower(a.nombre) LIKE lower(concat('%', :name, '%'))")
    Optional<Autor> findFirstByNameLike(String name);

//    @Query("SELECT DISTINCT a FROM Autor a WHERE YEAR(a.nacimiento) <= :year AND (a.fallecimiento IS NULL OR YEAR(a.fallecimiento) > :year)")
    @Query("SELECT DISTINCT a FROM Autor a WHERE :year BETWEEN a.nacimiento AND a.fallecimiento")
    List<Autor> autorVivoEn(Integer year);

}
