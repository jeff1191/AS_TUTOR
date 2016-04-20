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
        if(time.length() == 5)
            return Integer.parseInt(time.substring(0, 2));
        else
            return Integer.parseInt(time.substring(0, 1));
    }

    // Devuelve el minuto
    public static Integer min(String time){
        if(time.length() == 5)
            return Integer.parseInt(time.substring(3, 5));
        else
            return Integer.parseInt(time.substring(3, 4));
    }

    // Time realmente es el numero de milisegundos
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
