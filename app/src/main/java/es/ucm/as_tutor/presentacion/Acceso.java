package es.ucm.as_tutor.presentacion;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import es.ucm.as_tutor.R;

/**
 * Created by Juan Lu on 14/04/2016.
 */
public class Acceso extends Activity {

    private EditText codigoIntroducido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_acceso);
        codigoIntroducido = (EditText)findViewById(R.id.codigoAcceso);
    }

    public void permitirAcceso(View v){
        String codigo = String.valueOf(codigoIntroducido.getText());

        if(codigo.compareTo("1234")>=0){
            startActivity(new Intent(this, MainActivity.class));
        }
        else{
            Toast errorNombre =
                    Toast.makeText(getApplicationContext(),
                            "Código invalido", Toast.LENGTH_SHORT);
            errorNombre.show();
        }
    }
}
