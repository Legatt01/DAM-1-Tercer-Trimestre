import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GestorDatos {
    private ArrayList<Alumno> alumnos;
    private ArrayList<Asignatura> asignaturas;

    public GestorDatos() {
        this.alumnos = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

//GESTION DE ALUMNOS  
    public void addAlumno(String nombre, String curso) {
        alumnos.add(new Alumno(nombre, curso));
    }
    
    public void removeAlumno(String nombre) {
        Alumno alumnoEliminar = null;
        for (Alumno alumno : alumnos) {
            if(nombre.equalsIgnoreCase(alumno.getNombre()))
                alumnoEliminar = alumno;
        }
        if(alumnoEliminar != null)
            alumnos.remove(alumnoEliminar);
    }

    public void listAlumnos() {
        System.out.println("\n=== Lista de alumnos ===\n");
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }

//GESTION ASIGNATURAS
    public void addAsignatura(String nombre) {
        asignaturas.add(new Asignatura(nombre));
    }    

    public void removeAsignatura(String nombre) {
        Asignatura asigEliminar = null;
        for (Asignatura asignatura : asignaturas) {
            if(nombre.equalsIgnoreCase(asignatura.getNombre()))
                asigEliminar = asignatura;            
        }
        if(asigEliminar != null)
            asignaturas.remove(asigEliminar);
    }

    public void listAsignatura(){
        System.out.println("=== Lista de Asignaturas ===\n");
        for (Asignatura asignatura : asignaturas) {
            System.out.println(asignatura);
        }
    }

//GESTION NOTAS
    public void ponerNotas(Scanner sc) {
        System.out.print("Dime el nombre del alumno: ");
        String nombre = sc.nextLine();
        boolean encontrado = false;

        for (Alumno alumno : alumnos) {
            if(nombre.equalsIgnoreCase(alumno.getNombre())){
                encontrado = true;
                HashMap<Asignatura, Double> notasAlumno = alumno.getNotas();
                for (Asignatura asignatura : asignaturas) {
                    System.out.print("Dame la nota de " + asignatura.getNombre() + ": ");
                    Double nota = Double.parseDouble(sc.nextLine());
                    notasAlumno.put(asignatura, nota);
                }
            }
        }
        if(!encontrado)
            System.out.println("El alumno no existe.");
    }

    public void quitarNotas(Scanner sc) {
        System.out.print("Dime el nombre del alumno: ");
        String nombre = sc.nextLine();
        boolean encontrado = false;

        for (Alumno alumno : alumnos) {
            if(nombre.equalsIgnoreCase(alumno.getNombre())){
                encontrado = true;
                alumno.setNotas(new HashMap<>());
            }
        }
        if(!encontrado)
            System.out.println("El alumno no existe.");
    }

//GESTION DATOS
    @SuppressWarnings("unchecked")
    public void cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("datos.bin"));) {
            alumnos.clear();
            alumnos = (ArrayList<Alumno>) ois.readObject();

            asignaturas.clear();
            asignaturas = (ArrayList<Asignatura>) ois.readObject();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("datos.bin"));) {
            oos.writeObject(alumnos);
            oos.writeObject(asignaturas);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
