/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Business.ActionCita;
import Business.ActionReportes;
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
public class ControlReportes extends HttpServlet {

	ActionReportes oActionReportes = new ActionReportes();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException, NumberFormatException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accion = request.getParameter("accion");
        accion = "".equals(accion) || accion == null? "L":accion;
        
        switch (accion) {
        case "L":
			listarReportes(request, response);
			break;
		case "EX":
			exportar(request, response);
			break;
		default:
			break;
		}
        
    }
    
    public void listarReportes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
    	
    	Map respuesta = new HashMap();
        try {
        	String informe = request.getParameter("informe");
			respuesta = oActionReportes.listarReportes(request, respuesta, informe);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        request.getRequestDispatcher("/JSP/"+respuesta.get("jsp")).forward(request,response);
    }
    
    public void exportar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, ClassNotFoundException, SQLException, ParseException {
    	String formato = request.getParameter("formato");
    	String informe = request.getParameter("informe");
    	if("".equals(formato) || null == formato){
    		formato = "pdf";
    	} 
    	
    	if("pdf".equalsIgnoreCase(formato)){
    		response.setContentType("application/pdf");
    	}else{
    		response.setContentType("application/vnd.ms-excel");
    	}
    	response.setHeader("Pragma", "no-cache");
    	response.setHeader("Content-Disposition", "attachment; filename=\"" + informe + "."+formato+"\";");
    	OutputStream out = response.getOutputStream();
    	String rutaJasper = getServletContext().getRealPath("/Jasper");
    	Map parametros = new HashMap();
    	parametros.put("request", request);
    	
    	oActionReportes.exportar(request, rutaJasper, parametros, out, false, formato, informe);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControlReportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlReportes.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControlReportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlReportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

}
