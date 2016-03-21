package es.ucm.as_tutor;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class FragmentListadoTareas extends Fragment {

	private Usuario[] datos =
			new Usuario[]{
					new Usuario("TAREA 1", "Tipo 1", "Usuario 1","651156498","98456214V"),
					new Usuario("TAREA 2", "Tipo 2", "Usuario 2","654987789","18187448V"),
					new Usuario("TAREA 3", "Tipo 3", "Usuario 3","123321123","98488944V"),
					new Usuario("TAREA 4", "Tipo 4", "Usuario 4","123546456","98887452V"),
					new Usuario("TAREA 5", "Tipo 5", "Usuario 5","789456012","26223562V")};

	private ListView lstListado;

	private CorreosListener listener;

	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container,
							 Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_listado, container, false);
	}

	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);

		lstListado = (ListView)getView().findViewById(R.id.LstListado);
		//lstListado.removeAllViews();
		lstListado.setAdapter(new AdaptadorTareas(this,datos));

		lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
				if (listener!=null) {
					listener.onCorreoSeleccionado(
							(Usuario)lstListado.getAdapter().getItem(pos));
				}
			}
		});
	}



	public interface CorreosListener {
		void onCorreoSeleccionado(Usuario c);
	}

	public void setCorreosListener(CorreosListener listener) {
		this.listener=listener;
	}
	public Usuario[] getUsuarios() {
		return datos;
	}
}

