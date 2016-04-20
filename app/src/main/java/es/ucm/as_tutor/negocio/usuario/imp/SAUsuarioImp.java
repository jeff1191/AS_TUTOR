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

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.suceso.Reto;
import es.ucm.as_tutor.negocio.usuario.SAUsuario;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import es.ucm.as_tutor.negocio.usuario.Usuario;
import es.ucm.as_tutor.presentacion.vista.main.Manager;


public class SAUsuarioImp implements SAUsuario {

	private DBHelper mDBHelper = null;

	private DBHelper getHelper() {
		if (mDBHelper == null) {
			mDBHelper = OpenHelperManager.getHelper(Manager.getInstance().getContext(), DBHelper.class);
		}
		return mDBHelper;
	}
	@Override
	public TransferUsuarioT consultarUsuario(Integer idUsuario) {
        TransferUsuarioT ret = new TransferUsuarioT();
        try {
            Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
            Usuario usuario = daoUsuario.queryForId(idUsuario);
            ret.setId(idUsuario);
            ret.setNombre(usuario.getNombre());
            ret.setPuntuacion(usuario.getPuntuacion());
            ret.setPuntuacionAnterior(usuario.getPuntuacionAnterior());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}