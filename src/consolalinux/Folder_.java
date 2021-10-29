/*
Nombres: Giuliano Bardecio [256113] y Vittorio Caiafa [252295]
 */
package consolalinux;

import java.util.*;

public class Folder_ {

    private String nombre;
    private String tipo;
    final String fechaYHora;
    private String propietario;
    private Map<String, Folder_> folderList;
    private String contenido;
    private Integer permisoPropietario;
    private Integer permisoGrupo;
    private Integer permisoUsuario;
    // VALOR: permisos  0...7 en octal, grupo no lo uso

    public Folder_(String nombre_, String _tipo) {
        nombre = nombre_;
        tipo = _tipo;
        propietario = "";
        folderList = new HashMap<String, Folder_>();
        contenido = "";
        fechaYHora = "" + new Date();
    }

    public String getFechaYHora() {
        return this.fechaYHora;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPropietario() {
        return this.propietario;
    }

    public void setPropietario(String nombre) {
        this.propietario = nombre;
    }

    public int getPermisos(String nombre) {
        if (this.propietario.equals(nombre)) {
            return this.permisoPropietario;
        } else {
            return this.permisoUsuario;
        }
    }

    public void setPermisos(String permisosXYZ) {
        if (permisosXYZ.length() == 3) {
            this.permisoPropietario = Character.getNumericValue(permisosXYZ.charAt(0));
            this.permisoGrupo = Character.getNumericValue(permisosXYZ.charAt(1));
            this.permisoUsuario = Character.getNumericValue(permisosXYZ.charAt(2));
        }
    }

    public String getContenido() {
        return this.contenido;
    }

    public void setContenido(String contenido) {
        if (this.getTipo().equals("FILE")) {
            if (this.contenido.equals("")) {
                this.contenido += contenido;
            } else {
                this.contenido += "\n" + contenido;
            }
        }
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

    public void addFolder(Folder_ folder) {
        if (this.getTipo().equals("FOLDER")) {
            this.folderList.put(folder.getNombre(), folder);
        }
    }

    // devuelve un array con todos los Folder_ que contiene
    public ArrayList<Folder_> getFolders() {
        ArrayList<Folder_> folders = new ArrayList<>();
        for (String key : this.folderList.keySet()) {
            Folder_ foli = this.folderList.get(key);
            if (foli != null) {
                folders.add(foli);
            }
        }
        return folders;
    }

    public void copiarData(Folder_ to) {

    }

    @Override
    public String toString() {
        // TIPO DE ARCHIVO
        String metadata = "";
        if (this.tipo == "FILE") {
            metadata = "-";
        } else {
            metadata = "d";
        }

        // PERMISOS DE PROPIETARIO
        int permProp = this.permisoPropietario;
        switch (permProp) {
            case 0:
                metadata += "---";
                break;
            case 2:
                metadata += "-w-";
                break;
            case 4:
                metadata += "r--";
                break;
            case 6:
                metadata += "rw-";
                break;
        }

        // PERMISOS DE GRUPO
        metadata += "---";

        // PERMISOS DE USUARIO
        int permUsuario = this.permisoUsuario;
        switch (permUsuario) {
            case 0:
                metadata += "---";
                break;
            case 2:
                metadata += "-w-";
                break;
            case 4:
                metadata += "r--";
                break;
            case 6:
                metadata += "rw-";
                break;
        }

        metadata += " " + this.propietario;
        metadata += " " + this.fechaYHora;
        metadata += " " + this.nombre;

        return metadata;
    }

}
