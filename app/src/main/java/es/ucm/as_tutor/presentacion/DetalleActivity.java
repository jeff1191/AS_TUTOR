package es.ucm.as_tutor.presentacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import es.ucm.as_tutor.R;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        FragmentDetalle detalle =
                (FragmentDetalle)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgDetalle);
       // detalle.mostrarDetalle(getIntent().getStringExtra("Detalle Texto"));
        //detalle.mostrarDetalle(getIntent().getStringExtra(EXTRA_TEXTO));
    }
}
