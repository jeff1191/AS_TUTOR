package es.ucm.as_tutor;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentDetalle extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

    public void mostrarDetalle(Usuario usuario) {
        TextView txtNombre =(TextView)getView().findViewById(R.id.DetalleNombreUsuario);
        EditText txtUsuario =(EditText)getView().findViewById(R.id.editTextDetalleNombre);
        EditText txtDni=(EditText)getView().findViewById(R.id.editTextDetalleDni);
        EditText txtTelefono=(EditText)getView().findViewById(R.id.editTextDetalleTelefono);

        txtNombre.setText(usuario.getTexto());
        txtUsuario.setText(usuario.getNombre());
        txtDni.setText(usuario.getDni());
        txtTelefono.setText(usuario.getTel());
    }
}
