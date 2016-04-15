
package es.ucm.as_tutor.negocio.suceso.imp;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.presentacion.vista.main.Manager;


public class SASucesoImp implements SASuceso {

    private DBHelper mDBHelper = null;

    private DBHelper getHelper() {
        if (mDBHelper == null) {
            mDBHelper = OpenHelperManager.getHelper(Manager.getInstance().getContext(), DBHelper.class);
        }
        return mDBHelper;
    }

	@Override
	public void crearTarea() {

	}

	@Override
	public void editarTarea() {

	}

	@Override
	public void eliminarTarea() {

	}
}