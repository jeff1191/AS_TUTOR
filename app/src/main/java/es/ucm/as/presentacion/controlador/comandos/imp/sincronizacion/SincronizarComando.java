package es.ucm.as.presentacion.controlador.comandos.imp.sincronizacion;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.ucm.as.negocio.conexion.ConectionManager;
import es.ucm.as.negocio.conexion.msg.Mensaje;
import es.ucm.as.negocio.factoria.FactoriaSA;
import es.ucm.as.negocio.suceso.SASuceso;
import es.ucm.as.negocio.suceso.TransferEvento;
import es.ucm.as.negocio.suceso.TransferReto;
import es.ucm.as.negocio.suceso.TransferUsuarioEvento;
import es.ucm.as.negocio.usuario.SAUsuario;
import es.ucm.as.negocio.usuario.TransferUsuario;
import es.ucm.as.presentacion.controlador.comandos.Command;
import es.ucm.as.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by Jeffer on 06/05/2016.
 */
public class SincronizarComando implements Command {
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SAUsuario saUsuario = FactoriaSA.getInstancia().nuevoSAUsuario();
        TransferUsuario usuarioSincro = saUsuario.consultarUsuario((Integer) datos);
        SASuceso saSuceso = FactoriaSA.getInstancia().nuevoSASuceso();
        TransferReto retoSincro= saSuceso.consultarReto(usuarioSincro.getId());

        ArrayList<TransferUsuarioEvento> eventosBDD = saUsuario.consultarEventosUsuario((Integer) datos);
        List<TransferEvento> eventosSincro = new ArrayList<>();

        for(int i=0; i < eventosBDD.size(); i++){
            eventosSincro.add(eventosBDD.get(i).getEvento());
        }

        Log.e("COD: ", usuarioSincro.getCodigoSincronizacion());

        Mensaje pepe = new Mensaje();
        pepe.setUsuario(usuarioSincro);
        pepe.setReto(retoSincro);
        pepe.setEventos(eventosSincro);

        ConectionManager conectionManager = new ConectionManager(pepe);
        String mensaje="VACIO";
      //  Utils.mostrarProgreso(Manager.getInstance().getActivity(), "Sincronización", "Cargando base de datos de " + usuarioSincro.getNombre(), 2);
        conectionManager.lanzarHebra();

      /* while(conectionManager.sigueActivo()){
           // Log.e("Comprueba", "Esperando");
            mensaje=conectionManager.getMessage();
        }

        Log.e("COMPRUEBA", mensaje);*/
        return mensaje;
    }
}