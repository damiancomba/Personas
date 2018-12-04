package ar.com.supervielle.personas.modelo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoDocumento {

	@Id
	private String id;
	private String descripcion;
	
	public TipoDocumento() { }
	
	public TipoDocumento(String id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
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

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento tipoDocumento = (TipoDocumento) o;
        return Objects.equals(this.id, tipoDocumento.getId()) &&
        		Objects.equals(this.descripcion, tipoDocumento.getDescripcion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion);
    }
}
