package ar.com.supervielle.personas.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;

import ar.com.supervielle.personas.modelo.Sexo;

public interface SexoRepositorio extends PagingAndSortingRepository<Sexo,Long> {

	public void deleteById(int idSexo);

}
