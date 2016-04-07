package es.ucm.as_tutor.presentacion;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import es.ucm.as_tutor.R;

/**
 * Created by Juan Lu on 07/04/2016.
 */
public class UsuarioRetoDetalleActivity extends Activity {

    private EditText textoReto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_reto_detalle);

    }

    public void crearNuevoReto(View view){
        //Llama al comando para crear el nuevo reto con la nueva info
        this.textoReto = (EditText) findViewById(R.id.nuevoTextoReto);

    }

    public void cancelarNuevoReto(View view){
        //volver a la pantalla del usuario
        finish();
    }
}
