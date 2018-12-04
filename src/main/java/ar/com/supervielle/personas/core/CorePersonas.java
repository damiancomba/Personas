package ar.com.supervielle.personas.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ar.com.supervielle.personas.modelo.ArbolGenealogico;
import ar.com.supervielle.personas.modelo.Contacto;
import ar.com.supervielle.personas.modelo.Pais;
import ar.com.supervielle.personas.modelo.Persona;
import ar.com.supervielle.personas.modelo.Sexo;
import ar.com.supervielle.personas.modelo.TipoDocumento;
import ar.com.supervielle.personas.repositorio.ArbolGenealogicoRepositorio;
import ar.com.supervielle.personas.repositorio.ContactoRepositorio;
import ar.com.supervielle.personas.repositorio.PaisRepositorio;
import ar.com.supervielle.personas.repositorio.PersonaRepositorio;
import ar.com.supervielle.personas.repositorio.SexoRepositorio;

public class CorePersonas {
	private PersonaRepositorio repositorio;
	private ArbolGenealogicoRepositorio repoArbol;
	private PaisRepositorio repoPais;
	private SexoRepositorio repoSexo;
	private ContactoRepositorio repoContacto;

    public CorePersonas(PersonaRepositorio repositorio, ArbolGenealogicoRepositorio repoArbol, PaisRepositorio pais, SexoRepositorio sexo, ContactoRepositorio repoContacto) throws Exception {
    	this.repositorio = repositorio;
    	this.repoArbol = repoArbol;
    	this.repoPais = pais;
    	this.repoSexo = sexo;
    	this.repoContacto= repoContacto;

        repositorio.deleteAll();
        repoArbol.deleteAll();
        repoPais.deleteAll();
    	repoSexo.deleteAll();
    	repoContacto.deleteAll();
    	
        initPrediccion();
    }
    

