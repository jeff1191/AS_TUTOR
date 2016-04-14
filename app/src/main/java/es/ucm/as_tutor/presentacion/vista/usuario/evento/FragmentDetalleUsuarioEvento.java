package es.ucm.as_tutor.presentacion.vista.usuario.evento;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import es.ucm.as_tutor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleUsuarioEvento extends Fragment {


    private ListView listViewEventos;
    public FragmentDetalleUsuarioEvento() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detalle_usuario_evento, container, false);

        listViewEventos = (ListView) rootView.findViewById(R.id.listViewEventos);

        View header = inflater.inflate(R.layout.header_usuario_eventos, listViewEventos, false);
        listViewEventos.addHeaderView(header, null, false);

        ArrayList<String> items = new ArrayList<>();
        items.add("Ir al Barcas a las 12:00 - 14 feb 2016");
        items.add("Ir al Barcas a las 12:00 - 14 feb 2016");
        items.add("Ir al Barcas a las 12:00 - 14 feb 2016");
        items.add("Ir al Barcas a las 12:00 - 14 feb 2016");
        items.add("Ir al Barcas a las 12:00 - 14 feb 2016");
        items.add("Ir al Barcas a las 12:00 - 14 feb 2016");

        ArrayList<String> asistencia = new ArrayList<>();
        asistencia.add("Si");
        asistencia.add("Si");
        asistencia.add("Si");
        asistencia.add("No");
        asistencia.add("No");
        asistencia.add("No");

        AdaptadorUsuarioEventos adapter = new AdaptadorUsuarioEventos(getActivity());
        adapter.setEventos(items);
        adapter.setAsistencia(asistencia);

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
