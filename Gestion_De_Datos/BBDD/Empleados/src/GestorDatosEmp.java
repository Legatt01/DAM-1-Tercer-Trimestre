import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

public class GestorDatosEmp {
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void addRegistro(String nombre, String apellido, double salario) {
        try (Connection con = obtenerConexion();) {
            // Lógica para agregar registro en la base de datos
            String sqlStr = "INSERT INTO empleados (nombre, apellido, salario) VALUES ('" + nombre + "', '" + apellido
                    + "', " + salario + ")";

            Statement s = con.createStatement();
            s.executeUpdate(sqlStr);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void updateRegistro(String nombreActual, String apellidoActual, String nuevoNombre, String nuevoApellido,
            double salario) {
        try (Connection con = obtenerConexion();) {
            String sqlStr = "UPDATE empleados SET nombre = '" + nuevoNombre + "', apellido = '" + nuevoApellido
                    + "', salario = " + salario + " "
                    + "WHERE nombre = '" + nombreActual + "' AND apellido = '" + apellidoActual + "'";
            Statement s = con.createStatement();
            int filasActualizadas = s.executeUpdate(sqlStr);
            System.out.println("Filas actualizadas: " + filasActualizadas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarEmpleado(String nombre, String apellido) {
        String sqlStr = "DELETE FROM empleados WHERE nombre = '" + nombre + "' AND apellido = '" + apellido + "'";
        try (Connection con = obtenerConexion();) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlStr);
            if (rs.next()) {
                System.out.println("Empleado eliminado: " + nombre + " " + apellido);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error al eliminar empleado: " + e.getMessage());
        }
    }

    public String[] listarEmpleados() {
        String[] empleados = new String[100]; // Asumiendo un máximo de 100 empleados
        int i = 0;
        try (Connection con = obtenerConexion();) {
            String sqlStr = "SELECT * FROM empleados";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sqlStr);
            while (rs.next() && i < 100) {
                empleados[i] = rs.getString("nombre") + " " + rs.getString("apellido") + " - Salario: "
                        + rs.getDouble("salario");
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error al listar empleados: " + e.getMessage());
        }
        return empleados;
    }

    public static Connection obtenerConexion() {
        String url = "jdbc:mariadb://localhost:3306/empleadosdb";
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
