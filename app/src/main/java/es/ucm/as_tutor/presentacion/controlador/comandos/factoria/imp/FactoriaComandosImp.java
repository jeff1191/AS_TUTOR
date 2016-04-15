package es.ucm.as_tutor.presentacion.controlador.comandos.factoria.imp;


import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.factoria.FactoriaComandos;

/**
 * Created by Jeffer on 02/03/2016.
 */
public class FactoriaComandosImp extends FactoriaComandos {
    @Override
    public Command getCommand(String comando) {
        Command ret = null;
        switch(comando){
            case ListaComandos.CREAR_TAREA:
                break;
            case ListaComandos.EDITAR_TAREA:
                break;
            case ListaComandos.ELIMINAR_TAREA:
                break;
        }

        return ret;
    }
}
