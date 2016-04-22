package es.ucm.as_tutor.presentacion.vista.usuario;

import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import es.ucm.as_tutor.presentacion.controlador.Controlador;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.vista.main.Manager;

public class FragmentDetalleUsuario extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 3;
    private static final int SELECCIONAR_GALERIA = 2;
    private static final int CAMARA = 1;

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
    private String avatar;
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
            Log.e("testJL", "El valor del id de usuario es " + bundle.getInt("id"));
            id = bundle.getInt("id");
            Log.e("testJL", "El valor del id una vez guardado es " + id);
            nombre = bundle.getString("nombre");
            correo = bundle.getString("correo");
            avatar = bundle.getString("avatar");
            telefono = bundle.getString("telefono");
            puntuacion = bundle.getInt("puntuacion");
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

            avatarV.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final CharSequence[] items = { "Hacer foto", "Elegir de la galeria", "Imagen por defecto" };
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            if (items[item].equals("Hacer foto")) {
                                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, CAMARA);
                            } else if (items[item].equals("Elegir de la galeria")) {
                                Intent intent = new Intent(
                                        Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/*");
                                startActivityForResult(
                                        Intent.createChooser(intent, "Select File"),
                                        SELECCIONAR_GALERIA);
                            } else if (items[item].equals("Imagen por defecto")) {
                                avatarV.setImageResource(R.drawable.avatar);
                                avatar="";
                                dialog.dismiss();
                            }
                        }
                    });
                    builder.create().show();
                }
            });

            editarDatos.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransferUsuarioT usuario = new TransferUsuarioT();
                    usuario.setNombre(nombreV.getText().toString());
                    usuario.setCorreo(correoV.getText().toString());
                    usuario.setAvatar(avatar);
                    usuario.setTelefono(telefonoV.getText().toString());
                    usuario.setCurso(estudiosV.getText().toString());
                    usuario.setDni(dniV.getText().toString());
                    usuario.setDireccion(direccionV.getText().toString());
                    usuario.setNotas(notasV.getText().toString());
                    usuario.setCentroAcademico(centroEstudiosV.getText().toString());
                    usuario.setId(id);
                    Controlador.getInstancia().ejecutaComando(ListaComandos.EDITAR_USUARIO, usuario);
                    Controlador.getInstancia().ejecutaComando(ListaComandos.LISTADO_USUARIOS, null);
                }
            });

            infoPadreV.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog a = createInfoProgenitoresDialogo("padre", inflater);
                    a.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    a.show();
                }
            });

            infoMadreV.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog a = createInfoProgenitoresDialogo("madre", inflater);
                    a.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    a.show();
                }
            });

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
            if(avatar != null && !avatar.equals(""))
                avatarV.setImageBitmap(BitmapFactory.decodeFile(avatar));
            else
                avatarV.setImageResource(R.drawable.avatar);

            return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == CAMARA) {
                Bitmap imagen = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                imagen.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                avatarV.setImageBitmap(imagen);
                avatar = destination.getPath();
            } else if (requestCode == SELECCIONAR_GALERIA) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                CursorLoader cursorLoader = new CursorLoader(Manager.getInstance().getContext(),
                        selectedImageUri, projection, null, null, null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                avatarV.setImageBitmap(bm);
                avatar=selectedImagePath;
            }
        }
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

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Cambiar info padre o madre
                TransferUsuarioT editUser = new TransferUsuarioT();
                editUser.setId(id);
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
                Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_USUARIO, id);
            }
        });
        builder.setNegativeButton("Cancelar",
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
        Log.e("testJL", "Se mete en el del fragment");
        switch (item.getItemId()) {
            case R.id.tareasUsuario:
                Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_TAREAS, id);
                break;
            case R.id.retoUsuario:
                Log.e("testJL", "El id del usuario dueño del reto es " + id);
                Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_RETO, id);
                break;
            case R.id.eventosUsuario:
                /*FragmentDetalleUsuarioEvento fragmentEventoUsuario = new FragmentDetalleUsuarioEvento();
                Manager.getInstance().getFragmentManager().beginTransaction().replace(R.id.FrgDetalle, fragmentEventoUsuario).commit();
                */
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

                Manager.getInstance().getContext().startActivity(emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                ///////////////////////////////////////////////////////////////////////////////////////////
                break;

            case R.id.eliminarUsuario:
                Log.e("testJL", "va a la fun externa " + id + nombre + correo + centroEstudios);
                eliminaUsuario();
                /*
                Log.e("testJL", "entra en eliminar usuario con el id " + id);
                TransferUsuarioT consultarU = new TransferUsuarioT();
                consultarU.setId(id);
                Controlador.getInstancia().ejecutaComando(ListaComandos.ELIMINAR_USUARIO, consultarU);
                Controlador.getInstancia().ejecutaComando(ListaComandos.LISTADO_USUARIOS, null);*/
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void eliminaUsuario(){
        Log.e("testJL", "entra en eliminar usuario con el id " + id + nombre + correo + centroEstudios);
        TransferUsuarioT consultarU = new TransferUsuarioT();
        consultarU.setId(id);
        Controlador.getInstancia().ejecutaComando(ListaComandos.ELIMINAR_USUARIO, consultarU);
        Controlador.getInstancia().ejecutaComando(ListaComandos.LISTADO_USUARIOS, null);
    }


}
