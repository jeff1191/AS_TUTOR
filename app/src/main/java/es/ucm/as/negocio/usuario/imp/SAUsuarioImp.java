/**
 * 
 */
package es.ucm.as.negocio.usuario.imp;

import android.content.Intent;
import android.net.Uri;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ucm.as.integracion.DBHelper;
import es.ucm.as.integracion.UsuarioEvento;
import es.ucm.as.integracion.Evento;
import es.ucm.as.negocio.factoria.FactoriaSA;
import es.ucm.as.integracion.Reto;
import es.ucm.as.negocio.suceso.SASuceso;
import es.ucm.as.integracion.Tarea;
import es.ucm.as.negocio.suceso.TransferEvento;
import es.ucm.as.negocio.suceso.TransferUsuarioEvento;
import es.ucm.as.negocio.suceso.TransferReto;
import es.ucm.as.negocio.suceso.TransferTarea;
import es.ucm.as.integracion.Tutor;
import es.ucm.as.negocio.usuario.SAUsuario;
import es.ucm.as.negocio.usuario.TransferUsuario;
import es.ucm.as.integracion.Usuario;
import es.ucm.as.negocio.utils.PDFManager;
import es.ucm.as.negocio.utils.ParserText;
import es.ucm.as.negocio.utils.Perfil;
import es.ucm.as.presentacion.vista.main.Manager;


public class SAUsuarioImp implements SAUsuario {

	private DBHelper mDBHelper = null;

	private DBHelper getHelper() {
		if (mDBHelper == null) {
			mDBHelper = OpenHelperManager.getHelper(Manager.getInstance().getContext(), DBHelper.class);
		}
		return mDBHelper;
	}

