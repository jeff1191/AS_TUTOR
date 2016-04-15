package es.ucm.as_tutor.presentacion.controlador.imp;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import android.app.Fragment;
import android.util.Log;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.suceso.TransferEvento;
import es.ucm.as_tutor.presentacion.controlador.Dispatcher;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.vista.ayuda.FragmentListadoAyuda;
import es.ucm.as_tutor.presentacion.vista.evento.FragmentDetalleEvento;
import es.ucm.as_tutor.presentacion.vista.evento.FragmentListadoEvento;
import es.ucm.as_tutor.presentacion.vista.main.Manager;

/**
 * Created by Jeffer on 02/03/2016.
 */
public class DispatcherImp extends Dispatcher {
    @Override
    public void dispatch(String accion, Object datos) {

        switch(accion){
            case ListaComandos.CREAR_EVENTO:

            break;
            case ListaComandos.ELIMINAR_EVENTO:

            break;
            case ListaComandos.CONSULTAR_EVENTO:
                Log.e("WEPAAAAAAAA","WEPAAAAAAAAAAAAAAAAA");
                TransferEvento consulta = (TransferEvento) datos;
                ArrayList<String> nombresUsuarios = new ArrayList<String>();
                ArrayList<String> asistenciaUsuarios = new ArrayList<String>();
                ArrayList<Integer> usuariosActivos = new ArrayList<Integer>();
                FragmentDetalleEvento frgDetalleE = FragmentDetalleEvento.newInstance(consulta,nombresUsuarios,asistenciaUsuarios,usuariosActivos);
                Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgDetalle, frgDetalleE).commit();
            break;
            case ListaComandos.GUARDAR_EVENTO:

            break;
            case ListaComandos.LISTADO_EVENTOS:

                ArrayList<TransferEvento> eventos = (ArrayList<TransferEvento>) datos;
                FragmentListadoEvento fragmentListadoEvento = FragmentListadoEvento.newInstance(eventos);
                Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgListado, fragmentListadoEvento).commit();
            break;
            case ListaComandos.CONSUTAR_USUARIOS_EVENTO:

            break;
        }
    }
}
