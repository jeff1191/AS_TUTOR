/**
 * 
 */
package es.ucm.as_tutor.negocio.suceso;

import java.util.ArrayList;

public interface SASuceso {

	public void crearTarea(TransferTareaT transferTarea);

	public void editarTarea(TransferTareaT transferTarea);

	public void eliminarTarea(Integer idTarea);

	public void deshabilitarTarea(Integer idTarea);

	public ArrayList<TransferTareaT> consultarTareas(Integer idUsuario);

	public void crearRetos();

	public void crearReto(TransferRetoT transferReto);

	public TransferRetoT consultarReto(Integer idReto);
}