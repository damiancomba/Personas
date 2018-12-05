package ar.com.supervielle.personas.repositorio;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ar.com.supervielle.personas.modelo.Pais;

@Repository
public interface PaisRepositorio extends PagingAndSortingRepository<Pais, Long> {

	public List<Pais> findByNombre(String nombre);
	
	public void deleteById(int idPais);
	
}
