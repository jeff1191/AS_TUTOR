package es.ucm.as_tutor.presentacion;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.suceso.TransferTareaT;

/**
 * Created by msalitu on 01/04/2016.
 */
public class UsuarioTareasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_tareas);

        final ListView lista = (ListView) findViewById(R.id.listView);


        /*// Coge de la BBDD
        ArrayList<String> t = getIntent().getStringArrayListExtra("titulos");
        ArrayList<Integer> s = getIntent().getIntegerArrayListExtra("si");
        ArrayList<Integer> n = getIntent().getIntegerArrayListExtra("no");
*/

        // Esto lo hacemos así porque aún no cogemos de BBDD/*
        ArrayList<String> textosAlarma = new ArrayList<>();
        textosAlarma.add("Lavarse los dientes");
        textosAlarma.add("Meter las cosas en la mochila");

        ArrayList<String> horasAlarma = new ArrayList<>();
        horasAlarma.add("22.00");
        horasAlarma.add("8.30");

        ArrayList<String> textosPreguntas = new ArrayList<>();
        textosPreguntas.add("¿Te has lavado los dientes?");
        textosPreguntas.add("¿Has metido las cosas en la mochila?");

        ArrayList<String> horasPregunta = new ArrayList<>();
        horasPregunta.add("22.00");
        horasPregunta.add("8.30");

        ArrayList<Integer> si = new ArrayList<>();
        si.add(1);
        si.add(0);

        ArrayList<Integer> no = new ArrayList<>();
        no.add(1);
        no.add(0);

        ArrayList<String> frecuencias = new ArrayList<>();
        frecuencias.add("Diaria");
        frecuencias.add("Mensual");

        ArrayList<Boolean> habilitada = new ArrayList<>();
        habilitada.add(true);
        habilitada.add(false);
        //*/

        if(textosAlarma.size()!= 0){
            AdaptadorTareas adapter = new AdaptadorTareas(textosAlarma, horasAlarma, textosPreguntas,
                    horasPregunta, si, no, frecuencias, habilitada, this.getApplicationContext());
            lista.setAdapter(adapter);
        }
    }
}
