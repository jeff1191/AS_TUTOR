/**
 * 
 */
package es.ucm.as_tutor.negocio.suceso;

import java.util.ArrayList;

public interface SASuceso {

	// Tarea

	public void crearTarea(TransferTarea transferTarea);

	public void editarTarea(TransferTarea transferTarea);

	public void eliminarTarea(Integer idTarea);

	public void deshabilitarTarea(Integer idTarea);

	public ArrayList<TransferTarea> consultarTareas(Integer idUsuario);

	// Reto

	public void crearRetos();

	public void crearReto(TransferReto transferReto);

	public TransferReto consultarReto(Integer idUsuario);

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