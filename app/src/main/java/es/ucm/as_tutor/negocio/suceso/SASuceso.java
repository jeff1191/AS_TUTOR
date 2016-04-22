/**
 * 
 */
package es.ucm.as_tutor.negocio.suceso;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.util.ArrayList;

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;

public interface SASuceso {

	public void crearTarea();

	public void editarTarea();

	public void eliminarTarea();

	public boolean crearEvento(TransferEvento nuevoEvento);
	public boolean eliminarEvento(TransferEvento eliminaEvento);
	public TransferEvento consultaEvento(TransferEvento consulta);
	public boolean guardarEvento(TransferEvento guardaEvento);
	public ArrayList<TransferEvento> listadoEventos();
	public ArrayList<TransferUsuarioEvento> consultarUsuariosEvento(TransferEvento consultaEvento);
	public void crearUsuariosEvento(ArrayList<TransferUsuarioEvento> eventosUsuarios);
}