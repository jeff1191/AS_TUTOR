package es.ucm.as.presentacion.controlador.comandos.imp.reto;

import es.ucm.as.negocio.factoria.FactoriaSA;
import es.ucm.as.negocio.suceso.SASuceso;
import es.ucm.as.presentacion.controlador.comandos.Command;
import es.ucm.as.presentacion.controlador.comandos.exceptions.commandException;

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
