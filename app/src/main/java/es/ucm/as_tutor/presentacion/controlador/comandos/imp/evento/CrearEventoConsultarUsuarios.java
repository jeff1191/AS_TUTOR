package es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento;

import java.util.ArrayList;

import es.ucm.as_tutor.negocio.factoria.FactoriaSA;
import es.ucm.as_tutor.negocio.usuario.SAUsuario;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by Jeffer on 19/04/2016.
 */
public class CrearEventoConsultarUsuarios implements Command {
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SAUsuario sa = FactoriaSA.getInstancia().nuevoSAUsuario();
        ArrayList<TransferUsuarioT> ret = sa.consultarUsuarios();
        return ret;
    }
}
