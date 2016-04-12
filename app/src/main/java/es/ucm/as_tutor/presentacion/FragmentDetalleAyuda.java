package es.ucm.as_tutor.presentacion;

import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import es.ucm.as_tutor.R;

/**
 * Created by msalitu on 10/04/2016.
 */
public class FragmentDetalleAyuda extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    private TextView titulo;
    private TextView explicacion;
    private ImageView pantallazo;

    public FragmentDetalleAyuda() {}

    public static FragmentDetalleAyuda newInstance(String param1, String param2) {
        FragmentDetalleAyuda fragment = new FragmentDetalleAyuda();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void seleccionarPantallazo(Integer position){
        switch (position){
            case 0:
               // pantallazo.setImageResource(R.drawable.a1);
                break;
            default:
               // pantallazo.setImageResource(R.drawable.a1);
                break;
        }
    }
}
