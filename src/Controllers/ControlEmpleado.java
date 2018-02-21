/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Business.ActionEmpleado;
import entity.Empleado;
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
public class ControlEmpleado extends HttpServlet {

	ActionEmpleado oActionEmpleado = new ActionEmpleado();
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accion = request.getParameter("accion");
        accion = "".equals(accion) || accion == null? "L":accion;
        
        switch (accion) {
		case "L":
			listarEmpleados(request, response);
			break;
		case "G":
			guardarEmpleado(request, response);
			break;
		case "D":
			editarEmpleado(request, response);
			break;
		case "E":
			eliminarEmpleado(request, response);
			break;
		case "N":
			nuevoEmpleado(request, response);
			break;
		case "EX":
			exportar(request, response);
			break;
		default:
			break;
		}
        
    }
    
    public void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	
    	
        List listaEmpleados = oActionEmpleado.listarEmpleados(request);
        
        request.setAttribute("listaEmpleados", listaEmpleados);
        request.getRequestDispatcher("/JSP/buscar_empleados.jsp").forward(request,response);
    }
    
    public void guardarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase Empleado,conexion,DaoEmpleado,
       	Empleado oEmpleado = new Empleado();
        llenarEmpleado(request, oEmpleado);            
        
        Map proceso = oActionEmpleado.guardarActualizarEmpleado(oEmpleado);
        
        request.setAttribute("oEmpleado", proceso.get("oEmpleado"));
        request.setAttribute("okMessage", proceso.get("okMensaje"));
        request.getRequestDispatcher("/JSP/editar_empleados.jsp").forward(request,response);
    }
    
    public void editarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase Empleado,conexion,DaoEmpleado,
    	int idEmpleado = Integer.parseInt(request.getParameter("hidEmpleado"));
    	Map parametros = new HashMap();
    	
        Map proceso = oActionEmpleado.consultarEmpleado(idEmpleado, parametros);
        request.setAttribute("oEmpleado", proceso.get("oEmpleado"));
        
        request.getRequestDispatcher("/JSP/editar_empleados.jsp").forward(request,response);
    }
    
    public void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase Empleado,conexion,DaoEmpleado,
    	int idEmpleado = Integer.parseInt(request.getParameter("hidEmpleado"));
    	Map parametros = new HashMap();
    	Map proceso = oActionEmpleado.eliminarEmpleado(idEmpleado, parametros);
    	
    	request.setAttribute("okMessage", proceso.get("okMensaje"));
    	nuevoEmpleado(request, response);
    }
    
    public void nuevoEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase Empleado,conexion,DaoEmpleado,
    	Map parametros = new HashMap();
    	Empleado oEmpleado = new Empleado();
        request.setAttribute("oEmpleado", oEmpleado);
        
        request.getRequestDispatcher("/JSP/editar_empleados.jsp").forward(request,response);
    }
    
    public void llenarEmpleado(HttpServletRequest request, Empleado oEmpleados) {
        String stmp = "";
        
        //parametros que vienen desde la vista
        stmp = request.getParameter("hidEmpleado");
        oEmpleados.setIdEmpleado(stmp == "" ?0:Integer.parseInt(stmp));
        
        stmp = request.getParameter("tipoDocumento");
        oEmpleados.setTipoDocumentoEmpleado(stmp);
        stmp = request.getParameter("numeroDocumento");
        oEmpleados.setNumeroDocumentoEmpleado(stmp);
        stmp = request.getParameter("primerNombre");
        oEmpleados.setPrimerNombreEmpleado(stmp);
        stmp = request.getParameter("otrosNombres");
        oEmpleados.setOtrosNombresEmpleado(stmp);
        stmp = request.getParameter("primerApellido");
        oEmpleados.setPrimerApellidoEmpleado(stmp);
        stmp = request.getParameter("segundoApellido");
        oEmpleados.setSegundoApellidoEmpleado(stmp);
        stmp = request.getParameter("direccion");
        oEmpleados.setDireccionEmpleado(stmp);
        stmp = request.getParameter("telefono");
        oEmpleados.setTelefonoEmpleado(stmp);
        stmp = request.getParameter("celular");
        oEmpleados.setCelularEmpleado(stmp);
        stmp = request.getParameter("fax");
        oEmpleados.setFaxEmpleado(stmp);
        stmp = request.getParameter("email");
        oEmpleados.setCorreoEmpleado(stmp);
        stmp = request.getParameter("codigoPais");
        oEmpleados.setCodigoPais(stmp);
        stmp = request.getParameter("nombrePais");
        oEmpleados.setNombrePais(stmp);
        stmp = request.getParameter("codigoMunicipio");
        oEmpleados.setCodigoMunicipio(stmp);
        stmp = request.getParameter("nombreMunicipio");
        oEmpleados.setNombreMunicipio(stmp);
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
    	response.setHeader("Content-Disposition", "attachment; filename=\"" + "Empleados" + "."+formato+"\";");
    	OutputStream out = response.getOutputStream();
    	String rutaJasper = getServletContext().getRealPath("/Jasper");
    	Map parametros = new HashMap();
    	parametros.put("request", request);
    	
    	oActionEmpleado.exportar(rutaJasper, parametros, out, false, formato);
    	out.close();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControlEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControlEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
