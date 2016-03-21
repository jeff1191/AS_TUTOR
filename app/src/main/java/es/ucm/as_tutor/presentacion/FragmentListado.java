package es.ucm.as_tutor.presentacion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import es.ucm.as_tutor.R;


public class FragmentListado extends Fragment {

	private Usuario[] datos =
			new Usuario[]{
					new Usuario("Usuario 1", "Tipo 1", "Usuario 1","651156498","98456214V"),
					new Usuario("Usuario 2", "Tipo 2", "Usuario 2","654987789","18187448V"),
					new Usuario("Usuario 3", "Tipo 3", "Usuario 3","123321123","98488944V"),
					new Usuario("Usuario 4", "Tipo 4", "Usuario 4","123546456","98887452V"),
					new Usuario("Usuario 5", "Tipo 5", "Usuario 5","789456012","26223562V")};

	private ListView lstListado;
	private boolean activadoFrg=false;

	private ListadoListener listener;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null && getArguments().containsKey("activoListadoUsuario")) {
			//Cargamos el contenido de la entrada con cierto ID seleccionado en la lista. Se recomiendo usar un Loader para cargar el contenido
			activadoFrg= getArguments().getBoolean("activoListadoUsuario");
		}
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_listado, container, false);
		if(rootView != null && activadoFrg){
			lstListado = (ListView)rootView.findViewById(R.id.LstListado);
			lstListado.setAdapter(new AdaptadorUsuarios(this, datos));

			lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
					if (listener!=null) {
						listener.onUsuarioSeleccionado(
								(Usuario) lstListado.getAdapter().getItem(pos));
					}
				}
			});
		}
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
/*
		lstListado = (ListView)getView().findViewById(R.id.LstListado);

		lstListado.setAdapter(new AdaptadorUsuarios(this, datos));

		lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
				if (listener!=null) {
					listener.onUsuarioSeleccionado(
							(Usuario) lstListado.getAdapter().getItem(pos));
				}
			}
		});*/
	}



	public interface ListadoListener {
		void onUsuarioSeleccionado(Usuario c);
	}

	public void setListadoListener(ListadoListener listener) {
		this.listener=listener;
	}
	public Usuario[] getUsuarios() {
		return datos;
	}

	public void cambiarAdaptadorTareas(){

	}

}

