package es.ucm.as_tutor.presentacion.controlador.comandos.factoria.imp;


import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.reto.ConsultarRetoComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.reto.CrearRetoComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.ConsultarUsuarioComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.reto.CrearRetosComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.CrearUsuarioComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.EditarUsuarioComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.EliminarUsuarioComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.ListadoUsuariosComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.usuario.CrearUsuariosComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.factoria.FactoriaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.ConsultarTareasComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.CrearTareaComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.DeshabilitarTareaComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.EditarTareaComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.tarea.EliminarTareaComando;

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
            case ListaComandos.CREAR_USUARIOS:
                ret = new CrearUsuariosComando();
                break;
            case ListaComandos.CREAR_RETOS:
                ret = new CrearRetosComando();
                break;
            case ListaComandos.CREAR_USUARIO:
                ret = new CrearUsuarioComando();
                break;
            case ListaComandos.LISTADO_USUARIOS:
                ret = new ListadoUsuariosComando();
                break;
            case ListaComandos.CONSULTAR_USUARIO:
                ret = new ConsultarUsuarioComando();
                break;
            case ListaComandos.ELIMINAR_USUARIO:
                ret = new EliminarUsuarioComando();
                break;
            case ListaComandos.EDITAR_USUARIO:
                ret = new EditarUsuarioComando();
                break;
            case ListaComandos.CREAR_RETO:
                ret = new CrearRetoComando();
                break;
            case ListaComandos.CONSULTAR_RETO:
                ret = new ConsultarRetoComando();
                break;
        }
        return ret;
    }
}
