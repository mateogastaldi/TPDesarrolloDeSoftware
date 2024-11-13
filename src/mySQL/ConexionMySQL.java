package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static Connection con;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/tpdesarrollo";
    private static final String user = "mateo";
    private static final String password = "mateo";

    public void conectar() {
        con = null;
        try {
            Class.forName(driver);

            con = (Connection) DriverManager.getConnection(url,user,password);

            if(con != null) {
                System.out.println("Conectado correctamente");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error conexion " + e.getMessage());
        }
    }
}
