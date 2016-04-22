package es.ucm.as_tutor.presentacion.vista.ayuda;

import android.support.v4.app.Fragment;
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
    private Integer pos;

    public FragmentDetalleAyuda() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        pos = bundle.getInt("pos");
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
        titulo.setText(faq[pos]);
        String[] respuestasFaq = getResources().getStringArray(R.array.respuestasFaq);
        explicacion.setText(respuestasFaq[pos]);
        seleccionarPantallazo();
        return rootView;
    }

    private void seleccionarPantallazo(){
        switch (pos){
            case 0: //crear usuario
                pantallazo.setImageResource(R.drawable.a_crear_usuario);
                break;
            case 1: //crear tarea
                pantallazo.setImageResource(R.drawable.a_crear_tarea);
                break;
            case 2: //editar/deshabilitar/eliminar tarea
                pantallazo.setImageResource(R.drawable.a_editar_tarea);
                break;
            default:
                pantallazo.setImageResource(R.drawable.a_crear_tarea);
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_vacio, menu);
    }
}
