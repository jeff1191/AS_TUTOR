package es.ucm.as.presentacion.controlador.comandos.imp.tutor;

import es.ucm.as.negocio.factoria.FactoriaSA;
import es.ucm.as.negocio.tutor.SATutor;
import es.ucm.as.negocio.tutor.TransferTutor;
import es.ucm.as.presentacion.controlador.comandos.Command;
import es.ucm.as.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by msalitu on 15/04/2016.
 */
public class CrearTutorComando implements Command{
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SATutor saTutor = FactoriaSA.getInstancia().nuevoSATutor();
        saTutor.crearTutor((TransferTutor) datos);
        return null;
    }
}
