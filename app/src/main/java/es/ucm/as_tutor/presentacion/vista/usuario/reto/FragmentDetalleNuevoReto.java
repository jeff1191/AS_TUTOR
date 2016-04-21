package es.ucm.as_tutor.presentacion.vista.usuario.reto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.suceso.TransferRetoT;

/**
 * Created by Juan Lu on 07/04/2016.
 */
public class FragmentDetalleNuevoReto extends Fragment {

    EditText textoReto;
    ImageView premio;

    private Integer idUsuario;
    private String nombreUsuario;
    private String fotoUsuario;

    public static FragmentDetalleNuevoReto newInstance(TransferRetoT reto) {
        FragmentDetalleNuevoReto frgNuevoReto = new FragmentDetalleNuevoReto();

        Bundle arguments = new Bundle();
        if(reto.getUsuario() != null) {
            arguments.putInt("usuario", reto.getUsuario().getId());
            arguments.putString("nombreUsuario", reto.getUsuario().getNombre());
            arguments.putString("fotoUsuario", reto.getUsuario().getAvatar());
        }

        frgNuevoReto.setArguments(arguments);

        return frgNuevoReto;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            idUsuario = bundle.getInt("usuario");
            nombreUsuario = bundle.getString("nombreUsuario");
            fotoUsuario = bundle.getString("fotoUsuario");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detalle_nuevo_reto, container, false);


        ((TextView) rootView.findViewById(R.id.retoNuevo)).setText("Reto de " + nombreUsuario);
        //((ImageView)rootView.findViewById(R.id.avatarR)).setImageBitmap(BitmapFactory.decodeFile(fotoUsuario));
        ((ImageView)rootView.findViewById(R.id.avatarNR)).setImageResource(R.drawable.avatar);

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
