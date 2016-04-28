package es.ucm.as_tutor.presentacion.vista.main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import es.ucm.as_tutor.R;


public class Bienvenido extends AppCompatActivity {
    private static final long DELAY = 2000;
    public boolean sincronizacion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bienvenido);

        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Bienvenido", Toast.LENGTH_SHORT);

        toast1.show();


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent().setClass(
                        Bienvenido.this, Decision.class/*MainActivity.class*/);
                startActivity(mainIntent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, DELAY);

    }
}
