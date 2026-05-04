import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        GestorSistema gestor = new GestorSistema();


        mostrarMenuPrincipal(gestor);
    }

    public static void mostrarMenuPrincipal(GestorSistema gestor) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n========================================");
            System.out.println("       SISTEMA DE GESTIÓN ACADÉMICA     ");
            System.out.println("========================================");
            System.out.println("1. GESTIONAR ALUMNOS");
            System.out.println("2. GESTIONAR ASIGNATURAS");
            System.out.println("3. NOTAS (ASIGNAR/MODIFICAR)");
            System.out.println("4. GUARDAR DATOS (ARCHIVO)");
            System.out.println("5. CARGAR DATOS (ARCHIVO)");
            System.out.println("0. SALIR");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduzca un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    ejecutarSubmenuAlumnos(sc, gestor);
                    break;
                case 2:
                    ejecutarSubmenuAsignaturas(sc, gestor);
                    break;
                case 3:
                    ejecutarGestionNotas(sc, gestor);
                    break;
                case 4:
                    gestor.guardarDatos("datos.bin");
                    break;
                case 5:
                    gestor.cargarDatos("datos.bin");
                    break;
                case 0:
                    System.out.println("Cerrando el sistema. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no reconocida.");
            }
        } while (opcion != 0);
    }

    private static void ejecutarSubmenuAlumnos(Scanner sc, GestorSistema gestor) {
        System.out.println("\n[1] Añadir Alumno | [2] Mostrar Alumnos | [3] Eliminar Alumno");
        String sub = sc.nextLine();

        if (sub.equals("1")) {
            System.out.print("Nombre del alumno: ");
            String nom = sc.nextLine();
            System.out.print("Curso: ");
            String cur = sc.nextLine();
            gestor.agregarAlumno(new Alumno(nom, cur));
        } else if (sub.equals("2")) {
            gestor.mostrarAlumnos();
        } else if (sub.equals("3")) {
            // Aquí llamarías a tu lógica de buscar y borrar
            System.out.println("Funcionalidad de borrado en desarrollo...");
        }
    }

    private static void ejecutarSubmenuAsignaturas(Scanner sc, GestorSistema gestor) {
        System.out.println("\n[1] Añadir Asignatura | [2] Mostrar Asignaturas");
        String sub = sc.nextLine();

        if (sub.equals("1")) {
            System.out.print("Nombre de la asignatura: ");
            String nom = sc.nextLine();
            gestor.agregarAsignatura(new Asignatura(nom));
        } else if (sub.equals("2")) {
            gestor.mostrarAsignaturas();
        }
    }

    private static void ejecutarGestionNotas(Scanner sc, GestorSistema gestor) {
        System.out.print("Nombre del alumno: ");
        String al = sc.nextLine();
        System.out.print("Nombre de la asignatura: ");
        String as = sc.nextLine();
        System.out.print("Nota: ");
        try {
            double nota = Double.parseDouble(sc.nextLine());
            gestor.asignarNota(al, as, nota);
        } catch (NumberFormatException e) {
            System.out.println("Error: La nota debe ser un número.");
        }
    }
}