    private void initPrediccion() throws Exception {
    	Sexo masculino = new Sexo("M", "Masculino");
    	Sexo femenino = new Sexo("F", "Femenino");

    	Pais paisArgentina = new Pais(1, "Argentina");
    	Pais paisEspania = new Pais(2, "Italia");
    	
    	Calendar fechaMayor18 = Calendar.getInstance();
    	fechaMayor18.set(1986, 4, 10);
    	
    	Date fechaMayorDe18 = fechaMayor18.getTime();
    	
    	Contacto contactoTelefono = new Contacto("Telefono", "02213175110");
    	Contacto contactoEmail = new Contacto("Email", "damiancom@gmail.com");
    	
    	TipoDocumento tipoDni = new TipoDocumento("DNI", "Documento Nacional de Identidad");
    	TipoDocumento tipoLc = new TipoDocumento("LC", "Libreta Civica");
    	TipoDocumento tipoLe = new TipoDocumento("LE", "Libreta de Enrolamiento");
    	
    	List<Contacto> cont = new ArrayList<Contacto>();
    	cont.add(contactoEmail);
    	cont.add(contactoTelefono);
    	
    	Persona julio = new Persona ("Julio", "Corengia", masculino, tipoDni, new Long(1), paisArgentina, fechaMayorDe18, cont);
    	try {repositorio.save(julio);}catch (Exception e) {System.out.println(e.getMessage());}
    	
    	Persona loly = new Persona ("Loly", "Cabrera", masculino, tipoLc, new Long(2), paisArgentina, fechaMayorDe18, cont);
    	try {repositorio.save(loly);}catch (Exception e) {System.out.println(e.getMessage());}
    	
    	Persona cecilia = new Persona ("Cecilia", "Corengia", femenino, tipoLc, new Long(3), paisEspania, fechaMayorDe18, cont);
    	try {repositorio.save(cecilia);}catch (Exception e) {System.out.println(e.getMessage());}
    	
    	Persona daniel = new Persona ("Daniel", "Corengia", masculino, tipoDni, new Long(4), paisArgentina, fechaMayorDe18, cont);
    	try {repositorio.save(daniel);}catch (Exception e) {System.out.println(e.getMessage());}
    	
    	Persona pablo = new Persona ("Pablo", "Corengia", masculino, tipoDni, new Long(5), paisArgentina, fechaMayorDe18, cont);
    	try {repositorio.save(pablo);}catch (Exception e) {System.out.println(e.getMessage());}
    	
    	Persona alejo = new Persona ("Alejo", "Corengia", masculino, tipoDni, new Long(6), paisArgentina, fechaMayorDe18, cont);
    	try {repositorio.save(alejo);}catch (Exception e) {System.out.println(e.getMessage());}
    	
    	Persona dante = new Persona ("Dante", "Corengia", masculino, tipoDni, new Long(7), paisArgentina, fechaMayorDe18, cont);
    	try {repositorio.save(dante);}catch (Exception e) {System.out.println(e.getMessage());}
    	
    	Persona damian = new Persona ("Damian", "Comba", masculino, tipoDni, new Long(8), paisEspania, fechaMayorDe18, cont);
    	try {repositorio.save(damian);}catch (Exception e) {System.out.println(e.getMessage());}
    	
    	Persona bautista = new Persona ("Bautista", "Comba", masculino, tipoDni, new Long(9), paisArgentina, fechaMayorDe18, cont);
    	try {repositorio.save(bautista);}catch (Exception e) {System.out.println(e.getMessage());}
    	
    	Persona rosa = new Persona ("Maria Rosa", "Corallo", femenino, tipoLc, new Long(10), paisArgentina, fechaMayorDe18, cont);
    	try {repositorio.save(rosa);}catch (Exception e) {System.out.println(e.getMessage());}
    	
    	Persona ricardo = new Persona ("Ricardo", "Comba", masculino, tipoLe, new Long(11), paisArgentina, fechaMayorDe18, cont);
    	try {repositorio.save(ricardo);}catch (Exception e) {System.out.println(e.getMessage());}

    	ArbolGenealogico lolyHija = new ArbolGenealogico(loly, null, null);
    	ArbolGenealogico julioHijo = new ArbolGenealogico(julio, null, null);
    	
    	ArbolGenealogico rosaHija = new ArbolGenealogico(rosa, null, null);
    	ArbolGenealogico ricardoHijo = new ArbolGenealogico(ricardo, null, null);
    	
    	//ArbolGenealogico ceciliaJulioLoly = new ArbolGenealogico(cecilia, julio, loly);
    	ArbolGenealogico pabloJulioLoly = new ArbolGenealogico(pablo, julio, loly);
    	ArbolGenealogico danielJulioLoly = new ArbolGenealogico(daniel, julio, loly);
    	
    	ArbolGenealogico alejoPablo = new ArbolGenealogico(alejo, pablo, null);
    	ArbolGenealogico dantePablo = new ArbolGenealogico(dante, pablo, null);
    	
    	ArbolGenealogico damianRosaRicardo = new ArbolGenealogico(damian, ricardo, rosa);
    	
    	//ArbolGenealogico bautistaDamianCecilia = new ArbolGenealogico(bautista, damian, cecilia);
    	
    	try {repoArbol.save(lolyHija);}catch (Exception e) {System.out.println(e.getMessage());}
    	try {repoArbol.save(julioHijo);}catch (Exception e) {System.out.println(e.getMessage());}
    	try {repoArbol.save(rosaHija);}catch (Exception e) {System.out.println(e.getMessage());}
    	try {repoArbol.save(ricardoHijo);}catch (Exception e) {System.out.println(e.getMessage());}
    	//try {repoArbol.save(ceciliaJulioLoly);}catch (Exception e) {System.out.println(e.getMessage());}
    	try {repoArbol.save(pabloJulioLoly);}catch (Exception e) {System.out.println(e.getMessage());}
    	try {repoArbol.save(danielJulioLoly);}catch (Exception e) {System.out.println(e.getMessage());}
    	try {repoArbol.save(alejoPablo);}catch (Exception e) {System.out.println(e.getMessage());}
    	try {repoArbol.save(dantePablo);}catch (Exception e) {System.out.println(e.getMessage());}
    	try {repoArbol.save(damianRosaRicardo);}catch (Exception e) {System.out.println(e.getMessage());}
    	//try {repoArbol.save(bautistaDamianCecilia);}catch (Exception e) {System.out.println(e.getMessage());}
    	
    }
}