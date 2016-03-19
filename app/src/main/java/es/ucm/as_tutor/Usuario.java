package es.ucm.as_tutor;

/**
 * Created by Jeffer on 19/03/2016.
 */
public class Usuario
{
    private String nombre;
    private String desc;
    private String texto;

    public Usuario(String de, String desc, String texto){
        this.nombre = de;
        this.desc = desc;
        this.texto = texto;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDesc(){
        return desc;
    }

    public String getTexto(){
        return texto;
    }
}