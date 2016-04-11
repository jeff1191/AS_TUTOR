package es.ucm.as_tutor.presentacion;

/**
 * Created by Jeffer on 11/04/2016.
 */
public class ItemUsuarioEvento {
    private int id;
    private String nombre;
    private String description;

    public ItemUsuarioEvento(int pID, String nombre, String descripcion) {
        id = pID;
        this.nombre=nombre;
        description = descripcion;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}