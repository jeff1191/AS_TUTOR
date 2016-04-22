/**
 * 
 */
package es.ucm.as_tutor.negocio.usuario;

import java.util.ArrayList;

public interface SAUsuario {

	public void crearUsuario(TransferUsuarioT transferUsuario);

	public void editarUsuario(TransferUsuarioT usuarioMod);

	public void eliminarUsuario(Integer idUsuario);

	public TransferUsuarioT consultarUsuario(Integer idUsuario);

	public void crearUsuarios();

	public ArrayList<TransferUsuarioT> consultarUsuarios();

	public void consultarInforme(Integer idUsuario);

}