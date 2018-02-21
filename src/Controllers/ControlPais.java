
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Business.ActionPais;
import Business.ActionUsuario;
import entity.Pais;
import entity.Usuario;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

/**
 *
 * @author Jefferson Palacios Torres | Correo: jefferson308@hotmail.com
 */
public class ControlPais extends HttpServlet {
    
	ActionPais oActionPais = new ActionPais();
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
			try {
				proccesRequest(request, response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
			try {
				proccesRequest(request, response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    }
    
    protected void proccesRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException, SQLException, IOException {
    	
    	String accion = request.getParameter("accion");
    	
    	if("".equals(accion) || accion == null) {
    		accion = "L";
    	}
    	
    	switch (accion) {
		case "L":
			listarPais(request, response);
			break;
		case "C":
			consultarPais(request, response);
			break;
		default:
			break;
		}
    }
    
    public void listarPais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException, SQLException, IOException {
    	
    	List listaPais = oActionPais.listarPais(request);
    	
    	request.setAttribute("listaPais", listaPais);
    	request.getRequestDispatcher("JSP/modal_pais.jsp").forward(request,response);
    }
    
    
    public void consultarPais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException, SQLException, IOException {
    	
    	String codigoPais = request.getParameter("codigoPais");
    	Pais oPais = new Pais();
    	Map parametros = new HashMap();
    	
    	oPais = oActionPais.consultarUsuario(codigoPais, parametros);
    	
    	if("".equals(oPais.getCodigoPais())) {
    		response.getWriter().print("N");
    	}else {
    		JSONObject oPaisJSON = new JSONObject(oPais);
    		response.getWriter().print(oPaisJSON);
    	}
    }
    
}
