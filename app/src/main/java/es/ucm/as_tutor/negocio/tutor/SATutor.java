package es.ucm.as_tutor.negocio.tutor;

public interface SATutor {

	public TransferTutor consultarTutor();

    public void editarTutor(TransferTutor transferTutor);

    public void crearTutor(TransferTutor transferTutor);

}
