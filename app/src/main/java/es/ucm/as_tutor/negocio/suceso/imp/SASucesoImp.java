
package es.ucm.as_tutor.negocio.suceso.imp;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

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
            // Busca al usuario por su id
            Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
            Usuario usuario = daoUsuario.queryForId(idUsuario);
            // Busca en la BBDD las tareas de ese usuario
            Dao<Tarea, Integer> daoTarea = getHelper().getTareaDao();
            List<Tarea> tareas = daoTarea.queryForEq("USUARIO", usuario);
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

    public void crearRetos(){
        Dao<Reto, Integer> reto;
        Dao<Usuario, Integer> usuario;
        try {
            reto = getHelper().getRetoDao();
            usuario = getHelper().getUsuarioDao();
            if(!reto.idExists(1)) { // Si no hay un usuario en la base de datos, que se creen
                Reto reto1 = new Reto();
                reto1.setContador(3);
                reto1.setTexto("Dar un beso de buenas noches a mama");
                reto1.setSuperado(false);
                reto1.setUsuario(usuario.queryForId(1));
                reto.create(reto1);
                Reto reto2 = new Reto();
                reto2.setContador(5);
                reto2.setTexto("Lavarse las manos antes de comer");
                reto2.setSuperado(false);
                reto2.setUsuario(usuario.queryForId(3));
                reto.create(reto2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TransferRetoT consultarReto(TransferRetoT consulta) {
        TransferRetoT ret = null;

        try {
            Dao<Reto, Integer> daoReto = getHelper().getRetoDao();
            Reto r = daoReto.queryForId(consulta.getId());
            if(r != null)
                ret = new TransferRetoT(r.getId(), r.getUsuario().getId(), r.getContador(),
                        r.getTexto(), r.getSuperado());
            else
                ret = consulta; // Esto depende como quede lo de mas arriba, poner tambien un new
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }
}