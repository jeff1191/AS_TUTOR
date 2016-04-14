package es.ucm.as_tutor.presentacion.vista.evento;

/**
 * Created by Jeffer on 11/04/2016.
 */
public class ItemUsuarioEvento {
    private int id;
    private String nombre;
    private String tipo;

    public ItemUsuarioEvento(int pID, String nombre,String tipo) {
        id = pID;
        this.nombre=nombre;
        this.tipo=tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }

}