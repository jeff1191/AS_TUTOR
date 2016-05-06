/**
 * 
 */
package es.ucm.as_tutor.negocio.usuario;

import java.util.ArrayList;

import es.ucm.as_tutor.negocio.suceso.TransferUsuarioEvento;

public interface SAUsuario {

	public void crearUsuario(TransferUsuario transferUsuario);

	public void editarUsuario(TransferUsuario usuarioMod);

	public void eliminarUsuario(Integer idUsuario);

	public TransferUsuario consultarUsuario(Integer idUsuario);

	public void crearUsuarios();

	public ArrayList<TransferUsuario> consultarUsuarios();

	public ArrayList<TransferUsuarioEvento> consultarEventosUsuario(Integer idUsuario);

	public void enviarCorreo(Integer idUsuario);

	public void generarPDF(Integer idUsuario);
}