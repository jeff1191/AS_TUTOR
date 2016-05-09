package es.ucm.as_tutor.negocio.conexion.msg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.ucm.as_tutor.negocio.suceso.TransferEvento;
import es.ucm.as_tutor.negocio.suceso.TransferReto;
import es.ucm.as_tutor.negocio.suceso.TransferTarea;
import es.ucm.as_tutor.negocio.usuario.TransferUsuario;

/**
 * Created by msalitu on 28/04/2016.
 */
public class Mensaje implements Serializable{

    static final long serialVersionUID = 1L;

    private String verificar;
    private TransferUsuario usuario;
    private TransferReto reto;
    private List<TransferEvento> eventos;
    private List<TransferTarea> tareas;

    public Mensaje(String verificar){
        this.verificar = verificar;
    }

    public Mensaje(){
        this.verificar = "";
        usuario = new TransferUsuario();
        reto = new TransferReto();
        eventos = new ArrayList<TransferEvento>();
        tareas = new ArrayList<TransferTarea>();
    }

    public Mensaje(TransferUsuario usuario, TransferReto reto,
                   ArrayList<TransferEvento> eventos, ArrayList<TransferTarea> tareas ){
        this.usuario = usuario;
        this.reto = reto;
        this.eventos = eventos;
        this.tareas = tareas;
    }

    public TransferUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(TransferUsuario usuario) {
        this.usuario = usuario;
    }

    public TransferReto getReto() {
        return reto;
    }

    public void setReto(TransferReto reto) {
        this.reto = reto;
    }

    public List<TransferEvento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<TransferEvento> eventos) {
        this.eventos = eventos;
    }

    public List<TransferTarea> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<TransferTarea> tareas) {
        this.tareas = tareas;
    }

    public String getVerificar() {
        return verificar;
    }

    public void setVerificar(String verificar) {
        this.verificar = verificar;
    }
}