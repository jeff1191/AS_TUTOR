package es.ucm.as_tutor.presentacion.vista.usuario.evento;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.usuario.TransferUsuario;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleUsuarioEvento extends Fragment {


    private ListView listViewEventos;
    private String nombreUsuario;
    private AdaptadorUsuarioEventos adapter;

    public FragmentDetalleUsuarioEvento() {
        // Required empty public constructor
    }
    public static FragmentDetalleUsuarioEvento newInstance(TransferUsuario usuario,
                                                    ArrayList<String> nombresEventos,
                                                    ArrayList<String> asistenciaEventos) {
        FragmentDetalleUsuarioEvento frgDetalleEvento = new FragmentDetalleUsuarioEvento();
        Bundle bundle = new Bundle();
        if(nombresEventos.size() != 0)
            bundle.putString("nombreUsuario","Eventos de " + usuario.getNombre());
        else
            bundle.putString("nombreUsuario","El usuario " + usuario.getNombre()+ " no tiene eventos");

        bundle.putStringArrayList("listaEventos", nombresEventos);
        bundle.putStringArrayList("listaEventosAsistencia", asistenciaEventos);

        frgDetalleEvento.setArguments(bundle);
        return frgDetalleEvento;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if(bundle != null) {

            ArrayList<String> listaEventos = bundle.getStringArrayList("listaEventos");
            ArrayList<String> listaUsuariosAsistencia = bundle.getStringArrayList("listaEventosAsistencia");
            nombreUsuario = bundle.getString("nombreUsuario");
            adapter = new AdaptadorUsuarioEventos(getActivity());
            adapter.setEventos(listaEventos);
            adapter.setAsistencia(listaUsuariosAsistencia);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detalle_usuario_evento, container, false);
        listViewEventos = (ListView) rootView.findViewById(R.id.listViewEventos);
        TextView titulo = (TextView) rootView.findViewById(R.id.textViewNombreEventosUsuario);
        titulo.setText(nombreUsuario);
        View header = inflater.inflate(R.layout.header_usuario_eventos, listViewEventos, false);
        listViewEventos.addHeaderView(header, null, false);
        listViewEventos.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear(); //poner otro menu
        inflater.inflate(R.menu.menu_eventos_usuario, menu);
    }


}
