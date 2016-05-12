package es.ucm.as.presentacion.vista.tutor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import es.ucm.as.R;
import es.ucm.as.negocio.tutor.TransferTutor;
import es.ucm.as.presentacion.controlador.Controlador;
import es.ucm.as.presentacion.controlador.ListaComandos;
import es.ucm.as.presentacion.vista.main.MainActivity;

public class RegistroTutor extends Activity {

    private EditText correoTutor;
    private EditText constrasenha;
    private EditText nombreTutor;
    private ImageButton info;
    private static final String PATRON_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombreTutor = (EditText) findViewById(R.id.nombreTutor);
        correoTutor = (EditText) findViewById(R.id.emailTutor);
        constrasenha = (EditText) findViewById(R.id.contrasenha);
    }

    public void realizarRegistro(View v) {
        //Leer los datos del "fomulario"
        String correo = String.valueOf(correoTutor.getText());
        String clave = String.valueOf(constrasenha.getText());
        String nombre = String.valueOf(nombreTutor.getText());
        String codigoSync;
        if(nombre.length() >= 3)
            codigoSync = nombre.substring(0, 3);
        else
            codigoSync = nombre.substring(0, 0);

        if (datosValidos(codigoSync, correo, clave)) {
            TransferTutor tutor = new TransferTutor();
            tutor.setNombre(nombre);
            tutor.setCorreo(correo);
            tutor.setContrasenha(clave);
            tutor.setCodigoSincronizacion(codigoSync);

            Controlador.getInstancia().ejecutaComando(ListaComandos.CREAR_TUTOR, tutor);
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }
    }

    private boolean datosValidos(String nombre, String correo, String clave) {
        if (!nombre.toString().matches("") &&
                !correo.toString().matches("") &&
                !clave.toString().matches("")) {
            if(nombre.toString().length()<3)
                mostrarMensajeError("El nombre debe tener al menos 3 letras");
            else if (correo.toString().matches(PATRON_EMAIL)) {
                return true;
            } else
                mostrarMensajeError("Campo email inválido");
        } else
            mostrarMensajeError("Algún campo está vacío");

        return false;
    }


    private void mostrarMensajeError(String msg) {
        Toast errorNombre =
                Toast.makeText(getApplicationContext(),
                        msg, Toast.LENGTH_SHORT);
        errorNombre.show();
    }

}
