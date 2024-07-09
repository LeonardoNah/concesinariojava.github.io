package dao;

import conexion.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

public class UsuarioDAO {

    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO registroUsuarios (correo , password) VALUES (? , ?)";

        try (Connection conn = ConexionDB.obtenerConexion(); 
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getCorreo());
            pstmt.setString(2, usuario.getPassword());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private Usuario extraerUsuarioResultSet(ResultSet rs) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setCorreo(rs.getString("correo"));
        usuario.setPassword(rs.getString("password"));
        return usuario;
    }

    public List<Usuario> obtenerTodos() {

        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM registroUsuarios";

        try (Connection conn = ConexionDB.obtenerConexion(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Usuario usuario = extraerUsuarioResultSet(rs);
                usuarios.add(usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;

    }

    public Usuario obtenerPorId(int id) {
        String query = "SELECT * FROM registroUsuarios WHERE id = ?";
        try (Connection conn = ConexionDB.obtenerConexion(); 
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extraerUsuarioResultSet(rs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean modificar(Usuario usuario){
        String query = "UPDATE registroUsuarios SET correo = ? , password=? WHERE id=?";
        try (Connection conn = ConexionDB.obtenerConexion();
                PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setString(1, usuario.getCorreo());
            pstmt.setString(2, usuario.getPassword()); 
            pstmt.setInt(3, usuario.getId());
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminar(int id){
        String query = "DELETE FROM registroUsuarios WHERE id=? ";
        try (Connection conn = ConexionDB.obtenerConexion();
                PreparedStatement pstmt = conn.prepareStatement(query)){
            
            pstmt.setInt(1,id);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}//fin clase usuariodao

