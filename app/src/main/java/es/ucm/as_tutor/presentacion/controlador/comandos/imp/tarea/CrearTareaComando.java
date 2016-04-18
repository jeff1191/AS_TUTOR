package es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea;

import es.ucm.as_tutor.negocio.factoria.FactoriaSA;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.negocio.suceso.TransferTareaT;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by msalitu on 15/04/2016.
 */
public class CrearTareaComando implements Command{
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SASuceso saSuceso = FactoriaSA.getInstancia().nuevoSASuceso();
        return saSuceso.crearTarea((TransferTareaT) datos);
    }
}
