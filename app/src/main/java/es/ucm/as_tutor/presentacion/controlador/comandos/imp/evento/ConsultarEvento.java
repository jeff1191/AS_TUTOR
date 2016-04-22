package es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento;

import java.util.ArrayList;

import es.ucm.as_tutor.negocio.factoria.FactoriaSA;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.negocio.suceso.TransferEvento;
import es.ucm.as_tutor.negocio.suceso.TransferUsuarioEvento;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by Jeffer on 15/04/2016.
 */
public class ConsultarEvento implements Command {
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SASuceso sa = FactoriaSA.getInstancia().nuevoSASuceso();
        //TransferEvento ret = sa.consultaEvento((TransferEvento) datos);
        ArrayList<TransferUsuarioEvento> ret = sa.consultarUsuariosEvento((TransferEvento) datos);
        return ret;
    }
}
