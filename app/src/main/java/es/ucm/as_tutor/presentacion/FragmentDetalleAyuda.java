package es.ucm.as_tutor.presentacion;

import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import es.ucm.as_tutor.R;

/**
 * Created by msalitu on 10/04/2016.
 */
public class FragmentDetalleAyuda extends Fragment {

    private TextView titulo;
    private TextView explicacion;
    private ImageView pantallazo;

    public FragmentDetalleAyuda() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detalle_ayuda, container, false);
        titulo = (TextView) rootView.findViewById(R.id.titulo);
        explicacion = (TextView) rootView.findViewById(R.id.explicacion);
        pantallazo = (ImageView) rootView.findViewById(R.id.pantallazo);
        String[] faq = getResources().getStringArray(R.array.faq);
        titulo.setText(faq[0]);
        String[] respuestasFaq = getResources().getStringArray(R.array.respuestasFaq);
        explicacion.setText(respuestasFaq[0]);
        seleccionarPantallazo(0);
        return rootView;
    }

    private void seleccionarPantallazo(Integer position){
        switch (position){
            case 0:
                pantallazo.setImageResource(R.drawable.a1);
                break;
            default:
                pantallazo.setImageResource(R.drawable.a1);
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear(); //poner otro menu
        inflater.inflate(R.menu.menu_usuario, menu);
    }
}
