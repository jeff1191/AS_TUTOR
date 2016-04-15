package es.ucm.as_tutor.presentacion.vista.main;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;

/**
 * Created by msalitu on 15/04/2016.
 */

public class Manager {
    private static Manager ourInstance = new Manager();
    private Activity activity;

    // Metodo que se utiliza desde los servicios de aplicacion
    public static Manager getInstance() {
        return ourInstance;
    }

    // Constructor privado del singleton
    private Manager() { }

    // Metodo que cambia la activity, se utiliza desde las propias activities
    public void setActivity(Activity miActivity){
        activity = miActivity;
    }

    // Metodo que devuelve el context, se utiliza desde los servicios de aplicacion
    public Context getContext(){
        return activity.getApplicationContext();
    }

    // Metodo que devuelve el FragmentManager, se utiliza desde el dispatcher
    public FragmentManager getFragmentManager(){
        return activity.getFragmentManager();
    }
}
