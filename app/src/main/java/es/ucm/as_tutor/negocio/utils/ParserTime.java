package es.ucm.as_tutor.negocio.utils;

import android.util.Log;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

/**
 * Created by msalitu on 15/04/2016.
 */
public class ParserTime {

    // Forma el string a partir de dos enteros: la hora y el minuto
    public static String toString(Integer hour, Integer min){
        return hour.toString() + ":" + min.toString();
    }

    // Devuelve la hora
    public static Integer hour(String time){
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]);
    }

    // Devuelve el minuto
    public static Integer min(String time){
        String[] parts = time.split(":");
        return Integer.parseInt(parts[1]);
    }

    // Se crea el objeto a partir del numero de milisegundos desde el 1/1/1970 pero a nosotros
    // solo nos interesa la hora, no la fecha ya que ni la almacenamos en la BBDD
    public static Date toDate(String time){
        Integer hour = hour(time);
        Integer min = min(time);
        long ret = (hour*3600 + min*60)*1000;
        Date d = new Date(ret);
        return d;
    }

    public static String DateToString(Date date){
        String ret = date.toString().substring(10, 16);
        return ret;
    }

}
