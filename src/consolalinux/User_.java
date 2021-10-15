/*
Nombres: Giuliano Bardecio [256113] y Vittorio Caiafa [252295]
 */
package consolalinux;

import java.util.*;

public class User_ {

    private String name;
    private String password;
    private ArrayList<String> folder;
    private ArrayList<String> listaComandos;

    public User_(String name) {
        this.name = name;
        this.password = null;
        folder = new ArrayList<>();
        listaComandos = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public ArrayList<String> getFolders() {
//        return folder;
//    }

//    public void addFolder(String folder) { // cambiar
//        folder.add(files);
//    }
//    
//    public void setFiles(String files) {
//        folder.add(files);
//    }
//    
     public void addCommand(String comando) {
        listaComandos.add(comando);
    }
     
    public ArrayList<String> getCommands() {
        return listaComandos;
    }

}
