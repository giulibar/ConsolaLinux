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
    private ArrayList<Folder_> folderList;
    private String contenido;
    final String fechaYHora;

    public Folder_(String nombre_, String _tipo) {
        nombre = nombre_;
        tipo = _tipo;
        permisoUsuario = new HashMap<String, Integer>();
        folderList = new ArrayList<Folder_>();
        propietario = null;
        fechaYHora = "" + new Date();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPermisos(String nombre) {
        int permiso = permisoUsuario.get(nombre);
        return permiso;

    }

    public String getPropietario() {
        return propietario;
    }

    public ArrayList<Folder_> getFolders() {
        return this.folderList;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getContenido() {
        return this.getContenido();
    }

    public String getFechaYHora() {
        return this.fechaYHora;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Folder_ buscarFolder(String nombreFolder) {
        for (Folder_ folder : folderList) {
            if (folder.getNombre().equals(nombreFolder)) {
                return folder;
            }
        }
        return null;
    }

    public void quitarArchivo(Folder_ fol) {
        for (Folder_ folder : folderList) {
            if (folder.getNombre().equals(fol.getNombre())) {
                this.folderList.remove(folder);
            }
        }
    }

    public void setPermisos(int permisos, String nombre) {
        this.permisoUsuario.put(nombre, permisos);
    }

    public void setContenido(String contenido) {
        if (this.getTipo().equals("FILE")) {
            this.contenido = contenido;
        }
    }

    public void addFolder(Folder_ folder) {
        if (this.getTipo().equals("FOLDER")) {
            this.folderList.add(folder);
        }
    }

    public void setPropietario(String nombre) {
        this.propietario = nombre;
    }

}
