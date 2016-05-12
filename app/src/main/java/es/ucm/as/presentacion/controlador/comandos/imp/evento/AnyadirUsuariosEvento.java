package es.ucm.as.presentacion.controlador.comandos.imp.evento;

import java.util.ArrayList;

import es.ucm.as.negocio.factoria.FactoriaSA;
import es.ucm.as.negocio.suceso.SASuceso;
import es.ucm.as.negocio.suceso.TransferUsuarioEvento;
import es.ucm.as.presentacion.controlador.comandos.Command;
import es.ucm.as.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by Jeffer on 21/04/2016.
 */
public class AnyadirUsuariosEvento implements Command {
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SASuceso sa = FactoriaSA.getInstancia().nuevoSASuceso();
        sa.crearUsuariosEvento((ArrayList<TransferUsuarioEvento>) datos);
        return null;
    }
}
