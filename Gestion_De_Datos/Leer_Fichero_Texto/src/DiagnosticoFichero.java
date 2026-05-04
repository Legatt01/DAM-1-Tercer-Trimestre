import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class DiagnosticoFichero {
    public static void main(String[] args) {
        File arch = new File("C:\\archivero\\miFichero.txt");

        System.out.println("--- DIAGNÓSTICO ---");
        System.out.println("¿Existe el archivo?: " + arch.exists());
        System.out.println("¿Es un archivo real?: " + arch.isFile());
        System.out.println("¿Se puede leer?: " + arch.canRead());
        System.out.println("Ruta absoluta: " + arch.getAbsolutePath());
        System.out.println("Tamaño en bytes: " + arch.length());
        System.out.println("-------------------\n");

        if (arch.exists() && arch.length() > 0) {
            leerFichero(arch);
        } else if (arch.length() == 0) {
            System.out.println("OJO: El archivo existe pero está VACÍO.");
        }
    }

    public static void leerFichero(File arch) {
        try (BufferedReader br = new BufferedReader(new FileReader(arch))) {
            String linea;
            System.out.println("Contenido:");
            while ((linea = br.readLine()) != null) {
                System.out.println(">> " + linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
