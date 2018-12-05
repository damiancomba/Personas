package ar.com.supervielle.personas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.supervielle.personas.modelo.ArbolGenealogico;
import ar.com.supervielle.personas.modelo.Pais;
import ar.com.supervielle.personas.modelo.Persona;
import ar.com.supervielle.personas.modelo.Sexo;
import ar.com.supervielle.personas.modelo.TipoDocumento;
import ar.com.supervielle.personas.repositorio.ArbolGenealogicoRepositorio;
import ar.com.supervielle.personas.repositorio.PersonaRepositorio;
import ar.com.supervielle.personas.utils.Relaciones;


@RestController
public class PersonaRestController {
	
	@Autowired
	private PersonaRepositorio repositorioPers;
	
	@Autowired
	private ArbolGenealogicoRepositorio repositorioArbol;
	
	@GetMapping("/persona/{ids}")
	@Transactional(readOnly = true)
	public Persona getPersona(@MatrixVariable (required=true) String idSexo,
			@MatrixVariable	(required=true) String idTipoDocumento,
			@MatrixVariable (required=true) Long nroDocumento,
			@MatrixVariable (required=true) int idPais ) {
		System.out.println("Consulta por la persona: " + idSexo + " - " + idTipoDocumento + " - " + nroDocumento);
		
		Sexo sexoIn = new Sexo();
		sexoIn.setId(idSexo);
		
		Pais paisIn = new Pais();
		paisIn.setId(idPais);
		
		TipoDocumento tipoDocIn = new TipoDocumento();
		tipoDocIn.setId(idTipoDocumento);
		
		return repositorioPers.findBySexoAndTipoDocumentoAndNroDocumentoAndPais(sexoIn, tipoDocIn, nroDocumento, paisIn);
	}
	
	@GetMapping("/persona") //para consultar
	@Transactional(readOnly = true)
    public Iterable<Persona> persona() {
        System.out.println("Consulta todas las personas");
        
        return repositorioPers.findAll();
    }

	@PostMapping("/persona") //Para crear
	@Transactional
    public Persona personaSave(@RequestBody Persona persona	) {
        System.out.println("Va a crear la persona: " + persona.toString());
        
        return repositorioPers.save(persona);
    }
	
	@DeleteMapping("/persona/{id}") //Elimina la informacion
	@Transactional
    public void personaDelete(@MatrixVariable (required=true) String idSexo,
			@MatrixVariable	(required=true) String idTipoDocumento,
			@MatrixVariable (required=true) Long nroDocumento,
			@MatrixVariable (required=true) int idPais) {
        System.out.println("Va a eliminar la persona con " + idSexo + " - " + idTipoDocumento + " - " + nroDocumento + " - " + idPais);
        
        Sexo sexoIn = new Sexo();
        sexoIn.setId(idSexo);
        
        Pais paisIn = new Pais();
        paisIn.setId(idPais);
        
        TipoDocumento tipoDocIn = new TipoDocumento();
        tipoDocIn.setId(idTipoDocumento);
        
        repositorioPers.deleteBySexoAndTipoDocumentoAndNroDocumentoAndPais(sexoIn, tipoDocIn, nroDocumento, paisIn);
    }
	
	@GetMapping("/persona/sexo/cantidad/{sexoIn}") //para consultar
	@Transactional(readOnly = true)
    public Object cantidadPersonasPorSexo(@PathVariable String sexoIn) {
        System.out.println("Consulta la cantidad de personas de un sexo: " + sexoIn);
        
        Sexo sexo = new Sexo();
        sexo.setId(sexoIn);
        
        return repositorioPers.countBySexo(sexo);

    }
	
	@GetMapping("/persona/pais/cantidad/{paisIn}") //para consultar
	@Transactional(readOnly = true)
    public Object cantidadPersonasPorPais(@PathVariable int paisIn) {
        System.out.println("Consulta la cantidad de personas de un pais: " + paisIn);
        
        Pais pais = new Pais();
        pais.setId(1);
        
        return repositorioPers.countByPais(pais);

    }
	
	@GetMapping("/persona/cantidad") //para consultar
	@Transactional(readOnly = true)
    public Object cantidadPorSexo() {
        System.out.println("Consulta la cantidad de personas");
        
        return repositorioPers.count();

    }
	
	@PutMapping("/persona") //Para editar sustituyendo toda la informacion por la ingresada como parametro
	@Transactional
    public Persona personaUpdateAll(@RequestBody Persona persona) {
        System.out.println("Voy a actualizar todos los campos de: " + persona.toString());
        return repositorioPers.save(persona);
    }
	
	@PatchMapping("/persona") //Para modificar solo campos especificos
	@Transactional
    public Persona personaUpdate(@RequestBody Persona persona) {
        System.out.println("Voy a actualizar solo los campos especificos de la persona: " + persona.toString());
        return repositorioPers.save(persona);
    }
	
	
	@PostMapping("/persona/{id1}/padre") //Para insertar una relacion de hijo a padre
	@Transactional
    public ArbolGenealogico personaAltaPadre(@MatrixVariable (required=true) String idSexo,
			@MatrixVariable	(required=true) String idTipoDocumento,
			@MatrixVariable (required=true) Long nroDocumento,
			@MatrixVariable (required=true) int idPais,
			@RequestBody Persona hijo) {
        System.out.println("Voy a insertar una relacion de hijo a padre: " + hijo.toString());
        
        //Buscar la persona que ingresa
        Sexo sexoIn = new Sexo();
		sexoIn.setId(idSexo);
		
		Pais paisIn = new Pais();
		paisIn.setId(idPais);
		
		TipoDocumento tipoDocIn = new TipoDocumento();
		tipoDocIn.setId(idTipoDocumento);
		
		Persona padre = repositorioPers.findBySexoAndTipoDocumentoAndNroDocumentoAndPais(sexoIn, tipoDocIn, nroDocumento, paisIn);
        
        //Darla de alta
        
        ArbolGenealogico relacion = new ArbolGenealogico();
        relacion.setHijo(hijo);
        relacion.setPadre(padre);       
        
        return repositorioArbol.save(relacion);
    }
	
	@PostMapping("/persona/{id1}/madre") //Para insertar una relacion de hijo a madre
	@Transactional
    public ArbolGenealogico personaAltaMadre(@MatrixVariable (required=true) String idSexo,
			@MatrixVariable	(required=true) String idTipoDocumento,
			@MatrixVariable (required=true) Long nroDocumento, 
			@MatrixVariable (required=true) int idPais,
			@RequestBody Persona hijo) {
        System.out.println("Voy a insertar una relacion de hijo a madre: " + hijo.toString());
        
		// Buscar la persona que ingresa
        Sexo sexoIn = new Sexo();
		sexoIn.setId(idSexo);
		
		Pais paisIn = new Pais();
		paisIn.setId(idPais);
		
		TipoDocumento tipoDocIn = new TipoDocumento();
		tipoDocIn.setId(idTipoDocumento);
		
		Persona madre = repositorioPers.findBySexoAndTipoDocumentoAndNroDocumentoAndPais(sexoIn, tipoDocIn, nroDocumento, paisIn);

		// Darla de alta

		ArbolGenealogico relacion = new ArbolGenealogico();
		relacion.setHijo(hijo);
		relacion.setMadre(madre);

		return repositorioArbol.save(relacion);
    }	
	
}

