package ar.com.supervielle.personas.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ar.com.supervielle.personas.modelo.ArbolGenealogico;
import ar.com.supervielle.personas.modelo.Pais;
import ar.com.supervielle.personas.modelo.Persona;
import ar.com.supervielle.personas.modelo.Sexo;
import ar.com.supervielle.personas.modelo.TipoDocumento;

@Repository
public interface ArbolGenealogicoRepositorio extends PagingAndSortingRepository<ArbolGenealogico, Long> {
	
	public ArbolGenealogico findByHijo(Persona hijo);
	
}
