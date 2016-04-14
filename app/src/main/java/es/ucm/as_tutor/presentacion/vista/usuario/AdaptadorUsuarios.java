package es.ucm.as_tutor.presentacion.vista.usuario;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.ucm.as_tutor.R;

/**
 * Created by Jeffer on 19/03/2016.
 */
class AdaptadorUsuarios extends BaseAdapter {

        private LayoutInflater inflater;
        private ArrayList<String> nombres = new ArrayList<String>();
        private ArrayList<Integer> imagenes = new ArrayList<Integer>();

    public AdaptadorUsuarios(Activity context) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return nombres.size();
    }

    @Override
    public Object getItem(int position) {
        return nombres.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = inflater.inflate(R.layout.row_usuarios, null);

        TextView nombreUser= (TextView)item.findViewById(R.id.nombreUsuario);
        nombreUser.setText(nombres.get(position));
        ImageView img = (ImageView)item.findViewById(R.id.avatar);
        img.setImageResource(imagenes.get(position));

        return(item);
    }

    public void setNombres(ArrayList<String> nombres) {
        this.nombres = nombres;
        notifyDataSetChanged();
    }

    public void setImagenes(ArrayList<Integer> imagenes) {
        this.imagenes = imagenes;
        notifyDataSetChanged();
    }
}
