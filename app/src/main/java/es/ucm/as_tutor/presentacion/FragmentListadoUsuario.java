package es.ucm.as_tutor.presentacion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import es.ucm.as_tutor.R;


public class FragmentListadoUsuario extends Fragment {

	private ListView listadoUsuarios;
	private AdaptadorUsuarios adaptadorUsuarios;

	private ArrayList<String> nombres;
	private ArrayList<Integer> imagenes;
	private ArrayList<String> dnis;
	private ArrayList<String> direcciones;
	private ArrayList<String> telefonos;
	private ArrayList<String> correos;
	private ArrayList<String> colegios;
	private ArrayList<String> estudios;
	private ArrayList<String> cursos;
	private ArrayList<String> notas;
	private ArrayList<String> nombrePadres;
	private ArrayList<String> nombreMadres;
	private ArrayList<String> telfPadres;
	private ArrayList<String> telfMadres;
	private ArrayList<String> correoPadres;
	private ArrayList<String> correoMadres;
	ArrayList<String> perfiles;
	ArrayList<String> sincronizaciones;
	private ArrayList<String> puntuaciones;
	private Menu menuActionBar;


	public FragmentListadoUsuario() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle bundle = getArguments();
		if (bundle != null) {
			nombres = bundle.getStringArrayList("nombres");
			imagenes = bundle.getIntegerArrayList("imagenes");
			dnis = bundle.getStringArrayList("dnis");
			direcciones = bundle.getStringArrayList("direcciones");
			telefonos = bundle.getStringArrayList("telefonos");
			correos = bundle.getStringArrayList("correos");
			colegios = bundle.getStringArrayList("colegios");
			estudios = bundle.getStringArrayList("estudios");
			cursos = bundle.getStringArrayList("cursos");
			notas = bundle.getStringArrayList("notas");
			nombrePadres = bundle.getStringArrayList("nombrePadres");
			nombreMadres = bundle.getStringArrayList("nombreMadres");
			telfPadres = bundle.getStringArrayList("telfPadres");
			telfMadres = bundle.getStringArrayList("telfMadres");
			correoPadres = bundle.getStringArrayList("correoPadres");
			correoMadres = bundle.getStringArrayList("correoMadres");
			perfiles = bundle.getStringArrayList("perfiles");
			sincronizaciones = bundle.getStringArrayList("sincronizaciones");
			puntuaciones = bundle.getStringArrayList("puntuaciones");

			adaptadorUsuarios = new AdaptadorUsuarios(getActivity());
			adaptadorUsuarios.setNombres(nombres);
			adaptadorUsuarios.setImagenes(imagenes);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_listado_usuario, container, false);
		listadoUsuarios = (ListView)rootView.findViewById(R.id.listadoUsuarios);
		listadoUsuarios.setAdapter(adaptadorUsuarios);

		listadoUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
				Bundle bundle = new Bundle();

				bundle.putString("nombre", nombres.get(pos));
				bundle.putInt("avatar", imagenes.get(pos));
				bundle.putString("dnis", dnis.get(pos));
				bundle.putString("direcciones", direcciones.get(pos));
				bundle.putString("telefonos", telefonos.get(pos));
				bundle.putString("correos", correos.get(pos));
				bundle.putString("colegios", colegios.get(pos));
				bundle.putString("estudios", estudios.get(pos));
				bundle.putString("cursos", cursos.get(pos));
				bundle.putString("notas", notas.get(pos));
				bundle.putString("nombrePadres", nombrePadres.get(pos));
				bundle.putString("nombreMadres", nombreMadres.get(pos));
				bundle.putString("telfPadres", telfPadres.get(pos));
				bundle.putString("telfMadres", telfMadres.get(pos));
				bundle.putString("correoPadres", correoPadres.get(pos));
				bundle.putString("correoMadres", correoMadres.get(pos));
				bundle.putString("perfiles", perfiles.get(pos));
				bundle.putString("sincronizaciones", sincronizaciones.get(pos));
				bundle.putString("puntuaciones", puntuaciones.get(pos));

				FragmentDetalleUsuario frgUsuario = new FragmentDetalleUsuario();
				frgUsuario.setArguments(bundle);
				getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FrgDetalle, frgUsuario).commit();
			}
		});

		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuActionBar = menu;
		getActivity().getMenuInflater().inflate(R.menu.menu_usuario, menu);
	}
}

