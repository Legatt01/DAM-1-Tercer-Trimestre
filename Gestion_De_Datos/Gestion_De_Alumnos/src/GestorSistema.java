//AUNQUE NO LO CREA PROFE, ALGUNAS COSAS COSAS COMO LOS MENUS Y LOS SERIALIZABLES FUERON HECHAS CON IA

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class GestorSistema implements Serializable {
    private ArrayList<Alumno> alumnos;
    private ArrayList<Asignatura> asignaturas;

    public GestorSistema() {
        this.alumnos = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
    }

    public void agregarAlumno(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    public void agregarAsignatura(Asignatura asignatura) {
        this.asignaturas.add(asignatura);
    }

    public void eliminarAlumno(Alumno alumno) {
        this.alumnos.remove(alumno);
    }

    public void eliminarAsignatura(Asignatura asignatura) {
        this.asignaturas.remove(asignatura);
    }

    public void mostrarAlumnos() {
        for (Alumno alumno : alumnos) {
            System.out.println("Nombre: " + alumno.getNombre() + ", Curso: " + alumno.getCurso());
        }
    }

    public void mostrarAsignaturas() {
        for (Asignatura asignatura : asignaturas) {
            System.out.println("Nombre: " + asignatura.getNombre());
        }
    }

    private Alumno buscarAlumno(String nombre) {
        for (Alumno a : alumnos) {
            if (a.getNombre().equalsIgnoreCase(nombre))
                return a;
        }
        return null;
    }

    private Asignatura buscarAsignatura(String nombre) {
        for (Asignatura asig : asignaturas) {
            if (asig.getNombre().equalsIgnoreCase(nombre))
                return asig;
        }
        return null;
    }

    public void asignarNota(String nombreAlumno, String nombreAsig, Double nota) {

        Alumno al = buscarAlumno(nombreAlumno);
        Asignatura asig = buscarAsignatura(nombreAsig);

        if (al != null && asig != null) {
            al.añadirNota(asig, nota);
            System.out.println("Nota asignada.");
        }
    }

    public void modificarNota(String nombreAlumno, String nombreAsig, Double nota) {
        Alumno al = buscarAlumno(nombreAlumno);
        Asignatura asig = buscarAsignatura(nombreAsig);

        if (al != null && asig != null) {
            al.modificarNota(asig, nota);
            System.out.println("Nota modificada.");
        }
    }

    public void guardarDatos(String nombreFichero) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFichero))) {

            oos.writeObject(this.alumnos);
            oos.writeObject(this.asignaturas);

            System.out.println("Datos guardados correctamente en " + nombreFichero);

        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos(String nombreFichero) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFichero))) {

            this.alumnos = (ArrayList<Alumno>) ois.readObject();
            this.asignaturas = (ArrayList<Asignatura>) ois.readObject();

            System.out.println("Datos cargados correctamente desde " + nombreFichero);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }
}