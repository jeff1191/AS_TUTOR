package es.ucm.as_tutor.negocio;

import com.j256.ormlite.field.DatabaseField;

import es.ucm.as_tutor.negocio.suceso.Evento;
import es.ucm.as_tutor.negocio.usuario.Usuario;

/**
 * Created by Juan Lu on 31/03/2016.
 */
public class UsuarioEvento {

    @DatabaseField(generatedId = true, columnName = "ID")
    private Integer id;
    @DatabaseField(foreign = true, columnName = "USUARIO")
    private Usuario usuario;
    @DatabaseField(foreign = true, columnName = "EVENTO")
    private Evento evento;
    @DatabaseField(columnName = "ACTIVO")
    private Integer activo;
    @DatabaseField(columnName = "ASISTENCIA")
    private String asistencia;


    public UsuarioEvento(Evento evento, Usuario usuario) {
        this.usuario = usuario;
        this.evento = evento;
    }
    public UsuarioEvento(){} // ORMLITE

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }
}
