import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class JuegoPrares {
    // Declaramos el contador como una variable estática para poder modificarla desde el evento
    static int contador = 0;

    public static void main(String[] args) throws Exception {
        Frame marco = new Frame("Juego de Memoria");
        marco.setSize(400, 400);
        marco.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Panel para los botones del juego
        Panel panel = new Panel(new GridLayout(2, 3));
        marco.add(panel);

        // Panel inferior para la puntuación
        Panel panel2 = new Panel(new BorderLayout());
        marco.add(panel2, BorderLayout.SOUTH);
        
        Label etiqueta = new Label("Puntuación: " + contador);
        panel2.add(etiqueta, BorderLayout.CENTER);

        // CREACIÓN DE LA INTERFAZ (Se ejecuta una sola vez)
        for (int i = 1; i <= 6; i++) {
            int numeroAleatorio = new Random().nextInt(10) + 1;
            Button boton = new Button();
            
            boton.addActionListener(e -> {
                // 1. Mostramos el número del botón al pulsar
                boton.setLabel(String.valueOf(numeroAleatorio));
                
                // 2. LÓGICA DEL JUEGO: Aquí reaccionamos al evento (Sustituye al bucle while)
                contador++; 
                etiqueta.setText("Puntuación: " + contador);

                // Comprobamos si el jugador ha alcanzado la meta
                if (contador >= 10) {
                    etiqueta.setText("¡Has ganado! Puntuación máxima alcanzada.");
                    // Aquí podrías desactivar los botones o finalizar el juego
                }
            });
            
            panel.add(boton);
        }

        // Hacemos la ventana visible AL FINAL, cuando ya todo está creado
        marco.setVisible(true);
    }
}
