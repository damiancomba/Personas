package ar.com.supervielle.personas.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import ar.com.supervielle.personas.modelo.Contacto.ContactoId;

@Entity
@IdClass(ContactoId.class)
public class Contacto {

	@Id	
	private String tipo;
	
	@Id
	private String descripcion;
	
	public Contacto() {}
	
	public Contacto(String tipo, String descripcion) {
		this.tipo = tipo;
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static class ContactoId implements Serializable {

		private static final long serialVersionUID = 7295741567692230651L;
		private String tipo;
		private String descripcion;

        public ContactoId() {}

        public ContactoId(String tipo, String descripcion) {
        	this.tipo = tipo;
        	this.descripcion = descripcion;
        }

        @Override
        public boolean equals(Object o) {

            if (o == this) {
                return true;
            }
            if (!(o instanceof Contacto)) {
                return false;
            }
            Contacto contacto = (Contacto) o;
            return Objects.equals(this.tipo, contacto.getTipo()) &&
            		Objects.equals(this.descripcion, contacto.getDescripcion());
        }

        @Override
        public int hashCode() {
            return Objects.hash(tipo, descripcion);
        }
	}
	
	
}
