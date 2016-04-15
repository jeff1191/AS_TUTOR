/**
 * 
 */
package es.ucm.as_tutor.negocio.factoria;

import es.ucm.as_tutor.negocio.factoria.imp.FactoriaSAImp;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.negocio.tutor.SATutor;
import es.ucm.as_tutor.negocio.usuario.SAUsuario;

public abstract class FactoriaSA {

	private static FactoriaSA instancia;

	public abstract SATutor nuevoSATutor();

	public abstract SAUsuario nuevoSAUsuario();

	public abstract SASuceso nuevoSASuceso();

	public static FactoriaSA getInstancia() {
		if (instancia == null) {
			instancia = new FactoriaSAImp();
		}
		return instancia;
	}
}