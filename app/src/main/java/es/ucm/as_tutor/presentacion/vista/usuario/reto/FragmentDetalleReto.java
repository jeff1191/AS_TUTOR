package es.ucm.as_tutor.presentacion.vista.usuario.reto;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.suceso.TransferRetoT;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import es.ucm.as_tutor.presentacion.controlador.Controlador;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;
import es.ucm.as_tutor.presentacion.controlador.comandos.factoria.FactoriaComandos;

/**
 * Created by Juan Lu on 07/04/2016.
 */
public class FragmentDetalleReto extends Fragment {

    private Integer id;
    private String texto;
    private Integer contador;
    private Boolean superado;
    private static Integer idUsuario;
    private String nombreUsuario;
    private String fotoUsuario;
    private String premio;

    public FragmentDetalleReto() {
    }

    public static FragmentDetalleReto newInstance(TransferRetoT reto) {
        FragmentDetalleReto frgReto = new FragmentDetalleReto();

        Bundle arguments = new Bundle();
        arguments.putInt("id", reto.getId());
        if(reto.getIdUsuario() != null) {
            arguments.putInt("usuario", reto.getIdUsuario());
            try {
                TransferUsuarioT ret = (TransferUsuarioT) FactoriaComandos.getInstancia()
                        .getCommand(ListaComandos.CONSULTAR_USUARIO).ejecutaComando(reto.getIdUsuario());
                arguments.putString("nombreUsuario", ret.getNombre());
                arguments.putString("fotoUsuario", ret.getAvatar());
            } catch (commandException e) {
                e.printStackTrace();
            }
        }
        arguments.putString("texto", reto.getTexto());
        arguments.putInt("contador", reto.getContador());
        arguments.putBoolean("superado", reto.getSuperado());
        arguments.putString("premio", reto.getPremio());

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
            idUsuario = bundle.getInt("usuario");
            nombreUsuario = bundle.getString("nombreUsuario");
            fotoUsuario = bundle.getString("fotoUsuario");
            premio = bundle.getString("premio");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detalle_reto, container, false);

        ((TextView) rootView.findViewById(R.id.reto)).setText("Reto de " + nombreUsuario);

        if(fotoUsuario != null && !fotoUsuario.equals(""))
            ((ImageView)rootView.findViewById(R.id.avatarR)).setImageBitmap(BitmapFactory.decodeFile(fotoUsuario));
        else
            ((ImageView)rootView.findViewById(R.id.avatarR)).setImageResource(R.drawable.avatar);

        ((TextView) rootView.findViewById(R.id.textoReto)).setText(texto);

        if(premio != null && !premio.equals(""))
            ((TextView) rootView.findViewById(R.id.textoPremio)).setText("Premio: " + premio);
        else
            ((TextView) rootView.findViewById(R.id.textoPremio)).setText("No tiene ningun premio asignado");

        //Si esta superado el texto debe ser diferente
        if (superado)
            ((TextView) rootView.findViewById(R.id.cont)).setText("RETO SUPERADO");
        else
            ((TextView) rootView.findViewById(R.id.cont)).setText("Ha respondido afirmativante "
                    + contador + " veces");
        ((TextView) rootView.findViewById(R.id.contador)).setText(contador + "/30");
        ((ProgressBar) rootView.findViewById(R.id.progressBar)).setProgress(contador);


        return rootView;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear(); //poner otro menu
        inflater.inflate(R.menu.menu_reto_usuario, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.usuario:
                Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_USUARIO, idUsuario);
                break;
            case R.id.tareasUsuario:
                Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_TAREAS, idUsuario);
                break;
            case R.id.eventosUsuario:

                break;
            case R.id.enviarCorreo:

                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}
