
package es.ucm.as_tutor.negocio.usuario;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import es.ucm.as_tutor.negocio.suceso.Reto;
import es.ucm.as_tutor.negocio.suceso.Tarea;
import es.ucm.as_tutor.negocio.utils.Perfil;


public class Usuario {


    @DatabaseField(generatedId = true, columnName = "ID")
    private Integer id;

    @DatabaseField(columnName = "NOMBRE")
    private String nombre;

    @DatabaseField(columnName = "CORREO")
    private String correo;

    @DatabaseField(columnName = "AVATAR")
    private String avatar;

    @DatabaseField(columnName = "PUNTUACION")
    private Integer puntuacion;

    @DatabaseField(columnName = "PUNTUACION_ANTERIOR")
    private Integer puntuacionAnterior;

    @DatabaseField(columnName = "CURSO")
	private String curso;

    @DatabaseField(columnName = "DNI")
	private String dni;

    @DatabaseField(columnName = "TIPO_PERFIL", dataType = DataType.ENUM_STRING)
	private Perfil tipoPerfil;

    @DatabaseField(columnName = "NOTAS")
	private String notas;

    @DatabaseField(columnName = "NOMBRE_PADRE")
	private String nombrePadre;

    @DatabaseField(columnName = "NOMBRE_MADRE")
	private String nombreMadre;

    @DatabaseField(columnName = "CORREO_PADRE")
	private String correoPadre;

    @DatabaseField(columnName = "CORREO_MADRE")
	private String correoMadre;

    @DatabaseField(columnName = "TEL_PADRE")
	private String telfPadre;

    @DatabaseField(columnName = "TEL_MADRE")
	private String telfMadre;

    @DatabaseField(columnName = "CENTRO_ACADEMICO")
	private String centroAcademico;

    @DatabaseField(foreign = true, columnName = "RETO")
	private Reto reto;

    @ForeignCollectionField(eager = true, columnName = "TAREAS")
    private ForeignCollection<Tarea> tareas;

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
}