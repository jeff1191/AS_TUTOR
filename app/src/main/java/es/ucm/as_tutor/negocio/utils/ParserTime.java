package es.ucm.as_tutor.negocio.utils;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

/**
 * Created by msalitu on 15/04/2016.
 */
public class ParserTime {

    // Forma el string a partir de dos enteros: la hora y el minuto
    public static String toString(Integer hour, Integer min){
        return hour + ":" + min;
    }

    // Devuelve la hora
    public static Integer hour(String time){
        return Integer.parseInt(time.substring(0, 2));
    }

    // Devuelve el minuto
    public static Integer min(String time){
        return Integer.parseInt(time.substring(3, 5));
    }

    // Time realmente es el numero de milisegundos
    public static Time toTime(String time){
        Integer hour = hour(time);
        Integer min = min(time);
        Integer ret = hour*3600 + min*20;
        Time t = new Time(ret);
        return t;
    }

}
