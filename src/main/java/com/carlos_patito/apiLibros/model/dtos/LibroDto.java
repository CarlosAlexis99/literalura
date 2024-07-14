package com.carlos_patito.apiLibros.model.dtos;

import com.carlos_patito.apiLibros.model.entities.Autor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.ManyToOne;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDto (
        @JsonAlias("id") Integer id_gutendex,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<AutorDto> autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Integer numeroDeDescargas
){
}
