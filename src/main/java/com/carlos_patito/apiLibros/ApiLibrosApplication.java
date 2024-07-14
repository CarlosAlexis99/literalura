package com.carlos_patito.apiLibros;

import com.carlos_patito.apiLibros.controller.LibrosController;
import com.carlos_patito.apiLibros.model.dtos.DataDto;
import com.carlos_patito.apiLibros.model.dtos.LibroDto;
import com.carlos_patito.apiLibros.model.entities.Autor;
import com.carlos_patito.apiLibros.model.entities.Libro;
import com.carlos_patito.apiLibros.service.AutorService;
import com.carlos_patito.apiLibros.service.ConsumoAPI;
import com.carlos_patito.apiLibros.service.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class ApiLibrosApplication implements CommandLineRunner {

	private Scanner teclado = new Scanner(System.in);

	private ConsumoAPI consumoApi = new ConsumoAPI();
	@Autowired
	private LibrosService librosService;
	@Autowired
	private AutorService autorService;

	private final String URL_BASE = "https://gutendex.com/books/";


	public static void main(String[] args) {
		SpringApplication.run(ApiLibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var opcion = -1;
		while (opcion != 0) {
			var menu = """
					1 - Buscar libro por nombre 
					2 - Buscar autor por nombre
					3 - Mostrar libros registrados en la BD
					4 - Mostrar autores registrados en la BD
					5 - Mostrar autores vivos en un determinado año
					6 - Mostrar libros por idioma
					
					 0 - Salir
                    """;
			System.out.println(menu);
			opcion = teclado.nextInt();
			teclado.nextLine();

			switch (opcion) {
				case 1:
					libroDto();
					break;
                case 2:
					buscarAutorPorNombre();
                    break;
                case 3:
                    librosBD();
                    break;
				case 4:
					autoresBD();
					break;
				case 5:
					try{
						System.out.println("¿En qué año?");
						autoresVivosEnElAnio(teclado.nextInt());
					}catch (Exception e){
						teclado.nextLine();
						System.out.println("Año no válido");
					}

					break;
				case 6:
					buscarLibrosPorIdioma();
					break;
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
		if (!datos.results().isEmpty()){
		librosService.save(datos.results().get(0));
		} else {
			System.out.println("Prueba buscando con otro título");
		}
		return null;
	}

	private void buscarAutorPorNombre(){
		System.out.println("Ingrese el nombre del autor que desea buscar");

		try {
			var autorBuscado = teclado.nextLine();
			Optional<Autor> autor = autorService.autorPorNombre(autorBuscado);
			if (autor.isPresent()){
				autor.stream()
						.forEach(System.out::println);
			} else {
				System.out.println("Autor no encontrado");
			}
		} catch (Exception e){
			System.out.println("Ingrese un nombre correcto. - Warning: " + e.getMessage());
		}

	}

	public void librosBD(){
		System.out.println("LIBROS");
		List<Libro> libro = librosService.devolverTodos();
		libro.forEach(libro1 -> System.out.println(librosService.imprimirLibro(libro1)));
	}

	public void autoresBD(){
		List<Autor> autores = autorService.devolverAutores();
		autores.forEach(System.out::println);
	}

	public void autoresVivosEnElAnio(int year){
		teclado.nextLine();
		List<Autor> autores = autorService.vivoEnElAnioTal(year);
		autores.forEach(System.out::println);
	}

	private void buscarLibrosPorIdioma() {
		var menuIdiomas = """
                ¿En qué idioma?
                
                1 - Inglés
                2 - Español
                
                0 - salir
                """;
		System.out.println(menuIdiomas);

		try {
			var opcionIdioma = teclado.nextInt();

			switch (opcionIdioma) {
				case 1:
					librosService.librosPorIdioma("en")
							.forEach(libro1 -> System.out.println(librosService.imprimirLibro(libro1)));;
					break;
				case 2:
					librosService.librosPorIdioma("es")
							.forEach(libro1 -> System.out.println(librosService.imprimirLibro(libro1)));;
					break;

				case 0:
					System.out.println("Volviendo al menú...");
					break;
				default:
					System.out.println("Ingrese una opción válida");
			}
		} catch (NumberFormatException e) {
			System.out.println("Opción no válida: " + e.getMessage());
		}
	}
}
