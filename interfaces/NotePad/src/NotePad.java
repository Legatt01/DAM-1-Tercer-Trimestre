import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class NotePad {
    public static void main(String[] args) throws Exception {
        Frame frame = new Frame("NotePad");
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Archivo");
        TextArea textArea = new TextArea();

        MenuItem openItem = new MenuItem("Abrir");
        MenuItem saveItem = new MenuItem("Guardar");
        MenuItem exitItem = new MenuItem("Salir");

        fileMenu.add(openItem);
        openItem.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(frame, "Abrir archivo", FileDialog.LOAD);
            fileDialog.setVisible(true);
            String directory = fileDialog.getDirectory();
            String fileName = fileDialog.getFile();

            if (directory != null && fileName != null) {
                String fullPath = directory + fileName;
                System.out.println("Ruta para abrir: " + fullPath);

                // Aquí procedes a leer el archivo usando FileInputStream o FileReader
                try {
                    FileReader lector = new FileReader(fullPath);
                    BufferedReader buffer = new BufferedReader(lector);

                    StringBuilder contenido = new StringBuilder();
                    String linea;
                    while ((linea = buffer.readLine()) != null) {
                        contenido.append(linea).append("\n");
                    }

                    textArea.setText(contenido.toString());
                    buffer.close();
                    lector.close();

                } catch (IOException ex) {
                    System.out.println("Error al intentar leer el archivo: " + ex.getMessage());
                }
            }
        });
        fileMenu.add(saveItem);
        saveItem.addActionListener(e -> {
            FileDialog fileDialog = new FileDialog(frame, "Guardar archivo", FileDialog.SAVE);
            fileDialog.setVisible(true);
            String directory = fileDialog.getDirectory();
            String fileName = fileDialog.getFile();

            if (directory != null && fileName != null) {
                String fullPath = directory + fileName + ".txt";
                System.out.println("Ruta para guardar: " + fullPath);

                // Aquí procedes a escribir el archivo usando FileOutputStream o FileWriter
                try {
                    FileWriter escritor = new FileWriter(fullPath);
                    BufferedWriter buffer = new BufferedWriter(escritor);

                    // Aquí escribes lo que necesites (ejemplo: el texto de un TextArea)
                    buffer.write(textArea.getText());

                    buffer.close();
                    escritor.close(); // ¡Importante cerrar para que se guarde!
                    System.out.println("¡Archivo guardado con éxito en: " + fullPath);

                } catch (IOException ex) {
                    System.out.println("Error al intentar crear el archivo: " + ex.getMessage());
                }
            }
        });
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        exitItem.addActionListener(e -> System.exit(0));

        menuBar.add(fileMenu);

        frame.add(textArea);
        frame.setMenuBar(menuBar);
        frame.setSize(1200, 750);
        frame.setResizable(true); // Evita que el usuario lo deforme
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}
