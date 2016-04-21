package es.ucm.as_tutor.presentacion.vista.usuario;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.suceso.TransferRetoT;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import es.ucm.as_tutor.presentacion.controlador.Controlador;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.vista.main.Manager;
import es.ucm.as_tutor.presentacion.vista.usuario.evento.FragmentDetalleUsuarioEvento;

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
    private FloatingActionButton editarDatos;

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
        if(usuario.getIdReto() != null)
            arguments.putInt("reto", usuario.getIdReto());
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
    public View onCreateView(final LayoutInflater inflater,
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
            infoPadreV = (TextView) rootView.findViewById(R.id.infoPadre);
            infoMadreV = (TextView) rootView.findViewById(R.id.infoMadre);
            editarDatos = (FloatingActionButton) rootView.findViewById(R.id.guardarCambios);

            editarDatos.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransferUsuarioT usuario = new TransferUsuarioT();
                    usuario.setNombre(nombreV.getText().toString());
                    usuario.setCorreo(correoV.getText().toString());
                    usuario.setAvatar(avatarV.toString());
                    usuario.setTelefono(telefonoV.getText().toString());
                    usuario.setCurso(estudiosV.getText().toString());
                    usuario.setDni(dniV.getText().toString());
                    usuario.setDireccion(direccionV.getText().toString());
                    usuario.setNotas(notasV.getText().toString());
                    usuario.setCentroAcademico(centroEstudiosV.getText().toString());
                    usuario.setId(id);
                    Controlador.getInstancia().ejecutaComando(ListaComandos.EDITAR_USUARIO, usuario);
                }
            });

            infoPadreV.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog a = createInfoProgenitoresDialogo("padre", inflater);
                    a.show();
                }
            });

            infoMadreV.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog a = createInfoProgenitoresDialogo("madre", inflater);
                    a.show();
                }
            });

            nombreV.setText(nombre);
            sincronizacionV.setText("Sincronización: " + sincronizacion);
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

    public AlertDialog createInfoProgenitoresDialogo(final String progenitor, LayoutInflater inflater) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = inflater.inflate(R.layout.dialog_info_padres, null);
        builder.setView(v);

        final EditText name = (EditText) v.findViewById(R.id.name);
        final EditText phone = (EditText) v.findViewById(R.id.phone);
        final EditText mail = (EditText) v.findViewById(R.id.mail);

        if (progenitor.equals("padre")) {
            builder.setTitle("Información del padre");
            name.setText(nombrePadre);
            phone.setText(telefonoPadre);
            mail.setText(correoPadre);
        }
        else{
            builder.setTitle("Información de la madre");
            name.setText(nombreMadre);
            phone.setText(telefonoMadre);
            mail.setText(correoMadre);
        }

        builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Cambiar info padre o madre
                TransferUsuarioT editUser = new TransferUsuarioT();
                TransferUsuarioT consultarU = new TransferUsuarioT();
                editUser.setId(id);
                consultarU.setId(id);
                if(progenitor.equals("padre")){
                    editUser.setNombrePadre(name.getText().toString());
                    editUser.setTelPadre(phone.getText().toString());
                    editUser.setCorreoPadre(mail.getText().toString());
                }
                else{
                    editUser.setNombreMadre(name.getText().toString());
                    editUser.setTelMadre(phone.getText().toString());
                    editUser.setCorreoMadre(mail.getText().toString());
                }
                Controlador.getInstancia().ejecutaComando(ListaComandos.EDITAR_USUARIO, editUser);
                Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_USUARIO, consultarU);
            }
        });
        builder.setNegativeButton("Salir",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main_usuarios, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("testJL", "entra pls");
        switch (item.getItemId()) {
            case R.id.tareasUsuario:
                Log.e("testJL", "entra en tareas usuario");
                Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_TAREAS, 1);
                break;
            case R.id.retoUsuario:
                TransferRetoT consultarR = new TransferRetoT();
                Log.e("testJL", "El id del retos es " + reto);
                consultarR.setId(reto);
                Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_RETO, consultarR);
                break;
            case R.id.eventosUsuario:
                FragmentDetalleUsuarioEvento fragmentEventoUsuario = new FragmentDetalleUsuarioEvento();
                Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentEventoUsuario).commit();
                break;
            case R.id.enviarCorreo:
                // aquí habrá que ejecutar el comando de enviar correo

                // Enviar correo abriendo aplicación/////////////////////////////////////////////////////
                //Instanciamos un Intent del tipo ACTION_SEND
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                //Definimos la tipologia de datos del contenido dle Email en este caso text/html
                emailIntent.setType("application/pdf");
                // Indicamos con un Array de tipo String las direcciones de correo a las cuales
                //queremos enviar el texto
                //emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
                // Definimos un titulo para el Email
                emailIntent.putExtra(android.content.Intent.EXTRA_TITLE, "Informe AS");
                // Definimos un Asunto para el Email
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Informe AS");
                // Obtenemos la referencia al texto y lo pasamos al Email Intent
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "¡Hola " + /*name + */"!\n " +
                        "Este es tu progreso hasta el momento. Sigue esforzándote para continuar mejorando."
                        + "\n¡Ánimo!" + "\n\nEnviado desde AS");

                Uri uri = Uri.parse( new File("file://" + "/sdcard/Download/AS/Informe.pdf").toString());
                emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

                //getApplicationContext().startActivity(emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                ///////////////////////////////////////////////////////////////////////////////////////////

                break;
            case R.id.eliminarUsuario:
                Log.e("testJL", "entra en eliminar usuario con el id " + id);
                TransferUsuarioT consultarU = new TransferUsuarioT();
                consultarU.setId(id);
                Controlador.getInstancia().ejecutaComando(ListaComandos.ELIMINAR_USUARIO, consultarU);
                Controlador.getInstancia().ejecutaComando(ListaComandos.LISTADO_USUARIOS, null);
                break;
        }

        return true;
    }




}
