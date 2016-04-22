/**
 * 
 */
package es.ucm.as_tutor.negocio.usuario.imp;

import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.suceso.Evento;
import es.ucm.as_tutor.negocio.suceso.Reto;
import es.ucm.as_tutor.negocio.suceso.TransferEvento;
import es.ucm.as_tutor.negocio.usuario.SAUsuario;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import es.ucm.as_tutor.negocio.usuario.Usuario;
import es.ucm.as_tutor.presentacion.vista.main.Manager;


/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Jeffer
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SAUsuarioImp implements SAUsuario {
	private DBHelper mDBHelper = null;

	private DBHelper getHelper() {
		if (mDBHelper == null) {
			mDBHelper = OpenHelperManager.getHelper(Manager.getInstance().getContext(), DBHelper.class);
		}
		return mDBHelper;
	}
	public void crearUsuario(TransferUsuarioT usuario) {
		// begin-user-code
		// TODO Ap�ndice de m�todo generado autom�ticamente

		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @see SAUsuario#editarUsuario(TransferUsuarioT usuarioMod)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void editarUsuario(TransferUsuarioT usuarioMod) {
		// begin-user-code
		// TODO Ap�ndice de m�todo generado autom�ticamente

		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @see SAUsuario#eliminarUsuario(Integer idUsuario)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void eliminarUsuario(Integer idUsuario) {
		// begin-user-code
		// TODO Ap�ndice de m�todo generado autom�ticamente

		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @see SAUsuario#consultarUsuario(Integer idUsuario)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Usuario consultarUsuario(Integer idUsuario) {
		// begin-user-code
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return null;
		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @see SAUsuario#consultarTodosUsuarios()
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Collection consultarTodosUsuarios() {
		// begin-user-code
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return null;
		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @see SAUsuario#consultarInforme(Integer idUsuario)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void consultarInforme(Integer idUsuario) {
		// begin-user-code
		// TODO Ap�ndice de m�todo generado autom�ticamente

		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @see SAUsuario#consultarTareasUsuario(Integer idUsuario)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Collection consultarTareasUsuario(Integer idUsuario) {
		// begin-user-code
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return null;
		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @see SAUsuario#consultarRetoUsuario(Integer idUsuario)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Reto consultarRetoUsuario(Integer idUsuario) {
		// begin-user-code
		// TODO Ap�ndice de m�todo generado autom�ticamente
		return null;
		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @see SAUsuario#restaurar(Integer idUsuario)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void restaurar(Integer idUsuario) {
		// begin-user-code
		// TODO Ap�ndice de m�todo generado autom�ticamente

		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @see SAUsuario#desHabilitarTareasUsuario(Integer idUsuario, Integer idTarea)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void desHabilitarTareasUsuario(Integer idUsuario, Integer idTarea) {
		// begin-user-code
		// TODO Ap�ndice de m�todo generado autom�ticamente

		// end-user-code
	}

	/** 
	 * (sin Javadoc)
	 * @see SAUsuario#generarAPK(Integer idUsuario, String nombreTutor, String correoTutor, String nombreUsuario, String correoUsuario, Collection tareas)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void generarAPK(Integer idUsuario, String nombreTutor,
			String correoTutor, String nombreUsuario, String correoUsuario,
			Collection tareas) {
		// begin-user-code
		// TODO Ap�ndice de m�todo generado autom�ticamente

		// end-user-code
	}

	@Override
	public ArrayList<TransferUsuarioT> listadoUsuarios() {
		ArrayList<TransferUsuarioT> ret = new ArrayList<TransferUsuarioT>();

		try {
			Dao<Usuario, Integer> daoUsuario =  getHelper().getUsuarioDao();

			/*////////////////////////////////Borrar /////////////////////////////////
			Usuario a = new Usuario();
			a.setNombre("Pepe Pánfilo");
		Usuario a1 = new Usuario();
			a1.setNombre("Julio Andres");
		Usuario a2= new Usuario();
			a2.setNombre("Gandalf Granados");
		Usuario a3 = new Usuario();
			a3.setNombre("Pablo Pedro");
		Usuario a4 = new Usuario();
			a4.setNombre("Simeone Godin");
			Usuario a5 = new Usuario();
			a5.setNombre("Maria Camila");
			Usuario a6 = new Usuario();
			a6.setNombre("Julian Juan");


			daoUsuario.create(a);daoUsuario.create(a1);daoUsuario.create(a2);daoUsuario.create(a3);daoUsuario.create(a4);
			daoUsuario.create(a5);daoUsuario.create(a6);
			/*************************************************/
			List<Usuario> usuariosBDD = daoUsuario.queryForAll();
			for(int i=0; i < usuariosBDD.size(); i++) {
				TransferUsuarioT e = new TransferUsuarioT();
				e.setNombre(usuariosBDD.get(i).getNombre());
				e.setID(usuariosBDD.get(i).getId());
				ret.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
}