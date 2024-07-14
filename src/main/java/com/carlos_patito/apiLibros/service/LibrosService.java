package com.carlos_patito.apiLibros.service;

import com.carlos_patito.apiLibros.model.dtos.LibroDto;
import com.carlos_patito.apiLibros.model.entities.Autor;
import com.carlos_patito.apiLibros.model.entities.Libro;
import com.carlos_patito.apiLibros.repository.AutorRepository;
import com.carlos_patito.apiLibros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibrosService {

    @Autowired
    public LibroRepository repository;
    @Autowired
    public AutorRepository repositoryAutor;

    public void save(LibroDto libroDto){
        System.out.println(libroDto);
        Libro libro = new Libro();
        Autor autor = new Autor(libroDto.autor().get(0).nacimiento(),libroDto.autor().get(0).fallecimiento(), libroDto.autor().get(0).nombre());

        Optional<Autor> existingAuthor = repositoryAutor.findFirstByNombre(autor.nombre);

        if (existingAuthor.isPresent()) {
            libro.setAutor(existingAuthor.get());
        } else {
            libro.setAutor(autor);
        }

        libro.setId_gutendex(libroDto.id_gutendex());
        libro.setTitulo(libroDto.titulo());
        libro.setIdioma(libroDto.idioma().get(0));
        libro.setNumeroDeDescargas(libroDto.numeroDeDescargas());
        System.out.println(autor);
        System.out.println(libro);
        Optional<Libro> libro1 = repository.findByGutendex(libro.getId_gutendex());
        if (libro1.isPresent()){
            System.out.println("Libro existente en la BD");
        }else {
            repository.save(libro);
        }
        System.out.println(imprimirLibro(libro));
    }


    public String imprimirLibro(Libro libro) {
        return "\n\n////////////////////////\nLibro: " +
                "\n titulo='" + libro.titulo + '\'' +
                "\n id_gutendex=" + libro.id_gutendex +
                "\n autor=" + libro.autor.toString() +
                "\n idioma='" + libro.idioma + '\'' +
                "\n numeroDeDescargas=" + libro.numeroDeDescargas +
                "\n////////////////////////\n\n";
    }

    public List<Libro> devolverTodos(){
        List<Libro> libros = repository.findAll();
        return libros;
    }

    public List<Libro> librosPorIdioma(String idioma){
        return repository.librosPorIdioma(idioma);
    }
}
