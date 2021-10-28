/*
Nombres: Giuliano Bardecio [256113] y Vittorio Caiafa [252295]
 */
package consolalinux;

import java.util.*;

public class User_ {

    private String name;
    private String password;
    private ArrayList<String> listaComandos;
    private Boolean isRoot;

    public User_(String name) {
        this.name = name;
        this.password = "";
        listaComandos = new ArrayList<>();
        isRoot = false;
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

    public Boolean isRoot() {
        return this.isRoot;
    }

    public void setRoot(Boolean is) {
        isRoot = is;
    }

}
