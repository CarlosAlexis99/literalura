package com.carlos_patito.apiLibros.service;

import com.carlos_patito.apiLibros.model.entities.Autor;
import com.carlos_patito.apiLibros.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autor;

    public List<Autor> devolverTodos(){
        return autor.findAll();
    }

    public Optional<Autor> autorPorNombre(String nombre){
        return autor.findFirstByNameLike(nombre);
    }

    public List<Autor> devolverAutores(){
        return autor.findAll();
    }

    public List<Autor> vivoEnElAnioTal(int year){
        return autor.autorVivoEn(year);
    }


}
