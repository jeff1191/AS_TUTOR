package es.ucm.as.presentacion.vista.evento;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import es.ucm.as.negocio.suceso.TransferEvento;
import es.ucm.as.presentacion.controlador.Controlador;
import es.ucm.as.presentacion.controlador.ListaComandos;

/**
 * Created by Jeffer on 13/04/2016.
 */
public class DialogEliminarEvento extends DialogFragment {

    public static DialogEliminarEvento newInstance(int idEvento, String nombreEvento) {
        DialogEliminarEvento frag = new DialogEliminarEvento();
        Bundle args = new Bundle();
        args.putInt("idEvento", idEvento);
        args.putString("nombreEvento", nombreEvento);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
               // .setIcon(R.drawable.androidhappy)
                .setTitle("Eliminar Evento")
                .setMessage("¿Desea eliminar el evento: " + getArguments().getString("nombreEvento")+"?")
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Enviaria datos al controlador (ID seguramente)
                        TransferEvento delEvento = new TransferEvento();
                        delEvento.setId(getArguments().getInt("idEvento"));
                        Toast.makeText(getActivity(), "Evento eliminado CON ID: " + getArguments().getInt("idEvento"), Toast.LENGTH_SHORT).show();
                        Controlador.getInstancia().ejecutaComando(ListaComandos.ELIMINAR_EVENTO,delEvento);
                        Controlador.getInstancia().ejecutaComando(ListaComandos.LISTADO_EVENTOS,null);
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                }).create();
    }
}
