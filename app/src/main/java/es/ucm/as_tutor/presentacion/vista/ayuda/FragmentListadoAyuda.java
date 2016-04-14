package es.ucm.as_tutor.presentacion.vista.ayuda;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.ucm.as_tutor.R;

/**
 * Created by msalitu on 10/04/2016.
 */
public class FragmentListadoAyuda extends Fragment {

    private ListView listadoAyuda;

    private OnFragmentInteractionListener mListener;

    public FragmentListadoAyuda() { }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        listadoAyuda = (ListView)getView().findViewById(R.id.listaAyuda);

        ArrayAdapter<CharSequence> adapterAyuda = ArrayAdapter.createFromResource(
                this.getActivity(), R.array.faq, android.R.layout.simple_list_item_1);
        listadoAyuda.setAdapter(adapterAyuda);

        // Se habilita la posibilidad de seleccionar una fila para que se muestre un menu
        listadoAyuda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarAyuda(view, position);
            }
        });
    }

    private void mostrarAyuda(View view, int position) {
        // dependiendo de la pregunta se lanza un fragment u otro
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado_ayuda, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
