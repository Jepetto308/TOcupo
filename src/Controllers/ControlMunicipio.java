
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

import Business.ActionMunicipio;
import entity.Municipio;

import org.json.JSONObject;

/**
 *
 * @author Jefferson Palacios Torres | Correo: jefferson308@hotmail.com
 */
public class ControlMunicipio extends HttpServlet {
    
	ActionMunicipio oActionMunicipio = new ActionMunicipio();
	
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
			listarMunicipio(request, response);
			break;
		case "C":
			consultarMunicipio(request, response);
			break;
		default:
			break;
		}
    }
    
    public void listarMunicipio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException, SQLException, IOException {
    	
    	List listaMunicipio = oActionMunicipio.listarMunicipio(request);
    	
    	request.setAttribute("listaMunicipio", listaMunicipio);
    	request.getRequestDispatcher("JSP/modal_municipio.jsp").forward(request,response);
    }
    
    
    public void consultarMunicipio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException, SQLException, IOException {
    	
    	String codigoMunicipio = request.getParameter("codigoMunicipio");
    	Municipio oMunicipio = new Municipio();
    	Map parametros = new HashMap();
    	
    	oMunicipio = oActionMunicipio.consultarUsuario(codigoMunicipio, parametros);
    	
    	if("".equals(oMunicipio.getCodigoMunicipio())) {
    		response.getWriter().print("N");
    	}else {
    		JSONObject oMunicipioJSON = new JSONObject(oMunicipio);
    		response.getWriter().print(oMunicipioJSON);
    	}
    }
    
}
