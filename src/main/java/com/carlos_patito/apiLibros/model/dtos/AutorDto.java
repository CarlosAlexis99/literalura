package com.carlos_patito.apiLibros.model.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Date;

public record AutorDto(
        @JsonAlias("birth_year") Integer nacimiento,
        @JsonAlias("death_year") Integer fallecimiento,
        @JsonAlias("name") String nombre
) {
}
