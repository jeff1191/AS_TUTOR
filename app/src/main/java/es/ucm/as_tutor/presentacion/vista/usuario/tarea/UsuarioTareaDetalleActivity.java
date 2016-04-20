package es.ucm.as_tutor.presentacion.vista.usuario.tarea;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import android.widget.Toast;

import java.util.ArrayList;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.suceso.TransferTareaT;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import es.ucm.as_tutor.negocio.usuario.Usuario;
import es.ucm.as_tutor.negocio.utils.Frecuencia;
import es.ucm.as_tutor.negocio.utils.ParserTime;
import es.ucm.as_tutor.presentacion.controlador.Controlador;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;

/**
 * Created by msalitu on 06/04/2016.
 */
public class UsuarioTareaDetalleActivity extends AppCompatActivity {

    private boolean nuevaTarea;
    private Integer idUsuario;
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

        Bundle bundle = getIntent().getExtras();
        idUsuario = bundle.getInt("usuario");
        Log.e("tareas", idUsuario.toString());
        if (!bundle.get("nueva").equals("nueva")) {   // Editar
            nuevaTarea = false;
            completarCampos(bundle);
        } else{                 // Crear
            nuevaTarea = true;
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

    // Este metodo carga los valores de la BBDD en los campos
    private void completarCampos(Bundle bundle){
        textoAlarma.setText(bundle.getString("txtAlarma"));
        textoPregunta.setText(bundle.getString("txtPregunta"));
        si.setText(bundle.get("si").toString());
        no.setText(bundle.get("no").toString());
        mejorar.setText(bundle.get("mejorar").toString());
        total.setText(bundle.get("total").toString());
        // TimePicker hora alarma
        String horaLargo = bundle.getString("horaAlarma");
        horaAlarma.setCurrentHour(ParserTime.hour(horaLargo));
        horaAlarma.setCurrentMinute(ParserTime.min(horaLargo));
        // TimePicker hora pregunta
        horaLargo = bundle.getString("horaPregunta");
        horaPregunta.setCurrentHour(ParserTime.hour(horaLargo));
        horaPregunta.setCurrentMinute(ParserTime.min(horaLargo));
        // Frecuencia
        cambiarOrdenFrecuencias(bundle.getString("frecuencia"));
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

    private Frecuencia frecuenciaSeleccionada(){
        Frecuencia ret = Frecuencia.DIARIA;
        switch (frecuenciaSpinner.getSelectedItem().toString()){
            case "Diaria":
                ret = Frecuencia.DIARIA;
            case "Semanal":
                ret = Frecuencia.SEMANAL;
            case "Mensual":
                ret = Frecuencia.MENSUAL;
        }
        return ret;
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

    public void aceptar(View view){
        String alarm_hour = horaAlarma.getCurrentHour() + ":" + horaAlarma.getCurrentMinute();
        String pregunta_hour = horaPregunta.getCurrentHour() + ":" + horaPregunta.getCurrentMinute();
        TransferTareaT transfer = new TransferTareaT();
        transfer.setTextoAlarma(textoAlarma.getText().toString());
        transfer.setTextoPregunta(textoPregunta.getText().toString());
        transfer.setHoraAlarma(ParserTime.toDate(alarm_hour));
        transfer.setHoraPregunta(ParserTime.toDate(pregunta_hour));
        transfer.setFrecuenciaTarea(frecuenciaSeleccionada());
        transfer.setMejorar(Integer.parseInt(mejorar.getText().toString()));
        transfer.setNumSi(Integer.parseInt(si.getText().toString()));
        transfer.setNumNo(Integer.parseInt(no.getText().toString()));
        transfer.setHabilitada(true);
        transfer.setIdUsuario(idUsuario);

        if (nuevaTarea){
            Controlador.getInstancia().ejecutaComando(ListaComandos.CREAR_TAREA, transfer);
            Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_TAREAS, idUsuario);
        }else{
           // Controlador.getInstancia().ejecutaComando(ListaComandos.EDITAR_TAREA, transfer);
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Editar", Toast.LENGTH_SHORT);

            toast1.show();
        }

    }

    public void cancelar(View view){
        Intent cancelar = new Intent(this, UsuarioTareasActivity.class);
        startActivity(cancelar);
    }
}
