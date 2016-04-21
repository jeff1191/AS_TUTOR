/**
 * 
 */
package es.ucm.as_tutor.negocio.usuario.imp;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.suceso.Reto;
import es.ucm.as_tutor.negocio.usuario.SAUsuario;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import es.ucm.as_tutor.negocio.usuario.Usuario;
import es.ucm.as_tutor.negocio.utils.Perfil;
import es.ucm.as_tutor.presentacion.vista.main.Manager;


public class SAUsuarioImp implements SAUsuario {

	private DBHelper mDBHelper = null;

	private DBHelper getHelper() {
		if (mDBHelper == null) {
			mDBHelper = OpenHelperManager.getHelper(Manager.getInstance().getContext(), DBHelper.class);
		}
		return mDBHelper;
	}

	public void crearUsuario(TransferUsuarioT usuario) {

	}

	public void editarUsuario(TransferUsuarioT usuarioMod) {

	}

	public void eliminarUsuario(Integer idUsuario) {

	}

	public TransferUsuarioT consultarUsuario(TransferUsuarioT consulta) {
		TransferUsuarioT ret = null;

		try {
			Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
			Usuario user = daoUsuario.queryForId(consulta.getId());
			//Faltarian de traer las tareas y ver si reto y perfil funcionan bien
			ret = new TransferUsuarioT(user.getId(), user.getNombre(),
					user.getCorreo(), user.getAvatar(), user.getTelefono(), user.getPuntuacion(),
					user.getPuntuacionAnterior(), user.getCurso(), user.getDni(),
					user.getDireccion(), user.getTipoPerfil().toString(), user.getNotas(),
					user.getNombrePadre(), user.getNombreMadre(), user.getCorreoPadre(),
					user.getCorreoMadre(), user.getTelfPadre(), user.getTelfMadre(),
					user.getCentroAcademico(), user.getReto(), user.getCodigoSincronizacion());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;

	}

	public void crearUsuarios(){
		Dao<Usuario, Integer> usuario;
		Dao<Reto, Integer> reto;
		try {
			usuario = getHelper().getUsuarioDao();
			reto = getHelper().getRetoDao();
			if(!usuario.idExists(1)) { // Si no hay un usuario en la base de datos, que se creen
				Usuario user1 = new Usuario();
				user1.setNombre("María Salgado");
				user1.setAvatar(""); // Aqui tendria que ir la direccion de la foto
				user1.setDni("12345678Q");
				user1.setDireccion("C/ Alacala 46, 6ºA");
				user1.setTelefono("678678678");
				user1.setCorreo("correo@gmail.com");
				user1.setCentroAcademico("Colegio del Pilar");
				user1.setCurso("4º ESO");
				user1.setNotas("Le gusta el chocolate");
				user1.setNombrePadre("Manuel");
				user1.setNombreMadre("Carmen");
				user1.setTelfPadre("666666666");
				user1.setTelfMadre("666666666");
				user1.setCorreoPadre("correo@gmail.com");
				user1.setCorreoMadre("correo@gmail.com");
				user1.setTipoPerfil(Perfil.A);
				user1.setCodigoSincronizacion("VIC001");
				user1.setPuntuacion(9);
				user1.setPuntuacionAnterior(10);
				user1.setReto(reto.queryForId(1));
				usuario.create(user1);
				Usuario user2 = new Usuario();
				user2.setNombre("Juanlu Armas");
				user2.setAvatar(""); // Aqui tendria que ir la direccion de la foto
				user2.setDni("12345678Q");
				user2.setDireccion("C/ Alacala 46, 6ºA");
				user2.setTelefono("678678678");
				user2.setCorreo("correo@gmail.com");
				user2.setCentroAcademico("Colegio del Pilar");
				user2.setCurso("4º ESO");
				user2.setNotas("Le gusta el chocolate");
				user2.setNombrePadre("Manuel");
				user2.setNombreMadre("Carmen");
				user2.setTelfPadre("666666666");
				user2.setTelfMadre("888888888");
				user2.setCorreoPadre("correo@gmail.com");
				user2.setCorreoMadre("correo@gmail.com");
				user2.setTipoPerfil(Perfil.B);
				user2.setCodigoSincronizacion("VIC001");
				user2.setPuntuacion(9);
				user2.setPuntuacionAnterior(10);
				usuario.create(user2);
				Usuario user3 = new Usuario();
				user3.setNombre("Jefferson Almache");
				user3.setAvatar(""); // Aqui tendria que ir la direccion de la foto
				user3.setDni("12345678Q");
				user3.setDireccion("C/ Alacala 46, 6ºA");
				user3.setTelefono("678678678");
				user3.setCorreo("correo@gmail.com");
				user3.setCentroAcademico("Colegio del Pilar");
				user3.setCurso("4º ESO");
				user3.setNotas("Le gusta el chocolate");
				user3.setNombrePadre("Manuel");
				user3.setNombreMadre("Carmen");
				user3.setTelfPadre("666666666");
				user3.setTelfMadre("666666666");
				user3.setCorreoPadre("correo@gmail.com");
				user3.setCorreoMadre("correo@gmail.com");
				user3.setTipoPerfil(Perfil.C);
				user3.setCodigoSincronizacion("VIC001");
				user3.setPuntuacion(9);
				user3.setPuntuacionAnterior(10);
				user3.setReto(reto.queryForId(2));
				usuario.create(user3);
				Usuario user4 = new Usuario();
				user4.setNombre("El de prueba");
				user4.setAvatar(""); // Aqui tendria que ir la direccion de la foto
				user4.setDni("12345678Q");
				user4.setDireccion("C/ Alacala 46, 6ºA");
				user4.setTelefono("678678678");
				user4.setCorreo("correo@gmail.com");
				user4.setCentroAcademico("Colegio del Pilar");
				user4.setCurso("4º ESO");
				user4.setNotas("Le gusta el chocolate");
				user4.setNombrePadre("Manuel");
				user4.setNombreMadre("Carmen");
				user4.setTelfPadre("666666666");
				user4.setTelfMadre("666666666");
				user4.setCorreoPadre("correo@gmail.com");
				user4.setCorreoMadre("correo@gmail.com");
				user4.setTipoPerfil(Perfil.C);
				user4.setCodigoSincronizacion("VIC001");
				user4.setPuntuacion(9);
				user4.setPuntuacionAnterior(10);
				usuario.create(user4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public ArrayList<TransferUsuarioT> consultarUsuarios() {
		ArrayList<TransferUsuarioT> ret = new ArrayList<TransferUsuarioT>();
		Dao<Usuario, Integer> usuarios;
		try {
			usuarios = getHelper().getUsuarioDao();
			List<Usuario> listaUsuarios = usuarios.queryForAll();
			for(Usuario user : listaUsuarios){
				TransferUsuarioT transfer = new TransferUsuarioT(user.getId(), user.getNombre(),
						user.getAvatar());
				ret.add(transfer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public void consultarInforme(Integer idUsuario) {

	}

	public Reto consultarRetoUsuario(Integer idUsuario) {

		return null;

	}

}