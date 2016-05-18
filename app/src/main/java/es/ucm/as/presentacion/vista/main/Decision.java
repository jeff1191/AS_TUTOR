package es.ucm.as.presentacion.vista.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import es.ucm.as.negocio.tutor.TransferTutor;
import es.ucm.as.presentacion.controlador.Controlador;
import es.ucm.as.presentacion.controlador.ListaComandos;
import es.ucm.as.presentacion.controlador.comandos.Command;
import es.ucm.as.presentacion.controlador.comandos.exceptions.commandException;
import es.ucm.as.presentacion.controlador.comandos.factoria.FactoriaComandos;
import es.ucm.as.presentacion.vista.tutor.RegistroTutor;


/**
 * Created by msalitu on 17/03/2016.
 */
public class Decision extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Manager.getInstance().setActivity(this);
        Controlador.getInstancia().ejecutaComando(ListaComandos.EXISTE_TUTOR,null);
        finish();
    }
}
