/*
Nombres: Giuliano Bardecio [256113] y Vittorio Caiafa [252295]
*/

package consolalinux;

public class Folder_ {

    private String nombre;
    private int permisos; // numeros del 0-3
    private String contenido;
    private String propietario;
    
    public Folder_() {
        nombre = null;
        permisos = 0;
        contenido = null;
        propietario = null;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getPermisos() {
        return permisos;
    }
    
    public String getContenido() {
        return contenido;
    }
    
    public String getPropietario() {
        return propietario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPermisos(int permisos) {
        this.permisos = permisos;
    }
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public void setPropietario(String nuevoNombrePropietario) {
        this.propietario = nuevoNombrePropietario;
    }
    
}
