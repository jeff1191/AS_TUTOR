package es.ucm.as_tutor;

/**
 * Created by Jeffer on 19/03/2016.
 */
public class Usuario
{
    private String nombre;
    private String desc;
    private String texto;
    private String tel;
    private String dni;

    public Usuario(String de, String desc, String texto,String tel, String dni){
        this.nombre = de;
        this.desc = desc;
        this.texto = texto;
        this.tel=tel;
        this.dni=dni;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }



    public String getNombre(){
        return nombre;
    }
    public void setNombre( String nombre){
        this.nombre=nombre;
    }
    public String getDesc(){
        return desc;
    }

    public String getTexto(){
        return texto;
    }
}