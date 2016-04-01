package es.ucm.as_tutor.presentacion;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.ucm.as_tutor.R;
/**
 * Created by msalitu on 01/04/2016.
 */
public class AdaptadorEventos extends ArrayAdapter<Evento> {

    Activity context;
    Evento[] datosUsuarios;
    AdaptadorEventos(Fragment context, Evento[] datos) {
        super(context.getActivity(), R.layout.lista_eventos, datos);
        datosUsuarios=datos;
        this.context = context.getActivity();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.lista_eventos, null);

        TextView nombreTarea= (TextView)item.findViewById(R.id.ListaTareasNombre);
        nombreTarea.setText(datosUsuarios[position].getNombre());

        TextView desc = (TextView)item.findViewById(R.id.ListaTareasDes);
        desc.setText(datosUsuarios[position].getHoraIni());

        return(item);
    }
}