package es.ucm.as_tutor.presentacion.vista.usuario.reto;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import es.ucm.as_tutor.presentacion.controlador.Controlador;
import es.ucm.as_tutor.presentacion.controlador.ListaComandos;

/**
 * Created by Jeffer on 13/04/2016.
 */
public class DialogEliminarReto extends DialogFragment {

    public static DialogEliminarReto newInstance(int idReto, String nombreReto, int idUsuario) {
        DialogEliminarReto frag = new DialogEliminarReto();
        Bundle args = new Bundle();
        args.putInt("idReto", idReto);
        args.putString("nombreReto", nombreReto);
        args.putInt("idUser", idUsuario);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
               // .setIcon(R.drawable.androidhappy)
                .setTitle("Eliminar Reto")
                .setMessage("¿Desea eliminar el reto: " + getArguments().getString("nombreReto")+"?")
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Reto eliminado con ID: " + getArguments().getInt("idReto"), Toast.LENGTH_SHORT).show();
                        Controlador.getInstancia().ejecutaComando(ListaComandos.ELIMINAR_RETO, getArguments().getInt("idReto"));
                        Controlador.getInstancia().ejecutaComando(ListaComandos.CONSULTAR_RETO, getArguments().getInt("idUser"));
                    }
                })

                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                }).create();
    }
}
