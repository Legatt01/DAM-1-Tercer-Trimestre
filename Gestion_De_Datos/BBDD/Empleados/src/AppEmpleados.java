import java.util.Scanner;

public class AppEmpleados {
    private Scanner sc;
    private GestorDatosEmp datos;

    AppEmpleados() {
        this.sc = new Scanner(System.in);
        this.datos = new GestorDatosEmp();
    }

    public static void main(String[] args) throws Exception {
        AppEmpleados app = new AppEmpleados();
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

    private void menu() {
        int opc;

        do {
            cls();
            System.out.println("===== Gestión de Empleados =====\n");
            System.out.println("1.- Añadir nuevo empleado\r\n" + //
                    "2.- Modificar información de un empleado\r\n" + //
                    "3.- Eliminar empleado\r\n" + //
                    "4.- Listar empleados\r\n" + //
                    "0.- Salir");
            System.out.print("\tSeleccione opción: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    addRegistro();
                    break;
                case 2:
                    updateRegistro();
                    break;
                case 3:
                    eliminarEmpleado();
                    break;
                case 4:
                    listarEmpleados();
                    break;
                case 0:
                    System.out.println("\nFinalizado correctamente.");
                    break;
                default:
                    System.out.println("\nOpción no válida.");
                    esperaIntro();
                    break;
            }

        } while (opc != 0);
    }

    private void addRegistro() {
        cls();
        System.out.println("--- Añadir Registro ---\n");
        System.out.print("Dime el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Dime el apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Dime el salario: ");
        double salario = Double.parseDouble(sc.nextLine());

        datos.addRegistro(nombre, apellido, salario);
        esperaIntro();
    }

    private void updateRegistro() {
        cls();
        System.out.println("--- Modificar Registro ---\n");
        System.out.println("Dime el nombre y apellido del empleado que quieres modificar:");
        System.out.print("Dime el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Dime el apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Dime el nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        System.out.print("Dime el nuevo apellido: ");
        String nuevoApellido = sc.nextLine();
        System.out.print("Dime el nuevo salario: ");
        double salario = Double.parseDouble(sc.nextLine());

        datos.updateRegistro(nombre, apellido, nuevoNombre, nuevoApellido, salario);
        esperaIntro();
    }

    private void eliminarEmpleado() {
        cls();
        System.out.println("--- Eliminar Empleado ---\n");
        System.out.print("Dime el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Dime el apellido: ");
        String apellido = sc.nextLine();

        datos.eliminarEmpleado(nombre, apellido);
        esperaIntro();
    }

    private void listarEmpleados() {
        cls();
        System.out.println("--- Listar Empleados ---\n");
        String[] empleados = datos.listarEmpleados();

        for (String empleado : empleados) {
            if (empleado != null) {
                System.out.println(empleado);
            }
        }
        esperaIntro();
    }
}
