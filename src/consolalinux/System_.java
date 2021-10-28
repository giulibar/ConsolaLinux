/*
Nombres: Giuliano Bardecio [256113] y Vittorio Caiafa [252295]
 */
package consolalinux;

import java.util.*;

public class System_ {

    private ArrayList<User_> userList;
    private Folder_ foldersList;
    private User_ loggedUser;
    private String rute;

    public System_() {
        userList = new ArrayList<>();
        foldersList = new Folder_("/", "FOLDER"); // creo el directorio inicial como si fuera una carpeta
        loggedUser = null;
        rute = "/";
    }

    // retorna la ruta actual
    public String getRute() {
        return rute;
    }

    // avanza o retrocede en la ruta actual
    public void setRoute(String rut) {
        String aux[] = this.rute.split("/");
        if (!rut.equals("..")) {
            if (aux.length == 0) {
                this.rute += rut;
            } else {
                this.rute += "/" + rut;
            }
        } else {
            if (aux.length != 0) {
                this.rute = "";
                for (int i = 1; i < aux.length - 1; i++) {
                    this.rute += "/" + aux[i];
                }
                if (this.rute.equals("")){ this.rute = "/";}
            }
        }
    }
    
    // si hubo un error con el seteo de la ruta me deja poner una "a fuerza"
    public void setDirectRoute(String rut){ this.rute = rut;}

    // retorna el Folder_ que esta a lo ultimo de la ruta q le pase, devuelve null si no lo encontro
    public Folder_ ultimoFolder(String ruta) {
        String rute_[] = ruta.split("/");
        Folder_ primerFolder = this.getHomeFolder(); // empieza en 1 o 0?
        for (int i = 1; i < rute_.length && primerFolder != null; i++) {
            Folder_ fol = primerFolder.buscarFolder(rute_[i]);
            primerFolder = fol;
        }
        return primerFolder;
    }

    // retorna una lista de los usuarios registrados
    public ArrayList<User_> getUserList() {
        return userList;
    }

    // retorna el Folder_ home , "/"
    public Folder_ getHomeFolder() {
        return foldersList;
    }

    // retorna un User_ de nombre "name"
    public User_ getUser(String name) {
        User_ user = null;
        for (User_ userInList : userList) {
            if (userInList.getName().equals(name)) {
                user = userInList;
            }
        }
        return user;
    }

    // retorna el user q esta logueado, null si no hay nadie logueado
    public User_ getLoggedUser() {
        return this.loggedUser;
    }

    // agregua un user al sistema
    public void addUser(User_ user) {
        userList.add(user);
    }

    // setea el user que esta logueado en el sistema
    public void setLoggedUser(User_ user) {
        loggedUser = user;
    }
}
