package com.carlos_patito.apiLibros.model.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataDto(
        @JsonAlias("results")List<LibroDto> results
        ) {
}
