package ar.com.supervielle.personas.utils;

public enum Relaciones {

	HERMANO("HERMAN@"), PRIMO("PRIM@"), TIO("TI@");
	
	private String descripcion;
	
	Relaciones (String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
	
}
