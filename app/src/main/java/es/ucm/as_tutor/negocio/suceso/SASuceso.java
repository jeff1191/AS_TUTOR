/**
 * 
 */
package es.ucm.as_tutor.negocio.suceso;

import java.util.ArrayList;

public interface SASuceso {

	public void crearTarea(TransferTareaT transferTarea);

	public void editarTarea(TransferTareaT transferTarea);

	public void eliminarTarea(TransferTareaT transferTarea);

	public void deshabilitarTarea(TransferTareaT datos);

	public ArrayList<TransferTareaT> consultarTareas(Integer usuario);

	public void crearRetos();

	public TransferRetoT consultarReto(TransferRetoT consulta);
}