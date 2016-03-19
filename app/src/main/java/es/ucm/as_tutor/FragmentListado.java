package es.ucm.as_tutor;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class FragmentListado extends Fragment {

	private Usuario[] datos =
			new Usuario[]{
					new Usuario("Usuario 1", "Tipo 1", "Informacion 1"),
					new Usuario("Usuario 2", "Tipo 2", "Informacion 2"),
					new Usuario("Usuario 3", "Tipo 3", "Informacion 3"),
					new Usuario("Usuario 4", "Tipo 4", "Informacion 4"),
					new Usuario("Usuario 5", "Tipo 5", "Informacion 5")};

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

		lstListado.setAdapter(new AdaptadorCorreos(this));

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

	class AdaptadorCorreos extends ArrayAdapter<Usuario> {

		Activity context;

		AdaptadorCorreos(Fragment context) {
			super(context.getActivity(), R.layout.listitem_correo, datos);
			this.context = context.getActivity();
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.listitem_correo, null);

			TextView lblDe = (TextView)item.findViewById(R.id.LblDe);
			lblDe.setText(datos[position].getNombre());

			TextView lblAsunto = (TextView)item.findViewById(R.id.LblAsunto);
			lblAsunto.setText(datos[position].getDesc());

			return(item);
		}
	}

	public interface CorreosListener {
		void onCorreoSeleccionado(Usuario c);
	}

	public void setCorreosListener(CorreosListener listener) {
		this.listener=listener;
	}
}

