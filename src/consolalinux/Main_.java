/*
Nombres: Giuliano Bardecio [256113] y Vittorio Caiafa [252295]
 */
package consolalinux;

import java.util.*;

public class Main_ {

    public static void main(String[] args) {
        System.out.println("hola giuli");
        System_ system = new System_();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Se habilitó la consola.\n");
        String textoEscrito = entrada.nextLine();
        String comando[] = textoEscrito.split(" ");
        boolean primeraVez = true;
        while (!comando[0].equals("fin")) {
            if (primeraVez) {
                primeraVez = false;
            } else {
                textoEscrito = entrada.nextLine();
                comando = textoEscrito.split(" ");
            }
            switch (comando[0]) {
                case "useradd":
                    User_ user = new User_();
                    user.setName(comando[1]);
                    System.out.println("Se agregó a " + user.getName() + "\n");
                    system.addUser(user);
                    break;
                case "passwd": // preguntar de nuevo las passwords o salir     ||     si el usuario no existe, me avisa que no existe?
                    boolean sonIguales = false;
                    User_ user1 = system.getUser(comando[1]);
                    if (user1 == null) {
                        System.out.println("Ese usuario no existe!\n");
                    } else {
                        while (!sonIguales) {
                            System.out.print("Ingrese la password: ");
                            String password1 = entrada.nextLine();
                            System.out.print("Vuelva a ingresarla: ");
                            String password2 = entrada.nextLine();
                            if (password1.equals(password2)) {
                                user1.setPassword(password1);
                                sonIguales = true;
                                System.out.println("Su password quedó lista!\n");
                            } else {
                                System.out.println("Password incorrecta!\n");
                            }
                        }
                    }
                    break;
                case "su":
                    User_ user2 = system.getUser(comando[1]);
                    if (user2 != null) {
                        System.out.print("Ingrese la password de " + comando[1] + ": ");
                        String password = entrada.nextLine();
                        while (!password.equals(user2.getPassword())) { // preguntar de nuevo la password o salir
                            System.out.println("Password incorrecta, vuelva a intentarlo!\n");
                            System.out.print("Ingrese la password de " + comando[1] + ": ");
                            password = entrada.nextLine();
                        }
                        system.setLoggedUser(user2);
                        System.out.println("Se logueó correctamente a " + user2.getName() + "!\n");
                    } else {
                        System.out.println("Ese usuario no existe!\n");
                    }
                    break;
                case "whoami":
                    User_ user3 = system.getLoggedUser();
                    if (user3 != null) {
                        System.out.println(user3.getName());
                    } else {
                        System.out.println("No hay ningun usuario logueado");
                    }
                    break;
                case "pwd":
                    break;
                case "mkdir":
                    break;
                case "touch":
                    break;
                case "echo":
                    break;
                case "mv":
                    break;
                case "cp":
                    break;
                case "cat":
                    break;
                case "rm":
                    break;
                case "cd":
                    break;
                case "ls -l":
                    break;
                case "history":
                    break;
                case "1er comando | 2do comando":
                    break;
                case "history | grep":
                    break;
                case "chmod":
                    break;
                case "chown":
                    break;
            }
        }
        

    }

}
