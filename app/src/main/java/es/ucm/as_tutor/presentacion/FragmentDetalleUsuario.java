package es.ucm.as_tutor.presentacion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    private EditText cursoV;
    private EditText notasV;
    private TextView puntuacionV;
    private ImageView avatarV;
    private TextView infoPadreV;
    private TextView infoMadreV;

    private String nombre;
    private String sincronizacion;
    private String perfil;
    private String dni;
    private String direccion;
    private String telefono;
    private String correo;
    private String centroEstudios;
    private String estudios;
    private String curso;
    private String notas;
    private String puntuacion;
    private Integer avatar;
    private String infoPadre;
    private String infoMadre;

    public FragmentDetalleUsuario(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if(bundle != null) {
            nombre = bundle.getString("nombre");
            sincronizacion = bundle.getString("sincronizaciones");
            perfil = bundle.getString("perfiles");
            dni = bundle.getString("dnis");
            direccion = bundle.getString("direcciones");
            telefono = bundle.getString("telefonos");
            correo = bundle.getString("correos");
            centroEstudios = bundle.getString("colegios");
            estudios = bundle.getString("estudios");
            curso = bundle.getString("cursos");
            notas = bundle.getString("notas");
            puntuacion = bundle.getString("puntuaciones");
            avatar = bundle.getInt("avatar");
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
            cursoV = (EditText) rootView.findViewById(R.id.curso);
            notasV = (EditText) rootView.findViewById(R.id.notas);
            puntuacionV = (TextView) rootView.findViewById(R.id.puntuacionUsuario);
            avatarV = (ImageView) rootView.findViewById(R.id.avatar);
            infoPadreV = (TextView) rootView.findViewById(R.id.infoPadre);
            infoMadreV = (TextView) rootView.findViewById(R.id.infoMadre);

            nombreV.setText(nombre);
            sincronizacionV.setText(sincronizacion);
            perfilV.setText(perfil);
            dniV.setText(dni);
            direccionV.setText(direccion);
            telefonoV.setText(telefono);
            correoV.setText(correo);
            centroEstudiosV.setText(centroEstudios);
            estudiosV.setText(estudios);
            cursoV.setText(curso);
            notasV.setText(notas);
            puntuacionV.setText(puntuacion + "/10");
           // avatarV.setImageResource(avatar);

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
