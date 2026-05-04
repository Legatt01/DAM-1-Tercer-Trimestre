import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

public class GestorUni {
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public boolean inscribirAlumno(String matricula, String clave, String semestre) {
        String sql = "INSERT INTO matriculas (estudiante_id, curso_id, estatus) " +
                "SELECT e.id, c.id, 'inscrito' " +
                "FROM estudiantes e, cursos c " +
                "WHERE e.matricula = ? AND c.clave = ? AND c.semestre = ?";

        try (Connection con = obtenerConexion();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Pasamos los argumentos directamente al PreparedStatement
            pstmt.setString(1, matricula);
            pstmt.setString(2, clave);
            pstmt.setString(3, semestre);

            int filas = pstmt.executeUpdate();
            return filas > 0; // Retorna true si se insertó correctamente

        } catch (SQLException e) {
            System.out.println("Error en el DAO: " + e.getMessage());
            return false;
        }
    }

    public boolean subirNota(String matricula, String clave, String semestre, String tipoExamen, double nota) {
    String sql = "UPDATE matriculas m " +
                 "JOIN estudiantes e ON m.estudiante_id = e.id " +
                 "JOIN cursos c ON m.curso_id = c.id " +
                 "SET m." + tipoExamen + " = ? " + 
                 "WHERE e.matricula = ? AND c.clave = ? AND c.semestre = ?";

        try (Connection con = obtenerConexion();
         PreparedStatement pstmt = con.prepareStatement(sql)) {
        
        pstmt.setDouble(1, nota);
        pstmt.setString(2, matricula);
        pstmt.setString(3, clave);
        pstmt.setString(4, semestre);

        return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
        e.printStackTrace();
        return false;
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
        String[] empleados = new String[100];
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
        String url = "jdbc:mariadb://localhost:3306/universidad";
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
