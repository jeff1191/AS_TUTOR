/**
 * 
 */
package es.ucm.as_tutor.negocio.suceso;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import es.ucm.as_tutor.integracion.DBHelper;

public interface SASuceso {

	public void crearTarea(TransferTareaT transferTarea);

	public void editarTarea(TransferTareaT transferTarea);

	public void eliminarTarea(TransferTareaT transferTarea);
}