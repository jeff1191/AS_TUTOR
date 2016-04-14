package es.ucm.as_tutor.presentacion;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

import es.ucm.as_tutor.R;

/**
 * Created by msalitu on 06/04/2016.
 */
public class UsuarioTareaDetalleActivity extends AppCompatActivity {

    private EditText textoAlarma;
    private EditText textoPregunta;
    private EditText mejorar;
    private TextView si;
    private TextView no;
    private TimePicker horaAlarma;
    private TimePicker horaPregunta;
    private Spinner frecuenciaSpinner;
    private TextView total;
    private String[] frecuencias = {"Diaria", "Semanal", "Mensual"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_usuario_tarea_detalle);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logo);

        // Se buscan las views por su id
        this.textoAlarma = (EditText) findViewById(R.id.textoAlarma);
        this.textoPregunta = (EditText) findViewById(R.id.textoPregunta);
        this.si = (TextView) findViewById(R.id.si);
        this.no = (TextView) findViewById(R.id.no);
        this.mejorar = (EditText) findViewById(R.id.mejora);
        this.horaAlarma = (TimePicker) findViewById(R.id.horaAlarma);
        this.horaPregunta = (TimePicker) findViewById(R.id.horaPregunta);
        this.frecuenciaSpinner = (Spinner) findViewById(R.id.frecuencia);
        this.total = (TextView) findViewById(R.id.total);


        // En caso de provenir de la seleccion de una tarea para editar se dan valores a los campos
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            textoAlarma.setText(bundle.getString("txtAlarma"));
            textoPregunta.setText(bundle.getString("txtPregunta"));
            si.setText(bundle.get("si").toString());
            no.setText(bundle.get("no").toString());
            mejorar.setText(bundle.get("mejorar").toString());
            total.setText(bundle.get("total").toString());
            // TimePicker hora alarma
            String horaLargo = bundle.getString("horaAlarma");
            String horas = horaLargo.substring(0, 2);
            String mins = horaLargo.substring(3, 5);
            Integer hora = Integer.parseInt(horas);
            Integer min = Integer.parseInt(mins);
            horaAlarma.setCurrentHour(hora);
            horaAlarma.setCurrentMinute(min);
            // TimePicker hora pregunta
            horaLargo = bundle.getString("horaPregunta");
            horas = horaLargo.substring(0, 2);
            mins = horaLargo.substring(3, 5);
            hora = Integer.parseInt(horas);
            min = Integer.parseInt(mins);
            horaPregunta.setCurrentHour(hora);
            horaPregunta.setCurrentMinute(min);
            // Frecuencia
            cambiarOrdenFrecuencias(bundle.getString("frecuencia"));
        }

        // Spinner de frecuencia
        ArrayAdapter<String> adapter_frecuencia = new ArrayAdapter(this.getApplicationContext(),
                android.R.layout.simple_spinner_item, frecuencias);
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

    // Este metodo es necesario para que se muestre como primera opcion del spinner la frecuencia
    // de la tarea seleccionada
    private void cambiarOrdenFrecuencias(String frecuencia) {
        switch (frecuencia){
            case "Diaria":
                this.frecuencias[0] = "Diaria";
                this.frecuencias[1] = "Semanal";
                this.frecuencias[2] = "Mensual";
                break;
            case "Semanal":
                this.frecuencias[0] = "Semanal";
                this.frecuencias[1] = "Mensual";
                this.frecuencias[2] = "Diaria";
                break;
            case "Mensual":
                this.frecuencias[0] = "Mensual";
                this.frecuencias[1] = "Diaria";
                this.frecuencias[2] = "Semanal";
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
