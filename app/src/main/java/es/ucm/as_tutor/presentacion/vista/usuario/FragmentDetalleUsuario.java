package es.ucm.as_tutor.presentacion.vista.usuario;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;

public class FragmentDetalleUsuario extends Fragment {


    private TextView nombreV;
    private TextView sincronizacionV;
    private TextView perfilV;
    private EditText dniV;
    private EditText direccionV;
    private EditText telefonoV;
    private EditText correoV;
    private EditText centroEstudiosV;
    private EditText estudiosV;
    private EditText notasV;
    private TextView puntuacionV;
    private ImageView avatarV;
    private TextView infoPadreV;
    private TextView infoMadreV;

    private Integer id;
    private String nombre;
    private String sincronizacion;
    private String perfil;
    private String dni;
    private String direccion;
    private String telefono;
    private String correo;
    private String centroEstudios;
    private String estudios;
    private String notas;
    private Integer puntuacion;
    private Integer puntuacionAnterior;
    private String avatar;
    private Integer reto;
    private String nombrePadre;
    private String nombreMadre;
    private String telefonoPadre;
    private String telefonoMadre;
    private String correoPadre;
    private String correoMadre;


    public FragmentDetalleUsuario(){}

    public static FragmentDetalleUsuario newInstance(TransferUsuarioT usuario) {
        FragmentDetalleUsuario frgUsuario = new FragmentDetalleUsuario();

        Bundle arguments = new Bundle();
        arguments.putInt("id", usuario.getId());
        arguments.putString("nombre", usuario.getNombre());
        arguments.putString("correo", usuario.getCorreo());
        arguments.putString("avatar", usuario.getAvatar());
        arguments.putString("telefono", usuario.getTelefono());
        arguments.putInt("puntuacion", usuario.getPuntuacion());
        arguments.putInt("puntuacionAnterior", usuario.getPuntuacionAnterior());
        arguments.putString("estudios", usuario.getCurso());
        arguments.putString("dni", usuario.getDni());
        arguments.putString("direccion", usuario.getDireccion());
        arguments.putString("perfil", usuario.getTipoPerfil());
        arguments.putString("notas", usuario.getNotas());
        arguments.putString("nombrePadre", usuario.getNombrePadre());
        arguments.putString("nombreMadre", usuario.getNombreMadre());
        arguments.putString("correoPadre", usuario.getCorreoPadre());
        arguments.putString("correoMadre", usuario.getCorreoMadre());
        arguments.putString("telfPadre", usuario.getTelPadre());
        arguments.putString("telfMadre", usuario.getTelMadre());
        arguments.putString("colegio", usuario.getCentroAcademico());
        if(usuario.getReto() != null)
            arguments.putInt("reto", usuario.getReto().getId());
        arguments.putString("sincronizacion", usuario.getCodigoSincronizacion());

        frgUsuario.setArguments(arguments);

        return frgUsuario;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if(bundle != null) {
            id = bundle.getInt("id");
            nombre = bundle.getString("nombre");
            correo = bundle.getString("correo");
            avatar = bundle.getString("avatar");
            telefono = bundle.getString("telefono");
            puntuacion = bundle.getInt("puntuacion");
            puntuacionAnterior = bundle.getInt("puntuacionAnterior");
            estudios = bundle.getString("estudios");
            dni = bundle.getString("dni");
            direccion = bundle.getString("direccion");
            perfil = bundle.getString("perfil");
            notas = bundle.getString("notas");
            nombrePadre = bundle.getString("nombrePadre");
            nombreMadre = bundle.getString("nombreMadre");
            telefonoPadre = bundle.getString("telfPadre");
            telefonoMadre = bundle.getString("telfMadre");
            correoPadre = bundle.getString("correoPadre");
            correoMadre = bundle.getString("correoMadre");
            centroEstudios = bundle.getString("colegio");
            reto = bundle.getInt("reto");
            sincronizacion = bundle.getString("sincronizacion");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detalle_usuario, container, false);

            nombreV = (TextView) rootView.findViewById(R.id.nombre);
            sincronizacionV = (TextView) rootView.findViewById(R.id.sincronizacion);
            perfilV = (TextView) rootView.findViewById(R.id.perfil);
            dniV = (EditText) rootView.findViewById(R.id.dni);
            direccionV = (EditText) rootView.findViewById(R.id.direccion);
            telefonoV = (EditText) rootView.findViewById(R.id.telefono);
            correoV = (EditText) rootView.findViewById(R.id.correo);
            centroEstudiosV = (EditText) rootView.findViewById(R.id.colegio);
            estudiosV = (EditText) rootView.findViewById(R.id.estudios);
            notasV = (EditText) rootView.findViewById(R.id.notas);
            puntuacionV = (TextView) rootView.findViewById(R.id.puntuacionUsuario);
            avatarV = (ImageView) rootView.findViewById(R.id.avatar);

            //Falta poner bien la info de los padres
            infoPadreV = (TextView) rootView.findViewById(R.id.infoPadre);
            infoMadreV = (TextView) rootView.findViewById(R.id.infoMadre);

            nombreV.setText(nombre);
            sincronizacionV.setText(sincronizacion);
            perfilV.setText("Perfil " + perfil);
            dniV.setText(dni);
            direccionV.setText(direccion);
            telefonoV.setText(telefono);
            correoV.setText(correo);
            centroEstudiosV.setText(centroEstudios);
            estudiosV.setText(estudios);
            notasV.setText(notas);
            puntuacionV.setText(puntuacion + "/10");
           /* if(!avatar.equals(""))
                avatarV.setImageBitmap(BitmapFactory.decodeFile(avatar));
            else */
                avatarV.setImageResource(R.drawable.avatar);

            return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear(); //poner otro menu
        inflater.inflate(R.menu.menu_main_usuarios, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }




}
