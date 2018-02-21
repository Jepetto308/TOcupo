/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Business.ActionCliente;
import entity.Cliente;
import entity.Municipio;

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

import org.json.JSONObject;

/**
 *
 * @author Jefferson Palacios Torres | Tecnologo Analisis y Desarrollo de Software | Correo: Jefferson308@hotmail.com
 */
public class ControlCliente extends HttpServlet {

	ActionCliente oActionCliente = new ActionCliente();
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accion = request.getParameter("accion");
        accion = "".equals(accion) || accion == null? "L":accion;
        
        switch (accion) {
		case "L":
			listarClientes(request, response);
			break;
		case "G":
			guardarCliente(request, response);
			break;
		case "D":
			editarCliente(request, response);
			break;
		case "C":
			consultarCliente(request, response);
			break;
		case "E":
			eliminarCliente(request, response);
			break;
		case "N":
			nuevoCliente(request, response);
			break;
		case "EX":
			exportar(request, response);
			break;
		default:
			break;
		}
        
    }
    
    public void listarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	
    	
        List listaClientes = oActionCliente.listarClientes(request);
        
        request.setAttribute("listaClientes", listaClientes);
        
        String subAccion = request.getParameter("subAccion");
        if("Emergente".equalsIgnoreCase(subAccion)) {
        	request.getRequestDispatcher("/JSP/modal_cliente.jsp").forward(request,response);
        }
        else {
        	request.getRequestDispatcher("/JSP/buscar_clientes.jsp").forward(request,response);
        }
    }
    
    public void guardarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase cliente,conexion,Daocliente,
       	Cliente oCliente = new Cliente();
        llenarCliente(request, oCliente);            
        
        Map proceso = oActionCliente.guardarActualizarCliente(oCliente);
        
        request.setAttribute("oCliente", proceso.get("oCliente"));
        request.setAttribute("okMessage", proceso.get("okMensaje"));
        request.getRequestDispatcher("/JSP/editar_clientes.jsp").forward(request,response);
    }
    
    public void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase cliente,conexion,Daocliente,
    	int idCliente = Integer.parseInt(request.getParameter("hidCliente"));
    	Map parametros = new HashMap();
    	
        Map proceso = oActionCliente.consultarCliente(idCliente, parametros);
        request.setAttribute("oCliente", proceso.get("oCliente"));
        
        request.getRequestDispatcher("/JSP/editar_clientes.jsp").forward(request,response);
    }
    
    public void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase cliente,conexion,Daocliente,
    	int idCliente = Integer.parseInt(request.getParameter("hidCliente"));
    	Map parametros = new HashMap();
    	Map proceso = oActionCliente.eliminarCliente(idCliente, parametros);
    	
    	request.setAttribute("okMessage", proceso.get("okMensaje"));
    	nuevoCliente(request, response);
    }
    
    public void nuevoCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase cliente,conexion,Daocliente,
    	Map parametros = new HashMap();
    	Cliente oCliente = new Cliente();
        request.setAttribute("oCliente", oCliente);
        
        request.getRequestDispatcher("/JSP/editar_clientes.jsp").forward(request,response);
    }
    
    public void llenarCliente(HttpServletRequest request, Cliente oClientes) {
        String stmp = "";
        
        //parametros que vienen desde la vista
        stmp = request.getParameter("hidCliente");
        oClientes.setIdCliente(stmp == "" ?0:Integer.parseInt(stmp));
        
        stmp = request.getParameter("tipoDocumento");
        oClientes.setTipoDocumentoCliente(stmp);
        stmp = request.getParameter("numeroDocumento");
        oClientes.setNumeroDocumentoCliente(Integer.parseInt(stmp));
        stmp = request.getParameter("primerNombre");
        oClientes.setPrimerNombreCliente(stmp);
        stmp = request.getParameter("otrosNombres");
        oClientes.setOtrosNombresCliente(stmp);
        stmp = request.getParameter("primerApellido");
        oClientes.setPrimerApellidoCliente(stmp);
        stmp = request.getParameter("segundoApellido");
        oClientes.setSegundoApellidoCliente(stmp);
        stmp = request.getParameter("razonSocial");
        oClientes.setRazonSocialCliente(stmp);
        stmp = request.getParameter("direccion");
        oClientes.setDireccionCliente(stmp);
        stmp = request.getParameter("telefono");
        oClientes.setTelefonoCliente(stmp);
        stmp = request.getParameter("celular");
        oClientes.setCelularCliente(stmp);
        stmp = request.getParameter("fax");
        oClientes.setFaxCliente(stmp);
        stmp = request.getParameter("email");
        oClientes.setEmailCliente(stmp);
        stmp = request.getParameter("codigoPais");
        oClientes.setCodigoPais(stmp);
        stmp = request.getParameter("nombrePais");
        oClientes.setNombrePais(stmp);
        stmp = request.getParameter("codigoMunicipio");
        oClientes.setCodigoMunicipio(stmp);
        stmp = request.getParameter("nombreMunicipio");
        oClientes.setNombreMunicipio(stmp);
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
    	response.setHeader("Content-Disposition", "attachment; filename=\"" + "Clientes" + "."+formato+"\";");
    	OutputStream out = response.getOutputStream();
    	String rutaJasper = getServletContext().getRealPath("/Jasper");
    	Map parametros = new HashMap();
    	parametros.put("request", request);
    	
    	oActionCliente.exportar(rutaJasper, parametros, out, false, formato);
    }
    
    public void consultarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, ClassNotFoundException, SQLException, IOException {
    	
    	int numeroDocumentoCliente = request.getParameter("numeroDocumentoCliente") != "" ? Integer.parseInt(request.getParameter("numeroDocumentoCliente")) : 0;
    	Cliente oCliente = new Cliente();
    	Map parametros = new HashMap();
    	
    	oCliente = (Cliente) ((Map) oActionCliente.consultarClientePorDocumento(numeroDocumentoCliente, parametros)).get("oCliente");
    	
    	if("".equals(oCliente.getNumeroDocumentoCliente()) || oCliente.getIdCliente() == 0) {
    		response.getWriter().print("N");
    	}else {
    		JSONObject oClienteJSON = new JSONObject(oCliente);
    		response.getWriter().print(oClienteJSON);
    	}
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
