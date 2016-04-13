package es.ucm.as_tutor.presentacion;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import es.ucm.as_tutor.R;


public class FragmentListadoEvento extends Fragment {

    private ListView listadoEvento;
    private AdaptadorEventos adaptadorEventos;

    private ArrayList<String> listaEventos;
    private ArrayList<String> fechasEventos;
    private ArrayList<String> horaEventos;
    private ArrayList<String> horaAlarmas;
    private String nombreEvento ;
    private String horaAlarma ;
    private String horaEvento ;

    public FragmentListadoEvento() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            listaEventos = bundle.getStringArrayList("listaEventos");
            fechasEventos = bundle.getStringArrayList("fechasEventos");
            horaEventos = bundle.getStringArrayList("horaEventos");
            horaAlarmas = bundle.getStringArrayList("horaAlarmas");
            adaptadorEventos = new AdaptadorEventos(getActivity());
            adaptadorEventos.setEventos(listaEventos);
            adaptadorEventos.setFechasEventos(fechasEventos);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listado_evento, container, false);
        listadoEvento = (ListView)rootView.findViewById(R.id.ListadoEventos);
        listadoEvento.setAdapter(adaptadorEventos);
        listadoEvento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
              /*  if (listener != null) {
                    listener.onUsuarioSeleccionado(
                            (Usuario) lstListado.getAdapter().getItem(pos));
                }*/

                /******AQUI SE LLAMA AL DISPATCHER QUE HARA TO/DO ESTO ********************/


                ArrayList<String> nombresUsuarios = new ArrayList<String>();
                ArrayList<Integer> usuariosActivos = new ArrayList<Integer>();




                cargarDatos(pos,nombresUsuarios,usuariosActivos);

                Bundle bundle = new Bundle();
                FragmentDetalleEvento frgEvento = new FragmentDetalleEvento();

                //datos
                bundle.putString("nombreEvento", nombreEvento);
                bundle.putString("horaAlarma", horaAlarma);
                bundle.putString("horaEvento", horaEvento);

                bundle.putStringArrayList("listaUsuarios", nombresUsuarios);
                bundle.putIntegerArrayList("listaUsuariosActivos", usuariosActivos);

                frgEvento.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, frgEvento).commit();

                /*************************************************************************/
            }
        });

        return rootView;
    }

//ESTE METODO SIMULA LA CARGA DE LOS DATOS DE BDD (PROVISIONAL)
    public void cargarDatos(int pos,ArrayList<String> nombresUsuarios,
                            ArrayList<Integer> usuariosActivos
                            ){
        nombreEvento = listaEventos.get(pos);
        horaAlarma = horaAlarmas.get(pos);
        horaEvento = horaEventos.get(pos);
        switch (pos){
            case 0:
                nombresUsuarios.add("Juan Perez");
                usuariosActivos.add(1);
                nombresUsuarios.add("Maria Salgado");
                usuariosActivos.add(1);
                nombresUsuarios.add("Julian Iturrino");
                usuariosActivos.add(1);
                nombresUsuarios.add("Juan Luis Armas");
                usuariosActivos.add(0);
                nombresUsuarios.add("David Guess");
                usuariosActivos.add(0);
                nombresUsuarios.add("Alfredo Almache");
                usuariosActivos.add(0);
                nombresUsuarios.add("Jeff Gordon");
                usuariosActivos.add(0);
                nombresUsuarios.add("Andres Hamilton");
                usuariosActivos.add(0);
                break;
            case 1:
                nombresUsuarios.add("Juan Perez");
                usuariosActivos.add(1);
                nombresUsuarios.add("Maria Salgado");
                usuariosActivos.add(1);
                nombresUsuarios.add("Julian Iturrino");
                usuariosActivos.add(1);
                nombresUsuarios.add("Juan Luis Armas");
                usuariosActivos.add(1);
                nombresUsuarios.add("David Guess");
                usuariosActivos.add(1);
                nombresUsuarios.add("Alfredo Almache");
                usuariosActivos.add(1);
                nombresUsuarios.add("Jeff Gordon");
                usuariosActivos.add(1);
                nombresUsuarios.add("Andres Hamilton");
                usuariosActivos.add(1);
                break;
            case 2:
                nombresUsuarios.add("Juan Perez");
                usuariosActivos.add(1);
                nombresUsuarios.add("Maria Salgado");
                usuariosActivos.add(0);
                nombresUsuarios.add("Julian Iturrino");
                usuariosActivos.add(0);
                nombresUsuarios.add("Juan Luis Armas");
                usuariosActivos.add(0);
                nombresUsuarios.add("David Guess");
                usuariosActivos.add(0);
                nombresUsuarios.add("Alfredo Almache");
                usuariosActivos.add(0);
                nombresUsuarios.add("Jeff Gordon");
                usuariosActivos.add(0);
                nombresUsuarios.add("Andres Hamilton");
                usuariosActivos.add(0);
                break;
            case 3:
                nombresUsuarios.add("Juan Perez");
                usuariosActivos.add(0);
                nombresUsuarios.add("Maria Salgado");
                usuariosActivos.add(0);
                nombresUsuarios.add("Julian Iturrino");
                usuariosActivos.add(0);
                nombresUsuarios.add("Juan Luis Armas");
                usuariosActivos.add(0);
                nombresUsuarios.add("David Guess");
                usuariosActivos.add(0);
                nombresUsuarios.add("Alfredo Almache");
                usuariosActivos.add(0);
                nombresUsuarios.add("Jeff Gordon");
                usuariosActivos.add(0);
                nombresUsuarios.add("Andres Hamilton");
                usuariosActivos.add(0);
                break;
            case 4:
                nombresUsuarios.add("Juan Perez");
                usuariosActivos.add(1);
                nombresUsuarios.add("Maria Salgado");
                usuariosActivos.add(1);
                nombresUsuarios.add("Julian Iturrino");
                usuariosActivos.add(1);
                nombresUsuarios.add("Juan Luis Armas");
                usuariosActivos.add(1);
                nombresUsuarios.add("David Guess");
                usuariosActivos.add(1);
                nombresUsuarios.add("Alfredo Almache");
                usuariosActivos.add(0);
                nombresUsuarios.add("Jeff Gordon");
                usuariosActivos.add(0);
                nombresUsuarios.add("Andres Hamilton");
                usuariosActivos.add(0);
                break;
        }
    }


}
