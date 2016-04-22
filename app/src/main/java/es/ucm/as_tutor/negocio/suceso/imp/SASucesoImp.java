
package es.ucm.as_tutor.negocio.suceso.imp;

import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.UsuarioEvento;
import es.ucm.as_tutor.negocio.suceso.Evento;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.negocio.suceso.TransferEvento;
import es.ucm.as_tutor.negocio.suceso.TransferUsuarioEvento;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import es.ucm.as_tutor.negocio.usuario.Usuario;
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
			Dao<Evento, Integer> daoEvento =  getHelper().getEventoDao();
			Evento createE = new Evento();
			createE.setNombreEvento(nuevoEvento.getNombre());
			createE.setHoraAlarma(nuevoEvento.getHoraAlarma());
			createE.setHoraEvento(nuevoEvento.getHoraEvento());
			createE.setFecha(nuevoEvento.getFecha());
			daoEvento.create(createE);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminarEvento(TransferEvento eliminaEvento) {

		try {
			Dao<Evento, Integer> daoEvento =  getHelper().getEventoDao();
			Evento eventoBDD = daoEvento.queryForId(eliminaEvento.getId());
			daoEvento.delete(eventoBDD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
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
		try {
			Dao<Evento, Integer> daoEvento =  getHelper().getEventoDao();
			Evento eventoBDD = daoEvento.queryForId(guardaEvento.getId());
			eventoBDD.setFecha(guardaEvento.getFecha());
			eventoBDD.setHoraAlarma(guardaEvento.getHoraAlarma());
			eventoBDD.setHoraEvento(guardaEvento.getHoraEvento());
			eventoBDD.setNombreEvento(guardaEvento.getNombre());
			daoEvento.update(eventoBDD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public ArrayList<TransferEvento> listadoEventos() {
		ArrayList<TransferEvento> ret = new ArrayList<TransferEvento>();

		try {
			Dao<Evento, Integer> daoEvento =  getHelper().getEventoDao();
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
	public ArrayList<TransferUsuarioEvento> consultarUsuariosEvento(TransferEvento consultaEvento) {
		ArrayList<TransferUsuarioEvento> ret = new ArrayList<TransferUsuarioEvento>();

		try {
			Dao<Evento, Integer> daoEvento =  getHelper().getEventoDao();
			Dao<Usuario, Integer> daoUsuario =  getHelper().getUsuarioDao();
			Dao<UsuarioEvento, Integer> daoUsuarioEvento =  getHelper().getUsuarioEventoDao();
			//Consultar Evento
			Evento eventoBDD = daoEvento.queryForId(consultaEvento.getId());

			TransferEvento tEvento = new TransferEvento(eventoBDD.getId(),eventoBDD.getNombreEvento(),eventoBDD.getFecha()
					,eventoBDD.getHoraAlarma(),eventoBDD.getHoraEvento());

			QueryBuilder<UsuarioEvento, Integer> tQb = daoUsuarioEvento.queryBuilder();

			List<UsuarioEvento> eventoUsuarios = tQb.where().eq("EVENTO", eventoBDD).query(); //tendremos todos los eventos con ID_EVENTO

			List<Usuario> usuariosEventos = getHelper().lookupUsuariosForEvento(eventoBDD);
			for(int i = 0; i < usuariosEventos.size(); i++) {
				TransferUsuarioT tUsuario= new TransferUsuarioT();
				tUsuario.setID(usuariosEventos.get(i).getId());
				tUsuario.setNombre(usuariosEventos.get(i).getNombre());
				TransferUsuarioEvento tRet = new TransferUsuarioEvento(tEvento,tUsuario);
				tRet.setActivo(eventoUsuarios.get(i).getActivo());
				tRet.setAsistencia(eventoUsuarios.get(i).getAsistencia());
				ret.add(tRet);
			}


			List<Usuario> usuariosBDD = daoUsuario.queryForAll();

			for(int i=0; i < usuariosBDD.size(); i++){
				boolean anyadir =true;
				for(int j=0; j < usuariosEventos.size();j++){
					if(usuariosBDD.get(i).getNombre().equalsIgnoreCase(usuariosEventos.get(j).getNombre()))
						anyadir=false;// Si en algun momento se encuentra en la lista del Evento no lo añado
				}
				if(anyadir){//puedo añadir el objeto i - ESTOS NO ESTAN ACTIVOS
					TransferUsuarioT tUsuario= new TransferUsuarioT();
					tUsuario.setID(usuariosBDD.get(i).getId());
					tUsuario.setNombre(usuariosBDD.get(i).getNombre());
					TransferUsuarioEvento tRet = new TransferUsuarioEvento(tEvento,tUsuario);
					tRet.setActivo(0);
					tRet.setAsistencia("NO");//No van a ir obviamente porque no les han invitado
					ret.add(tRet);
				}
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}


	@Override
	public void crearUsuariosEvento(ArrayList<TransferUsuarioEvento> eventosUsuarios) {
		if(eventosUsuarios.size() > 0) {
			try {
				Dao<Evento, Integer> daoEvento =  getHelper().getEventoDao();
				Dao<UsuarioEvento, Integer> daoUsuarioEvento = getHelper().getUsuarioEventoDao();
				Dao<Usuario, Integer> daoUsuario =  getHelper().getUsuarioDao();

				QueryBuilder<Evento, Integer> tQb = daoEvento.queryBuilder();
				tQb.where().eq("NOMBRE", eventosUsuarios.get(0).getEvento().getNombre());
				Evento eventoBDD = tQb.queryForFirst();

				for(int i=0; i < eventosUsuarios.size(); i++){
					UsuarioEvento relacion = new UsuarioEvento(eventoBDD, daoUsuario.queryForId(eventosUsuarios.get(i).getUsuario().getID()));
					relacion.setAsistencia("NO");
					relacion.setActivo(1);
					daoUsuarioEvento.create(relacion);
				}


			} catch (SQLException e) {
				e.printStackTrace();
			}


		}
	}

}