package ar.com.supervielle.personas.modelo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sexo {

	@Id
	private String id;
	
	private String descripcion;
	
	public Sexo() {}
	
	public Sexo(String desc) {
		this.descripcion = desc;
	}
	
	public Sexo(String id, String desc) {
		this.id = id;
		this.descripcion = desc;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String sexo) {
		this.descripcion = sexo;
	}

	@Override
	public String toString() {
		return "Sexo [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof Sexo)) {
            return false;
        }
        Sexo sexo = (Sexo) o;
        return Objects.equals(this.id, sexo.getId()) &&
        		Objects.equals(this.descripcion, sexo.getDescripcion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion);
    }
}
