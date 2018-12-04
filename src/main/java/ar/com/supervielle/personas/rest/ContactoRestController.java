package ar.com.supervielle.personas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.supervielle.personas.modelo.Contacto;
import ar.com.supervielle.personas.repositorio.ContactoRepositorio;

@RestController
public class ContactoRestController {

	@Autowired
	private ContactoRepositorio contactoRepositorio;

	@GetMapping("/contacto") //para consultar
	@Transactional(readOnly = true)
    public Iterable<Contacto> getContactos() {
        System.out.println("Consulta todas las personas");
        
        return contactoRepositorio.findAll();
    }
	
	@PostMapping("/contacto") //para insertar un contacto
	@Transactional
    public Contacto insertContacto(@RequestBody Contacto contacto) {
        System.out.println("Consulta todas las personas");
        
        return contactoRepositorio.save(contacto);
    }
	
	@DeleteMapping("/contacto/{id}") //Elimina la informacion
	@Transactional
    public void deleteContacto(@MatrixVariable (required=true) String tipo,
			@MatrixVariable	(required=true) String descripcion) {
        System.out.println("Va a eliminar el contacto " + tipo + " - " + descripcion);
        
        contactoRepositorio.deleteByTipoAndDescripcion(tipo, descripcion);
    }
	
}
