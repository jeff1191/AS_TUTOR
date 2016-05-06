/**
 * 
 */
package es.ucm.as_tutor.negocio.factoria.imp;


import es.ucm.as_tutor.negocio.factoria.FactoriaSA;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.negocio.suceso.imp.SASucesoImp;
import es.ucm.as_tutor.negocio.tutor.SATutor;
import es.ucm.as_tutor.negocio.tutor.imp.SATutorImp;
import es.ucm.as_tutor.negocio.usuario.SAUsuario;
import es.ucm.as_tutor.negocio.usuario.imp.SAUsuarioImp;

public class FactoriaSAImp extends FactoriaSA {

	@Override
	public SATutor nuevoSATutor() {
		return new SATutorImp();
	}

	@Override
	public SAUsuario nuevoSAUsuario() {
		return new SAUsuarioImp();
	}

	@Override
	public SASuceso nuevoSASuceso() {
		return new SASucesoImp();
	}
}