package es.ucm.as_tutor.presentacion;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import es.ucm.as_tutor.R;

/**
 * Created by msalitu on 02/04/2016.
 */
public class AdaptadorTareas extends BaseAdapter {

    Context context;
    ArrayList<String> textosAlarma;
    ArrayList<String> horasAlarma;
    ArrayList<String> textosPreguntas;
    ArrayList<String> horasPregunta;
    ArrayList<Integer> si;
    ArrayList<Integer> no;
    ArrayList<String> frecuencias;
    ArrayList<Boolean> habilitada;
    LayoutInflater inflater;

    public AdaptadorTareas(ArrayList<String> textosAlarma, ArrayList<String> horasAlarma,
                           ArrayList<String> textosPreguntas, ArrayList<String> horasPregunta,
                           ArrayList<Integer> si,ArrayList<Integer> no, ArrayList<String> frecuencias,
                           ArrayList<Boolean> habilitada, Context context) {
        this.context = context;
        this.textosAlarma = textosAlarma;
        this.horasAlarma = horasAlarma;
        this.textosPreguntas = textosPreguntas;
        this.horasPregunta = horasPregunta;
        this.si = si;
        this.no = no;
        this.frecuencias = frecuencias;
        this.habilitada = habilitada;
    }

    @Override
    public int getCount() {
        return textosAlarma.size();
    }

    @Override
    public Object getItem(int position) {
        return textosAlarma.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        TextView txtTitle;  // texto alarma
        TextView sit;       // numero de si
        TextView not;       // numero de no
        TextView total;     // balance del progreso (si - no = total)

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.tareas_row, parent, false);

        // Locacalizar en el listview_item.xml
        // me falta encontrar todos los campos que no tiene t
        txtTitle = (TextView) itemView.findViewById(R.id.nombreTarea);
        sit = (TextView) itemView.findViewById(R.id.si);
        not = (TextView) itemView.findViewById(R.id.no);
        total = (TextView) itemView.findViewById(R.id.total);

        // Dar valores a los TextView
        txtTitle.setText(textosAlarma.get(position));
        String ss = si.get(position).toString();
        sit.setText(ss);
        String sn = no.get(position).toString();
        not.setText(sn);
        Integer t = si.get(position)-no.get(position);
        if(t >= 0)
            total.setTextColor(Color.GREEN);
        else
            total.setTextColor(Color.RED);
        total.setText(t.toString());

        return itemView;
    }
}
