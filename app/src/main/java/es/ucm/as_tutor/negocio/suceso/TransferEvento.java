package es.ucm.as_tutor.negocio.suceso;

import java.util.Date;
import java.util.List;

import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;

/**
 * Created by Jeffer on 15/04/2016.
 */
public class TransferEvento {
    private Integer id;
    private String nombre; // Esto es lo que considero como el TextoAlarma osea el evento
    private Date fecha; //Esto es lo que considero como el Texto en que se da la alarma real
    private Date horaAlarma;
    private Date horaEvento;

    public TransferEvento(){}



    public TransferEvento(Integer id, String nombre, Date fecha, Date horaAlarma, Date horaEvento) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.horaAlarma = horaAlarma;
        this.horaEvento = horaEvento;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraAlarma() {
        return horaAlarma;
    }

    public void setHoraAlarma(Date horaAlarma) {
        this.horaAlarma = horaAlarma;
    }

    public Date getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(Date horaEvento) {
        this.horaEvento = horaEvento;
    }
}
