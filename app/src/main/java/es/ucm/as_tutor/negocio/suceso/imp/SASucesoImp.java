
package es.ucm.as_tutor.negocio.suceso.imp;

import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.suceso.Evento;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.negocio.suceso.Tarea;
import es.ucm.as_tutor.negocio.suceso.TransferEvento;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
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

	@Override
	public boolean crearEvento(TransferEvento nuevoEvento) {

		try {
			Dao<Evento, Integer> daoTarea =  getHelper().getEventoDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminarEvento(TransferEvento eliminaEvento) {
		return false;
	}

	@Override
	public TransferEvento consultaEvento(TransferEvento consulta) {
		TransferEvento ret = null;
		try {
			Dao<Evento, Integer> daoEvento =  getHelper().getEventoDao();
			Evento eventoBDD = daoEvento.queryForId(consulta.getId());
			ret = new TransferEvento(eventoBDD.getId(),eventoBDD.getNombreEvento(),
					eventoBDD.getFecha(),eventoBDD.getHoraAlarma(),eventoBDD.getHoraEvento());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public boolean guardarEvento(TransferEvento guardaEvento) {
		return false;
	}

	@Override
	public ArrayList<TransferEvento> listadoEventos() {
		ArrayList<TransferEvento> ret = new ArrayList<TransferEvento>();

		try {
			Dao<Evento, Integer> daoEvento =  getHelper().getEventoDao();

			/*////////////////////////////////Borrar /////////////////////////////////
			Evento a = new Evento();
			a.setNombreEvento("Ir a las barcas");a.setFecha(new Date(1234));a.setHoraAlarma(new Date(1234)); a.setHoraEvento(new Date(1234));
			Evento a1 = new Evento();
			a1.setNombreEvento("Ir a beber cerveza");a1.setFecha(new Date(3211));a1.setHoraAlarma(new Date(3211)); a1.setHoraEvento(new Date(3211));
			Evento a2 = new Evento();
			a2.setNombreEvento("Ir a las barcas a beber cerveza");a2.setFecha(new Date(3322));a2.setHoraAlarma(new Date(3322)); a2.setHoraEvento(new Date(3322));
			Evento a3 = new Evento();
			a3.setNombreEvento("Ir a las barcas a beber cerveza, mucha :D");a3.setFecha(new Date(4411));a3.setHoraAlarma(new Date(4411)); a3.setHoraEvento(new Date(4411));


			daoEvento.create(a);daoEvento.create(a1);daoEvento.create(a2);daoEvento.create(a3);

			/*************************************************/
			List<Evento> eventosBDD = daoEvento.queryForAll();
			for(int i=0; i < eventosBDD.size(); i++) {
				TransferEvento e = new TransferEvento(eventosBDD.get(i).getId(),eventosBDD.get(i).getNombreEvento(),
						eventosBDD.get(i).getFecha(),eventosBDD.get(i).getHoraAlarma(),eventosBDD.get(i).getHoraEvento());
				ret.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public ArrayList<TransferUsuarioT> consultarUsuariosEvento(TransferEvento guardaEvento) {
		return null;
	}
}