/**
 * 
 */
package es.ucm.as_tutor.negocio.suceso;

public class TransferRetoT {

	private Integer id;

	private Integer idUsuario;

	private Integer contador;

	private String texto;

	private Boolean superado;

	private String premio;

	public TransferRetoT() {
	}

	public TransferRetoT(Integer id, Integer idUsuario, Integer contador, String texto, Boolean superado, String premio) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.contador = contador;
		this.texto = texto;
		this.superado = superado;
		this.premio = premio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Boolean getSuperado() {
		return superado;
	}

	public void setSuperado(Boolean superado) {
		this.superado = superado;
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}
}