package es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario;

import android.util.Log;

import es.ucm.as_tutor.negocio.factoria.FactoriaSA;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.negocio.usuario.SAUsuario;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.exceptions.commandException;

/**
 * Created by msalitu on 15/04/2016.
 */
public class ConsultarUsuarioComando implements Command{
    @Override
    public Object ejecutaComando(Object datos) throws commandException {
        SAUsuario saUsuario = FactoriaSA.getInstancia().nuevoSAUsuario();
        TransferUsuarioT tansferUsuario = saUsuario.consultarUsuario((Integer) datos);
        return tansferUsuario;
    }
}
