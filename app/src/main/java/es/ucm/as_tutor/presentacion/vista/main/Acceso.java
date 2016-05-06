package es.ucm.as_tutor.presentacion.vista.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.tutor.TransferTutor;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;
import es.ucm.as_tutor.presentacion.controlador.comandos.factoria.FactoriaComandos;

/**
 * Created by Juan Lu on 14/04/2016.
 */
public class Acceso extends AppCompatActivity {

    private String password;
    private String preguntaSeguridad;
    private String respuestaSeguridad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        Command c = FactoriaComandos.getInstancia().getCommand(ListaComandos.CONSULTAR_TUTOR);
        try {
            TransferTutor tutor = (TransferTutor) c.ejecutaComando(null);
            password = tutor.getContrasenha();
            preguntaSeguridad = tutor.getPregunta();
            respuestaSeguridad = tutor.getRespuesta();
        } catch (commandException e) {
            e.printStackTrace();
        }

        AlertDialog a = dialogVerificarAcceso(getLayoutInflater());
        a.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        a.show();
    }

    public AlertDialog dialogVerificarAcceso(final LayoutInflater inflater) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = inflater.inflate(R.layout.dialog_password, null);

        final EditText pwd1 = (EditText) v.findViewById(R.id.pwd1);
        final TextView recuperarPassword = (TextView) v.findViewById(R.id.recuperarPassword);
        recuperarPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog a = dialogRecuperarPassword(inflater);
                a.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                a.show();
            }
        });


        builder.setView(v);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(pwd1.getText().toString().equals(password.toString())) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                }else{
                    Toast errorNombre =
                            Toast.makeText(getApplicationContext(),
                                    "Clave incorrecta", Toast.LENGTH_SHORT);
                    errorNombre.show();
                    Intent intent = new Intent(getApplicationContext(), Acceso.class);
                    intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                }
            }
        });

        builder.setTitle("Clave de acceso");
        builder.setIcon(R.drawable.logo);
        return builder.create();
    }


    public AlertDialog dialogRecuperarPassword(LayoutInflater inflater) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = inflater.inflate(R.layout.dialog_password_restaurar, null);

        final TextView question = (TextView) v.findViewById(R.id.pregunta);
        question.setText(preguntaSeguridad);
        final EditText answer = (EditText) v.findViewById(R.id.respuesta);

        builder.setView(v);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(answer.getText().toString().equals(respuestaSeguridad))
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                else{
                    Toast errorNombre =
                            Toast.makeText(getApplicationContext(),
                                    "Respuesta incorrecta", Toast.LENGTH_SHORT);
                    errorNombre.show();
                    startActivity(new Intent(getApplicationContext(), Acceso.class));
                }
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        builder.setTitle("Recuperar contraseña");
        builder.setIcon(R.drawable.logo);
        return builder.create();
    }
}
