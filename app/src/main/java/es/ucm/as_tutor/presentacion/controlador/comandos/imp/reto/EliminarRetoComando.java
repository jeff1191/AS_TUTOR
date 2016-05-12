package es.ucm.as_tutor.presentacion.controlador.comandos.imp.reto;

import es.ucm.as_tutor.negocio.factoria.FactoriaSA;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by Juan Lu on 12/05/2016.
 */
public class EliminarRetoComando implements Command {
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SASuceso sa = FactoriaSA.getInstancia().nuevoSASuceso();
        sa.eliminarReto((Integer) datos);
        return null;
    }
}
