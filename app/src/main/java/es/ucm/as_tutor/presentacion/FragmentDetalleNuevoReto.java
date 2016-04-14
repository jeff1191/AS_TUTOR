package es.ucm.as_tutor.presentacion;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import es.ucm.as_tutor.R;

/**
 * Created by Juan Lu on 07/04/2016.
 */
public class FragmentDetalleNuevoReto extends Fragment {

    EditText textoReto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detalle_nuevo_reto, container, false);

        return rootView;
    }

    public void crearNuevoReto(View view){
        //Llama al comando para crear el nuevo reto con la nueva info
        //this.textoReto = (EditText) findViewById(R.id.nuevoTextoReto);

    }

    public void cancelarNuevoReto(View view){
        //volver a la pantalla del usuario
    }
}
