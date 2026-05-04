import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Temperatura {
    private LocalDate fecha;
    private double tMaxima, tMinima;

    public Temperatura(LocalDate fecha, double tMaxima, double tMinima) {
        this.fecha = fecha;
        this.tMaxima = tMaxima;
        this.tMinima = tMinima;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getTMaxima() {
        return tMaxima;
    }

    public double getTMinima() {
        return tMinima;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void settMaxima(double tMaxima) {
        this.tMaxima = tMaxima;
    }

    public void settMinima(double tMinima) {
        this.tMinima = tMinima;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Temperatura{" +
                "fecha=" + fecha.format(formato) +
                ", tMaxima=" + tMaxima +
                ", tMinima=" + tMinima +
                '}';
    }
}
