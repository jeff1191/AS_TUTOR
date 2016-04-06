package es.ucm.as_tutor.presentacion;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import es.ucm.as_tutor.R;

/**
 * Created by msalitu on 06/04/2016.
 */
public class UsuarioTareaDetalleActivity extends Activity{

    private EditText textoAlarma;
    private EditText textoPregunta;
    private EditText mejorar;
    private EditText si;
    private EditText no;
    private TimePicker horaAlarma;
    private TimePicker horaPregunta;
    private Spinner frecuenciaSpinner;
    private TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_tarea_detalle);

        // Se buscan las views por su id
        this.textoAlarma = (EditText) findViewById(R.id.textoAlarma);
        this.textoPregunta = (EditText) findViewById(R.id.textoPregunta);
        this.si = (EditText) findViewById(R.id.si);
        this.no = (EditText) findViewById(R.id.no);
        this.mejorar = (EditText) findViewById(R.id.mejora);
        this.horaAlarma = (TimePicker) findViewById(R.id.horaAlarma);
        this.horaPregunta = (TimePicker) findViewById(R.id.horaPregunta);
        this.frecuenciaSpinner = (Spinner) findViewById(R.id.frecuencia);
        this.total = (TextView) findViewById(R.id.total);

        // Valroes para el spinner de frecuencia
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

        // En caso de provenir de la seleccion de una tarea para editar se dan valores a los campos
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("txtAlarma")!=null){
            textoAlarma.setText(bundle.getString("txtAlarma"));
            textoPregunta.setText(bundle.getString("txtPregunta"));
            si.setText(bundle.get("si").toString());
            no.setText(bundle.get("no").toString());
            mejorar.setText(bundle.get("mejorar").toString());
            total.setText(bundle.get("total").toString());
        }
    }
}
