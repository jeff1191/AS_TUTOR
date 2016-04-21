/**
 * 
 */
package es.ucm.as_tutor.negocio.suceso;

import es.ucm.as_tutor.negocio.usuario.Usuario;


public class TransferRetoT {

	private Integer id;

	private Usuario usuario;

	private Integer contador;

	private String texto;

	private Boolean superado;

	public TransferRetoT() {
	}

	public TransferRetoT(Integer id, Usuario usuario, Integer contador, String texto, Boolean superado) {
		this.id = id;
		this.usuario = usuario;
		this.contador = contador;
		this.texto = texto;
		this.superado = superado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
}