/**
 * 
 */
package es.ucm.as_tutor.negocio.suceso;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.util.ArrayList;

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.usuario.Usuario;

public interface SASuceso {

	public Integer crearTarea(TransferTareaT transferTarea);

	public void editarTarea(TransferTareaT transferTarea);

	public void eliminarTarea(TransferTareaT transferTarea);

	public void deshabilitarTarea(TransferTareaT datos);

	public ArrayList<TransferTareaT> consultarTareas(Integer usuario);
}