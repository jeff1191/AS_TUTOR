package es.ucm.as_tutor.presentacion.controlador.comandos;

import es.ucm.as_tutor.negocio.factoria.FactoriaSA;
import es.ucm.as_tutor.negocio.usuario.SAUsuario;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by Juan Lu on 20/04/2016.
 */
public class ListadoUsuariosComando implements Command{
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SAUsuario saUsuario = FactoriaSA.getInstancia().nuevoSAUsuario();
        return saUsuario.consultarUsuarios();
    }
}
