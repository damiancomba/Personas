package ar.com.supervielle.personas.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;

import ar.com.supervielle.personas.modelo.ArbolGenealogico;

public interface ArbolGenealogicoRepositorio extends PagingAndSortingRepository<ArbolGenealogico, Long> {
	
}
