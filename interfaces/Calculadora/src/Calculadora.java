import java.awt.*;
import java.awt.event.*;

public class Calculadora {
    public static void main(String[] args) throws Exception {
        Frame frame = new Frame("Calculadora");
        frame.setLayout(new BorderLayout());

        Panel panel0 = new Panel();
        panel0.setPreferredSize(new Dimension(100, 50));
        panel0.setLayout(new BorderLayout());
        panel0.setBackground(Color.WHITE);
        frame.add(panel0, BorderLayout.NORTH);
        Label label = new Label("0", Label.RIGHT);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setPreferredSize(new Dimension(1000, 0));
        label.setBackground(Color.white);
        label.setForeground(Color.black);
        panel0.add(label, BorderLayout.EAST);

        // Dentro de main(), antes de crear los botones:

        double[] memoria = { 0 }; // el primer número guardado
        String[] operadorActual = { "" }; // el operador elegido (+, -, *, /)
        boolean[] esperandoNumero = { true }; // ¿estamos escribiendo número nuevo?

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 3));
        panel.setBackground(Color.blue);
        frame.add(panel, BorderLayout.CENTER);
        String[] numeros = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "C" };
        for (String num : numeros) {
            Button button = new Button(num);
            button.addActionListener(e -> {
                String pulsado = e.getActionCommand();

                if (pulsado.equals("C")) {
                    memoria[0] = 0;
                    operadorActual[0] = "";
                    esperandoNumero[0] = true;
                    label.setText("0");
                } else {
                    if (esperandoNumero[0]) {
                        // Número nuevo: reemplaza lo que había
                        label.setText(pulsado);
                        esperandoNumero[0] = false;
                    } else {
                        // Continuación: añade el dígito
                        // Evita múltiples puntos decimales
                        if (pulsado.equals(".") && label.getText().contains("."))
                            return;
                        label.setText(label.getText() + pulsado);
                    }
                }
            });
            panel.add(button);
        }

        Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(5, 1));
        panel2.setPreferredSize(new Dimension(100, 0));
        panel2.setBackground(Color.red);
        frame.add(panel2, BorderLayout.EAST);
        String[] operadores = { "+", "-", "*", "/", "=" };
        for (String op : operadores) {
            Button button = new Button(op);
            button.addActionListener(e -> {
                String pulsado = e.getActionCommand();
                double numeroActual = Double.parseDouble(label.getText());

                if (pulsado.equals("=")) {
                    if (!operadorActual[0].isEmpty()) {
                        // Instancia necesaria para llamar al método de instancia
                        double resultado = operaciones(memoria[0], numeroActual, operadorActual[0]);

                        // Muestra entero si no tiene decimales (ej: 6.0 → "6")
                        if (resultado == (long) resultado) {
                            label.setText(String.valueOf((long) resultado));
                        } else {
                            label.setText(String.valueOf(resultado));
                        }
                        operadorActual[0] = "";
                        memoria[0] = resultado;
                    }
                } else {
                    // Guarda número actual y el operador elegido
                    memoria[0] = numeroActual;
                    operadorActual[0] = pulsado;
                }

                esperandoNumero[0] = true; // siguiente pulsación empieza número nuevo
            });
            panel2.add(button);
        }

        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

    }

    public static double operaciones(double a, double b, String operador) {
        switch (operador) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                throw new IllegalArgumentException("Operador no válido");
        }
    }

}