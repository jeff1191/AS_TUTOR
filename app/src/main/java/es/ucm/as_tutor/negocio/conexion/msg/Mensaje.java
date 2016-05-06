package es.ucm.as_tutor.negocio.conexion.msg;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by msalitu on 28/04/2016.
 */
public class Mensaje implements Serializable{
    static final long serialVersionUID = 1L;

    private String texto;

    private Integer numero;

    private Date fecha;

    public Mensaje(){
        texto="Mensaje por defecto";
        numero=0;
        fecha = new Date();
    }

    public Mensaje(String respuesta){
        texto=respuesta;
        numero=texto.length();
        fecha = new Date();
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
