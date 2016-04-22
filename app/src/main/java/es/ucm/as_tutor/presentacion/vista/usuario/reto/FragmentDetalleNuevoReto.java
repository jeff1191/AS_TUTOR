package es.ucm.as_tutor.presentacion.vista.usuario.reto;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
public class FragmentDetalleNuevoReto extends Fragment {

    private EditText textoReto;
    private EditText premio;
    private Button anadirNuevoReto;
    private Button cancelarNuevoReto;

    private Integer idUsuario;
    private String nombreUsuario;
    private String fotoUsuario;

    public static FragmentDetalleNuevoReto newInstance(TransferRetoT reto) {
        FragmentDetalleNuevoReto frgNuevoReto = new FragmentDetalleNuevoReto();

        Bundle arguments = new Bundle();
        arguments.putInt("usuario", reto.getIdUsuario());
        try {
            TransferUsuarioT ret = (TransferUsuarioT) FactoriaComandos.getInstancia()
                    .getCommand(ListaComandos.CONSULTAR_USUARIO).ejecutaComando(reto.getIdUsuario());
            arguments.putString("nombreUsuario", ret.getNombre());
            arguments.putString("fotoUsuario", ret.getAvatar());
        } catch (commandException e) {
            e.printStackTrace();
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

        if(fotoUsuario != null && !fotoUsuario.equals(""))
            ((ImageView)rootView.findViewById(R.id.avatarNR)).setImageBitmap(BitmapFactory.decodeFile(fotoUsuario));
        else
            ((ImageView)rootView.findViewById(R.id.avatarNR)).setImageResource(R.drawable.avatar);

        textoReto = (EditText) rootView.findViewById(R.id.textoReto);
        premio = (EditText) rootView.findViewById(R.id.premio);
        anadirNuevoReto = (Button) rootView.findViewById(R.id.botonAceptarReto);
        cancelarNuevoReto = (Button) rootView.findViewById(R.id.botonCancelarReto);

        anadirNuevoReto.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comando guarda reto al usuario
                TransferRetoT reto = new TransferRetoT();
                reto.setTexto(textoReto.getText().toString());
                reto.setContador(0);
                reto.setIdUsuario(idUsuario);
                reto.setPremio(premio.getText().toString());
                reto.setSuperado(false);
                Controlador.getInstancia().ejecutaComando(ListaComandos.CREAR_RETO, reto);
                Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_RETO, idUsuario);
            }
        });

        cancelarNuevoReto.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_USUARIO, idUsuario);
            }
        });

        return rootView;
    }

}
