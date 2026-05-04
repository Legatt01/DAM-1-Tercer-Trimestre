import java.io.File;
import java.util.Scanner;

public class CrearBorrarFichero {
    public static void main(String[] args) {
        // Usamos una ruta genérica o asegúrate de que C:\archivero exista
        File fichero = new File("C:\\archivero\\miFichero.txt");
        Scanner sc = new Scanner(System.in);
        int opc = -1; // Declaramos la variable fuera para que el while la reconozca

        do {
            mostrarMenu(); // Solo muestra el texto
            try {
                opc = Integer.parseInt(sc.nextLine()); // Leemos la opción aquí
                
                switch (opc) {
                    case 1:
                        crearFichero(fichero);
                        break;
                    case 2:
                        borrarFichero(fichero);
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número válido.");
            }
        } while (opc != 0);

        sc.close();
    }

    public static void crearFichero(File fichero) {
        try {
            // Importante: createNewFile() lanza una excepción si la carpeta NO existe
            if (fichero.createNewFile()) {
                System.out.println("El fichero se ha creado correctamente.");
            } else {
                System.out.println("El fichero ya existe.");
            }
        } catch (Exception e) {
            System.out.println("Error: Asegúrate de que la carpeta 'C:\\archivero' exista.");
        }
    }

    public static void borrarFichero(File fichero) {
        if (fichero.exists()) {
            if (fichero.delete()) {
                System.out.println("El fichero se ha borrado correctamente.");
            } else {
                System.out.println("No se pudo borrar el fichero.");
            }
        } else {
            System.out.println("El fichero no existe.");
        }
    }

    public static void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("       SISTEMA DE GESTIÓN DE FICHEROS     ");
        System.out.println("========================================");
        System.out.println("1. CREAR FICHERO");
        System.out.println("2. BORRAR FICHERO");
        System.out.println("0. SALIR");
        System.out.print("Seleccione una opción: ");
    }
}
