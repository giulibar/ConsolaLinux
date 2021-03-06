/*
Nombres: Giuliano Bardecio [256113] y Vittorio Caiafa [252295]
 */
package consolalinux;

import java.util.*;

public class User_ {

    private String name;
    private String password;
    private ArrayList<String> listaComandos;
    private boolean root; //

    public User_(String name) {
        this.name = name;
        this.password = null;
        listaComandos = new ArrayList<>();
        root = false;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addComando(String comando) {
        listaComandos.add(comando);
    }

    public ArrayList<String> getListaComandos() {
        return listaComandos;
    }

    public boolean isRoot() {
        return this.root;
    }

    public void setRoot(boolean is) {
        root = is;
    }

}
