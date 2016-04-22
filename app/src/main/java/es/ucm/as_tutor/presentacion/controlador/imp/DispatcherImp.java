package es.ucm.as_tutor.presentacion.controlador.imp;

import java.util.ArrayList;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.suceso.TransferEvento;
import es.ucm.as_tutor.negocio.suceso.TransferUsuarioEvento;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import es.ucm.as_tutor.presentacion.controlador.Dispatcher;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.controlador.comandos.imp.evento.CrearEventoConsultarUsuarios;
import es.ucm.as_tutor.presentacion.vista.evento.FragmentDetalleEvento;
import es.ucm.as_tutor.presentacion.vista.evento.FragmentListadoEvento;
import es.ucm.as_tutor.presentacion.vista.main.BlankFragment;
import es.ucm.as_tutor.presentacion.vista.main.Manager;

/**
 * Created by Jeffer on 02/03/2016.
 */
public class DispatcherImp extends Dispatcher {
    @Override
    public void dispatch(String accion, Object datos) {

        switch(accion){
            case ListaComandos.CREAR_EVENTO:
              //Una vez que creas el evento pones el blankFragment
                BlankFragment bk1 = new BlankFragment();
                Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgDetalle, bk1).commit();

            break;
            case ListaComandos.ELIMINAR_EVENTO:
                BlankFragment del = new BlankFragment();
                Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgDetalle, del).commit();
            break;
            case ListaComandos.CONSULTAR_EVENTO:
                //REllenar las lista con los usuarios.

                ArrayList<TransferUsuarioEvento> info = (ArrayList<TransferUsuarioEvento>) datos;

                ArrayList<String> nombresUsuarios = new ArrayList<String>();
                ArrayList<String> asistenciaUsuarios = new ArrayList<String>();
                ArrayList<Integer> usuariosActivos = new ArrayList<Integer>();
                ArrayList<Integer> idsUsuarios = new ArrayList<Integer>();

                for(int i=0; i < info.size(); i++){
                    idsUsuarios.add(info.get(i).getUsuario().getID());
                    nombresUsuarios.add(info.get(i).getUsuario().getNombre());
                    asistenciaUsuarios.add(info.get(i).getAsistencia());
                    usuariosActivos.add(info.get(i).getActivo());
                }


                TransferEvento consulta = info.get(0).getEvento();//Sabemos que estamaos consultando evento, por lo tanto no hay nullPointer

                FragmentDetalleEvento frgDetalleE = FragmentDetalleEvento.newInstance(consulta,nombresUsuarios, idsUsuarios, asistenciaUsuarios,usuariosActivos,"guardar");
                Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgDetalle, frgDetalleE).commit();
            break;
            case ListaComandos.GUARDAR_EVENTO:
                //Una vez que creas el evento pones el blankFragment
                BlankFragment bk2 = new BlankFragment();
                Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgDetalle, bk2).commit();
            break;
            case ListaComandos.LISTADO_EVENTOS:

                ArrayList<TransferEvento> eventos = (ArrayList<TransferEvento>) datos;
                FragmentListadoEvento fragmentListadoEvento = FragmentListadoEvento.newInstance(eventos);
                Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgListado, fragmentListadoEvento).commit();
            break;
            case ListaComandos.CONSUTAR_USUARIOS_EVENTO:

            break;
            case ListaComandos.CREAR_EVENTO_CONSULTAR_USUARIOS:
                TransferEvento crear = null;
                ArrayList<String> nombresUsuariosCrear = new ArrayList<String>();
                ArrayList<Integer> usuariosActivosCrear = new ArrayList<Integer>();
                ArrayList<Integer> indicesUsuarios = new ArrayList<>();
                for(int i =0; i < ((ArrayList<TransferUsuarioT>) datos).size(); i++) {
                    nombresUsuariosCrear.add(((ArrayList<TransferUsuarioT>) datos).get(i).getNombre());
                    indicesUsuarios.add(((ArrayList<TransferUsuarioT>) datos).get(i).getID());
                    usuariosActivosCrear.add(1); // Todos activos
                }
                FragmentDetalleEvento frgDetalleCrear = FragmentDetalleEvento.newInstance(crear,nombresUsuariosCrear,indicesUsuarios,null,usuariosActivosCrear,"crear");
                Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgDetalle, frgDetalleCrear).commit();
            break;


            case ListaComandos.ANYADIR_USUARIOS_EVENTO:

                break;
        }
    }
}
