
package es.ucm.as_tutor.negocio.usuario;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import es.ucm.as_tutor.negocio.suceso.Reto;
import es.ucm.as_tutor.negocio.suceso.Tarea;
import es.ucm.as_tutor.negocio.utils.Perfil;


public class TransferUsuarioT {

	private Integer id;

	private String nombre;

	private String correo;

	private String avatar;

	private Integer puntuacion;

	private Integer puntuacionAnterior;

	private String curso;

	private String dni;

	private String direccion;

	private Perfil tipoPerfil;

	private String notas;

	private String nombrePadre;

	private String nombreMadre;

	private String correoPadre;

	private String correoMadre;

	private String telfPadre;

	private String telfMadre;

	private String centroAcademico;

	private Reto reto;

	private ForeignCollection<Tarea> tareas;

	public TransferUsuarioT(){
		puntuacion = 0;
		puntuacionAnterior = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Integer getPuntuacionAnterior() {
		return puntuacionAnterior;
	}

	public void setPuntuacionAnterior(Integer puntuacionAnterior) {
		this.puntuacionAnterior = puntuacionAnterior;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Perfil getTipoPerfil() {
		return tipoPerfil;
	}

	public void setTipoPerfil(Perfil tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public String getNombrePadre() {
		return nombrePadre;
	}

	public void setNombrePadre(String nombrePadre) {
		this.nombrePadre = nombrePadre;
	}

	public String getNombreMadre() {
		return nombreMadre;
	}

	public void setNombreMadre(String nombreMadre) {
		this.nombreMadre = nombreMadre;
	}

	public String getCorreoPadre() {
		return correoPadre;
	}

	public void setCorreoPadre(String correoPadre) {
		this.correoPadre = correoPadre;
	}

	public String getCorreoMadre() {
		return correoMadre;
	}

	public void setCorreoMadre(String correoMadre) {
		this.correoMadre = correoMadre;
	}

	public String getTelfPadre() {
		return telfPadre;
	}

	public void setTelfPadre(String telfPadre) {
		this.telfPadre = telfPadre;
	}

	public String getTelfMadre() {
		return telfMadre;
	}

	public void setTelfMadre(String telfMadre) {
		this.telfMadre = telfMadre;
	}

	public String getCentroAcademico() {
		return centroAcademico;
	}

	public void setCentroAcademico(String centroAcademico) {
		this.centroAcademico = centroAcademico;
	}

	public Reto getReto() {
		return reto;
	}

	public void setReto(Reto reto) {
		this.reto = reto;
	}

	public ForeignCollection<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(ForeignCollection<Tarea> tareas) {
		this.tareas = tareas;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}