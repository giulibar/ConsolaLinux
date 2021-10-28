/*
Nombres: Giuliano Bardecio [256113] y Vittorio Caiafa [252295]
 */
package consolalinux;

import java.util.*;

public class Folder_ {

    private String nombre;
    private String tipo;
    private Map<String, Integer> permisoUsuario;
    // CLAVE: nombre del usuario
    // VALOR: permisos  0:Ninguno  1:Leer  2:Escribir  3:Leer y escribir
    private String propietario;
    private Map<String, Folder_> folderList;
    private ArrayList<String> nombresFolClave; // necesito un array con todas las claves si quiero obtenerlos todos del hashmap()
    private String contenido;
    final String fechaYHora;

    public Folder_(String nombre_, String _tipo) {
        nombre = nombre_;
        tipo = _tipo;
        permisoUsuario = new HashMap<String, Integer>();
        propietario = "";
        folderList = new HashMap<String, Folder_>();
        nombresFolClave = new ArrayList<>();
        contenido = "";
        fechaYHora = "" + new Date();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPermisos(String nombre) {
        if (this.folderList.containsKey(nombre)){
            return this.permisoUsuario.get(nombre);
        }else{
            return -1;
        }
    }

    public String getPropietario() {
        return this.propietario;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getContenido() {
        return this.contenido;
    }

    public String getFechaYHora() {
        return this.fechaYHora;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // devuelve el Folder_ de nombre "nombreFolder" si lo contiene, o null sino lo contiene
    public Folder_ buscarFolder(String nombreFolder) {
        Folder_ fol = folderList.get(nombreFolder);
        if (fol != null) {
            return fol;
        } else {
            return null;
        }
    }

    public void quitarArchivo(Folder_ fol) {
        Folder_ fol1 = folderList.get(fol.getNombre());
        if (fol1 != null) {
            folderList.put(fol.getNombre(), null);
        }
    }

    public void setPermisos(int permisos, String nombre) {
        this.permisoUsuario.put(nombre, permisos);
    }

    public void setContenido(String contenido) {
        if (this.getTipo().equals("FILE")) {
            this.contenido += "\n" + contenido;
        }
    }

    public void addFolder(Folder_ folder) {
        if (this.getTipo().equals("FOLDER")) {
            this.folderList.put(folder.getNombre(), folder);
            this.nombresFolClave.add(folder.getNombre());
        }
    }
    
    // devuelve un array con todos los Folder_ que contiene
    public ArrayList<Folder_> getFolders(){
        ArrayList<Folder_> folders = new ArrayList<>();
        for (String nombres : this.nombresFolClave){
            folders.add(this.buscarFolder(nombres));
        }
        return folders;
    }

    public void setPropietario(String nombre) {
        this.propietario = nombre;
    }

}
