package ar.com.supervielle.personas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.supervielle.personas.modelo.ArbolGenealogico;
import ar.com.supervielle.personas.repositorio.ArbolGenealogicoRepositorio;

@RestController
public class ArbolGenealogicoRestController {
	
	@Autowired
	private ArbolGenealogicoRepositorio arbolGenealogicoRepositorio;
	
	@GetMapping("/relacion") //para consultar
	@Transactional(readOnly = true)
    public Iterable<ArbolGenealogico> relacion() {
        System.out.println("Consulta todas las personas");
        
        return arbolGenealogicoRepositorio.findAll();
    }
	
	
	
//	@GetMapping("/padre/{id}") //para consultar
//  public Persona getPadre(@MatrixVariable (required=true) String sexo,
//				@MatrixVariable	(required=true) String tipoDocumento,
//				@MatrixVariable (required=true) Long nroDocumento) {
//		System.out.println("Consulta por la persona: " + sexo + " - " + tipoDocumento + " - " + nroDocumento);
//		
//		ArbolGenealogico arbol = repositorioArbol.findBySexoAndTipoDocumentoAndNroDocumento(sexo, tipoDocumento, nroDocumento);
//		
//		return repositorioPers.findBySexoAndTipoDocumentoAndNroDocumento(arbol.getPadreSexo(), arbol.getPadreTipoDocumento(), arbol.getPadreNroDocumento());
//		
//	}
//	
//	@GetMapping("/madre/{id}") //para consultar
//  public Persona getMadre(@MatrixVariable (required=true) String sexo,
//				@MatrixVariable	(required=true) String tipoDocumento,
//				@MatrixVariable (required=true) Long nroDocumento) {
//		System.out.println("Consulta por la persona: " + sexo + " - " + tipoDocumento + " - " + nroDocumento);
//		
//		ArbolGenealogico arbol = repositorioArbol.findBySexoAndTipoDocumentoAndNroDocumento(sexo, tipoDocumento, nroDocumento);
//		
//		return repositorioPers.findBySexoAndTipoDocumentoAndNroDocumento(arbol.getMadreSexo(), arbol.getMadreTipoDocumento(), arbol.getMadreNroDocumento());
//		
//	}
}
