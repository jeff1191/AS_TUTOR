package es.ucm.as_tutor.presentacion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import es.ucm.as_tutor.R;

/**
 * Created by Juan Lu on 07/04/2016.
 */
public class FragmentDetalleReto extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detalle_reto, container, false);

        ((TextView) rootView.findViewById(R.id.reto)).setText("Reto del usuario X");
        ((TextView) rootView.findViewById(R.id.textoReto)).setText("¿Te has cepillado los dientes?");
        ((TextView) rootView.findViewById(R.id.cont)).setText("Ha respondido afirmativante 8 veces");
        ((ProgressBar) rootView.findViewById(R.id.progressBar)).setProgress(8);

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
