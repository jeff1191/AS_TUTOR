
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
    public ArrayList<TransferTareaT> consultarTareas(TransferUsuarioT idUsuario) {
        ArrayList<TransferTareaT> ret = new ArrayList<TransferTareaT>();
        try {
            // Busca al usuario por su id
            Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
            Usuario usuario = daoUsuario.queryForId(idUsuario.getId());
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
                reto1.setPremio("Si lo completas, ganaras un kitkat");
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
            Dao<Reto, Integer> daoReto = getHelper().getRetoDao();
            Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
            Reto reto = new Reto();
            reto.setTexto(transferReto.getTexto());
            reto.setPremio(transferReto.getPremio());
            reto.setSuperado(transferReto.getSuperado());
            reto.setContador(transferReto.getContador());
            reto.setUsuario(daoUsuario.queryForId(transferReto.getIdUsuario()));
            daoReto.create(reto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TransferRetoT consultarReto(TransferRetoT consulta) {
        TransferRetoT ret = null;

        try {
            Dao<Reto, Integer> daoReto = getHelper().getRetoDao();
            if(consulta.getId() != null) {
                Reto r = daoReto.queryForId(consulta.getId());
                if (r != null)
                    ret = new TransferRetoT(r.getId(), r.getUsuario().getId(), r.getContador(),
                            r.getTexto(), r.getSuperado(), r.getPremio());
                else
                    ret = consulta; // Esto depende como quede lo de mas arriba, poner tambien un new
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }
}