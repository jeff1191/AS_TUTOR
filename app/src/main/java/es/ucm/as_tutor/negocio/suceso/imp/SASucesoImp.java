
package es.ucm.as_tutor.negocio.suceso.imp;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.UsuarioEvento;
import es.ucm.as_tutor.negocio.suceso.Evento;
import es.ucm.as_tutor.negocio.suceso.Reto;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.negocio.suceso.Tarea;
import es.ucm.as_tutor.negocio.suceso.TransferEvento;
import es.ucm.as_tutor.negocio.suceso.TransferRetoT;
import es.ucm.as_tutor.negocio.suceso.TransferTareaT;
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


	// Tarea
	@Override
	public void crearTarea(TransferTareaT transferTarea) {
            // El constructor vacio de Tarea da valores por defecto a los campos no editables
            Tarea tarea = new Tarea();
            tarea.setTextoPregunta(transferTarea.getTextoPregunta());
            tarea.setTextoAlarma(transferTarea.getTextoAlarma());
            tarea.setHoraPregunta(transferTarea.getHoraPregunta());
            tarea.setHoraAlarma(transferTarea.getHoraAlarma());
            tarea.setMejorar(transferTarea.getMejorar());

            if (transferTarea.getFrecuenciaTarea() != null)
                tarea.setFrecuenciaTarea(transferTarea.getFrecuenciaTarea());
            if (transferTarea.getHabilitada() != null)
                tarea.setHabilitada(transferTarea.getHabilitada());

        try {
            Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
            QueryBuilder<Usuario, Integer> uQb = daoUsuario.queryBuilder();
            uQb.where().idEq(transferTarea.getIdUsuario());
            Usuario usuario = uQb.queryForFirst();
            tarea.setUsuario(usuario);

            Dao<Tarea, Integer> daoTarea = getHelper().getTareaDao();
            daoTarea.create(tarea);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void editarTarea(TransferTareaT transferTarea) {
        Tarea tarea = new Tarea();
        tarea.setTextoPregunta(transferTarea.getTextoPregunta());
        tarea.setTextoAlarma(transferTarea.getTextoAlarma());
        tarea.setHoraPregunta(transferTarea.getHoraPregunta());
        tarea.setHoraAlarma(transferTarea.getHoraAlarma());
        tarea.setMejorar(transferTarea.getMejorar());
        tarea.setFrecuenciaTarea(transferTarea.getFrecuenciaTarea());
        tarea.setHabilitada(transferTarea.getHabilitada());
        tarea.setNumSi(transferTarea.getNumSi());
        tarea.setNumNo(transferTarea.getNumNo());
        tarea.setContador(transferTarea.getContador());
        tarea.setId(transferTarea.getId());
        try {
            // Se busca al usuario por su id para asignarselo a la tarea
            Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
            QueryBuilder<Usuario, Integer> uQb = daoUsuario.queryBuilder();
            uQb.where().idEq(transferTarea.getIdUsuario());
            Usuario usuario = uQb.queryForFirst();
            tarea.setUsuario(usuario);
            // Se actualiza la informacion de la tarea en la BBDD
            Dao<Tarea, Integer> daoTarea = getHelper().getTareaDao();
            daoTarea.update(tarea);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarTarea(Integer idTarea) {
        try {
            Dao<Tarea, Integer> daoTarea = getHelper().getTareaDao();
            daoTarea.deleteById(idTarea);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deshabilitarTarea(Integer idTarea) {
        try {
            Dao<Tarea, Integer> daoTarea = getHelper().getTareaDao();
            Tarea tarea = daoTarea.queryForId(idTarea);
            tarea.setHabilitada(!tarea.getHabilitada());
            daoTarea.update(tarea);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<TransferTareaT> consultarTareas(Integer idUsuario) {
        ArrayList<TransferTareaT> ret = new ArrayList<TransferTareaT>();
        try {

            Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
            Dao<Tarea, Integer> daoTarea = getHelper().getTareaDao();

            // Busca al usuario por su id
            QueryBuilder<Usuario, Integer> uQb = daoUsuario.queryBuilder();
            uQb.where().idEq(idUsuario);
            Usuario usuario = uQb.queryForFirst();
            // Busca las tareas de ese usuario
            QueryBuilder<Tarea, Integer> tQb = daoTarea.queryBuilder();
            tQb.where().eq("USUARIO" , usuario);
            List<Tarea> tareas = tQb.query();

            // Las transformamos en transfers para devolverselas a la vista
            for(int i = 0; i < tareas.size(); i++){
                Tarea tarea = tareas.get(i);
                TransferTareaT transfer = new TransferTareaT(
                        tarea.getId(), tarea.getTextoAlarma(), tarea.getHoraAlarma(),
                        tarea.getTextoPregunta(), tarea.getHoraPregunta(), tarea.getMejorar(),
                        tarea.getUsuario().getId(), tarea.getContador(), tarea.getFrecuenciaTarea(),
                        tarea.getNumSi(), tarea.getNumNo(), tarea.getHabilitada());
                transfer.setId(tarea.getId());
                ret.add(i, transfer);
            }

            // En la primera posicion se almacena una tarea fantasma que solo tiene el id de usuario
            // para cuando no haya ninguna
            if(tareas.size() == 0) {
                TransferTareaT fantasma = new TransferTareaT();
                fantasma.setIdUsuario(idUsuario);
                ret.add(0, fantasma);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    // Evento
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
            ret.add(new TransferUsuarioEvento(tEvento,null));//posicion 0 para el evento ( tiene que mostrarse en la vista)
			for(int i = 0; i < usuariosEventos.size(); i++) {
				TransferUsuarioT tUsuario= new TransferUsuarioT();
				tUsuario.setId(usuariosEventos.get(i).getId());
				tUsuario.setNombre(usuariosEventos.get(i).getNombre());
				TransferUsuarioEvento tRet = new TransferUsuarioEvento(tEvento,tUsuario);
                tRet.setId(eventoUsuarios.get(i).getId());
				tRet.setActivo(1);
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
					tUsuario.setId(usuariosBDD.get(i).getId());
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
					UsuarioEvento relacion = new UsuarioEvento(eventoBDD, daoUsuario.queryForId(eventosUsuarios.get(i).getUsuario().getId()));
					relacion.setAsistencia("NO");
					daoUsuarioEvento.create(relacion);
				}


			} catch (SQLException e) {
				e.printStackTrace();
			}


		}
	}


    @Override
    public void guardarUsuariosEvento(ArrayList<TransferUsuarioEvento> eventosUsuarios) {
        if(eventosUsuarios.size() > 0) {
            try {
                Dao<Evento, Integer> daoEvento =  getHelper().getEventoDao();
                Dao<UsuarioEvento, Integer> daoUsuarioEvento = getHelper().getUsuarioEventoDao();
                Dao<Usuario, Integer> daoUsuario =  getHelper().getUsuarioDao();

                QueryBuilder<Evento, Integer> tQb = daoEvento.queryBuilder();
                tQb.where().eq("NOMBRE", eventosUsuarios.get(0).getEvento().getNombre());
                Evento eventoBDD = tQb.queryForFirst(); // Obtenemos el evento


                QueryBuilder<UsuarioEvento, Integer> tQbUsuario_e = daoUsuarioEvento.queryBuilder();

                List<UsuarioEvento> eventoUsuarios = tQbUsuario_e.where().eq("EVENTO", eventoBDD).query(); //tendremos todos los eventos con ID_EVENTO
                daoUsuarioEvento.delete(eventoUsuarios); //Quitamos todos sus usuarios para volverlos añadir

                for(int i=1; i < eventosUsuarios.size(); i++){
                    UsuarioEvento relacion = new UsuarioEvento(eventoBDD, daoUsuario.queryForId(eventosUsuarios.get(i).getUsuario().getId()));
                    relacion.setAsistencia("NO");
                    daoUsuarioEvento.create(relacion);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // Reto

    public void crearRetos(){
        Dao<Reto, Integer> reto;
        try {
            reto = getHelper().getRetoDao();
            Usuario u = new Usuario();
            if(!reto.idExists(1)) { // Si no hay un usuario en la base de datos, que se creen
                Reto reto1 = new Reto();
                reto1.setContador(3);
                reto1.setTexto("Dar un beso de buenas noches a mama");
                reto1.setSuperado(false);
                u.setId(1);
                reto1.setUsuario(u);
                reto1.setPremio("Kitkat");
                reto.create(reto1);
                Reto reto2 = new Reto();
                reto2.setContador(5);
                reto2.setTexto("Lavarse las manos antes de comer");
                reto2.setSuperado(false);
                u.setId(3);
                reto2.setUsuario(u);
                reto2.setPremio("");
                reto.create(reto2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void crearReto(TransferRetoT transferReto) {
        try{
            //Hay que asignarselo a ambos
            Dao<Reto, Integer> daoReto = getHelper().getRetoDao();
            Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
            Reto reto = new Reto();
            Usuario usuario = daoUsuario.queryForId(transferReto.getIdUsuario());
            reto.setTexto(transferReto.getTexto());
            reto.setPremio(transferReto.getPremio());
            reto.setSuperado(transferReto.getSuperado());
            reto.setContador(transferReto.getContador());
            reto.setUsuario(usuario);
            daoReto.create(reto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TransferRetoT consultarReto(Integer idUsuario) {
        TransferRetoT ret = null;

        try {
            Dao<Reto, Integer> daoReto = getHelper().getRetoDao();
            Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
            QueryBuilder<Usuario, Integer> uQb = daoUsuario.queryBuilder();
            uQb.where().idEq(idUsuario);
            Usuario usuario = uQb.queryForFirst();
            //Guarda el usuario en el transfer
            ret = new TransferRetoT();
            ret.setIdUsuario(usuario.getId());
            //Busca el reto de ese usuario
            QueryBuilder<Reto, Integer> rQb = daoReto.queryBuilder();
            rQb.where().eq("USUARIO", usuario);
            Reto r = rQb.queryForFirst();
            if (r != null)
                ret = new TransferRetoT(r.getId(), r.getUsuario().getId(), r.getContador(),
                        r.getTexto(), r.getSuperado(), r.getPremio());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }
}