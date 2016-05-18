package es.ucm.as.presentacion.controlador.comandos.imp.tutor;

import es.ucm.as.negocio.factoria.FactoriaSA;
import es.ucm.as.negocio.tutor.SATutor;
import es.ucm.as.presentacion.controlador.comandos.Command;
import es.ucm.as.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by Jeffer on 18/05/2016.
 */
public class AccesoComando implements Command {
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SATutor saTutor = FactoriaSA.getInstancia().nuevoSATutor();
        return  saTutor.consultarTutor();
    }
}
