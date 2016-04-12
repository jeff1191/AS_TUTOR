package es.ucm.as_tutor.presentacion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.ucm.as_tutor.R;

public class FragmentDetalleUsuario extends Fragment {
    String textViewNombre;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey("campoApasar")) {
            //Cargamos el contenido de la entrada con cierto ID seleccionado en la lista. Se recomiendo usar un Loader para cargar el contenido
            textViewNombre= getArguments().getString("campoApasar");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detalle_nuevo_usuario, container, false);

        //Mostramos el contenido al usuario
        if (textViewNombre != null) {
            ((TextView) rootView.findViewById(R.id.nombre)).setText(textViewNombre);

        }

        return rootView;
    }

    public void mostrarDetalle(Usuario usuario) {
        TextView txtNombre =(TextView)getView().findViewById(R.id.nombre);
        /*EditText txtDni=(EditText)getView().findViewById(R.id.dniUsuario);
        EditText txtTelefono=(EditText)getView().findViewById(R.id.telefonoUsuario);

        txtNombre.setText(usuario.getNombre());
        txtDni.setText(usuario.getDni());
        txtTelefono.setText(usuario.getTel());*/
    }
}
