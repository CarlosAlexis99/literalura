package com.carlos_patito.apiLibros.controller;



import com.carlos_patito.apiLibros.model.dtos.DataDto;
import com.carlos_patito.apiLibros.model.dtos.LibroDto;
import com.carlos_patito.apiLibros.service.ConsumoAPI;
import com.carlos_patito.apiLibros.service.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class LibrosController {
    private Scanner teclado = new Scanner(System.in);

    private ConsumoAPI consumoApi = new ConsumoAPI();
    @Autowired
    private LibrosService librosService;
    private final String URL_BASE = "https://gutendex.com/books/";

    public void muestraElMenu() throws IOException, InterruptedException {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por nombre 
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    libroDto();
                    break;
                /*case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    mostrarSeriesBuscadas();
                    break;*/
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private LibroDto libroDto() throws IOException, InterruptedException {
        System.out.println("Escribe el nombre del libro que estás buscando");
        var libro = teclado.nextLine();
        String json = consumoApi.request(URL_BASE +"?search="+ libro.replace(" ", "+"));
        DataDto datos = consumoApi.convertirDatps(json, DataDto.class);
        librosService.save(datos.results().get(0));
        System.out.println(datos.results().get(0));
        return null;
    }
    /*
    private void buscarEpisodioPorSerie() {
        mostrarSeriesBuscadas();
        System.out.println("Escribe el nombre de la seria de la cual quieres ver los episodios");
        var nombreSerie = teclado.nextLine();

        Optional<Serie> serie = series.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nombreSerie.toLowerCase()))
                .findFirst();

        if(serie.isPresent()){
            var serieEncontrada = serie.get();
            List<DatosTemporadas> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumoApi.obtenerDatos(URL_BASE + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DatosTemporadas datosTemporada = conversor.obtenerDatos(json, DatosTemporadas.class);
                temporadas.add(datosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        }



    }
    private void buscarSerieWeb() {
        DatosSerie datos = getDatosSerie();
        Serie serie = new Serie(datos);
        repositorio.save(serie);
        //datosSeries.add(datos);
        System.out.println(datos);
    }

    private void mostrarSeriesBuscadas() {
        series = repositorio.findAll();

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }
    */
}