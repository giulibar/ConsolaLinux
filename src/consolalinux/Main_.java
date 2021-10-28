/*
Nombres: Giuliano Bardecio [256113] y Vittorio Caiafa [252295]
 */
package consolalinux;

import java.util.*;

public class Main_ {

    public static void main(String[] args) {
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";
        String ANSI_RESET = "\u001B[0m";

        System_ system = new System_();
        Scanner entrada = new Scanner(System.in);

        System.out.println(ANSI_YELLOW + "Se habilito la consola.\n" + ANSI_RESET);
        System.out.print(ANSI_PURPLE + system.getLoggedUser().getName() + "@DESKTOP" + ANSI_RESET + ":");
        System.out.print(ANSI_CYAN + system.getRute() + ANSI_RESET + "$ ");
        String textoEscrito = entrada.nextLine();
        String comando[] = textoEscrito.split(" ");
        boolean primeraVez = true;
        while (!comando[0].equals("fin")) {
            if (primeraVez) {
                primeraVez = false;
            } else {
                System.out.print(ANSI_PURPLE + system.getLoggedUser().getName() + "@DESKTOP" + ANSI_RESET + ":");
                System.out.print(ANSI_CYAN + system.getRute() + ANSI_RESET + "$ ");
                textoEscrito = entrada.nextLine();
                comando = textoEscrito.split(" ");
            }
            switch (comando[0]) {
                case "useradd":
                    if (comando.length == 1) {
                        System.out.println(ANSI_RED + "Debe ingresar el nombre del usuario" + ANSI_RESET);
                    } else {
                        User_ user = new User_(comando[1]);
                        System.out.println(ANSI_GREEN + "Se agregó a " + user.getName() + "\n" + ANSI_RESET);
                        system.addUser(user);
                    }
                    break;
                case "passwd":
                    if (comando.length == 1) {
                        System.out.println(ANSI_RED + "Debe ingresar el nombre del usuario a setear la password" + ANSI_RESET);
                    } else {
                        User_ user1 = system.getUser(comando[1]);
                        if (user1 == null) {
                            System.out.println(ANSI_RED + "Ese usuario no existe!\n" + ANSI_RESET);
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
                                    System.out.println(ANSI_GREEN + "Su password quedó lista!\n" + ANSI_RESET);
                                } else {
                                    System.out.println(ANSI_RED + "Password incorrecta!\n" + ANSI_RESET);
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
                        System.out.println(ANSI_RED + "Debe ingresar el nombre del usuario" + ANSI_RESET);
                    } else {
                        User_ user2 = system.getUser(comando[1]);
                        if (user2 != null && user2.getPassword() != null) {
                            System.out.print("Ingrese la password de " + comando[1] + ": ");
                            String password = entrada.nextLine();
                            while (!password.equals(user2.getPassword())) {
                                System.out.println(ANSI_RED + "Password incorrecta, vuelva a intentarlo!\n" + ANSI_RESET);
                                System.out.print("Ingrese la password de " + comando[1] + ": ");
                                password = entrada.nextLine();
                            }
                            system.setLoggedUser(user2);
                            System.out.println(ANSI_GREEN + "Se logueó correctamente a " + user2.getName() + "!\n" + ANSI_RESET);
                        } else {
                            System.out.println(ANSI_RED + "Ese usuario no existe o su contraseña no fue seteada!\n" + ANSI_RESET);
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
                        System.out.println(ANSI_YELLOW + "No hay ningun usuario logueado" + ANSI_RESET);
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
                    Folder_ folderActual_1 = system.ultimoFolder(system.getRute());
                    int permisos = folderActual_1.getPermisos(system.getLoggedUser().getName());
                    if (permisos == 2 || permisos == 6) {
                        Folder_ archivoMkdir = new Folder_(comando[1], "FOLDER");
                        archivoMkdir.setPermisos("666"); // en la practica, PROP y USER tienen permisos de lectura y escritura
                        archivoMkdir.setPropietario(system.getLoggedUser().getName());
                        Folder_ folderActual = system.ultimoFolder(system.getRute());
                        folderActual.addFolder(archivoMkdir);
                        System.out.println(ANSI_GREEN + "Su directorio se creó correctamente!\n" + ANSI_RESET);
                    } else {
                        System.out.println(ANSI_RED + "Error: no se tiene permisos para realizar la operacion" + ANSI_RESET);
                    }
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("mkdir");
                    }
                    break;
                case "touch":
                    Folder_ folderActual_2 = system.ultimoFolder(system.getRute());
                    int permisos2 = folderActual_2.getPermisos(system.getLoggedUser().getName());
                    if (permisos2 == 2 || permisos2 == 6) {
                        Folder_ file = new Folder_(comando[1], "FILE");
                        file.setPermisos("644"); // en la practica, PROP tiene permisos de lectura y escritura, User solo de lectura
                        file.setPropietario(system.getLoggedUser().getName());
                        Folder_ folderActual_ = system.ultimoFolder(system.getRute());
                        folderActual_.addFolder(file);
                        System.out.println(ANSI_GREEN + "Su archivo se creó correctamente!\n" + ANSI_RESET);
                        System.out.println(ANSI_GREEN + "Su directorio se creó correctamente!\n" + ANSI_RESET);
                    } else {
                        System.out.println(ANSI_RED + "Error: no se tiene permisos para realizar la operacion" + ANSI_RESET);
                    }
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("touch");
                    }
                    break;
                case "echo":
                    Folder_ folderActual_3 = system.ultimoFolder(system.getRute());
                    int permisos3 = folderActual_3.getPermisos(system.getLoggedUser().getName());
                    if (permisos3 == 2 || permisos3 == 6) {
                        int largo = comando.length;
                        String textoAIngresar = comando[1];
                        for (int i = 2; i < largo - 2; i++) {
                            textoAIngresar += " " + comando[i];
                        }
                        String nombreArchivo = comando[largo - 1];
                        Folder_ folder = system.ultimoFolder(system.getRute());
                        Folder_ file_ = folder.buscarFolder(nombreArchivo);
                        file_.setContenido(textoAIngresar);
                    } else {
                        System.out.println(ANSI_RED + "Error: no se tiene permisos para realizar la operacion" + ANSI_RESET);
                    }
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("echo");
                    }
                    break;
                case "mv":
                    String nombreArchivo_ = comando[1];
                    String rutaOrigen = comando[2];
                    String rutaDestino = comando[3];

                    Folder_ eliminarDe = system.ultimoFolder(rutaOrigen);
                    Folder_ moverA = system.ultimoFolder(rutaDestino);

                    Folder_ archivoAgregar = eliminarDe.buscarFolder(nombreArchivo_);
                    archivoAgregar.setPermisos("644"); // en la practica, PROP tiene permisos de lectura y escritura, User solo de lectura
                    archivoAgregar.setPropietario(system.getLoggedUser().getName());

                    eliminarDe.quitarArchivo(archivoAgregar);
                    moverA.addFolder(archivoAgregar);

                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("mv");
                    }
                    break;
                case "cp":
                    String nombreArchivo2_ = comando[1];
                    String rutaOrigen2 = comando[2];
                    String rutaDestino2 = comando[3];

                    Folder_ copiarDe = system.ultimoFolder(rutaOrigen2);
                    Folder_ fileAcopiar = copiarDe.buscarFolder(nombreArchivo2_);

                    Folder_ nuevaCopia = new Folder_(fileAcopiar.getNombre(), fileAcopiar.getTipo());
                    nuevaCopia.setPermisos("644"); // en la practica, PROP tiene permisos de lectura y escritura, User solo de lectura
                    nuevaCopia.setPropietario(system.getLoggedUser().getName());
                    nuevaCopia.setContenido(fileAcopiar.getContenido());

                    Folder_ moverA2 = system.ultimoFolder(rutaDestino2);
                    moverA2.addFolder(nuevaCopia);

                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("cp");
                    }
                    break;
                case "cat":
                    String nombreFolder = comando[1];
                    Folder_ actualFolder = system.ultimoFolder(system.getRute());
                    Folder_ fileAImprimir = actualFolder.buscarFolder(nombreFolder);
                    if (fileAImprimir != null) {
                        System.out.println(fileAImprimir.getContenido());
                    } else {
                        System.out.println(ANSI_RED + "Error: ese archivo no existe" + ANSI_RESET);
                    }
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("cat");
                    }
                    break;
                case "rm":
                    String nombreFolder_ = comando[1];
                    Folder_ actualFolder_ = system.ultimoFolder(system.getRute());
                    Folder_ folderABorrar_ = actualFolder_.buscarFolder(nombreFolder_);
                    actualFolder_.quitarArchivo(folderABorrar_);
                    folderABorrar_ = null;
                    break;
                case "cd":
                    String ruta = comando[1];
                    String rutaIni = system.getRute();
                    system.setRoute(ruta);
                    if (system.ultimoFolder(system.getRute()) == null) {
                        system.setDirectRoute(rutaIni);
                        System.out.println(ANSI_RED + "Error: esa ruta no existe" + ANSI_RESET);
                    }
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("rm");
                    }
                    break;
                case "ls":
                    if (comando[1].equals("-l")) {
                        Folder_ actualFolder2 = system.ultimoFolder(system.getRute());
                        ArrayList<Folder_> folders = actualFolder2.getFolders();
                        for (Folder_ fol : folders) {
                            System.out.println(fol.toString()); //imprimo la metadata del folder
                        }
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
                    String permisos_ = comando[1];
                    String nombreArch_ = comando[2];
                    Folder_ actualFolder4 = system.ultimoFolder(system.getRute());
                    Folder_ folderACambiarPermisos = actualFolder4.buscarFolder(nombreArch_);
                    folderACambiarPermisos.setPermisos(permisos_);
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("chmod");
                    }
                    break;
                case "chown":
                    String nuevoProp = comando[1];
                    String nombreArch = comando[2];
                    Folder_ actualFolder3 = system.ultimoFolder(system.getRute());
                    Folder_ folderACambiarProp = actualFolder3.buscarFolder(nombreArch);
                    folderACambiarProp.setPropietario(nuevoProp);
                    if (system.getLoggedUser() != null) {
                        system.getLoggedUser().addComando("chown");
                    }
                    break;
            }
        }
    }
}
