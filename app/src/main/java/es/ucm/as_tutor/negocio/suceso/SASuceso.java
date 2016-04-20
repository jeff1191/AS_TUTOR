/**
 * 
 */
package es.ucm.as_tutor.negocio.suceso;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.util.ArrayList;

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.usuario.Usuario;

public interface SASuceso {

	public void crearTarea(TransferTareaT transferTarea);

	public void editarTarea(TransferTareaT transferTarea);

	public void eliminarTarea(Integer idTarea);

	public void deshabilitarTarea(Integer idTarea);

	public ArrayList<TransferTareaT> consultarTareas(Integer idUsuario);
}