	public void crearUsuario(TransferUsuario transferUsuario) {

		try {
			Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
			Usuario usuario = new Usuario();
			usuario.setNombre(transferUsuario.getNombre());
			usuario.setCorreo(transferUsuario.getCorreo());
			usuario.setAvatar(transferUsuario.getAvatar());
			usuario.setTelefono(transferUsuario.getTelefono());
			usuario.setPuntuacion(transferUsuario.getPuntuacion());
			usuario.setPuntuacionAnterior(transferUsuario.getPuntuacionAnterior());
			usuario.setCurso(transferUsuario.getCurso());
			usuario.setDni(transferUsuario.getDni());
			usuario.setDireccion(transferUsuario.getDireccion());
			switch (transferUsuario.getTipoPerfil()){
				case "Estandar":
					usuario.setTipoPerfil(Perfil.Estandar);
					break;
				case "Chica":
					usuario.setTipoPerfil(Perfil.Chica);
					break;
			}
			usuario.setNotas(transferUsuario.getNotas());
			usuario.setNombrePadre(transferUsuario.getNombrePadre());
			usuario.setNombreMadre(transferUsuario.getNombreMadre());
			usuario.setCorreoPadre(transferUsuario.getCorreoPadre());
			usuario.setCorreoMadre(transferUsuario.getCorreoMadre());
			usuario.setTelfPadre(transferUsuario.getTelPadre());
			usuario.setTelfMadre(transferUsuario.getTelMadre());
			usuario.setCentroAcademico(transferUsuario.getCentroAcademico());
			usuario.setCodigoSincronizacion(transferUsuario.getCodigoSincronizacion());

            Dao<Tutor, Integer> daoTutor = getHelper().getTutorDao();
            Tutor tutor = daoTutor.queryForId(1);
            daoUsuario.create(usuario);

            List<Usuario> aux = daoUsuario.queryForEq("NOMBRE", usuario.getNombre());
            Usuario usuario1 = aux.get(0);

            String codigoSincronizacion = tutor.getCodigoSincronizacion()+usuario1.getId();
            usuario1.setCodigoSincronizacion(codigoSincronizacion);
			daoUsuario.update(usuario1);
			cargarTareasBBDD(usuario1.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editarUsuario(TransferUsuario usuarioMod) {
		try {
			Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
			Dao<Reto, Integer> daoReto = getHelper().getRetoDao();
			Usuario usuario = daoUsuario.queryForId(usuarioMod.getId());
			//Aqui buscar los campos modificados y machacharlos
			//¿Que tiene mas sentido... pasar todo o ver aqui que campos no son nulos?
			if(usuarioMod.getNombreMadre() == null && usuarioMod.getNombrePadre() == null
					&& usuarioMod.getNombre() != null){
				//Implica que viene de detalleUsuario
				usuario.setNombre(usuarioMod.getNombre());
				usuario.setCorreo(usuarioMod.getCorreo());
				usuario.setAvatar(usuarioMod.getAvatar());
				usuario.setTelefono(usuarioMod.getTelefono());
				usuario.setCurso(usuarioMod.getCurso());
				usuario.setDni(usuarioMod.getDni());
				usuario.setDireccion(usuarioMod.getDireccion());
				usuario.setNotas(usuarioMod.getNotas());
				usuario.setCentroAcademico(usuarioMod.getCentroAcademico());
			}
			else if(usuarioMod.getNombre() == null && (usuarioMod.getNombreMadre() != null
					|| usuarioMod.getNombrePadre() != null)){
				//Implica que viene de infoPadres
				if(usuarioMod.getNombreMadre() == null) {
					//Implica que viene de infoPadre
					usuario.setNombrePadre(usuarioMod.getNombrePadre());
					usuario.setCorreoPadre(usuarioMod.getCorreoPadre());
					usuario.setTelfPadre(usuarioMod.getTelPadre());
				}
				else{
					usuario.setNombreMadre(usuarioMod.getNombreMadre());
					usuario.setCorreoMadre(usuarioMod.getCorreoMadre());
					usuario.setTelfMadre(usuarioMod.getTelMadre());
				}
			} else {
				//Sincronizacion (Tal vez otro comando?)
				usuario.setPuntuacion(usuarioMod.getPuntuacion());
				usuario.setPuntuacionAnterior(usuarioMod.getPuntuacionAnterior());
			}
			daoUsuario.update(usuario);
			Usuario aux = daoUsuario.queryForId(usuarioMod.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void eliminarUsuario(Integer idUsuario) {
		try {

            // Eliminamos todas las tareas de el usuario
            SASuceso saSuceso = FactoriaSA.getInstancia().nuevoSASuceso();
            ArrayList<TransferTarea> tareas = saSuceso.consultarTareas(idUsuario);
            for (int i = 0; i < tareas.size(); i++)
                saSuceso.eliminarTarea(tareas.get(i).getId());

            // Eliminamos al usuario
            Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
			daoUsuario.deleteById(idUsuario);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public TransferUsuario consultarUsuario(Integer idUsuario) {
		TransferUsuario ret = null;

		try {
			Dao<Usuario, Integer> daoUsuario = getHelper().getUsuarioDao();
			Usuario user = daoUsuario.queryForId(idUsuario);

			ret = new TransferUsuario(user.getId(), user.getNombre(),
					user.getCorreo(), user.getAvatar(), user.getTelefono(), user.getPuntuacion(),
					user.getPuntuacionAnterior(), user.getCurso(), user.getDni(),
					user.getDireccion(), user.getTipoPerfil().toString(), user.getNotas(),
					user.getNombrePadre(), user.getNombreMadre(), user.getCorreoPadre(),
					user.getCorreoMadre(), user.getTelfPadre(), user.getTelfMadre(),
					user.getCentroAcademico(), user.getCodigoSincronizacion());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;

	}

	public void crearUsuarios(){
		Dao<Usuario, Integer> usuario;
		Dao<Reto, Integer> reto;
		try {
			usuario = getHelper().getUsuarioDao();
			reto = getHelper().getRetoDao();
			if(!usuario.idExists(1)) { // Si no hay un usuario en la base de datos, que se creen
				Usuario user1 = new Usuario();
				user1.setNombre("María Salgado");
				user1.setAvatar("");
				user1.setDni("12345678Q");
				user1.setDireccion("C/ Alacala 46, 6ºA");
				user1.setTelefono("678678678");
				user1.setCorreo("correo@gmail.com");
				user1.setCentroAcademico("Colegio del Pilar");
				user1.setCurso("4º ESO");
				user1.setNotas("Le gusta el chocolate");
				user1.setNombrePadre("Manuel");
				user1.setNombreMadre("Carmen");
				user1.setTelfPadre("666666666");
				user1.setTelfMadre("666666666");
				user1.setCorreoPadre("correo@gmail.com");
				user1.setCorreoMadre("correo@gmail.com");
				user1.setTipoPerfil(Perfil.Chica);
				user1.setCodigoSincronizacion("VIC001");
				user1.setPuntuacion(9);
				user1.setPuntuacionAnterior(10);
				usuario.create(user1);
				Usuario user2 = new Usuario();
				user2.setNombre("Juanlu Armas");
				user2.setAvatar("");
				user2.setDni("12345678Q");
				user2.setDireccion("C/ Alacala 46, 6ºA");
				user2.setTelefono("678678678");
				user2.setCorreo("correo@gmail.com");
				user2.setCentroAcademico("Colegio del Pilar");
				user2.setCurso("4º ESO");
				user2.setNotas("Le gusta el chocolate");
				user2.setNombrePadre("Manuel");
				user2.setNombreMadre("Carmen");
				user2.setTelfPadre("666666666");
				user2.setTelfMadre("888888888");
				user2.setCorreoPadre("correo@gmail.com");
				user2.setCorreoMadre("correo@gmail.com");
				user2.setTipoPerfil(Perfil.Estandar);
				user2.setCodigoSincronizacion("VIC002");
				user2.setPuntuacion(9);
				user2.setPuntuacionAnterior(10);
				usuario.create(user2);
				Usuario user3 = new Usuario();
				user3.setNombre("Jefferson Almache");
				user3.setAvatar("");
				user3.setDni("12345678Q");
				user3.setDireccion("C/ Alacala 46, 6ºA");
				user3.setTelefono("678678678");
				user3.setCorreo("correo@gmail.com");
				user3.setCentroAcademico("Colegio del Pilar");
				user3.setCurso("4º ESO");
				user3.setNotas("Le gusta el chocolate");
				user3.setNombrePadre("Manuel");
				user3.setNombreMadre("Carmen");
				user3.setTelfPadre("666666666");
				user3.setTelfMadre("666666666");
				user3.setCorreoPadre("correo@gmail.com");
				user3.setCorreoMadre("correo@gmail.com");
				user3.setTipoPerfil(Perfil.Estandar);
				user3.setCodigoSincronizacion("VIC003");
				user3.setPuntuacion(9);
				user3.setPuntuacionAnterior(10);
				usuario.create(user3);
				Usuario user4 = new Usuario();
				user4.setNombre("Clara Paules");
				user4.setAvatar("");
				user4.setDni("12345678Q");
				user4.setDireccion("C/ Alacala 46, 6ºA");
				user4.setTelefono("678678678");
				user4.setCorreo("correo@gmail.com");
				user4.setCentroAcademico("Colegio del Pilar");
				user4.setCurso("4º ESO");
				user4.setNotas("Le gusta el chocolate");
				user4.setNombrePadre("Manuel");
				user4.setNombreMadre("Carmen");
				user4.setTelfPadre("666666666");
				user4.setTelfMadre("666666666");
				user4.setCorreoPadre("correo@gmail.com");
				user4.setCorreoMadre("correo@gmail.com");
				user4.setTipoPerfil(Perfil.Chica);
				user4.setCodigoSincronizacion("VIC004");
				user4.setPuntuacion(9);
				user4.setPuntuacionAnterior(10);
				usuario.create(user4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<TransferUsuario> consultarUsuarios() {
		ArrayList<TransferUsuario> ret = new ArrayList<TransferUsuario>();
		Dao<Usuario, Integer> usuarios;
		try {
			usuarios = getHelper().getUsuarioDao();
			List<Usuario> listaUsuarios = usuarios.queryForAll();
			for(Usuario user : listaUsuarios){
				TransferUsuario transfer = new TransferUsuario(user.getId(), user.getNombre(),
						user.getAvatar());
				ret.add(transfer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public void cargarTareasBBDD(Integer idUsuario) {
		ParserText p = new ParserText();
		Dao<Tarea, Integer> tareaDao;
		Dao<Usuario, Integer> usuarioDao;

		try {
			tareaDao = getHelper().getTareaDao();
			usuarioDao = getHelper().getUsuarioDao();
			Usuario usuario = usuarioDao.queryForId(idUsuario);

			// lee del fichero que corresponda al perfil y convierte en tareas
			p.readTareas(usuario.getTipoPerfil());
			ArrayList<Tarea> tareasFichero = p.getTareas();

			// crea las nuevas tareas en BBDD
			for (int i = 0; i < tareasFichero.size(); i++){
				Tarea t = tareasFichero.get(i);
				t.setUsuario(usuario);
				tareaDao.create(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void generarPDF(Integer idUsuario) {

        SASuceso saSuceso = FactoriaSA.getInstancia().nuevoSASuceso();
        ArrayList<TransferTarea> tareas = saSuceso.consultarTareas(idUsuario);
        TransferReto reto = saSuceso.consultarReto(idUsuario);
        TransferUsuario usuario = consultarUsuario(idUsuario);

        PDFManager.generarPDF(usuario, reto, tareas);
    }


	@Override
	public void enviarCorreo(Integer idUsuario) {

        Dao<Usuario, Integer> usuarioDao;
        Dao<Tutor, Integer> tutorDao;

        try {
            // Se busca en la BBDD los datos necesarios para el correo
            usuarioDao = getHelper().getUsuarioDao();
            tutorDao = getHelper().getTutorDao();
            Usuario usuario = usuarioDao.queryForId(idUsuario);
            String nombreUsuario = usuario.getNombre();
            Tutor tutor = tutorDao.queryForId(1);
            String nombreTutor = tutor.getNombre();
            String correoTutor = tutor.getCorreo();

            //Se abre la aplicación de correo que haya instalada en la tablet con la sesion
            // que los usuarios tengan iniciada
            //Instanciamos un Intent del tipo ACTION_SEND
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            //Definimos la tipologia de datos del contenido dle Email en este caso pdf
            emailIntent.setType("application/pdf");
            // Indicamos con un Array de tipo String las direcciones de correo a las cuales
            //queremos enviar el texto
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{correoTutor});
            // Definimos un titulo para el Email
            emailIntent.putExtra(android.content.Intent.EXTRA_TITLE, "Informe AS");
            // Definimos un asunto para el Email
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Informe AS");
            // Obtenemos la referencia al texto y lo pasamos al Email Intent
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "¡Hola " + nombreTutor + "!\n " +
                    "Este es el progreso de "+ nombreUsuario +" hasta el momento.\n\nEnviado desde AS");

            Uri uri = Uri.parse( new File("file://" + "/storage/sdcard/Download/AS/Informe.pdf").toString());

            emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
            Manager.getInstance().getContext().startActivity(emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public ArrayList<TransferUsuarioEvento> consultarEventosUsuario(Integer idUsuario) {
		ArrayList<TransferUsuarioEvento> ret = new ArrayList<TransferUsuarioEvento>();

		try {
			Dao<Evento, Integer> daoEvento =  getHelper().getEventoDao();
			Dao<Usuario, Integer> daoUsuario =  getHelper().getUsuarioDao();
			Dao<UsuarioEvento, Integer> daoUsuarioEvento =  getHelper().getUsuarioEventoDao();
			//Consultar usuario
			Usuario usuarioBDD = daoUsuario.queryForId(idUsuario);

			TransferUsuario tUsuario = new TransferUsuario();
			tUsuario.setId(usuarioBDD.getId());
			tUsuario.setNombre(usuarioBDD.getNombre());

			QueryBuilder<UsuarioEvento, Integer> tQb = daoUsuarioEvento.queryBuilder();

			List<UsuarioEvento> eventosUsuarioTabla = tQb.where().eq("USUARIO", usuarioBDD).query(); //tendremos todos los eventos con ID_EVENTO

			List<Evento> usuarioEventos = getHelper().lookupEventosForUsuario(usuarioBDD); //tenemos los usuarios de ese evento
			ret.add(new TransferUsuarioEvento(null,tUsuario));
			for(int i = 0; i < usuarioEventos.size(); i++) {
				TransferEvento tEvento= new TransferEvento();
				tEvento.setId(usuarioEventos.get(i).getId());
				tEvento.setNombre(usuarioEventos.get(i).getNombreEvento());
				tEvento.setFecha(usuarioEventos.get(i).getFecha());
				tEvento.setHoraEvento(usuarioEventos.get(i).getHoraEvento());
				tEvento.setHoraAlarma(usuarioEventos.get(i).getHoraAlarma());
				TransferUsuarioEvento tRet = new TransferUsuarioEvento(tEvento,tUsuario);
				tRet.setAsistencia(eventosUsuarioTabla.get(i).getAsistencia());
				ret.add(tRet);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public void guardarEventosUsuario(ArrayList<TransferUsuarioEvento> eventosUsuario) {
		if(eventosUsuario.size() > 0) {
			try {
				Dao<Evento, Integer> daoEvento =  getHelper().getEventoDao();
				Dao<UsuarioEvento, Integer> daoUsuarioEvento = getHelper().getUsuarioEventoDao();
				Dao<Usuario, Integer> daoUsuario =  getHelper().getUsuarioDao();

				QueryBuilder<Usuario, Integer> tQb = daoUsuario.queryBuilder();
				tQb.where().eq("NOMBRE", eventosUsuario.get(0).getUsuario().getNombre());
				Usuario usuarioBDD = tQb.queryForFirst(); // Obtenemos el usuario


				QueryBuilder<UsuarioEvento, Integer> tQbUsuario_e = daoUsuarioEvento.queryBuilder();

				List<UsuarioEvento> eventos_de_Usuario = tQbUsuario_e.where().eq("USUARIO", usuarioBDD).query(); //tendremos todos los eventos con ID_EVENTO


				List<Evento> eventos_uBDD= getHelper().lookupEventosForUsuario(usuarioBDD);

				for(int i=1; i < eventosUsuario.size(); i++){
					Evento eventoU =daoEvento.queryForId(eventosUsuario.get(i).getEvento().getId());
					//UsuarioEvento relacion = new UsuarioEvento( daoEvento.queryForId(eventosUsuario.get(i).getEvento().getId()),usuarioBDD);
					UsuarioEvento relacion = tQbUsuario_e.where().eq("USUARIO" , usuarioBDD).and().eq("EVENTO", eventoU).queryForFirst();
					relacion.setAsistencia(eventosUsuario.get(i).getEvento().getAsistencia());

					daoUsuarioEvento.createOrUpdate(relacion);
				}
				//daoUsuarioEvento.delete(eventos_de_Usuario); //Quitamos todos sus eventos para volverlos añadir

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actualizarPuntuacion(TransferUsuario actualizaPuntuacion) {
		try {
			Dao<Usuario, Integer> daoUsuario  = getHelper().getUsuarioDao();
			Usuario usuarioPuntuacion = daoUsuario.queryForId(actualizaPuntuacion.getId());

			usuarioPuntuacion.setPuntuacion(actualizaPuntuacion.getPuntuacion());

			daoUsuario.update(usuarioPuntuacion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}