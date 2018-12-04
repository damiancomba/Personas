package ar.com.supervielle.personas.modelo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity 
public class Pais {

	@Id
	private int id;
	
	private String nombre;
	
	public Pais() {}
	
	public Pais(String nombre) {
		this.nombre = nombre;
	}
	
	public Pais(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof Pais)) {
            return false;
        }
        Pais pais = (Pais) o;
        return Objects.equals(id, pais.getId()) &&
        		Objects.equals(nombre, pais.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
	
}
