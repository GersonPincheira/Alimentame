package cl.telematica.android.alimentame.POST.Models;

/**
 * Created by Claudio on 30-09-2016.
 */

public class Datos {
    private String nombre;
    private String descripcion;
    private String precio;
    private String state;
    private String latitud;
    private String longitud;
    private String imagen;

    public Datos(String nombre, String descripcion, String precio, String state, String latitud, String longitud, String imagen){
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setPrecio(precio);
        this.setState(state);
        this.setLatitud(latitud);
        this.setLongitud(longitud);
        this.setImagen(imagen);
    }
    public String getNombre(){return nombre;}
    public String getDescripcion(){return descripcion;}
    public String getPrecio() {return precio;}
    public String getState(){return state;}
    public String getLatitud(){return latitud;}
    public String getLongitud(){return longitud;}
    public String getImagen() {return imagen;}

    public void setNombre(String nombre){this.nombre=nombre;}
    public void setDescripcion(String descripcion) {this.descripcion=descripcion;}
    public void setPrecio(String precio)  {this.precio=precio;}
    public void setState(String state){this.state=state;}
    public void setLatitud(String latitud){this.latitud=latitud;}
    public void setLongitud(String longitud) {this.longitud=longitud;}
    public void setImagen(String imagen)  {this.imagen=imagen;}
}
