package es.ucm.as_tutor.presentacion.controlador.comandos.imp.sincronizacion;

import android.util.Log;

import es.ucm.as_tutor.negocio.conexion.ConectionManager;
import es.ucm.as_tutor.negocio.conexion.msg.Mensaje;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by Jeffer on 06/05/2016.
 */
public class Sincronizar implements Command {
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        ConectionManager conectionManager = new ConectionManager(new Mensaje("mensaje AS-TUTOR"),"VIC01");
        String mensaje="VACIO";
        conectionManager.lanzarHebra();
        while(conectionManager.sigueActivo()){
            Log.e("Comprueba", "Esperando");
            mensaje=conectionManager.getMessage();
        }
        Log.e("COMPRUEBA", mensaje);
        return mensaje;
    }
}
