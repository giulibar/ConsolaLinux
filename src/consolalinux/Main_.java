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
                case "passwd":
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
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("passwd");
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
                            while (!password.equals(user2.getPassword())) {
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
                        system.getLoggedUser().addComando("su");
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
                        system.getLoggedUser().addComando("whoami");
                    }
                    break;
                case "pwd":
                    System.out.println(system.getRute());
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("pwd");
                    }
                    break;
                case "mkdir":
                    Folder_ archivoMkdir = new Folder_();
                    archivoMkdir.setNombre(comando[1]);
                    System.out.print("Que permisos desea?: ");
                    int permisos = Integer.parseInt(entrada.nextLine());
                    while (permisos < 0 || permisos > 3) {
                        System.out.println("ERROR: solo se permiten permisos de 0-3.");
                        System.out.print("¿Que permisos desea?: ");
                        permisos = Integer.parseInt(entrada.nextLine());
                    }
                    archivoMkdir.setPermisos(permisos);
                    System.out.println("Su repositorio se creó correctamente!\n");
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("mkdir");
                    }
                    break;
                case "touch":
                    Folder_ file = new Folder_();
                    file.setNombre("nuevo.txt");
                    file.setPermisos(3);
                    System.out.println("Su archivo se creó correctamente!\n");
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("touch");
                    }
                    break;
                case "echo":
                    int largo = comando.length;
                    String textoAIngresar = comando[1];
                    for (int i = 2; i < largo - 2; i++) {
                        textoAIngresar += " " + comando[i];
                    }
//                    for (int j = 0; j < textoAIngresar; j++){
//                        if (textoAIngresar.charAt(j).equals("-")){
//                            
//                        }
//                    }
                    // textoAIngresar.replaceAll('"', ''); // faltaria poder quitarle los parentesis a ese texto a ingresar al final del contenido
                    String nombreArchivo = comando[4];
                    boolean encontre = false;
                    for (Folder_ folder : system.getFolderList()) {
                        if (!encontre && comando[1].equals(folder.getNombre())) {
                            encontre = true;
                            folder.setContenido(folder.getContenido() + "\n" + textoAIngresar);
                        }
                    }
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("echo");
                    }
                    break;
                case "mv":

                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("mv");
                    }
                    break;
                case "cp":

                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("cp");
                    }
                    break;
                case "cat":
                    boolean mostre = false;
                    for (Folder_ folder : system.getFolderList()) {
                        if (!mostre && comando[1].equals(folder.getNombre())) {
                            System.out.println(folder.getContenido());
                            mostre = true;
                        }
                    }
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("cat");
                    }
                    break;
                case "rm":
                    boolean borre = false;
                    for (Folder_ folder : system.getFolderList()) {
                        if (!borre && comando[1].equals(folder.getNombre())) {
                            folder.setContenido(null);
                            system.getFolderList().remove(folder);
                            borre = true;
                        }
                    }
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("rm");
                    }
                    break;
                case "cd":
                    break;
                case "ls":
                    if (comando[1].equals("-l")) {
                        System.out.println("Propietario:");
                        // ultima visitada?
                        System.out.println("Carpetas:");
                        // agregar estructura de organizacion de carpetas y archivos
                        // mostrar todas las carpetas o archivos de donde estoy posicionado
                    }
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("ls");
                    }
                    break;
                case "history":
                    int largoComando = comando.length;
                    if (largoComando == 4) {
                        if (comando[1].equals("|") && comando[2].equals("grep")) {
                            User_ user = system.getLoggedUser();
                            for (String comand : user.getListaComandos()) {
                                if (comand.equals(comando[3])) {
                                    System.out.println("Se encontro ese comando.");
                                } else {
                                    System.out.println("No se encontro ese comando.");
                                }
                            }
                        }
                    } else if (largoComando == 1) {
                        User_ user = system.getLoggedUser();
                        if (user != null) {
                            for (String comand : user.getListaComandos()) {
                                System.out.println(comand);
                            }
                        }
                    }
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("history");
                    }
                    break;
                case "1erComando | 2doComando":

                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("pipe");
                    }
                    break;
                case "chmod":

                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("chmod");
                    }
                    break;
                case "chown":
                    boolean cambiePropietario = false;
                    boolean existeUsuarioConEseNombre = false;
                    for (User_ u : system.getUserList()) {
                        if (comando[1].equals(u.getName())) {
                            existeUsuarioConEseNombre = true;
                        }
                    }
                    if (existeUsuarioConEseNombre) {
                        for (Folder_ folder : system.getFolderList()) {
                            if (!cambiePropietario && comando[2].equals(folder.getNombre())) {
                                folder.setPropietario(comando[1]);
                                cambiePropietario = true;
                            }
                        }
                    } else {
                        System.out.println("ERROR: No existe un usuario con ese nombre.");
                    }
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("chown");
                    }
                    break;
            }
        }
    }
}
