package es.ucm.as_tutor.presentacion.controlador.comandos.imp.sincronizacion;

import android.util.Log;

import es.ucm.as_tutor.negocio.conexion.ConectionManager;
import es.ucm.as_tutor.negocio.conexion.msg.Mensaje;
import es.ucm.as_tutor.negocio.usuario.TransferUsuario;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by Jeffer on 06/05/2016.
 */
public class SincronizarComando implements Command {
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        TransferUsuario usuarioSincro = new TransferUsuario();
        usuarioSincro.setNombre("Megan Trainor");
        usuarioSincro.setCodigoSincronizacion("VIC01");
        Mensaje pepe = new Mensaje();
        pepe.setUsuario(usuarioSincro);
        ConectionManager conectionManager = new ConectionManager(pepe);
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
