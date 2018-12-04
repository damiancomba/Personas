package ar.com.supervielle.personas.rest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.supervielle.personas.modelo.Estadistica;
import ar.com.supervielle.personas.modelo.Pais;
import ar.com.supervielle.personas.modelo.Sexo;
import ar.com.supervielle.personas.repositorio.PaisRepositorio;
import ar.com.supervielle.personas.repositorio.PersonaRepositorio;

@RestController
public class EstadisticaRestController {

	@Autowired
	private PersonaRepositorio repositorioPers;
	
	@Autowired
	private PaisRepositorio repositorioPais;
	
	
	@GetMapping("/estadisticas") //Retorna las estadisticas en base a los datos cargados
	@Transactional(readOnly = true)
	public Estadistica getEstadisticas() {
		System.out.println("Consulta por las estadisticas");
		
		Sexo sexoMasculino = new Sexo();
		sexoMasculino.setId("M");
        
        Sexo sexoFemenino = new Sexo();
        sexoFemenino.setId("F");
        
        List<Pais> paises = repositorioPais.findByNombre("Argentina");
        
        int cantArgentinos = repositorioPers.countByPais(paises.get(0));
        System.out.println("Cantidad de Argentinos: " + cantArgentinos);
        
        int cantidadMasculinos = repositorioPers.countBySexo(sexoMasculino);
        System.out.println("Cantidad de Masculinos: " + cantidadMasculinos);
        int cantidadFemeninos = repositorioPers.countBySexo(sexoFemenino);
        System.out.println("Cantidad de Femeninos: " + cantidadFemeninos);
        
        long cantidadTotal = repositorioPers.count();
        
        Estadistica estadistica = new Estadistica();
        estadistica.setCantidadHombres(cantidadMasculinos);
        estadistica.setCantidadMujeres(cantidadFemeninos);
        
        if (cantidadTotal > 0) {
        	
        	BigDecimal bd = new BigDecimal((cantArgentinos * 100.0) / cantidadTotal);
        	bd = bd.setScale(2, RoundingMode.HALF_UP);
        	
        	estadistica.setPorcentajeArgentinos(bd.doubleValue());
        }
        
		return estadistica;
	}
	
}
