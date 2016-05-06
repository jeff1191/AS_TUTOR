package es.ucm.as_tutor.presentacion.controlador.comandos.imp.tutor;

import es.ucm.as_tutor.negocio.factoria.FactoriaSA;
import es.ucm.as_tutor.negocio.tutor.SATutor;
import es.ucm.as_tutor.negocio.tutor.TransferTutor;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;

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
