package es.ucm.as_tutor.presentacion.controlador.comandos.factoria.imp;


import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.Command;
import es.ucm.as_tutor.presentacion.controlador.comandos.factoria.FactoriaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.AnyadirUsuariosEvento;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.ConsultarEvento;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.ConsultarUsuariosEvento;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.CrearEventoComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.CrearEventoConsultarUsuarios;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.EliminarEventoComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.GuardarEventoComando;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.ListadoEventoComando;

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
            //Evento
            case ListaComandos.CREAR_EVENTO:
                ret = new CrearEventoComando();
                break;
            case ListaComandos.ELIMINAR_EVENTO:
                ret= new EliminarEventoComando();
                break;
            case ListaComandos.CONSULTAR_EVENTO:
                ret= new ConsultarEvento();
                break;
            case ListaComandos.GUARDAR_EVENTO:
                ret=new GuardarEventoComando();
                break;
            case ListaComandos.LISTADO_EVENTOS:
                ret= new ListadoEventoComando();
                break;
            case ListaComandos.CONSUTAR_USUARIOS_EVENTO:
                ret= new ConsultarUsuariosEvento();
                break;
            case ListaComandos.CREAR_EVENTO_CONSULTAR_USUARIOS:
                ret= new CrearEventoConsultarUsuarios();
                break;
            case ListaComandos.ANYADIR_USUARIOS_EVENTO:
                ret= new AnyadirUsuariosEvento();
                break;
            //Fin evento
        }

        return ret;
    }
}
