import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AppTemperaturas {
    private Scanner sc;
    private GestorDatosT datos;
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

 
    AppTemperaturas() {
        this.sc = new Scanner(System.in);
        this.datos = new GestorDatosT();
    }

    public static void main(String[] args) throws Exception {
        AppTemperaturas app = new AppTemperaturas();
        app.menu();
        app.sc.close();        
    }

    private void cls() {
        System.out.print("\033[H\033[2J");
    }

    private void esperaIntro() {
        System.out.println("\n[Pulse intro para continuar]");
        sc.nextLine();
    }

    private void menu(){
        int opc;

        do {
            cls();
            System.out.println("===== Registro de Temperaturas =====\n");
            System.out.println("1.- Añadir nuevo registro\r\n" + //
                                "2.- Modificar temperatura de una fecha\r\n" + //
                                "3.- Consultar temperaturas por fecha\r\n" + //
                                "4.- Calcular promedio de temperaturas por mes\r\n"+ //
                                "0.- Salir");
            System.out.print("\tSeleccione opción: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1: addRegistro(); break;
                case 2: updateRegistro(); break;
                case 3: showRegistro(); break;
                case 4: calcularPromedio(); break;
                case 0: System.out.println("\nFinalizado correctamente."); break;           
                default: System.out.println("\nOpción no válida."); esperaIntro(); break;
            }
            
        } while (opc != 0);
    }

    private void addRegistro(){
        cls();
        System.out.println("--- Añadir Registro ---\n");
        System.out.print("Dime la fecha (dd/MM/yyyy): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine(), formato);
        System.out.print("Dime la temperatura máxima: ");
        double tMax = Double.parseDouble(sc.nextLine());
        System.out.print("Dime la temperatura mínima: ");
        double tMin = Double.parseDouble(sc.nextLine());
        
        datos.addRegistro(fecha, tMax, tMin);
        esperaIntro();
    }

    private void updateRegistro(){
        cls();
        System.out.println("--- Modificar Registro ---\n");
        System.out.print("Dime la fecha (dd/MM/yyyy): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine(), formato);
        System.out.print("Dime la nueva temperatura máxima: ");
        double tMax = Double.parseDouble(sc.nextLine());
        System.out.print("Dime la nueva temperatura mínima: ");
        double tMin = Double.parseDouble(sc.nextLine());

        datos.updateRegistro(fecha, tMax, tMin);
        esperaIntro();
    }
    
    private void showRegistro(){
        cls();
        System.out.println("--- Consultar Registro ---\n");
        System.out.print("Dime la fecha (dd/MM/yyyy): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine(), formato);

        datos.showRegistro(fecha);
        esperaIntro();
    }
    
    private void calcularPromedio(){
        cls();
        System.out.print("Dime el mes a calcular (en número): ");
        int mes = Integer.parseInt(sc.nextLine());

        double[] promedio = datos.calcularPromedio(mes);

        if(promedio != null) {
            System.out.println("El promedio es [" + promedio[0] + " | " + promedio[1] + "]" );
        }
        else
            System.out.println("No hay datos de ese mes");
  
        esperaIntro();
    }
    
}