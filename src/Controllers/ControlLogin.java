
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Business.ActionUsuario;
import entity.Usuario;
import Utils.Encriptador;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jefferson Palacios Torres | Correo: jefferson308@hotmail.com
 */
public class ControlLogin extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
			proccesRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
			proccesRequest(request, response);
    }
    
    protected void proccesRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
    	
    	if(request.getSession().getAttribute("user") == null){
    		try {
				iniciarSession(request, response);
			} catch (ClassNotFoundException e) {
				throw new ServletException(e.getMessage());
			} catch (IOException e) {
				throw new ServletException(e.getMessage());
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				throw new ServletException(e.getMessage());
			}
    	}else{
    		try {
				cerrarSession(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
            
    }
    
    
    public void iniciarSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
    	
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        ActionUsuario oActionUsuario = new ActionUsuario();
        Usuario oUsuario = new Usuario();
		try {
			oUsuario = oActionUsuario.login(username, Encriptador.Encriptar(password));
		} catch (SQLException e) {
			e.printStackTrace();
		}            
        
        if("".equals(oUsuario.getUsername())){
            response.getWriter().print(true);
        }else{
        	HttpSession objSesion = request.getSession(true); 
            objSesion.setAttribute("user", oUsuario );
        }
    }
    
    public void cerrarSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	request.getSession().invalidate();
    	request.getRequestDispatcher("login_DataPets.jsp").forward(request, response);
    }
}
