package es.ucm.as_tutor.presentacion.vista.main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * Created by msalitu on 17/03/2016.
 */
public class Decision extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* Contexto.getInstancia().setContext(this);
        Command c = FactoriaComandos.getInstancia().getCommand(ListaComandos.CONSULTAR_USUARIO);
        TransferUsuarioT cargarUsuario;
        try {
            cargarUsuario = (TransferUsuarioT) c.ejecutaComando(null);

            if (cargarUsuario == null){
                Intent intent = new Intent().setClass(Decision.this, Registro.class);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent().setClass(Decision.this, MainActivity.class);
                startActivity(intent);
            }

        } catch (commandException e) {
            e.printStackTrace();
        }
        finish();*/


            Intent intent = new Intent().setClass(Decision.this,/* Registro.class*/MainActivity.class);
            startActivity(intent);





            //Pantalla de pin
            //Intent intent = new Intent().setClass(Decision.this, Acceso.class);
            //startActivity(intent);

        finish();
    }
}
