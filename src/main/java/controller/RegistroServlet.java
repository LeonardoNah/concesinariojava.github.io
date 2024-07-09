
package controller;

import dao.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

@WebServlet ("/registro")
public class RegistroServlet extends HttpServlet{
    
    protected void doPost(HttpServletRequest request, 
        HttpServletResponse response) 
        throws ServletException,IOException{
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setPassword(password);
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuarioDAO.insertarUsuario(usuario);
            response.sendRedirect("index.html?exito=true");
        } catch (Exception e) {
            response.sendRedirect("/index.html?error=true");
        }
    }
    
} 
