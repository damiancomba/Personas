package ar.com.supervielle.personas.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

import ar.com.supervielle.personas.modelo.ArbolGenealogico.ArbolGenealogicoId;
import ar.com.supervielle.personas.modelo.Persona.PersonaId;

@Entity
@IdClass(ArbolGenealogicoId.class)
public class ArbolGenealogico implements Serializable {

	private static final long serialVersionUID = 1259327473598037386L;

	@Id
	@JoinColumns({
		@JoinColumn(name="id_sexo", referencedColumnName="id_sexo"),
		@JoinColumn(name="id_tipo_documento", referencedColumnName="id_tipo_documento"),
		@JoinColumn(name="nro_documento", referencedColumnName="nroDocumento"),
		@JoinColumn(name="id_pais", referencedColumnName="id_pais")
		})
	@OneToOne(optional=false, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private Persona hijo;
	
	@OneToOne(optional=true, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private Persona padre;
	
	@OneToOne(optional=true, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private Persona madre;
	
	public ArbolGenealogico() {}
	
	public ArbolGenealogico(Persona persona,
			Persona padre,
			Persona madre) {
	
		this.hijo = persona;
		this.padre = padre;
		this.madre = madre;
	}

	public Persona getHijo() {
		return hijo;
	}

	public void setHijo(Persona persona) {
		this.hijo = persona;
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

		PersonaId hijo;

   }
}
