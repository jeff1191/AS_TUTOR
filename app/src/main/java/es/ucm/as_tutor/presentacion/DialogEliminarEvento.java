package es.ucm.as_tutor.presentacion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import es.ucm.as_tutor.R;

/**
 * Created by Jeffer on 13/04/2016.
 */
public class DialogEliminarEvento extends DialogFragment {

    public static DialogEliminarEvento newInstance(String evento) {
        DialogEliminarEvento frag = new DialogEliminarEvento();
        Bundle args = new Bundle();
        args.putString("evento", evento);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
               // .setIcon(R.drawable.androidhappy)
                .setTitle("Eliminar Evento")
                .setMessage("¿Desea eliminar el evento: " + getArguments().getString("evento")+"?")
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Enviaria datos al controlador (ID seguramente)
                        Toast.makeText(getActivity(), "Evento eliminado", Toast.LENGTH_SHORT).show();
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                }).create();
    }
}
