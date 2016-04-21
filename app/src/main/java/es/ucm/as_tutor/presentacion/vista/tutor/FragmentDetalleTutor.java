package es.ucm.as_tutor.presentacion.vista.tutor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.tutor.TransferTutorT;
import es.ucm.as_tutor.presentacion.controlador.Controlador;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tutor.ConsultarTutorComando;

/**
 * Created by Juan Lu on 12/04/2016.
 */
public class FragmentDetalleTutor extends Fragment {

    private String codigoSincronizacion;
    private String nombre;
    private String email;
    private String contrasenha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Command c = new ConsultarTutorComando();
        try {
            TransferTutorT tutor = (TransferTutorT) c.ejecutaComando(null);
            codigoSincronizacion = tutor.getCodigoSincronizacion();
            nombre = tutor.getNombre();
            email = tutor.getCorreo();
            contrasenha = tutor.getContrasenha();
        } catch (commandException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detalle_tutor, container, false);
        EditText nombrev = (EditText)rootView.findViewById(R.id.nombreTutor);
        EditText correov = (EditText)rootView.findViewById(R.id.correoTutor);
        EditText contrasehav = (EditText)rootView.findViewById(R.id.contrasenha);

        nombrev.setText(nombre);
        correov.setText(email);
        contrasehav.setText(contrasenha);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear(); //poner otro menu
        inflater.inflate(R.menu.menu_usuario, menu);
    }
}
