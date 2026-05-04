import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

public class GestorDatosT {
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void addRegistro(LocalDate fecha, double tMax, double tMin) {
        try (Connection con = obtenerConexion();) {
            // Lógica para agregar registro en la base de datos
            String sqlStr = "INSERT INTO temperaturas (fecha, tMax, tMin) VALUES ('" + fecha + "'," + tMax + "," + tMin + ")";

            Statement s = con.createStatement();
            s.executeUpdate(sqlStr);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void updateRegistro(LocalDate fecha, double tMax, double tMin) {
        try (Connection con = obtenerConexion();) {
            String sqlStr = "UPDATE temperaturas SET tMax = " + tMax + ", tMin = " + tMin + " WHERE fecha = '" + fecha + "'";
            Statement s = con.createStatement();
            s.executeUpdate(sqlStr);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void showRegistro(LocalDate fecha) {
        try (Connection con = obtenerConexion();) {
            String sqlStr = "SELECT * FROM temperaturas WHERE fecha = '" + fecha + "'";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlStr);
            while (rs.next()) {
                System.out.println(rs.getString("fecha") + " - TMax: " + rs.getDouble("tMax") + " - TMin: " + rs.getDouble("tMin"));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public double[] calcularPromedio(int mes) {
       String sqlStr = "SELECT AVG(tMax) as promedioMax, AVG(tMin) as promedioMin FROM temperaturas WHERE MONTH(fecha) = " + mes;
       try (Connection con = obtenerConexion();) {
           Statement s = con.createStatement();
           ResultSet rs = s.executeQuery(sqlStr);
           if (rs.next()) {
               return new double[]{rs.getDouble("promedioMax"), rs.getDouble("promedioMin")};
           }
       } catch (Exception e) {
           // TODO: handle exception
           System.out.println("Error al calcular el promedio: " + e.getMessage());
       }
       return new double[2];
    }

    public static Connection obtenerConexion() {
        String url = "jdbc:mariadb://localhost:3306/pruebas";
        String usuario = "root";
        String password = "2223"; 
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexión establecida.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }
}