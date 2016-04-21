package es.ucm.as_tutor.presentacion.vista.usuario.reto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.suceso.TransferRetoT;

/**
 * Created by Juan Lu on 07/04/2016.
 */
public class FragmentDetalleReto extends Fragment {

    private Integer id;
    private String texto;
    private Integer contador;
    private Boolean superado;
    private Integer usuario;
    private String nombreUsuario;
    private String fotoUsuario;

    public FragmentDetalleReto() {
    }

    public static FragmentDetalleReto newInstance(TransferRetoT reto) {
        FragmentDetalleReto frgReto = new FragmentDetalleReto();

        Bundle arguments = new Bundle();
        arguments.putInt("id", reto.getId());
        if(reto.getUsuario() != null) {
            arguments.putInt("usuario", reto.getUsuario().getId());
            arguments.putString("nombreUsuario", reto.getUsuario().getNombre());
            arguments.putString("fotoUsuario", reto.getUsuario().getAvatar());
        }
        arguments.putString("texto", reto.getTexto());
        arguments.putInt("contador", reto.getContador());
        arguments.putBoolean("superado", reto.getSuperado());

        frgReto.setArguments(arguments);

        return frgReto;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if(bundle != null) {
            id = bundle.getInt("id");
            texto = bundle.getString("texto");
            contador = bundle.getInt("contador");
            superado = bundle.getBoolean("superado");
            usuario = bundle.getInt("usuario");
            nombreUsuario = bundle.getString("nombreUsuario");
            fotoUsuario = bundle.getString("fotoUsuario");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detalle_reto, container, false);

        ((TextView) rootView.findViewById(R.id.reto)).setText("Reto de " + nombreUsuario);
        //((ImageView)rootView.findViewById(R.id.avatarR)).setImageBitmap(BitmapFactory.decodeFile(fotoUsuario));
        ((ImageView)rootView.findViewById(R.id.avatarR)).setImageResource(R.drawable.avatar);
        ((TextView) rootView.findViewById(R.id.textoReto)).setText(texto);

        //Si esta superado el texto debe ser diferente
        if (superado)
            ((TextView) rootView.findViewById(R.id.cont)).setText("RETO SUPERADO");
        else
            ((TextView) rootView.findViewById(R.id.cont)).setText("Ha respondido afirmativante "
                    + contador + " veces");

        ((ProgressBar) rootView.findViewById(R.id.progressBar)).setProgress(contador);


        return rootView;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear(); //poner otro menu
        inflater.inflate(R.menu.menu_reto_usuario, menu);
    }

}
