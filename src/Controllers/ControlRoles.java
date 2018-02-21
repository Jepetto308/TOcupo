/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Business.ActionRoles;
import entity.Roles;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jefferson Palacios Torres | Tecnologo Analisis y Desarrollo de Software | Correo: Jefferson308@hotmail.com
 */
public class ControlRoles extends HttpServlet {

	ActionRoles oActionRoles = new ActionRoles();
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accion = request.getParameter("accion");
        accion = "".equals(accion) || accion == null? "L":accion;
        
        switch (accion) {
		case "L":
			listarRoless(request, response);
			break;
		case "G":
			guardarRoles(request, response);
			break;
		case "D":
			editarRoles(request, response);
			break;
		case "E":
			eliminarRoles(request, response);
			break;
		case "N":
			nuevoRoles(request, response);
			break;
		case "EX":
			exportar(request, response);
			break;
		default:
			break;
		}
        
    }
    
    public void listarRoless(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	
    	
        List listaRoles = oActionRoles.listarRoles(request);
        
        request.setAttribute("listaRoles", listaRoles);
        request.getRequestDispatcher("/JSP/buscar_roles.jsp").forward(request,response);
    }
    
    public void guardarRoles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase Roles,conexion,DaoRoles,
       	Roles oRoles = new Roles();
        llenarRoles(request, oRoles);            
        
        Map proceso = oActionRoles.guardarActualizarRoles(oRoles);
        
        request.setAttribute("oRoles", proceso.get("oRoles"));
        request.setAttribute("okMessage", proceso.get("okMensaje"));
        request.getRequestDispatcher("/JSP/editar_roles.jsp").forward(request,response);
    }
    
    public void editarRoles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase Roles,conexion,DaoRoles,
    	String idRoles = request.getParameter("hidRol");
    	Map parametros = new HashMap();
    	
        Map proceso = oActionRoles.consultarRoles(idRoles, parametros);
        request.setAttribute("oRoles", proceso.get("oRoles"));
        
        request.getRequestDispatcher("/JSP/editar_roles.jsp").forward(request,response);
    }
    
    public void eliminarRoles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase Roles,conexion,DaoRoles,
    	String idRoles = request.getParameter("hidRol");
    	Map parametros = new HashMap();
    	Map proceso = oActionRoles.eliminarRoles(idRoles, parametros);
    	
    	request.setAttribute("okMessage", proceso.get("okMensaje"));
    	nuevoRoles(request, response);
    }
    
    public void nuevoRoles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase Roles,conexion,DaoRoles,
    	Map parametros = new HashMap();
    	Roles oRoles = new Roles();
        request.setAttribute("oRoles", oRoles);
        
        request.getRequestDispatcher("/JSP/editar_roles.jsp").forward(request,response);
    }
    
    public void llenarRoles(HttpServletRequest request, Roles oRoles) {
        String stmp = "";
        stmp = request.getParameter("codRol");
        oRoles.setCodRol(stmp);
        stmp = request.getParameter("nombreRol");
        oRoles.setNombreRol(stmp);
        stmp = request.getParameter("descripcion");
        oRoles.setDescripcion(stmp);
    }
    
    
    public void exportar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String formato = request.getParameter("formato");
    	if("".equals(formato) || null == formato){
    		formato = "pdf";
    	} 
    	
    	if("pdf".equalsIgnoreCase(formato)){
    		response.setContentType("application/pdf");
    	}else{
    		response.setContentType("application/vnd.ms-excel");
    	}
    	response.setHeader("Pragma", "no-cache");
    	response.setHeader("Content-Disposition", "attachment; filename=\"" + "Roless" + "."+formato+"\";");
    	OutputStream out = response.getOutputStream();
    	String rutaJasper = getServletContext().getRealPath("/Jasper");
    	Map parametros = new HashMap();
    	parametros.put("request", request);
    	
    	oActionRoles.exportar(rutaJasper, parametros, out, false, formato);
    	out.close();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControlRoles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlRoles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControlRoles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlRoles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
