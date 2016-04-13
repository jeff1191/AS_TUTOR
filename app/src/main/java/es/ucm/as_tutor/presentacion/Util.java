package es.ucm.as_tutor.presentacion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;

/**
 * Created by Jeffer on 12/04/2016.
 */
public class Util {

    public static void mostrarProgreso(Activity activity, String titulo, String descripcion, int segundos){
        final ProgressDialog progress = new ProgressDialog(activity);
        progress.setTitle(titulo);
        progress.setMessage(descripcion);
        progress.setCancelable(false);
        progress.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() { //Pasan 2 segundos
            public void run() {
                progress.dismiss();
            }
        }, segundos*1000);
    }
}
