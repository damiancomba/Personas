package ar.com.supervielle.personas.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;

import ar.com.supervielle.personas.modelo.ArbolGenealogico.ArbolGenealogicoId;

@Entity
@IdClass(ArbolGenealogicoId.class)
public class ArbolGenealogico implements Serializable {

	private static final long serialVersionUID = 1259327473598037386L;

	@Id
	@OneToOne(optional=false, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Persona personaId;
	
	@OneToOne(optional=true, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Persona padre;
	
	@OneToOne(optional=true, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Persona madre;
	
	public ArbolGenealogico() {}
	
	public ArbolGenealogico(Persona persona,
			Persona padre,
			Persona madre) {
	
		this.personaId = persona;
		this.padre = padre;
		this.madre = madre;
	}

	public Persona getPersona() {
		return personaId;
	}

	public void setPersona(Persona persona) {
		this.personaId = persona;
	}

	public Persona getPadre() {
		return padre;
	}

	public void setPadre(Persona padre) {
		this.padre = padre;
	}

	public Persona getMadre() {
		return madre;
	}

	public void setMadre(Persona madre) {
		this.madre = madre;
	}
	
	public static class ArbolGenealogicoId implements Serializable {

		private static final long serialVersionUID = 7472442387397382180L;

		private Persona personaId;

        public ArbolGenealogicoId() {}

        public ArbolGenealogicoId(Persona personaId) {
            this.personaId = personaId;
        }

        @Override
        public boolean equals(Object o) {

            if (o == this) {
                return true;
            }
            if (!(o instanceof ArbolGenealogico)) {
                return false;
            }
            ArbolGenealogico arbolGenealogico = (ArbolGenealogico) o;
            return Objects.equals(personaId, arbolGenealogico.getPersona());
        }

        @Override
        public int hashCode() {
            return Objects.hash(personaId);
        }
   }
}
