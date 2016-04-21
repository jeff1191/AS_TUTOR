package es.ucm.as_tutor.presentacion.vista.usuario;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import es.ucm.as_tutor.R;
import es.ucm.as_tutor.negocio.usuario.TransferUsuarioT;
import es.ucm.as_tutor.negocio.utils.Perfil;
import es.ucm.as_tutor.presentacion.controlador.Controlador;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;
import es.ucm.as_tutor.presentacion.vista.main.BlankFragment;
import es.ucm.as_tutor.presentacion.vista.main.Manager;

public class FragmentDetalleNuevoUsuario extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 3;
    private static final int SELECCIONAR_GALERIA = 2;
    private static final int CAMARA = 1;

    private EditText nombreV;
    private Spinner perfilV;
    private EditText dniV;
    private EditText direccionV;
    private EditText telefonoV;
    private EditText correoV;
    private EditText centroEstudiosV;
    private EditText estudiosV;
    private EditText cursoV;
    private EditText notasV;
    private TextView puntuacionV;
    private ImageView avatarV;
    private TextView nombrePadreV;
    private TextView nombreMadreV;
    private TextView telPadreV;
    private TextView telMadreV;
    private TextView correoPadreV;
    private TextView correoMadreV;
    private Button aceptar;
    private Button cancelar;

    public FragmentDetalleNuevoUsuario(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detalle_nuevo_usuario, container, false);

            nombreV = (EditText) rootView.findViewById(R.id.nuevoNombre);
            perfilV = (Spinner) rootView.findViewById(R.id.nuevoPerfil);
            dniV = (EditText) rootView.findViewById(R.id.nuevoDni);
            direccionV = (EditText) rootView.findViewById(R.id.nuevaDireccion);
            telefonoV = (EditText) rootView.findViewById(R.id.nuevoTelefono);
            correoV = (EditText) rootView.findViewById(R.id.nuevoCorreo);
            centroEstudiosV = (EditText) rootView.findViewById(R.id.nuevoColegio);
            estudiosV = (EditText) rootView.findViewById(R.id.nuevoEstudios);
            notasV = (EditText) rootView.findViewById(R.id.nuevoNotas);
            avatarV = (ImageView) rootView.findViewById(R.id.nuevaImagenUsuario);
            nombrePadreV = (TextView) rootView.findViewById(R.id.nuevoNombrePadre);
            nombreMadreV = (TextView) rootView.findViewById(R.id.nuevoNombreMadre);
            telPadreV = (TextView) rootView.findViewById(R.id.nuevoTelefonoPadre);
            telMadreV = (TextView) rootView.findViewById(R.id.nuevoTelefonoMadre);
            correoPadreV = (TextView) rootView.findViewById(R.id.nuevoCorreoPadre);
            correoMadreV = (TextView) rootView.findViewById(R.id.nuevoCorreoMadre);
            aceptar = (Button) rootView.findViewById(R.id.botonAceptarNuevoU);
            cancelar = (Button) rootView.findViewById(R.id.botonCancelarNuevoU);

            avatarV.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final CharSequence[] items = { "Hacer foto", "Elegir de la galeria", "Imagen por defecto" };
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog
                            .Builder(Manager.getInstance().getContext());
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
                                dialog.dismiss();
                            }
                        }
                    });
                    builder.show();
                }
            });

            aceptar.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransferUsuarioT usuario = new TransferUsuarioT();
                    usuario.setNombre(nombreV.getText().toString());
                    usuario.setCorreo(correoV.getText().toString());
                    usuario.setAvatar(avatarV.toString());
                    usuario.setTelefono(telefonoV.getText().toString());
                    usuario.setPuntuacion(10);
                    usuario.setPuntuacionAnterior(10);
                    usuario.setCurso(estudiosV.getText().toString());
                    usuario.setDni(dniV.getText().toString());
                    usuario.setDireccion(direccionV.getText().toString());
                    usuario.setTipoPerfil(Perfil.A.toString());
                    usuario.setNotas(notasV.getText().toString());
                    usuario.setNombrePadre(nombrePadreV.getText().toString());
                    usuario.setNombreMadre(nombreMadreV.getText().toString());
                    usuario.setCorreoPadre(correoPadreV.getText().toString());
                    usuario.setCorreoMadre(correoMadreV.getText().toString());
                    usuario.setTelPadre(telPadreV.getText().toString());
                    usuario.setTelMadre(telMadreV.getText().toString());
                    usuario.setCentroAcademico(centroEstudiosV.getText().toString());
                    usuario.setReto(null);
                    usuario.setCodigoSincronizacion("111"); // Esto hay que mejorarlo

                    Controlador.getInstancia().ejecutaComando(ListaComandos.CREAR_USUARIO, usuario);
                    Controlador.getInstancia().ejecutaComando(ListaComandos.LISTADO_USUARIOS, null);
                    BlankFragment fragmentBlank = new BlankFragment();
                    Manager.getInstance().getFragmentManager().beginTransaction()
                            .replace(R.id.FrgDetalle, fragmentBlank).commit();
                }
            });

            cancelar.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BlankFragment fragmentBlank = new BlankFragment();
                    Manager.getInstance().getFragmentManager().beginTransaction()
                            .replace(R.id.FrgDetalle, fragmentBlank).commit();
                }
            });

        return rootView;
    }


}
