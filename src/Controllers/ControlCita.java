/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Business.ActionCita;
import Business.ActionTipoCita;
import entity.Cita;
import entity.parametros;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class ControlCita extends HttpServlet {

	ActionCita oActionCita = new ActionCita();
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException, NumberFormatException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accion = request.getParameter("accion");
        accion = "".equals(accion) || accion == null? "L":accion;
        
        switch (accion) {
		case "L":
			listarCitas(request, response);
			break;
		case "G":
			guardarCita(request, response);
			break;
		case "D":
			editarCita(request, response);
			break;
		case "E":
			eliminarCita(request, response);
			break;
		case "N":
			nuevoCita(request, response);
			break;
		case "EX":
			exportar(request, response);
			break;
		default:
			break;
		}
        
    }
    
    public void listarCitas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	
    	Map respuesta = new HashMap();
        try {
			respuesta = oActionCita.listarCitas(request,respuesta);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        request.getRequestDispatcher("/JSP/buscar_citas.jsp").forward(request,response);
    }
    
    public void guardarCita(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase cliente,conexion,Daocliente,
       	Cita oCita = new Cita();
        llenarCita(request, oCita);            
        
        Map proceso = oActionCita.guardarActualizarCita(oCita);
        
        request.setAttribute("oCita", proceso.get("oCita"));
        request.setAttribute("okMessage", proceso.get("okMensaje"));
        request.setAttribute("listaTiposCitas", proceso.get("listaTiposCitas"));
        request.getRequestDispatcher("/JSP/editar_citas.jsp").forward(request,response);
    }
    
    public void editarCita(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase cliente,conexion,Daocliente,
    	int idCita = Integer.parseInt(request.getParameter("hidCita"));
    	Map parametros = new HashMap();
    	
        Map proceso = oActionCita.consultarCita(idCita, parametros);
        request.setAttribute("oCita", proceso.get("oCita"));
        request.setAttribute("listaTiposCitas", proceso.get("listaTiposCitas"));
        
        request.getRequestDispatcher("/JSP/editar_citas.jsp").forward(request,response);
    }
    
    public void eliminarCita(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase cliente,conexion,Daocliente,
    	int idCita = Integer.parseInt(request.getParameter("hidCita"));
    	Map parametros = new HashMap();
    	Map proceso = oActionCita.eliminarCita(idCita, parametros);
    	
    	request.setAttribute("okMessage", proceso.get("okMensaje"));
    	nuevoCita(request, response);
    }
    
    public void nuevoCita(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	//instancias de la la clase cliente,conexion,Daocliente,
    	Map parametros = new HashMap();
    	Cita oCita = new Cita();
        request.setAttribute("oCita", oCita);
        ActionTipoCita oActionTipoCita = new ActionTipoCita();
		List listaTiposCitas = oActionTipoCita.listarTipoCitas();
		request.setAttribute("listaTiposCitas", listaTiposCitas);
        
        request.getRequestDispatcher("/JSP/editar_citas.jsp").forward(request,response);
    }
    
    public void llenarCita(HttpServletRequest request, Cita oCitas) {
         String stmp = "";
        
        //parametros que vienen desde la vista
        stmp = request.getParameter("hidCita");
        oCitas.setIdCita(stmp == "" ?0:Integer.parseInt(stmp));
        stmp = request.getParameter("numeroCita");
        oCitas.setNumeroCita(stmp);
        stmp = request.getParameter("fechaCita");
        String hora = "",minutos = "";
        hora = request.getParameter("horaCita");
        minutos = request.getParameter("minutosCita");
        DateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm");
        Date fechaCita = null;
		try {
			fechaCita = sdf.parse(stmp+" "+hora+":"+minutos);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        oCitas.setFechaCita(new Timestamp(fechaCita.getTime()));
        stmp = request.getParameter("idServicioCita");
        oCitas.setIdServicioCita(stmp == "" || stmp == null ?0:Integer.parseInt(stmp));
        stmp = request.getParameter("estadoCita");
        oCitas.setEstadoCita(stmp);
        stmp = request.getParameter("idCliente");
        oCitas.setIdCliente(stmp == "" || stmp == null ?0:Integer.parseInt(stmp));
        stmp = request.getParameter("nombreCompletoCliente");
        oCitas.setNombreCompletoCliente(stmp);
        stmp = request.getParameter("numeroDocumentoCliente");
        oCitas.setNumeroDocumentoCliente(stmp == "" || stmp == null ?0:Integer.parseInt(stmp));
        
    }
    
    
    public void exportar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, ClassNotFoundException, SQLException, ParseException {
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
    	response.setHeader("Content-Disposition", "attachment; filename=\"" + "Citas" + "."+formato+"\";");
    	OutputStream out = response.getOutputStream();
    	String rutaJasper = getServletContext().getRealPath("/Jasper");
    	Map parametros = new HashMap();
    	parametros.put("request", request);
    	
    	oActionCita.exportar(request, rutaJasper, parametros, out, false, formato);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControlCita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlCita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControlCita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlCita.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

}
