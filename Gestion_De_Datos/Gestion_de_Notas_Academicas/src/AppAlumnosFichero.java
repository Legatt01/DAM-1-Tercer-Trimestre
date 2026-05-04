import java.util.Scanner;

public class AppAlumnosFichero {
    private GestorDatos datos;
    private Scanner sc;

    AppAlumnosFichero() {
        datos = new GestorDatos();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) throws Exception {
        AppAlumnosFichero app = new AppAlumnosFichero();
        app.mostrarMenu();
    }

    private void cls() {
        System.out.print("\033[H\033[2J");
    }

    private void esperaIntro() {
        System.out.println("\n[Pulse intro para continuar]");
        sc.nextLine();
    }

    private void mostrarMenu() {
        int opc;

        do {
            cls();
            System.out.println("------ Gestión de Alumnos -----\n");
            System.out.println("1.- Gestionar alumnos\r\n" + //
                    "2.- Gestionar asignaturas\r\n" + //
                    "3.- Gestionar notas\r\n" + //
                    "4.- Guardar y cargar datos\r\n" + //
                    "0.- Salir");
            System.out.print("\tSeleccione opción: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1: menuAlumnos(); break;
                case 2: menuAsignaturas(); break;
                case 3: menuNotas(); break;
                case 4: menuDatos(); break;
                case 0: System.out.println("Gracias por usar el programa."); break;
                default: System.out.println("Opción inválida."); esperaIntro(); break;
            }
        } while (opc != 0);

    }

//GESTION DE ALUMNOS    
    private void menuAlumnos(){
        int opc;

        do {
            cls();
            System.out.println("------ Gestión de Alumnos -----\n");
            System.out.println("--- Menú Alumnos ---");
            System.out.println("1.- Alta alumnos\r\n" + //
                    "2.- Baja alumnos\r\n" + //
                    "3.- Listar alumnos\r\n" + //
                    "0.- Volver");
            System.out.print("\tSeleccione opción: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1: altaAlumnos(); break;
                case 2: bajaAlumnos(); break;
                case 3: listaAlumnos(); break;
                case 0: break;
                default: System.out.println("Opción inválida."); esperaIntro(); break;
            }
        } while (opc != 0);
    }

    private void altaAlumnos() {
        System.out.print("Dime el nombre del alumno: ");
        String nombre = sc.nextLine();
        System.out.print("Dime el curso del alumno: ");
        String curso = sc.nextLine();
        datos.addAlumno(nombre, curso);
    }

    private void bajaAlumnos() {
        System.out.print("Dime el nombre del alumno: ");
        String nombre = sc.nextLine();
        datos.removeAlumno(nombre);    
    }

    private void listaAlumnos() {
        datos.listAlumnos();
        esperaIntro();
    }

//GESTION ASIGNATURAS
    private void menuAsignaturas(){
        int opc;

        do {
            cls();
            System.out.println("------ Gestión de Alumnos -----\n");
            System.out.println("--- Menú Asignaturas ---");
            System.out.println("1.- Alta Asignatura\r\n" + //
                    "2.- Baja Asignatura\r\n" + //
                    "3.- Listar Asignaturas\r\n" + //
                    "0.- Volver");
            System.out.print("\tSeleccione opción: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1: altaAsignatura(); break;
                case 2: bajaAsignatura(); break;
                case 3: listaAsignatura(); break;
                case 0: break;
                default: System.out.println("Opción inválida."); esperaIntro(); break;
            }
        } while (opc != 0);
        
    }

    private void altaAsignatura() {
        System.out.print("Dime la asignatura: ");
        String nombre = sc.nextLine();
        datos.addAsignatura(nombre);
    }

    private void bajaAsignatura() {
        System.out.print("Dime la asignatura: ");
        String nombre = sc.nextLine();
        datos.removeAsignatura(nombre);        
    }

    private void listaAsignatura() {
        datos.listAsignatura();
        esperaIntro();
    }


//GESTION NOTAS
    private void menuNotas(){
        int opc;

        do {
            cls();
            System.out.println("------ Gestión de Alumnos -----\n");
            System.out.println("--- Menú Notas ---");
            System.out.println("1.- Asignar notas\r\n" + //
                    "2.- Eliminar notas\r\n" + //
                    "0.- Volver");
            System.out.print("\tSeleccione opción: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1: asignarNotas(); break;
                case 2: eliminarNotas(); break;
                case 0: break;
                default: System.out.println("Opción inválida."); esperaIntro(); break;
            }
        } while (opc != 0);
    }

    private void asignarNotas() {
        datos.ponerNotas(sc);
    }

    private void eliminarNotas() {
        datos.quitarNotas(sc);
    }


//GESTION DATOS
    private void menuDatos(){
        int opc;

        do {
            cls();
            System.out.println("------ Gestión de Alumnos -----\n");
            System.out.println("--- Menú Datos ---");
            System.out.println("1.- Cargar datos\r\n" + //
                    "2.- Guargar datos\r\n" + //
                    "0.- Volver");
            System.out.print("\tSeleccione opción: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1: cargarDatos(); break;
                case 2: guardarDatos(); break;
                case 0: break;
                default: System.out.println("Opción inválida."); esperaIntro(); break;
            }
        } while (opc != 0);
    }

    private void cargarDatos() {
        datos.cargar();
    }

    private void guardarDatos() {
        datos.guardar();
    }


}
