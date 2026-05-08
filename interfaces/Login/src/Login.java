import java.awt.*;
import java.awt.event.*;

public class Login {
    public static void main(String[] args) {
        Frame frame = new Frame("Formulario de Registro");
        
        // 1. Usamos un panel central con GridLayout (4 filas, 2 columnas)
        // Esto creará una columna para etiquetas y otra para campos
        Panel mainPanel = new Panel(new GridLayout(4, 2, 10, 10)); // 10 es el espacio entre celdas

        // 2. Definimos componentes
        Label label1 = new Label("Nombre:", Label.LEFT); // Alineamos texto a la derecha
        TextField txtNombre = new TextField();
        
        Label label2 = new Label("Contraseña:", Label.LEFT);
        TextField txtPass = new TextField();
        txtPass.setEchoChar('*');

        Label label3 = new Label("Email:", Label.LEFT);
        TextField txtEmail = new TextField();

        // Para el botón, si no queremos que ocupe toda la fila, 
        // lo metemos en un panel pequeño o dejamos una celda vacía
        Label blank = new Label(""); 
        Button button = new Button("Validar");

        // 3. Añadimos al mainPanel en ORDEN (izquierda a derecha, arriba a abajo)
        mainPanel.add(label1);    mainPanel.add(txtNombre);
        mainPanel.add(label2);    mainPanel.add(txtPass);
        mainPanel.add(label3);    mainPanel.add(txtEmail);
        mainPanel.add(blank);     mainPanel.add(button);

        // 4. Añadimos el panel al frame con un poco de margen (Padding)
        frame.add(mainPanel);
        
        // Ajustes finales
        frame.setSize(400, 250);
        frame.setResizable(false); // Evita que el usuario lo deforme
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}