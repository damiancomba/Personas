package ar.com.supervielle.personas.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.supervielle.personas.modelo.Pais;
import ar.com.supervielle.personas.modelo.Persona;
import ar.com.supervielle.personas.modelo.Sexo;
import ar.com.supervielle.personas.modelo.TipoDocumento;

@Repository
public interface PersonaRepositorio extends PagingAndSortingRepository<Persona,Long> {
	
	public int countBySexo(Sexo sexo);
	
	public int countByPais(Pais pais);
	
	public Persona findBySexoAndTipoDocumentoAndNroDocumentoAndPais(Sexo sexo, TipoDocumento tipoDocumento, Long nroDocumento, Pais pais);

	@Transactional
	public void deleteBySexoAndTipoDocumentoAndNroDocumentoAndPais(Sexo sexo, TipoDocumento tipoDocumento, Long nroDocumento, Pais pais);
	
}
