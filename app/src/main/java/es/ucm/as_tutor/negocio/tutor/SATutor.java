package es.ucm.as_tutor.negocio.tutor;

public interface SATutor {

	public TransferTutorT consultarTutor();

    public void editarTutor(TransferTutorT transferTutor);

    public void crearTutor(TransferTutorT transferTutor);

}
