import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class DivisorTexto {
    public static void main(String[] args) {
        String archivoEntrada = "texto.txt";
        int lineasPorArchivo = 20;
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada))) {
            String linea;
            int contadorLineas = 1;
            int numeroFichero = 1;
            BufferedWriter bw = null;

            while ((linea = br.readLine()) != null) {
                // Si el contador es 1, necesitamos abrir un nuevo archivo de salida
                if (contadorLineas == 1) {
                    bw = new BufferedWriter(new FileWriter("lineas" + numeroFichero + ".txt"));
                }

                bw.write(linea);
                bw.newLine();
                contadorLineas++;

                // Si alcanzamos el límite, cerramos este archivo y reiniciamos el contador
                if (contadorLineas == lineasPorArchivo) {
                    bw.close();
                    contadorLineas = 1;
                    numeroFichero++;
                }
            }

            // Cerramos el último archivo si quedó abierto
            if (bw != null) {
                bw.close();
            }

            System.out.println("Proceso finalizado. Se crearon " + (contadorLineas == 1 ? numeroFichero : numeroFichero) + " archivos.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}