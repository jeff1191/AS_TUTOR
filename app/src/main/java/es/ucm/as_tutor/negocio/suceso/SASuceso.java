/**
 * 
 */
package es.ucm.as_tutor.negocio.suceso;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.util.ArrayList;

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import java.util.ArrayList;

public interface SASuceso {

	// Tarea

	public void crearTarea(TransferTareaT transferTarea);

	public void editarTarea(TransferTareaT transferTarea);

	public void eliminarTarea(Integer idTarea);

	public void deshabilitarTarea(Integer idTarea);

	public ArrayList<TransferTareaT> consultarTareas(Integer idUsuario);

	// Reto

	public void crearRetos();

	public void crearReto(TransferRetoT transferReto);

	public TransferRetoT consultarReto(Integer idUsuario);

	// Evento

	public boolean crearEvento(TransferEvento nuevoEvento);

	public boolean eliminarEvento(TransferEvento eliminaEvento);

	public TransferEvento consultaEvento(TransferEvento consulta);

	public boolean guardarEvento(TransferEvento guardaEvento);

	public ArrayList<TransferEvento> listadoEventos();

	public ArrayList<TransferUsuarioEvento> consultarUsuariosEvento(TransferEvento consultaEvento);

	public void guardarUsuariosEvento(ArrayList<TransferUsuarioEvento> eventosUsuarios);

	public void crearUsuariosEvento(ArrayList<TransferUsuarioEvento> eventosUsuarios);

}