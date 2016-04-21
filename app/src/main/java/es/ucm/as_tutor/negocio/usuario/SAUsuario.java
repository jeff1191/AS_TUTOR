/**
 * 
 */
package es.ucm.as_tutor.negocio.usuario;

import java.util.ArrayList;

import es.ucm.as_tutor.negocio.suceso.Reto;

public interface SAUsuario {

	public void crearUsuario(TransferUsuarioT usuario);

	public void editarUsuario(TransferUsuarioT usuarioMod);

	public void eliminarUsuario(Integer idUsuario);

	public TransferUsuarioT consultarUsuario(TransferUsuarioT consulta);

	public void crearUsuarios();

	public ArrayList<TransferUsuarioT> consultarUsuarios();

	public void consultarInforme(Integer idUsuario);

	public Reto consultarRetoUsuario(Integer idUsuario);

}