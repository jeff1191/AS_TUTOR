package es.ucm.as_tutor.negocio.suceso;

import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;

/**
 * Created by Jeffer on 21/04/2016.
 */
public class TransferUsuarioEvento {
    private Integer id;
    private TransferEvento evento;
    private TransferUsuarioT usuario;
    private Integer activo;
    private String asistencia;

    public TransferUsuarioEvento(TransferEvento evento, TransferUsuarioT usuario) {
        this.evento = evento;
        this.usuario = usuario;
    }
    public TransferUsuarioEvento(){

    }

    public TransferEvento getEvento() {
        return evento;
    }

    public void setEvento(TransferEvento evento) {
        this.evento = evento;
    }

    public TransferUsuarioT getUsuario() {
        return usuario;
    }

    public void setUsuario(TransferUsuarioT usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
