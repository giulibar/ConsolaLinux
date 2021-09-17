/*
Nombres: Giuliano Bardecio [256113] y Vittorio Caiafa [252295]
*/

package consolalinux;

import java.util.*;

public class User_ {
    
    private String name;
    private String password;
    private ArrayList<Folder_> folder;
    
    public String getName(){
        return this.name;
    } 
    public String getPassword(){
        return this.password;
    }
    
    public ArrayList<Folder_> getFiles() { // cambiar
        return folder;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setFiles(ArrayList<Folder_> files) { // cambiar
        this.folder = files;
    }
    
}