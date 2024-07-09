package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String url = "jdbc:mysql://localhost:3306/proyectoJava_24112";
    private static final String user = "root";
    private static final String password = "adminroot1991#";

    public static Connection obtenerConexion() throws SQLException {
        try {
            //cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontro el driver JDBC", e);
        }
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        try {
            Connection con = ConexionDB.obtenerConexion();
            if (con != null) {
                System.out.println("Conexion exitosa");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
