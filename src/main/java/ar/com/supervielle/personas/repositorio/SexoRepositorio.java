package ar.com.supervielle.personas.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ar.com.supervielle.personas.modelo.Sexo;

@Repository
public interface SexoRepositorio extends PagingAndSortingRepository<Sexo,Long> {

	public void deleteById(int idSexo);

}
