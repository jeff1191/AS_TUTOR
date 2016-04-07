package es.ucm.as_tutor.presentacion;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import es.ucm.as_tutor.R;

/**
 * Created by msalitu on 01/04/2016.
 */
public class UsuarioRetoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_reto);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logo);
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
/*
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String nombre = bundle.getString("textReto");

            tv = (TextView) findViewById(R.id.textoReto);
            tv.setText(nombre);

            contInt = bundle.getInt("contadorReto");
            c = (TextView) findViewById(R.id.cont);
            c.setText(contInt.toString()+"/30");
            progreso = (ProgressBar) findViewById(R.id.progressBar);
            progreso.setProgress(contInt);

            boolean superado = bundle.getBoolean("superadoReto");

            if (superado) {
                TextView sup = (TextView) findViewById(R.id.reto);
                sup.setText("RETO SUPERADO");
            }else {
                progreso = (ProgressBar) findViewById(R.id.progressBar);
                progreso.setVisibility(View.INVISIBLE);
                TextView sup = (TextView) findViewById(R.id.reto);
                sup.setText("No tiene ningún reto asignado");
                sup.setTextColor(Color.GRAY);
            }

        }*/
    }

    // Metodo on click del boton "+" material design
    public void nuevoReto(View view){
        Intent intent = new Intent(getApplicationContext(), UsuarioRetoDetalleActivity.class);
        startActivity(intent);
    }
}
