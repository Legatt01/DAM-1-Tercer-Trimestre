import java.io.RandomAccessFile;
import java.time.LocalDate;

public class GestorDatosT {
    private final String ARCHIVO = "temperaturas.bin";
    private final int REGISTRO_SIZE = Double.BYTES * 2 + Long.BYTES;

    public void addRegistro(LocalDate fecha, double tMax, double tMin) {
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO, "rw");) {
            raf.seek(raf.length());
            raf.writeLong(fecha.toEpochDay());
            raf.writeDouble(tMax);
            raf.writeDouble(tMin);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void updateRegistro(LocalDate fecha, double tMax, double tMin) {
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO, "rw");) {
            for (int i = 0; i < raf.length(); i = i + REGISTRO_SIZE) {
                raf.seek(i);
                if (fecha.toEpochDay() == raf.readLong()) {
                    raf.writeDouble(tMax);
                    raf.writeDouble(tMin);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void showRegistro(LocalDate fecha) {
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO, "r");) {
            boolean encontrado = false;
            for (int i = 0; i < raf.length(); i = i + REGISTRO_SIZE) {
                raf.seek(i);
                if (fecha.toEpochDay() == raf.readLong()) {
                    double tMax = raf.readDouble();
                    double tMin = raf.readDouble();
                    Temperatura temp = new Temperatura(fecha, tMax, tMin);
                    System.out.println("El registro es: " + temp);
                    encontrado = true;
                }
            }
            if (!encontrado)
                System.out.println("Registro no encontrado.");

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public double[] calcularPromedio(int mes) {
        double sumaMax = 0, sumaMin = 0;
        int numTemperaturas = 0;
        double[] resultado = new double[2];

        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO, "r");) {

            for (int i = 0; i < raf.length(); i = i + REGISTRO_SIZE) {
                raf.seek(i);
                if (LocalDate.ofEpochDay(raf.readLong()).getMonthValue() == mes) {
                    sumaMax += raf.readDouble();
                    sumaMin += raf.readDouble();
                    numTemperaturas++;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        if(numTemperaturas > 0) {
            resultado[0] = sumaMax / numTemperaturas;
            resultado[1] = sumaMin / numTemperaturas;
            return resultado;
        }
        else
            return null;
    }
}