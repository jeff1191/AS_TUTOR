package es.ucm.as_tutor.negocio.tutor.imp;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.tutor.SATutor;
import es.ucm.as_tutor.negocio.tutor.TransferTutorT;
import es.ucm.as_tutor.negocio.tutor.Tutor;
import es.ucm.as_tutor.presentacion.vista.main.Manager;

public class SATutorImp implements SATutor {

	private DBHelper mDBHelper = null;

	private DBHelper getHelper() {
		if (mDBHelper == null) {
			mDBHelper = OpenHelperManager.getHelper(Manager.getInstance().getContext(), DBHelper.class);
		}
		return mDBHelper;
	}

	@Override
	public TransferTutorT consultarTutor() {
        TransferTutorT ret = new TransferTutorT();
        try {
            Dao<Tutor, Integer> daoTutor = getHelper().getTutorDao();
            if (daoTutor.idExists(1)) {
                Tutor tutor = daoTutor.queryForId(1);
                ret.setId(tutor.getId());
                ret.setNombre(tutor.getNombre());
                ret.setCorreo(tutor.getCorreo());
                ret.setCodigoSincronizacion(tutor.getCodigoSincronizacion());
                ret.setContrasenha(tutor.getContrasenha());
                ret.setPregunta(tutor.getPregunta());
                ret.setRespuesta(tutor.getRespuesta());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

	@Override
	public void editarTutor(TransferTutorT transferTutor) {
        try {
            Dao<Tutor, Integer> daoTutor = getHelper().getTutorDao();
            Tutor tutor = daoTutor.queryForId(1);
            tutor.setId(1);
            tutor.setNombre(transferTutor.getNombre());
            tutor.setCorreo(transferTutor.getCorreo());
            tutor.setCodigoSincronizacion(transferTutor.getCodigoSincronizacion());
            tutor.setContrasenha(transferTutor.getContrasenha());
            tutor.setPregunta(transferTutor.getPregunta());
            tutor.setRespuesta(transferTutor.getRespuesta());
            daoTutor.update(tutor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

    @Override
    public void crearTutor(TransferTutorT transferTutor) {
        try {
            Dao<Tutor, Integer> daoTutor = getHelper().getTutorDao();
            Tutor tutor = new Tutor();
            tutor.setId(1);
            tutor.setNombre(transferTutor.getNombre());
            tutor.setCorreo(transferTutor.getCorreo());
            tutor.setCodigoSincronizacion(transferTutor.getCodigoSincronizacion());
            tutor.setContrasenha(transferTutor.getContrasenha());
            tutor.setPregunta(transferTutor.getPregunta());
            tutor.setRespuesta(transferTutor.getRespuesta());
            daoTutor.create(tutor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
