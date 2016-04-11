package es.ucm.as_tutor.presentacion;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

import es.ucm.as_tutor.R;

/**
 * Created by Jeffer on 11/04/2016.
 */
public class AdaptadorEventoUsuarios  extends BaseAdapter {


        private ArrayList<ItemUsuarioEvento> items = new ArrayList<ItemUsuarioEvento>();
        private LayoutInflater inflater;
        private boolean[] itemSelection;

        public AdaptadorEventoUsuarios(int size,Activity actividad) {
            inflater = (LayoutInflater)actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.itemSelection = new boolean[size];
        }

        public void addItem(final ItemUsuarioEvento item) {
            items.add(item);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public String getItem(int position) {
            return items.get(position).getNombre();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.row_evento_listado_usuarios, null);
            final ViewHolder holder = new ViewHolder();
            holder.chkItem = (CheckBox)convertView.findViewById(R.id.chkItem);
            holder.chkItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    itemSelection[position] = holder.chkItem.isChecked();
                }
            });

            holder.chkItem.setChecked(itemSelection[position]);
            convertView.setTag(holder);
            holder.chkItem.setText(getItem(position));
            return convertView;
        }

        public int getItemsLength() {
            if(itemSelection == null) return 0;
            return itemSelection.length;
        }

        public void set(int i, boolean b) {
            itemSelection[i] = b;
        }

        public static class ViewHolder {
            public CheckBox chkItem;
        }
}

