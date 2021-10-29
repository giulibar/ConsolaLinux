/*
Nombres: Giuliano Bardecio [256113] y Vittorio Caiafa [252295]
 */
package consolalinux;

import java.util.*;

public class Main_ {

    // inicio el sistema
    public static System_ system = new System_();
    public static Scanner entrada = new Scanner(System.in);
    public static String ANSI_RED = "\u001B[31m";
    public static String ANSI_GREEN = "\u001B[32m";
    public static String ANSI_YELLOW = "\u001B[33m";
    public static String ANSI_PURPLE = "\u001B[35m";
    public static String ANSI_CYAN = "\u001B[36m";
    public static String ANSI_WHITE = "\u001B[37m";
    public static String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

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
            agregarComando(textoEscrito);

            switch (comando[0]) {
                case "useradd":
                    useradd(comando);
                    break;
                case "passwd":
                    passwd(comando);
                    break;
                case "su":
                    su(comando);
                    break;
                case "whoami":
                    whoami(comando);
                    break;
                case "pwd":
                    pwd(comando);
                    break;
                case "mkdir":
                    mkdir(comando);
                    break;
                case "touch":
                    touch(comando);
                    break;
                case "echo":
                    echo(comando);
                    break;
                case "mv":
                    mv(comando);
                    break;
                case "cp":
                    cp(comando);
                    break;
                case "cat":
                    cat(comando);
                    break;
                case "rm":
                    rm(comando);
                    break;
                case "cd":
                    cd(comando);
                    break;
                case "ls":
                    ls(comando);
                    break;
                case "history":
                    if (comando.length == 1) {
                        history(comando);
                    } else if (comando.length == 4) {
                        pipe(comando);
                    }
                    break;
                case "chmod":
                    chmod(comando);
                    break;
                case "chown":
                    chown(comando);
                    break;
            }
        }

    }

    public static void useradd(String[] comando) {
        if (sintaxisOk(comando)) {
            User_ user = new User_(comando[1]);
            System.out.println(ANSI_GREEN + "Se agregó a " + user.getName() + "\n" + ANSI_RESET);
            system.addUser(user);
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void passwd(String[] comando) {
        if (comando.length == 2) {
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
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void su(String[] comando) {
        if (comando.length == 2) {
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
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void whoami(String[] comando) {
        if (comando.length == 1) {
            User_ user3 = system.getLoggedUser();
            if (user3 != null) {
                System.out.println(user3.getName());
            } else {
                System.out.println(ANSI_YELLOW + "No hay ningun usuario logueado" + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void pwd(String[] comando) {
        if (comando.length == 1) {
            System.out.println(system.getRute());
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void mkdir(String[] comando) {
        if (comando.length == 2) {
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
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void touch(String[] comando) {
        if (comando.length == 2) {
            Folder_ folderActual_2 = system.ultimoFolder(system.getRute());
            int permisos2 = folderActual_2.getPermisos(system.getLoggedUser().getName());
            if (permisos2 == 2 || permisos2 == 6) {
                Folder_ file = new Folder_(comando[1], "FILE");
                file.setPermisos("644"); // en la practica, PROP tiene permisos de lectura y escritura, User solo de lectura
                file.setPropietario(system.getLoggedUser().getName());
                Folder_ folderActual_ = system.ultimoFolder(system.getRute());
                folderActual_.addFolder(file);
                System.out.println(ANSI_GREEN + "Su archivo se creó correctamente!\n" + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "Error: no se tiene permisos para realizar la operacion" + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void echo(String[] comando) {
        if (sintaxisOk(comando)) {
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
                if (file_ != null) {
                    file_.setContenido(textoAIngresar);
                } else {
                    String[] comandoArtificial = {"touch", nombreArchivo};
                    touch(comandoArtificial); // creamos el archivo porwue no existia
                    echo(comando); // le agregamos la linea al final del contenido
                }
            } else {
                System.out.println(ANSI_RED + "Error: no se tiene permisos para realizar la operacion" + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void mv(String[] comando) {
        if (sintaxisOk(comando)) {
            String nombreArchivo_ = comando[1];
            String rutaOrigen = comando[2];
            String rutaDestino = comando[3];
            Folder_ eliminarDe = system.ultimoFolder(rutaOrigen);
            Folder_ moverA = system.ultimoFolder(rutaDestino);
            Folder_ archivoAgregar = eliminarDe.buscarFolder(nombreArchivo_);
            if (existenRutasYFolder(rutaOrigen, rutaDestino, archivoAgregar)) {
                archivoAgregar.setPermisos("644"); // en la practica, PROP tiene permisos de lectura y escritura, User solo de lectura
                archivoAgregar.setPropietario(system.getLoggedUser().getName());
                eliminarDe.quitarArchivo(archivoAgregar);
                moverA.addFolder(archivoAgregar);
            } else {
                System.out.println(ANSI_RED + "La ruta y/o el archivo no existe.\n" + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void cp(String[] comando) {
        if (sintaxisOk(comando)) {
            String nombreArchivo2_ = comando[1];
            String rutaOrigen2 = comando[2];
            String rutaDestino2 = comando[3];
            Folder_ copiarDe = system.ultimoFolder(rutaOrigen2);
            Folder_ fileAcopiar = copiarDe.buscarFolder(nombreArchivo2_);
            if (existenRutasYFolder(rutaOrigen2, rutaDestino2, fileAcopiar)) {
                Folder_ nuevaCopia = new Folder_(fileAcopiar.getNombre(), fileAcopiar.getTipo());
                nuevaCopia.setPermisos("644"); // en la practica, PROP tiene permisos de lectura y escritura, User solo de lectura
                nuevaCopia.setPropietario(system.getLoggedUser().getName());
                nuevaCopia.setContenido(fileAcopiar.getContenido());
                Folder_ moverA2 = system.ultimoFolder(rutaDestino2);
                moverA2.addFolder(nuevaCopia);
            }
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static boolean existenRutasYFolder(String origen, String destino, Folder_ folder) {
        return origen != null && destino != null && folder != null;
    }

    public static void cat(String[] comando) {
        if (sintaxisOk(comando)) {
            String nombreFolder = comando[1];
            Folder_ actualFolder = system.ultimoFolder(system.getRute());
            Folder_ fileAImprimir = actualFolder.buscarFolder(nombreFolder);
            if (fileAImprimir != null) {
                System.out.println(fileAImprimir.getContenido());
            } else {
                System.out.println(ANSI_RED + "Error: ese archivo no existe" + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void rm(String[] comando) {
        if (sintaxisOk(comando)) {
            String nombreFolder_ = comando[1];
            Folder_ actualFolder_ = system.ultimoFolder(system.getRute());
            Folder_ folderABorrar_ = actualFolder_.buscarFolder(nombreFolder_);
            actualFolder_.quitarArchivo(folderABorrar_);
            folderABorrar_ = null;
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void cd(String[] comando) {
        if (sintaxisOk(comando)) {
            String ruta = comando[1];
            String rutaIni = system.getRute();
            system.setRoute(ruta);
            if (system.ultimoFolder(system.getRute()) == null) {
                system.setDirectRoute(rutaIni);
                System.out.println(ANSI_RED + "Error: esa ruta no existe" + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void ls(String[] comando) {
        if (sintaxisOk(comando)) {
            if (comando[1].equals("-l")) {
                Folder_ actualFolder2 = system.ultimoFolder(system.getRute());
                ArrayList<Folder_> folders = actualFolder2.getFolders();
                for (Folder_ fol : folders) {
                    System.out.println(fol.toString()); //imprimo la metadata del folder
                }
            }
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void history(String[] comando) {
        if (sintaxisOk(comando)) {
            User_ user = system.getLoggedUser();
            if (user != null) {
                for (String comand : user.getListaComandos()) {
                    System.out.println(comand);
                }
            }
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void pipe(String[] comando) {
        if (sintaxisOk(comando)) {
            User_ user = system.getLoggedUser();
            for (String comand : user.getListaComandos()) {
                if (comand.equals(comando[3])) {
                    System.out.println("Se encontro ese comando.");
                } else {
                    System.out.println("No se encontro ese comando.");
                }
            }
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void chmod(String[] comando) {
        if (sintaxisOk(comando)) {
            String permisos_ = comando[1];
            String nombreArch_ = comando[2];
            Folder_ actualFolder4 = system.ultimoFolder(system.getRute());
            if(actualFolder4 != null){
                Folder_ folderACambiarPermisos = actualFolder4.buscarFolder(nombreArch_);
                folderACambiarPermisos.setPermisos(permisos_);
            }else{
                System.out.println(ANSI_RED + "El archivo no existe.\n" + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void chown(String[] comando) {
        if (sintaxisOk(comando)) {
            String nuevoProp = comando[1];
            String nombreArch = comando[2];
            Folder_ actualFolder3 = system.ultimoFolder(system.getRute());
            if(actualFolder3 != null){
                Folder_ folderACambiarProp = actualFolder3.buscarFolder(nombreArch);
                folderACambiarProp.setPropietario(nuevoProp);
            }else{
                System.out.println(ANSI_RED + "El archivo no existe.\n" + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "La sentencia es incorrecta. Vuelva a intentarlo.\n" + ANSI_RESET);
        }
    }

    public static void agregarComando(String textoEscrito) {
        if (system.getLoggedUser() != null) {
            system.getLoggedUser().addComando(textoEscrito);
        }
    }

    public static boolean sintaxisOk(String[] comando) {
        boolean res = false;
        switch (comando[0]) {
            case "useradd":
                res = comando.length == 2;
                break;
            case "passwd":
                res = comando.length == 2;
                break;
            case "su":
                res = comando.length == 2;
                break;
            case "whoami":
                res = comando.length == 1;
                break;
            case "pwd":
                res = comando.length == 1;
                break;
            case "mkdir":
                res = comando.length == 2;
                break;
            case "touch":
                res = comando.length == 2;
                break;
            case "echo":
                res = comando[comando.length - 2].equals(">>"); // ponerle las cmoillas tambien o quitarselas?
                break;
            case "mv":
                res = comando.length == 4;
                break;
            case "cp":
                res = comando.length == 4;
                break;
            case "cat":
                res = comando.length == 2;
                break;
            case "rm":
                res = comando.length == 2;
                break;
            case "cd":
                res = comando.length == 2;
                break;
            case "ls":
                res = comando.length == 2;
                break;
            case "history":
                if (comando.length == 1) { // history
                    res = true;
                } else if (comando.length == 4) { // history | grep
                    res = (comando[1].equals("|") && comando[2].equals("grep"));
                }
                break;
            case "chmod":
                if (comando.length == 3) {
                    res = (Integer.parseInt(comando[1]) >= 0 && Integer.parseInt(comando[1]) <= 777);
                }
                break;
            case "chown":
                res = comando.length == 3;
                break;
        }
        return res;
    }

    public static void permisosOk(String[] comando) {

    }
}
