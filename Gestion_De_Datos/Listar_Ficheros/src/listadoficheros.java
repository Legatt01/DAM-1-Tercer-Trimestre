import java.io.File;
import java.util.Scanner;

public class listadoficheros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la ruta del directorio:");
        
        // Usamos nextLine para capturar toda la frase incluyendo espacios
        String ruta = sc.nextLine();

        // Limpieza básica: eliminamos comillas si las pegaste con la ruta
        ruta = ruta.replace("\"", "");

        mostrarRuta(ruta);

        sc.close();
    }

    public static void mostrarRuta(String ruta) {
        File fichero = new File(ruta);
        
        if (fichero.exists() && fichero.isDirectory()) {
            File[] archivos = fichero.listFiles();

            // Verificamos que listFiles no devuelva null (por falta de permisos)
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isFile()) {
                        System.out.println("\t - " + archivo.getName().toLowerCase());
                    } else if (archivo.isDirectory()) {
                        System.out.println(" + " + archivo.getName().toUpperCase());
                        // Llamada recursiva
                        mostrarRuta(archivo.getAbsolutePath());
                    }
                }
            } else {
                System.out.println("No se pudo acceder al contenido de: " + fichero.getName() + " (Permiso denegado)");
            }
        } else {
            System.out.println("Ruta no válida o no es un directorio: " + ruta);
        }
    }
}
