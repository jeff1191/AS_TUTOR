package es.ucm.as_tutor.presentacion.vista.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import es.ucm.as_tutor.R;

public class Registro extends Activity {

    private EditText correoTutor;
    private EditText codigoSincronizacion;
    private EditText claveAcceso;
    private ImageButton info;
    private static final String PATRON_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_registro);
        correoTutor = (EditText)findViewById(R.id.emailTutor);
        codigoSincronizacion = (EditText)findViewById(R.id.codigoSincronizacion);
        claveAcceso = (EditText)findViewById(R.id.claveAcceso);
        info = (ImageButton)findViewById(R.id.infoSync);
    }

    public void realizarRegistro(View v){

        //Leer los datos del "fomulario"
        String correo = String.valueOf(correoTutor.getText());
        String codigoSync = String.valueOf(codigoSincronizacion.getText());
        String clave = String.valueOf(claveAcceso.getText());

        if(datosValidos(codigoSync,correo,clave)){
            /*TransferUsuario crearUsuario = new TransferUsuario();
            crearUsuario.setNombre(nombre);
            crearUsuario.setAvatar("");
            crearUsuario.setColor("AS_theme_azul");
            crearUsuario.setPuntuacion(0);
            crearUsuario.setPuntuacionAnterior(0);
            crearUsuario.setCorreo(correo);

            Controlador.getInstancia().ejecutaComando(ListaComandos.CREAR_USUARIO, crearUsuario);
            Controlador.getInstancia().ejecutaComando(ListaComandos.CARGAR_BBDD, null);*/
            //startService(new Intent(this, ServicioNotificaciones.class));

            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private boolean datosValidos(String nombre, String correo, String clave) {
        if(!nombre.toString().matches("") &&
                !correo.toString().matches("")&&
                !clave.toString().matches("")) {

            if(correo.toString().matches(PATRON_EMAIL)){
                return true;
            }else
                mostrarMensajeError("Campo email inválido");
        }else
            mostrarMensajeError("Algún campo es vacío");

        return false;
    }


    private void mostrarMensajeError(String msg){
        Toast errorNombre =
                Toast.makeText(getApplicationContext(),
                        msg, Toast.LENGTH_SHORT);
        errorNombre.show();
    }

    public void mostrarInfo(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
        builder.setTitle("Aviso");
        builder.setMessage("El código de sincronizacion no se podrá modificar. Ejemplo: VIC");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        builder.create();
        builder.show();

        }

    }
