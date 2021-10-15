/*
Nombres: Giuliano Bardecio [256113] y Vittorio Caiafa [252295]
 */
package consolalinux;

import java.util.*;

public class Main_ {

    public static void main(String[] args) {
        System_ system = new System_();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Se habilito la consola.\n");
        String textoEscrito = entrada.nextLine();
        String comando[] = textoEscrito.split(" ");
        boolean primeraVez = true;
        while (!comando[0].equals("fin")) {
            if (primeraVez) {
                primeraVez = false;

                User_ giuli = new User_("Giuli"); // cargo en sistema al iniciar
                giuli.setPassword("admin"); // cargo en sistema al iniciar
                system.addUser(giuli); // cargo en sistema al iniciar

                User_ vito = new User_("Vito"); // cargo en sistema al iniciar
                vito.setPassword("admin"); // cargo en sistema al iniciar
                system.addUser(vito); // cargo en sistema al iniciar

                system.setLoggedUser(giuli); // cargo en sistema al iniciar

            } else {
                textoEscrito = entrada.nextLine();
                comando = textoEscrito.split(" ");
            }
            switch (comando[0]) {
                case "useradd":
                    if (comando.length == 1) {
                        System.out.println("Debe ingresar el nombre del usuario");
                    } else {
                        User_ user = new User_(comando[1]);
                        System.out.println("Se agregó a " + user.getName() + "\n");
                        system.addUser(user);
                    }
                    break;
                case "passwd": // preguntar de nuevo las passwords o salir
                    if (comando.length == 1) {
                        System.out.println("Debe ingresar el nombre del usuario a setear la password");
                    } else {
                        User_ user1 = system.getUser(comando[1]);
                        if (user1 == null) {
                            System.out.println("Ese usuario no existe!\n");
                        } else {
                            boolean sonIguales = false;
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
                    }

                    break;
                case "su":
                    if (comando.length == 1) {
                        System.out.println("Debe ingresar el nombre del usuario");
                    } else {
                        User_ user2 = system.getUser(comando[1]);
                        if (user2 != null && user2.getPassword() != null) {
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
                            System.out.println("Ese usuario no existe o su contraseña no fue seteada!\n");
                        }
                    }
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addCommand("su");
                    }
                    break;
                case "whoami":
                    User_ user3 = system.getLoggedUser();
                    if (user3 != null) {
                        System.out.println(user3.getName());
                    } else {
                        System.out.println("No hay ningun usuario logueado");
                    }
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addCommand("whoami");
                    }
                    break;
                case "pwd":
                    System.out.println(system.getRute());
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addCommand("pwd");
                    }
                    break;
                case "mkdir":
                    Folder_ archivoMkdir = new Folder_();
                    archivoMkdir.setNombre(comando[1]);
                    System.out.print("Que permisos desea?: ");
                    int permisos = Integer.parseInt(entrada.nextLine()); // checkear que este dentro del rango deseado?
                    archivoMkdir.setPermisos(permisos);
                    System.out.println("Su repositorio se creó correctamente!\n");
                    User_ user5 = system.getLoggedUser();
                    user5.addCommand("pwd");
                    break;
                case "touch":
                    Folder_ file = new Folder_();
                    file.setNombre("nuevo.txt");
                    file.setPermisos(7);
                    System.out.println("Su archivo se creó correctamente!\n");
                    break;
                case "echo":
                    String textoAIngresar = comando[1];
                    String nombreArchivo = comando[4];
                    break;
                case "mv":
                    break;
                case "cp":
                    break;
                case "cat":
//                    boolean mostre = false;
//                    for (!mostre && Folder_  folder  {
//                        
//                    }
//                    : system.getFolderList()
//                     
//                        ){
//                        if (folder.getNombre().equals(this.getFolder().getNombre())) {
//                            System.out.println("El contenido del archivo es:");
//                            System.out.println(folder.getContenido());
//                            mostre = true;
//                        }
//                    }
                    break;
                case "rm": // anda?
//                    boolean borre = false;
//                    for (!borre && Folder_  folder  {
//                        
//                    }
//                    : system.getFolderList()
//                     
//                        ){
//                        if (this.getNombre().equals(folder.getNombre())) {
//                            delete this.getFolder();
//                            borre = true;
//                        }
//                    }
                    break;
                case "cd":
                    break;
                case "ls -l":
                    break;
                case "history":
                    int largoComando = comando.length;
                    if (largoComando == 4) {
                        if (comando[1].equals("|") && comando[2].equals("grep")) {
                            User_ user = system.getLoggedUser();
                            for (String comand : user.getCommands()) {
                                if (comand.equals(comando[3])) {
                                    System.out.println("Se ejecuto ese comando.");
                                } else {
                                    System.out.println("No se ejecuto ese comando.");
                                }
                            }
                        }
                    } else if (largoComando == 1) {
                        User_ user = system.getLoggedUser();
                        if (user != null) {
                            for (String comand : user.getCommands()) {
                                System.out.println(comand);
                            }
                            user.addCommand("history");
                        }
                    }

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
