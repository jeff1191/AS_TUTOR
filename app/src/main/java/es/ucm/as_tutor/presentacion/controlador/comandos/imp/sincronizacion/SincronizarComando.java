package es.ucm.as_tutor.presentacion.controlador.comandos.imp.sincronizacion;

import android.util.Log;

import es.ucm.as_tutor.negocio.conexion.ConectionManager;
import es.ucm.as_tutor.negocio.conexion.msg.Mensaje;
import es.ucm.as_tutor.negocio.factoria.FactoriaSA;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.negocio.suceso.TransferReto;
import es.ucm.as_tutor.negocio.usuario.SAUsuario;
import es.ucm.as_tutor.negocio.usuario.TransferUsuario;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by Jeffer on 06/05/2016.
 */
public class SincronizarComando implements Command {
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SAUsuario saUsuario = FactoriaSA.getInstancia().nuevoSAUsuario();
        TransferUsuario usuarioSincro = saUsuario.consultarUsuario((Integer) datos);

        SASuceso saEvento = FactoriaSA.getInstancia().nuevoSASuceso();
        TransferReto retoUsuario = saEvento.consultarReto(usuarioSincro.getId());


        Log.e("COD: ", usuarioSincro.getNombre());
        Log.e("COD: ", usuarioSincro.getCodigoSincronizacion());

        Mensaje pepe = new Mensaje();
        pepe.setUsuario(usuarioSincro);
        pepe.setReto(retoUsuario);
        ConectionManager conectionManager = new ConectionManager(pepe);
        String mensaje="VACIO";
      //  Utils.mostrarProgreso(Manager.getInstance().getActivity(), "Sincronización", "Cargando base de datos de " + usuarioSincro.getNombre(), 2);
        conectionManager.lanzarHebra();
        //Mensaje mensajeUsuario  = conectionManager.getMensajeUsuario();
        //saUsuario.editarUsuario(mensajeUsuario.getUsuario());




      /* while(conectionManager.sigueActivo()){
           // Log.e("Comprueba", "Esperando");
            mensaje=conectionManager.getMessage();
        }

        Log.e("COMPRUEBA", mensaje);*/
        return mensaje;
    }
}
