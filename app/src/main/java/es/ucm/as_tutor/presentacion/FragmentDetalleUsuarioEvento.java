package es.ucm.as_tutor.presentacion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detalle_usuario_evento, container, false);

        listViewEventos = (ListView) rootView.findViewById(R.id.listViewEventos);

        String[] items = { "Ir al Barcas a las 12:00 - 14 feb 2016",
                "Ir al Barcas a las 12:00 - 14 febrero de 2016",
                "Ir al Sitio A a las 1:00 - 09 Marzo de 2016",
                "Ir al Museo a las 15:00 - 11 abril de 2016",
                "Ir al Sitio B a las 22:30 - 22 mayo de 2016",
                "Ir a donde Pepe a las 08:30 - 12 diciembre de 2016"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, items);

        listViewEventos.setAdapter(adapter);

        return rootView;



    }

}
