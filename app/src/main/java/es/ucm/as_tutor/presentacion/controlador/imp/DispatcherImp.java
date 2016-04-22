package es.ucm.as_tutor.presentacion.controlador.imp;

import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.suceso.TransferRetoT;
import es.ucm.as_tutor.negocio.suceso.TransferTareaT;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import es.ucm.as_tutor.negocio.utils.ParserTime;
import es.ucm.as_tutor.presentacion.controlador.Dispatcher;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.vista.main.Manager;
import es.ucm.as_tutor.presentacion.vista.usuario.FragmentDetalleUsuario;
import es.ucm.as_tutor.presentacion.vista.usuario.FragmentListadoUsuario;
import es.ucm.as_tutor.presentacion.vista.usuario.reto.FragmentDetalleNuevoReto;
import es.ucm.as_tutor.presentacion.vista.usuario.reto.FragmentDetalleReto;
import es.ucm.as_tutor.presentacion.vista.usuario.tarea.UsuarioTareasActivity;

/**
 * Created by Jeffer on 02/03/2016.
 */
public class DispatcherImp extends Dispatcher {
    @Override
    public void dispatch(String accion, Object datos) {

        switch(accion){

            case ListaComandos.CONSULTAR_TAREAS:
                ArrayList<String> textosAlarma = new ArrayList<String>();
                ArrayList<String> horasAlarma = new ArrayList<String>();
                ArrayList<String> textosPreguntas = new ArrayList<String>();
                ArrayList<String> horasPregunta = new ArrayList<String>();
                ArrayList<Integer> si = new ArrayList<Integer>();
                ArrayList<Integer> no = new ArrayList<Integer>();
                ArrayList<String> frecuencias = new ArrayList<String>();
                ArrayList<Integer> mejorar = new ArrayList<Integer>();
                ArrayList<Integer> habilitada = new ArrayList<Integer>();
                ArrayList<Integer> id = new ArrayList<Integer>();


                ArrayList<TransferTareaT> tareas = (ArrayList<TransferTareaT>) datos;

                // Si ese usuario tiene tareas se cogen los campos que vamos a mostrar
                if (tareas.size() >= 1 && tareas.get(0).getTextoAlarma()!= null) {
                    for (int i = 0; i < tareas.size(); i++) {
                        TransferTareaT transfer = tareas.get(i);
                        textosAlarma.add(i, transfer.getTextoAlarma());
                        textosPreguntas.add(i, transfer.getTextoPregunta());
                        si.add(transfer.getNumSi());
                        no.add(transfer.getNumNo());
                        frecuencias.add(transfer.getFrecuenciaTarea().toString());
                        mejorar.add(transfer.getMejorar());
                        Integer bool = 1;
                        if (!transfer.getHabilitada())
                            bool = 0;
                        habilitada.add(bool);
                        Date pregunta = transfer.getHoraPregunta();
                        horasPregunta.add(i, ParserTime.DateToString(pregunta));
                        Date alarma = transfer.getHoraAlarma();
                        horasAlarma.add(i, ParserTime.DateToString(alarma));
                        id.add(i, transfer.getId());
                    }
                }

                Intent iConsultarTareas = new Intent( Manager.getInstance().getContext(), UsuarioTareasActivity.class);
                iConsultarTareas.putStringArrayListExtra("horaPreguntas", horasPregunta);
                iConsultarTareas.putStringArrayListExtra("horaAlarmas", horasAlarma);
                iConsultarTareas.putStringArrayListExtra("textosPregunta", textosPreguntas);
                iConsultarTareas.putStringArrayListExtra("textosAlarma", textosAlarma);
                iConsultarTareas.putStringArrayListExtra("frecuencias", frecuencias);
                iConsultarTareas.putIntegerArrayListExtra("si", si);
                iConsultarTareas.putIntegerArrayListExtra("no", no);
                iConsultarTareas.putIntegerArrayListExtra("mejorar", mejorar);
                iConsultarTareas.putIntegerArrayListExtra("habilitada", habilitada);
                iConsultarTareas.putIntegerArrayListExtra("idsTareas", id);
                iConsultarTareas.putExtra("usuario", tareas.get(0).getIdUsuario());
                iConsultarTareas.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                iConsultarTareas.setFlags(iConsultarTareas.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                Manager.getInstance().getContext().startActivity(iConsultarTareas);
                break;

            case ListaComandos.LISTADO_USUARIOS:
                ArrayList<TransferUsuarioT> usuarios = (ArrayList<TransferUsuarioT>) datos;
                FragmentListadoUsuario frgListadoU = FragmentListadoUsuario.newInstance(usuarios);
                Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgListado, frgListadoU).commit();
                break;

            case ListaComandos.CONSULTAR_USUARIO:
                TransferUsuarioT usuario = (TransferUsuarioT) datos;
                FragmentDetalleUsuario frgDetalleU = FragmentDetalleUsuario.newInstance(usuario);
                Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgDetalle, frgDetalleU).commit();
                break;

            case ListaComandos.CONSULTAR_RETO:
                TransferRetoT reto = (TransferRetoT) datos;
                if(reto.getId() == null){ //Si no hay reto al menos pasas los datos del usuario
                    FragmentDetalleNuevoReto fragmentNuevoReto = FragmentDetalleNuevoReto.newInstance(reto);
                    Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentNuevoReto).commit();
                }
                else{
                    FragmentDetalleReto fragmentReto = FragmentDetalleReto.newInstance(reto);
                    Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentReto).commit();
                }
                break;
        }
    }
}
