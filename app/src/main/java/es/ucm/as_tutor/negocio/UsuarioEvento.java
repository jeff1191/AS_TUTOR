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
}
