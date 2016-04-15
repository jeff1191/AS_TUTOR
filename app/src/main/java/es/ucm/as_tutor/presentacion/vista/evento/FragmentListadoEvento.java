package es.ucm.as_tutor.presentacion.vista.evento;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.suceso.TransferEvento;
import es.ucm.as_tutor.presentacion.controlador.Controlador;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;


public class FragmentListadoEvento extends Fragment {

    private ListView listadoEvento;
    private AdaptadorEventos adaptadorEventos;
    private ArrayList<String> listaEventos;
    private ArrayList<String> fechasEventos;
    private ArrayList<Integer> idsEventos;

    public static FragmentListadoEvento newInstance(ArrayList<TransferEvento> eventos) {
        FragmentListadoEvento frgEventoLista = new FragmentListadoEvento();
        Bundle bundle = new Bundle();
        ArrayList<String> nombresEventos = new ArrayList<String>();
        ArrayList<String> fechaEventos= new ArrayList<String>();
        ArrayList<Integer> idsEventos= new ArrayList<Integer>();

        for(int i=0; i < eventos.size(); i++){
            nombresEventos.add(eventos.get(i).getNombre());
            fechaEventos.add(eventos.get(i).getHoraAlarma().toString());
            idsEventos.add(eventos.get(i).getId());
        }

        bundle.putStringArrayList("listaEventos", nombresEventos);
        bundle.putStringArrayList("fechasEventos", fechaEventos);
        bundle.putIntegerArrayList("idsEventos", idsEventos);
        frgEventoLista.setArguments(bundle);

        return frgEventoLista;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            listaEventos = bundle.getStringArrayList("listaEventos");
            fechasEventos = bundle.getStringArrayList("fechasEventos");
            idsEventos = bundle.getIntegerArrayList("idsEventos");
            adaptadorEventos = new AdaptadorEventos(getActivity());
            adaptadorEventos.setEventos(listaEventos);
            adaptadorEventos.setFechasEventos(fechasEventos);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.menu_usuario, menu);
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

                /*ArrayList<String> nombresUsuarios = new ArrayList<String>();
                ArrayList<String> asistenciaUsuarios = new ArrayList<String>();
                ArrayList<Integer> usuariosActivos = new ArrayList<Integer>();


                cargarDatos(pos, nombresUsuarios, usuariosActivos, asistenciaUsuarios);*/
                //Toast.makeText(getActivity().getApplicationContext(), "Opcion " + listaEventos.get(pos) + " ID: " + idsEventos.get(pos), Toast.LENGTH_SHORT).show();
               /* Toast errorNombre =
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Opcion " + listaEventos.get(pos)+ " ID: " + idsEventos.get(pos) , Toast.LENGTH_SHORT);
                errorNombre.show();*/
                TransferEvento consultar = new TransferEvento();
                consultar.setId(idsEventos.get(pos)); // Busco por id
                Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_EVENTO, consultar);
                //  FragmentDetalleEvento frgDetalleE = FragmentDetalleEvento.newInstance("PEPE","12:00","13:00",nombresUsuarios,asistenciaUsuarios,usuariosActivos);


//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, frgDetalleE).commit();

                /*************************************************************************/
            }
        });

        return rootView;
    }

//ESTE METODO SIMULA LA CARGA DE LOS DATOS DE BDD (PROVISIONAL)
    public void cargarDatos(int pos,ArrayList<String> nombresUsuarios,
                            ArrayList<Integer> usuariosActivos,
                            ArrayList<String> usuariosAsistencia){

        switch (pos){
            case 0:
                nombresUsuarios.add("Juan Perez");
                usuariosActivos.add(1);
                usuariosAsistencia.add("SI");

                nombresUsuarios.add("Maria Salgado");
                usuariosActivos.add(1);
                usuariosAsistencia.add("SI");

                nombresUsuarios.add("Julian Iturrino");
                usuariosActivos.add(1);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Juan Luis Armas");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("David Guess");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Alfredo Almache");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Jeff Gordon");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Andres Hamilton");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");
                break;
            case 1:
                nombresUsuarios.add("Juan Perez");
                usuariosActivos.add(1);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Maria Salgado");
                usuariosActivos.add(1);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Julian Iturrino");
                usuariosActivos.add(1);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Juan Luis Armas");
                usuariosActivos.add(1);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("David Guess");
                usuariosActivos.add(1);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Alfredo Almache");
                usuariosActivos.add(1);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Jeff Gordon");
                usuariosActivos.add(1);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Andres Hamilton");
                usuariosActivos.add(1);
                usuariosAsistencia.add("NO");
                break;
            case 2:
                nombresUsuarios.add("Juan Perez");
                usuariosActivos.add(1);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Maria Salgado");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Julian Iturrino");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Juan Luis Armas");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("David Guess");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Alfredo Almache");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Jeff Gordon");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Andres Hamilton");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");
                break;
            case 3:
                nombresUsuarios.add("Juan Perez");
                usuariosActivos.add(1);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Maria Salgado");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Julian Iturrino");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Juan Luis Armas");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("David Guess");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Alfredo Almache");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Jeff Gordon");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Andres Hamilton");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");
                break;
            case 4:
                nombresUsuarios.add("Juan Perez");
                usuariosActivos.add(1);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Maria Salgado");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Julian Iturrino");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Juan Luis Armas");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("David Guess");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Alfredo Almache");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Jeff Gordon");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");

                nombresUsuarios.add("Andres Hamilton");
                usuariosActivos.add(0);
                usuariosAsistencia.add("NO");
                break;
        }
    }


}
