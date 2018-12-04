package ar.com.supervielle.personas.repositorio;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import ar.com.supervielle.personas.modelo.Pais;

public interface PaisRepositorio extends PagingAndSortingRepository<Pais, Long> {

	public List<Pais> findByNombre(String nombre);
	
	public void deleteById(int idPais);
	
}
