package es.ucm.as.presentacion.controlador.comandos.imp.usuario;

import es.ucm.as.negocio.factoria.FactoriaSA;
import es.ucm.as.negocio.usuario.SAUsuario;
import es.ucm.as.presentacion.controlador.comandos.Command;
import es.ucm.as.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by Juan Lu on 20/04/2016.
 */
public class EnviarCorreoComando implements Command {
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SAUsuario saUsuario = FactoriaSA.getInstancia().nuevoSAUsuario();
        saUsuario.enviarCorreo((Integer) datos);
        return null;
    }
}