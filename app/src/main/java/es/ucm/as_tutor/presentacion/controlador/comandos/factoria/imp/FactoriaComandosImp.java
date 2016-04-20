package es.ucm.as_tutor.presentacion.controlador.comandos.factoria.imp;


import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.factoria.FactoriaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.ConsultarTareasComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.CrearTareaComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.DeshabilitarTareaComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.EditarTareaComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.EliminarTareaComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.ConsultarUsuarioComando;

/**
 * Created by Jeffer on 02/03/2016.
 */
public class FactoriaComandosImp extends FactoriaComandos {
    @Override
    public Command getCommand(String comando) {
        Command ret = null;
        switch(comando){
            case ListaComandos.CREAR_TAREA:
                ret = new CrearTareaComando();
                break;
            case ListaComandos.EDITAR_TAREA:
                ret = new EditarTareaComando();
                break;
            case ListaComandos.ELIMINAR_TAREA:
                ret = new EliminarTareaComando();
                break;
            case ListaComandos.DESHABILITAR_TAREA:
                ret = new DeshabilitarTareaComando();
                break;
            case ListaComandos.CONSULTAR_TAREAS:
                ret = new ConsultarTareasComando();
                break;
            case ListaComandos.CONSULTAR_USUARIO:
                ret = new ConsultarUsuarioComando();
                break;
        }
        return ret;
    }
}
