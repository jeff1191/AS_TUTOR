package es.ucm.as_tutor.presentacion.vista.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import es.ucm.as_tutor.negocio.tutor.TransferTutorT;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;
import es.ucm.as_tutor.presentacion.controlador.comandos.factoria.FactoriaComandos;
import es.ucm.as_tutor.presentacion.vista.tutor.RegistroTutor;


/**
 * Created by msalitu on 17/03/2016.
 */
public class Decision extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Manager.getInstance().setActivity(this);
        Command c = FactoriaComandos.getInstancia().getCommand(ListaComandos.CONSULTAR_TUTOR);
        TransferTutorT tutor;
        try {
            tutor = (TransferTutorT) c.ejecutaComando(null);

            if (tutor.getNombre() == null){
                Intent intent = new Intent(getApplicationContext(), RegistroTutor.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(getApplicationContext(), Acceso.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }

        } catch (commandException e) {
            e.printStackTrace();
        }
        finish();
    }
}
