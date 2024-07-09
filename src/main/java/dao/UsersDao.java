
package dao;

import conexion.ConexionDB;
import java.sql.Connection;
import java.sql.*;


public class UsersDao {
    
    public boolean validarUsuario(String email , String contrasena){
        boolean validar = false;
        
        String sql = "SELECT * FROM login WHERE email = ? AND contrasena = ?";
        
        try {
            //obtener la conexion
            Connection conexion = ConexionDB.obtenerConexion();
            //prerarar la consulta
            PreparedStatement consulta = conexion.prepareStatement(sql);
            //argumentos
            consulta.setString(1, email);
            consulta.setString(2, contrasena);
            //ejecutar la consulta
            ResultSet resultado = consulta.executeQuery();
            
            validar = resultado.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return validar;
    }
}
