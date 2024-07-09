
package controller;
import dao.UsersDao;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
        //obtener los parametros del formulario de login
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");
        
        //instanciar el DAO para validar las credenciales
        UsersDao userDao = new UsersDao();
        boolean usuarioValido = userDao.validarUsuario(email,contrasena);
        
        //redigir segun la validacion
        if(usuarioValido){
            response.sendRedirect("html/gestionUsuarios.html");
        }else {
            response.sendRedirect("index.html");
        }
        
        
    }
    
}
