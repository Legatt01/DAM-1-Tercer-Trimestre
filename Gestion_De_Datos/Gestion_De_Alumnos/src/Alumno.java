import java.io.Serializable;
import java.util.HashMap;

public class Alumno implements Serializable {
    private String nombre;
    private String curso;
    private HashMap<Asignatura, Double> notas;

    public Alumno(String nombre, String curso) {
        this.nombre = nombre;
        this.curso = curso;
        this.notas = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public HashMap<Asignatura, Double> getNotas() {
        return notas;
    }

    public void setNotas(HashMap<Asignatura, Double> notas) {
        this.notas = notas;
    }

    public void añadirNota(Asignatura asignatura, Double nota) {
        this.notas.put(asignatura, nota);
    }

    public void eliminarNota(Asignatura asignatura) {
        if (this.notas.containsKey(asignatura)) {
            this.notas.put(asignatura, 0.0);
        }
    }

    public void modificarNota(Asignatura asignatura, Double nota) {
        this.notas.put(asignatura, nota);
    }
}
