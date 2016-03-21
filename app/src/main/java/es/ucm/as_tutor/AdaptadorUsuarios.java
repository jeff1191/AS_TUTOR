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
class AdaptadorUsuarios extends ArrayAdapter<Usuario> {

    Activity context;
    Usuario[] datosUsuarios;
    AdaptadorUsuarios(Fragment context, Usuario[] datos) {
        super(context.getActivity(), R.layout.lista_usuarios, datos);
        datosUsuarios=datos;
        this.context = context.getActivity();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.lista_usuarios, null);

        TextView nombreUsuario= (TextView)item.findViewById(R.id.listaUsuarioTexto);
        nombreUsuario.setText(datosUsuarios[position].getNombre());

        TextView desc = (TextView)item.findViewById(R.id.ListaUsuarioDes);
        desc.setText(datosUsuarios[position].getDesc());

        return(item);
    }
}
