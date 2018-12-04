package ar.com.supervielle.personas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.supervielle.personas.modelo.Pais;
import ar.com.supervielle.personas.repositorio.PaisRepositorio;

@RestController
public class PaisRestController {

	@Autowired
	private PaisRepositorio paisRepositorio;
   
	@GetMapping("/pais") //para consultar
	@Transactional(readOnly = true)
    public Iterable<Pais> getPaises() {
        System.out.println("Consulta todas las personas");
        
        return paisRepositorio.findAll();
    }
	
	@PostMapping("/pais") //para insertar un contacto
	@Transactional
    public Pais insertPais(@RequestBody Pais pais) {
        System.out.println("Voy a dar de alta un pais");
        
        return paisRepositorio.save(pais);
    }
	
	@DeleteMapping("/pais/{id}") //Elimina la informacion
	@Transactional
    public void deletePais(@PathVariable int idPais) {
        System.out.println("Va a eliminar el pais " + idPais);
        
        paisRepositorio.deleteById(idPais);
    }
}
