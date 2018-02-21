
package Controllers;

import entity.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Business.ActionUsuario;

/**
 *
 * @author Jefferson Palacios Torres | Correo: jefferson308@hotmail.com
 */
public class ControlAccesoPages extends HttpServlet {
	
	Map noPermisos = new HashMap();
	
	public Map getNoPermisos() {
		return noPermisos;
	}
	
    public void validatePemisions(HttpServletRequest request, HttpServletResponse response,String modulo,Map noPermisos)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession objSesion = request.getSession(false);
        Usuario oUsuario;
        
        if (objSesion == null || objSesion.getAttribute("user") == null) {
            request.getRequestDispatcher("noLogin.jsp").forward(request, response); 
        } else {
            oUsuario = (Usuario) objSesion.getAttribute("user");
            request.setAttribute("user", oUsuario);
            
            ActionUsuario oActionUser = new ActionUsuario();
            boolean acceso = oActionUser.validarEntrada(request, modulo, oUsuario.getUsername());
            
            if("ROOT".equalsIgnoreCase(modulo) || "ROOT".equalsIgnoreCase(oUsuario.getCodigoRol())) {
            	acceso = true;
            }
            else if("TODOS".equalsIgnoreCase(modulo)) {
            	acceso = true;
            }
            
            if (!acceso) {
                request.getRequestDispatcher("noPermisin.jsp").forward(request, response); 
            }
        }
    }
    
    public boolean validatePemisionForOption(HttpServletRequest request, HttpServletResponse response,String modulo,String codigoOpcion)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession objSesion = request.getSession(false);
        Usuario oUsuario;
        
        if (objSesion == null || objSesion.getAttribute("user") == null) {
            return false; 
        } else {
            oUsuario = (Usuario) objSesion.getAttribute("user");
            request.setAttribute("user", oUsuario);
            
            ActionUsuario oActionUser = new ActionUsuario();
            boolean acceso = oActionUser.validarEntradaPorOpcion(request, modulo, codigoOpcion,oUsuario.getUsername());
            if("ROOT".equalsIgnoreCase(oUsuario.getCodigoRol())) {
            	acceso = true;
            }
            return acceso;
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
