import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class EscribirFicheroTexto {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Dame un texto: ");
        String texto = sc.nextLine();

        File f = new File("texto.txt");
        FileWriter fw = new FileWriter(f);
        //FileWriter fw = new FileWriter(f, true); (Solo si queremos añadir en vez de reemplazar)
        PrintWriter pw = new PrintWriter(fw);

        pw.println(texto);
        pw.close();
        sc.close();

    }
}
