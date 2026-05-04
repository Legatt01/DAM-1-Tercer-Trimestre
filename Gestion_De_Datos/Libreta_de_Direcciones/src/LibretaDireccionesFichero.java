import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LibretaDireccionesFichero {
    private ArrayList<Contacto> contactos;
    private Scanner sc;

    LibretaDireccionesFichero() {
        contactos = new ArrayList<>();
        sc = new Scanner(System.in);

    }

    public static void main(String[] args) {
        LibretaDireccionesFichero ldf = new LibretaDireccionesFichero();
        ldf.mostrarMenu();
        ldf.sc.close();
    }

    private void cls() {
        System.out.print("\033[H\033[2J");
    }

    public void mostrarMenu() {
        int opc;
        do {
            cls();
            System.out.println("=== Libreta de Direcciones ===");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Listar contactos");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Guardar libreta");
            System.out.println("6. Cargar libreta");
            System.out.println("7. Salir");
            System.out.print("\nSeleccione una opción: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    agregarContacto();
                    break;
                case 2:
                    listarContactos();
                    break;
                case 3:
                    buscarContacto();
                    break;
                case 4:
                    eliminarContacto();
                    break;
                case 5:
                    guardarLibreta();
                    break;
                case 6:
                    cargarLibreta();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    sc.close();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    System.out.println("Oprima Enter para continuar...");
                    sc.nextLine();
            }
        } while (opc != 7);
    }

    private void agregarContacto() {
        cls();
        System.out.println("=== Agregar Contacto ===");
        System.out.println("Dame el nombre del contacto:");
        String nombre = sc.nextLine();
        System.out.println("Dame el teléfono del contacto:");
        String telefono = sc.nextLine();;
        contactos.add(new Contacto(nombre, telefono));
        System.out.println("Contacto agregado correctamente.");
        System.out.println("Oprima Enter para continuar...");
        sc.nextLine();
    }

    private void listarContactos() {
        cls();
        System.out.println("=== Listar Contactos ===");
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos en la libreta.");
        } else {
            for (Contacto contacto : contactos) {
                System.out.println(contacto);
            }
        }
        System.out.println("Oprima Enter para continuar...");
        sc.nextLine();
    }

    private void buscarContacto() {
        cls();
        System.out.println("=== Buscar Contacto ===");
        System.out.println("Dame el nombre del contacto:");
        String nombre = sc.nextLine();
        boolean encontrado = false;
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Contacto encontrado:");
                System.out.println(contacto);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Contacto no encontrado.");
        }
        System.out.println("Oprima Enter para continuar...");
        sc.nextLine();
    }

    private void eliminarContacto() {
        cls();
        System.out.println("=== Eliminar Contacto ===");
        System.out.println("Dame el nombre del contacto:");
        String nombre = sc.nextLine();
        boolean eliminado = false;
        Contacto contactoAEliminar = null;

        for (Contacto contacto : contactos) {
            if(nombre.toLowerCase().equals(contacto.getNombre().toLowerCase())) {
                System.out.println(contacto);
                System.out.print("\t¿Quieres eliminarlo (s/n)? ");

                if(sc.nextLine().equalsIgnoreCase("s"))
                    contactoAEliminar = contacto;

                eliminado = true;
            }            
        }
        
        if(!eliminado)
            System.out.println("\nContacto no encontrado.");
        else
            contactos.remove(contactoAEliminar);

        System.out.println("\n[pulse intro para continuar]");
        sc.nextLine();
        
    }

    private void guardarLibreta() {
        cls();
        System.out.println("=== Guardar Libreta ===");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("libreta.bin"))) {

            for (Contacto contacto : contactos) {
                oos.writeObject(contacto);
            }
            System.out.println("\nGuardado correctamente.");
            System.out.println("\n[pulse intro para continuar]");
            sc.nextLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarLibreta() {
        contactos.clear();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("libreta.bin"))) {
            Contacto contacto;
            while (true) {
                contacto = (Contacto) ois.readObject();
                contactos.add(contacto);
            }

        } catch (EOFException oefe) {
            System.out.println("\nCargado correctamente.");
            System.out.println("\n[pulse intro para continuar]");
            sc.nextLine();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
