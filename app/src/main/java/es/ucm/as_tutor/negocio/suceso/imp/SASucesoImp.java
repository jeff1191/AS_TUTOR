
package es.ucm.as_tutor.negocio.suceso.imp;

import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ucm.as_tutor.integracion.DBHelper;
import es.ucm.as_tutor.negocio.suceso.SASuceso;
import es.ucm.as_tutor.negocio.suceso.Tarea;
import es.ucm.as_tutor.negocio.suceso.TransferTareaT;
import es.ucm.as_tutor.negocio.usuario.Usuario;
import es.ucm.as_tutor.negocio.utils.Frecuencia;
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
            tarea.setUsuario(transferTarea.getUsuario());
            if (transferTarea.getFrecuenciaTarea() != null)
                tarea.setFrecuenciaTarea(transferTarea.getFrecuenciaTarea());
            if (transferTarea.getHabilitada() != null)
                tarea.setHabilitada(transferTarea.getHabilitada());

        try {
            Dao<Tarea, Integer> daoTarea = getHelper().getTareaDao();
            daoTarea.create(tarea);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editarTarea(TransferTareaT transferTarea) {
		try {
			Dao<Tarea, Integer> daoTarea = getHelper().getTareaDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarTarea(TransferTareaT transferTarea) {
		try {
			Dao<Tarea, Integer> daoTarea = getHelper().getTareaDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void deshabilitarTarea(TransferTareaT datos) {
        try {
            Dao<Tarea, Integer> daoTarea = getHelper().getTareaDao();
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



            //*/////////////////////////////////////////////////////////////////////////////////////
            Usuario u = new Usuario();
            u.setNombre("Maria");
            daoUsuario.create(u);

            Tarea t1 = new Tarea();
            t1.setTextoAlarma("Prueba 1");
            t1.setUsuario(u);
            daoTarea.create(t1);

            Tarea t2 = new Tarea();
            t2.setTextoAlarma("Prueba 2");
            t2.setUsuario(u);
            daoTarea.create(t2);

            Tarea t3 = new Tarea();
            t3.setTextoAlarma("Prueba 3");
            t3.setUsuario(u);
            daoTarea.create(t3);
            ////////////////////////////////////////////////////////////////////////////////////////


            // Busca al usuario por su id
            QueryBuilder<Usuario, Integer> uQb = daoUsuario.queryBuilder();
            uQb.where().idEq(idUsuario);
            Usuario usuario = uQb.queryForFirst();
            // Busca las tareas de ese usuario
            QueryBuilder<Tarea, Integer> tQb = daoTarea.queryBuilder();
            tQb.where().eq("USUARIO" , usuario);
            List<Tarea> tareas = tQb.query();
            // Las transformamos en transfers para devolverselas a la vista
            for(Tarea tarea : tareas){
                TransferTareaT transfer = new TransferTareaT(
                        tarea.getId(), tarea.getTextoAlarma(), tarea.getHoraAlarma(),
                        tarea.getTextoPregunta(), tarea.getHoraPregunta(), tarea.getMejorar(),
                        tarea.getUsuario(), tarea.getContador(), tarea.getFrecuenciaTarea(),
                        tarea.getNumSi(), tarea.getNumNo(), tarea.getHabilitada());
                ret.add(transfer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}