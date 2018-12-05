package ar.com.superville.personas;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.supervielle.personas.PersonasApplication;
import ar.com.supervielle.personas.modelo.Contacto;
import ar.com.supervielle.personas.modelo.Pais;
import ar.com.supervielle.personas.modelo.Persona;
import ar.com.supervielle.personas.modelo.Sexo;
import ar.com.supervielle.personas.modelo.TipoDocumento;
import ar.com.supervielle.personas.repositorio.PersonaRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersonasApplication.class)
public class PersonasRepositoryIntegrationTest {

	@Autowired
	private PersonaRepositorio personaRepository;
	
	@Test
	public void testAltaPersona() throws Exception {
		
		Sexo sexo = new Sexo("M", "Masculino");
		
		TipoDocumento tipoDoc = new TipoDocumento("DNI", "Documento Nacional de Identidad");
		
		Pais pais = new Pais(2, "Italia");
		
		Calendar fechaNacimiento = Calendar.getInstance();
    	fechaNacimiento.set(1986, 4, 10);
    	
    	Date fechaNac = fechaNacimiento.getTime();
		
    	List<Contacto> contactos = new ArrayList<Contacto>();
    	Contacto contacto1 = new Contacto("mail", "prueba@abc.com");
    	contactos.add(contacto1);
    	
	    // inserto
	    Persona damian = new Persona("damian", "comba", sexo, tipoDoc, (long) 80, pais, fechaNac, contactos);
	    personaRepository.save(damian);
	 
	    // consulto
	    Persona found = personaRepository.findBySexoAndTipoDocumentoAndNroDocumentoAndPais(sexo, tipoDoc, (long) 80, pais);
	 
	    // valido
	    assertTrue((found.getSexo().getId().equals(damian.getSexo().getId())) &&
	    			(found.getPais().getId() == damian.getPais().getId()) &&
	    			(found.getTipoDocumento().getId().equals(damian.getTipoDocumento().getId())) &&
	    			(found.getNroDocumento() == damian.getNroDocumento()));

	}
}
