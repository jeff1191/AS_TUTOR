package es.ucm.as_tutor;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Jeffer on 19/03/2016.
 */
class AdaptadorTareas extends ArrayAdapter<Usuario> {

    Activity context;
    Usuario[] datosUsuarios;
    AdaptadorTareas(FragmentListadoTareas context, Usuario[] datos) {
        super(context.getActivity(), R.layout.lista_tareas, datos);
        datosUsuarios=datos;
        this.context = context.getActivity();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.lista_tareas, null);

        TextView nombreUsuario= (TextView)item.findViewById(R.id.ListaTareasNombre);
        nombreUsuario.setText(datosUsuarios[position].getNombre());

        TextView desc = (TextView)item.findViewById(R.id.ListaTareasDes);
        desc.setText(datosUsuarios[position].getDesc());

        return(item);
    }
}
