
package es.ucm.as_tutor.negocio.suceso.imp;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.suceso.Reto;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.negocio.suceso.Tarea;
import es.ucm.as_tutor.negocio.suceso.TransferRetoT;
import es.ucm.as_tutor.negocio.suceso.TransferTareaT;
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
            Tarea tarea = daoTarea.queryForId(idTarea);
            daoTarea.delete(tarea);
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

            /*/////////////////////////////////////////////////////////////////////////////////////
            Usuario u = new Usuario();
            u.setNombre("Maria");
            daoUsuario.create(u);
            *//////////////////////////////////////////////////////////////////////////////////////

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
                reto1.setPremio("Si lo completa, ganara un kitkat");
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
            // Busca el reto de ese usuario
            QueryBuilder<Reto, Integer> rQb = daoReto.queryBuilder();
            rQb.where().eq("USUARIO" , usuario);
            Reto r = rQb.queryForFirst();

            ret = new TransferRetoT(r.getId(), r.getUsuario().getId(), r.getContador(),
                    r.getTexto(), r.getSuperado(), r.getPremio());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }
}