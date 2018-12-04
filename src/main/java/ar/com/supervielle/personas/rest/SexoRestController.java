package ar.com.supervielle.personas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.supervielle.personas.modelo.Sexo;
import ar.com.supervielle.personas.repositorio.SexoRepositorio;

@RestController
public class SexoRestController {

	@Autowired
	private SexoRepositorio sexoRepositorio;
	
	@GetMapping("/sexo") //para consultar
	@Transactional(readOnly = true)
    public Iterable<Sexo> findAllSexo() {
		
        return sexoRepositorio.findAll();
    }
	
	@PostMapping("/sexo") //para insertar un contacto
	@Transactional
    public Sexo insertSexo(@RequestBody Sexo sexo) {
        System.out.println("Voy a dar de alta un sexo");
        
        return sexoRepositorio.save(sexo);
    }
	
	@DeleteMapping("/sexo/{id}") //Elimina la informacion
	@Transactional
    public void deleteSexo(@PathVariable int idSexo) {
        System.out.println("Va a eliminar el pais " + idSexo);
        
        sexoRepositorio.deleteById(idSexo);
    }
}
