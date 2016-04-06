package es.ucm.as_tutor.presentacion;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import es.ucm.as_tutor.R;

/**
 * Created by msalitu on 06/04/2016.
 */
public class UsuarioTareaDetalleActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_tarea_detalle);
        ActionBar actionBar = getActionBar();
        actionBar.show();

        // Spinner de frecuencia
        final Spinner frecuenciaSpinner = (Spinner) findViewById(R.id.frecuencia);
        ArrayAdapter<CharSequence> adapter_frecuencia = ArrayAdapter.createFromResource(this,
                R.array.frecuencias_array, android.R.layout.simple_spinner_item);
        adapter_frecuencia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frecuenciaSpinner.setAdapter(adapter_frecuencia);
        frecuenciaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                frecuenciaSpinner.setSelection(position);
                String frecuenciaSeleccionada = (String) frecuenciaSpinner.getSelectedItem();

                switch (frecuenciaSeleccionada) {
                    case "Diaria":
                        // set frecuencia
                        break;

                    case "Semanal":
                        // set frecuencia
                        break;

                    case "Mensual":
                        // set frecuencia
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
