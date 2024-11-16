// ConexionMySQL.java
package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static Connection con;
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Cambia a "com.mysql.cj.jdbc.Driver" si usas MySQL Connector/J 8.0 o superior
    private static final String url = "jdbc:mysql://localhost:3306/tpdesarrollo";
    private static final String user = "root";
    private static final String password = "mateomysql15";

    public static Connection conectar() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado correctamente");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }

    public static void cerrarConexion() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}