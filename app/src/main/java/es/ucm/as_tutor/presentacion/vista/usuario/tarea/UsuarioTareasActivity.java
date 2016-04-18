package es.ucm.as_tutor.presentacion.vista.usuario.tarea;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.presentacion.controlador.Controlador;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.vista.main.Manager;

/**
 * Created by msalitu on 01/04/2016.
 */
public class UsuarioTareasActivity extends AppCompatActivity {

    private Integer idUsuario;
    private ArrayList<String> textosAlarma;
    private ArrayList<String> horasAlarma;
    private ArrayList<String> textosPreguntas;
    private ArrayList<String> horasPregunta;
    private ArrayList<Integer> si;
    private ArrayList<Integer> no;
    private ArrayList<String> frecuencias;
    private ArrayList<Integer> mejorar;
    private ArrayList<Integer> habilitada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Manager.getInstance().setActivity(this);
        setContentView(R.layout.activity_usuario_tareas);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logo);

        /// Coge de la BBDD
        textosAlarma = new ArrayList<String>();
        horasAlarma = new ArrayList<String>();
        textosPreguntas = new ArrayList<String>();
        horasPregunta = new ArrayList<String>();
        si = new ArrayList<Integer>();
        no = new ArrayList<Integer>();
        frecuencias = new ArrayList<String>();
        mejorar = new ArrayList<Integer>();
        habilitada = new ArrayList<Integer>();

        this.idUsuario = getIntent().getIntExtra("usuario", 0);
        this.textosAlarma = getIntent().getStringArrayListExtra("textosAlarma");
        this.textosPreguntas = getIntent().getStringArrayListExtra("textosPregunta");
        this.si = getIntent().getIntegerArrayListExtra("si");
        this.no = getIntent().getIntegerArrayListExtra("no");
        this.frecuencias = getIntent().getStringArrayListExtra("frecuencias");
        this.habilitada = getIntent().getIntegerArrayListExtra("habilitada");
        this.mejorar = getIntent().getIntegerArrayListExtra("mejorar");

        // Las horas aun no las cogemos de la BBDD
        this.horasAlarma = new ArrayList<String>();
        horasAlarma.add("22:00");
        horasAlarma.add("08:30");

        this.horasPregunta = new ArrayList<String>();
        horasPregunta.add("22:10");
        horasPregunta.add("08:40");

        // Creacion de la lista de tareas
        final ListView lv = (ListView) findViewById(R.id.listView);
        LayoutInflater inflater = getLayoutInflater();
        // Se establece la fila cabecera
        View header = inflater.inflate(R.layout.tareas_header, lv, false);
        lv.addHeaderView(header, null, false);
        // Se rellenan las filas
        if(textosAlarma != null){
            AdaptadorTareas adapter = new AdaptadorTareas(textosAlarma, horasAlarma, textosPreguntas,
                    horasPregunta, si, no, frecuencias, habilitada, this.getApplicationContext());
            lv.setAdapter(adapter);
        }
        // Se habilita la posibilidad de seleccionar una fila para que se muestre un menu
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarMenuTarea(view, position);
            }
        });
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

    // Muestra el menú con las opciones para la tarea seleccionada
    public void mostrarMenuTarea(View v, int position) {
        final int pos = position;
        if (position > 0) { // así no ocurre nada si se selecciona la cabecera
            position--;
            final CharSequence[] items = {"Editar", "Habilitar/deshabilitar", "Eliminar"};
            AlertDialog.Builder builder = new AlertDialog.Builder(UsuarioTareasActivity.this);
            builder.setTitle(textosAlarma.get(position));
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (items[item].equals("Editar")) {
                        Intent intent = new Intent(getApplicationContext(), UsuarioTareaDetalleActivity.class);
                        intent.putExtra("nueva", "no");
                        // Se introducen todos los valores que se mostraran
                        intent.putExtra("txtAlarma", textosAlarma.get(pos-1));
                        intent.putExtra("horaAlarma", horasAlarma.get(pos-1));
                        intent.putExtra("txtPregunta", textosPreguntas.get(pos-1));
                        intent.putExtra("horaPregunta", horasPregunta.get(pos-1));
                        intent.putExtra("frecuencia", frecuencias.get(pos-1));
                        intent.putExtra("si", si.get(pos-1));
                        intent.putExtra("no", no.get(pos - 1));
                        Integer total = si.get(pos-1) - no.get(pos-1);
                        intent.putExtra("total", total);
                        intent.putExtra("mejorar", mejorar.get(pos-1));
                        intent.putExtra("habilitada", habilitada.get(pos-1));
                        startActivity(intent);
                    } else if (items[item].equals("Habilitar/deshabilitar")) {
                        // ejecuta comando deshabilitar tarea
                    } else if(items[item].equals("Eliminar")) {
                        // mensaje de confirmacion??
                        // ejecuta el comando de eliminar tarea
                    }
                }
            });
            builder.show();
        }
    }

    // Metodo on click del boton "+" material design
    public void nuevaTarea(View view){
        Intent nuevaTarea = new Intent(this, UsuarioTareaDetalleActivity.class);
        nuevaTarea.putExtra("nueva", "nueva");
        nuevaTarea.putExtra("usuario", idUsuario);
        startActivity(nuevaTarea);
    }
}
