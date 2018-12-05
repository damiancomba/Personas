package ar.com.supervielle.personas.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;

import ar.com.supervielle.personas.modelo.Persona.PersonaId;
import ar.com.supervielle.personas.utils.FechaUtil;


@Entity
@IdClass(PersonaId.class)
public class Persona implements Serializable {

	private static final long serialVersionUID = -7681326534850674114L;
		
	@Id
	@ManyToOne(optional=false, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_sexo", nullable=false, updatable=true)
	private Sexo sexo;
	
	@Id
	@ManyToOne(optional=false, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="id_tipo_documento", nullable=false, updatable=true)
	private TipoDocumento tipoDocumento;
	
	@Id
	private Long nroDocumento;
	
	@Id
	@ManyToOne(optional=false, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="id_pais", nullable=false, updatable=true)
	private Pais pais;
	
	private String nombre;
	
	private String apellido;
	
	@Type(type="date")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date fechaNacimiento;
	
	@Min(value = 18)
	private int edad;
	
	@Size(min = 1)
	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumns({
		@JoinColumn(name = "nroDocumento", nullable=false, updatable=true),
		@JoinColumn(name = "id_pais", nullable=false, updatable=true),
		  @JoinColumn(name = "id_sexo", nullable=false, updatable=true),
		  @JoinColumn(name = "id_tipo_documento", nullable=false, updatable=true)
		})	
	private List<Contacto> contactos;

	public Persona() {
		super();
	}
	
	public Persona(String nombre, String apellido, Sexo sexo, TipoDocumento tipoDocumento, Long nroDocumento, Pais pais, Date fechaNac, List<Contacto> contactos) throws Exception {
		super();
		
		FechaUtil feUtil = new FechaUtil();
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroDocumento = nroDocumento;
		this.sexo = sexo;
		this.tipoDocumento = tipoDocumento;
		this.pais = pais;
		this.edad = feUtil.calcularEdad(fechaNac);
		this.fechaNacimiento = fechaNac;
		this.contactos = contactos;
	}


	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(Long nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
		
		FechaUtil feUtil = new FechaUtil();
		this.edad = feUtil.calcularEdad(fechaNacimiento);
		//calculo la edad al momento de recibir la fecha de Nacimiento. No la persisto ya que puedo obtenerla partiendo de la fecha de nacimiento 
	}
	
	public List<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	public static class PersonaId implements Serializable {

		private static final long serialVersionUID = 7295741567692230651L;
		private String sexo;
		private String tipoDocumento;
		private Long nroDocumento;
		private Integer pais;

        public PersonaId() {}

        public PersonaId(Sexo sexo, TipoDocumento tipoDocumento, Long nroDocumento, Pais pais) {
        	this.sexo = sexo.getId();
        	this.tipoDocumento = tipoDocumento.getId();
        	this.nroDocumento = nroDocumento;
        	this.pais = pais.getId();
        	
        }

        @Override
        public boolean equals(Object o) {

            if (o == this) {
                return true;
            }
            if (!(o instanceof Persona)) {
                return false;
            }
            Persona persona = (Persona) o;
            return Objects.equals(sexo, persona.getSexo().getId()) && 
					Objects.equals(tipoDocumento, persona.getTipoDocumento().getId()) &&
					Objects.equals(nroDocumento, persona.getNroDocumento()) &&
					Objects.equals(pais, persona.getPais().getId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(sexo, tipoDocumento, nroDocumento, pais);
        }
	}
	
}