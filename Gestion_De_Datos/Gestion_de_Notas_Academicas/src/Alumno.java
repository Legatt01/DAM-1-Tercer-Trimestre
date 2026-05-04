import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Alumno implements Serializable {
    private String nombre, curso;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre + "(" + curso + ")\n");
        if(notas.isEmpty())
            sb.append("\tSin notas.\n");
        else {
            for (Map.Entry<Asignatura, Double> nota : notas.entrySet()) {
                sb.append("\t" + nota.getKey().getNombre() + ": " + nota.getValue() + "\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    
}
