/**
 * 
 */
package es.ucm.as_tutor.negocio.suceso;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class Evento {
    @DatabaseField(generatedId = true, columnName = "ID")
    private Integer id;

    @DatabaseField(columnName = "TEXTO_ALARMA")
    private String textoAlarma;

    @DatabaseField(columnName = "TEXTO_FECHA")
    private String textoFecha;

    @DatabaseField(columnName = "HORA_ALARMA", dataType = DataType.DATE_STRING, format = "dd-MM-yyyy HH:mm:ss")
    private Date horaAlarma;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextoAlarma() {
        return textoAlarma;
    }

    public void setTextoAlarma(String textoAlarma) {
        this.textoAlarma = textoAlarma;
    }

    public String getTextoFecha() {
        return textoFecha;
    }

    public void setTextoFecha(String textoFecha) {
        this.textoFecha = textoFecha;
    }

    public Date getHoraAlarma() {
        return horaAlarma;
    }

    public void setHoraAlarma(Date horaAlarma) {
        this.horaAlarma = horaAlarma;
    }

}