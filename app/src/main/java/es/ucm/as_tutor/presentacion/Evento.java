package es.ucm.as_tutor.presentacion;

/**
 * Created by Jeffer on 21/03/2016.
 */
public class Evento {
    private String nombre;
    private String horaIni;
    private String horaFin;
    public Evento(String nombre, String horaIni,String horaFin) {
        this.nombre = nombre;
        this.horaIni = horaIni;
        this.horaFin=horaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }
}
