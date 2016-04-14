package es.ucm.as_tutor.presentacion.controlador;


import es.ucm.as_tutor.presentacion.controlador.imp.DispatcherImp;

/**
 * Created by Jeffer on 02/03/2016.
 */
public abstract class Dispatcher  {
    private static Dispatcher dispatcher;

    public static Dispatcher getInstancia() {
        if (dispatcher == null){
            dispatcher = new DispatcherImp();
        }
        return dispatcher;
    }
    public abstract void dispatch(String comando,Object datos);//el comando será el que este definido en ListaComandos.java
}
