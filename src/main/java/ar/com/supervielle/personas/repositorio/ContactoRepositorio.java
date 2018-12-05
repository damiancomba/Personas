package ar.com.supervielle.personas.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.supervielle.personas.modelo.Contacto;

@Repository
public interface ContactoRepositorio extends PagingAndSortingRepository<Contacto, Long> {

	@Transactional
	public void deleteByTipoAndDescripcion(String tipo, String descripcion);
	
}