package ar.com.supervielle.personas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.supervielle.personas.core.CorePersonas;
import ar.com.supervielle.personas.repositorio.ArbolGenealogicoRepositorio;
import ar.com.supervielle.personas.repositorio.ContactoRepositorio;
import ar.com.supervielle.personas.repositorio.PaisRepositorio;
import ar.com.supervielle.personas.repositorio.PersonaRepositorio;
import ar.com.supervielle.personas.repositorio.SexoRepositorio;

@SpringBootApplication
@RestController
public class PersonasApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PersonasApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Bienvenidos a la API Personas.";
	}

	@Bean
	public CommandLineRunner init(PersonaRepositorio repository, 
									ArbolGenealogicoRepositorio arbol, 
									PaisRepositorio pais, 
									SexoRepositorio repoSexo,
									ContactoRepositorio contactoRepo) {
		return (args) -> {
			
			new CorePersonas(repository, arbol, pais, repoSexo, contactoRepo);
		};
	}
		
}