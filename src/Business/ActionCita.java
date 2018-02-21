package Business;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import entity.Cita;
import entity.Cliente;
import entity.Filtro;
import net.sf.jasperreports.engine.JRException;
import DAO.DaoCita;
import Utils.Conexion;
import Utils.GenerarReporte;
import Utils.Utilidades;;

public class ActionCita {
	private DaoCita oDaoCita = new DaoCita();
	
	public Map listarCitas(HttpServletRequest request,Map parametros) throws NumberFormatException, ClassNotFoundException, SQLException, ParseException{
		Conexion oConexion = new Conexion();
		List lista = new ArrayList();
		List<Filtro> filtros = new ArrayList();
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Filtro oFiltro = new Filtro();
		String numeroCita = request.getParameter("f_numeroCita");
		String idCliente = request.getParameter("idCliente");
		String tipoCita = request.getParameter("f_tipoCita");
		String estadoCita = request.getParameter("f_estadoCita");
		String sFechaCitaDesde = request.getParameter("f_fechaDesde");
		String sFechaCitaHasta = request.getParameter("f_fechaHasta");
		
		
		if(numeroCita != null && !numeroCita.equals("")) {
			oFiltro.setCampo("A.NUMERO_CITA");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(numeroCita+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_numeroCita", numeroCita);
		}
		
		if(idCliente != null && !idCliente.equals("")) {
			oFiltro = new Filtro();
			oFiltro.setCampo("A.ID_CLIENTE");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(idCliente+'%');
			filtros.add(oFiltro);
			
			ActionCliente oActionCliente = new ActionCliente();
			Cliente oCliente = (Cliente) (oActionCliente.consultarCliente(Integer.parseInt(idCliente), parametros).get("oCliente"));
			
			String nombreCompletoCliente = oCliente.getPrimerNombreCliente() + " " + oCliente.getOtrosNombresCliente() + " " +
					oCliente.getPrimerApellidoCliente() + " " + oCliente.getSegundoApellidoCliente();
			
			request.setAttribute("f_nombreCompletoCliente", nombreCompletoCliente);
			request.setAttribute("f_numeroDocumentoCliente", oCliente.getNumeroDocumentoCliente());
			request.setAttribute("idCliente", idCliente);
		}
		
		if(tipoCita != null && !tipoCita.equals("")) {
			oFiltro = new Filtro();
			oFiltro.setCampo("A.ID_SERVICIO_CITA");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(tipoCita+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_tipoCita", tipoCita);
		}
		
		if(estadoCita != null && !estadoCita.equals("")) {
			oFiltro = new Filtro();
			oFiltro.setCampo("A.ESTADO_CITA");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(estadoCita+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_estadoCita", estadoCita);
		}
		
		if(!new Utilidades().esVacio(sFechaCitaDesde) && !new Utilidades().esVacio(sFechaCitaHasta)) {
			oFiltro = new Filtro();
			oFiltro.setCampo("A.FECHA_CITA");
			oFiltro.setOperador("BETWEEN");
			oFiltro.setValor(new java.sql.Date(sdf.parse(sFechaCitaDesde.replaceAll("-", "/")).getTime()));
			oFiltro.setValor2(new java.sql.Date(sdf.parse(sFechaCitaHasta.replaceAll("-", "/")).getTime()));
			filtros.add(oFiltro);
			
			request.setAttribute("f_fechaDesde", sFechaCitaDesde);
			request.setAttribute("f_fechaHasta", sFechaCitaHasta);
		}
		else {
			
			if(sFechaCitaDesde != null && !sFechaCitaDesde.equals("")) {
				oFiltro = new Filtro();
				oFiltro.setCampo("A.FECHA_CITA");
				oFiltro.setOperador(">=");
				oFiltro.setValor(new java.sql.Date(sdf.parse(sFechaCitaDesde.replaceAll("-", "/")).getTime()));
				filtros.add(oFiltro);
				
				request.setAttribute("f_fechaDesde", sFechaCitaDesde);
			}
			
			if(sFechaCitaHasta != null && !sFechaCitaHasta.equals("")) {
				oFiltro = new Filtro();
				oFiltro.setCampo("A.FECHA_CITA");
				oFiltro.setOperador("<=");
				oFiltro.setValor(new java.sql.Date(sdf.parse(sFechaCitaHasta.replaceAll("-", "/")).getTime()));
				filtros.add(oFiltro);
				
				request.setAttribute("f_fechaHasta", sFechaCitaHasta);
			}
		}
		
        try {
        	
			int paginaActual = request.getParameter("f_paginaActual") != null && !request.getParameter("f_paginaActual").equals("") ? 
					Integer.parseInt(request.getParameter("f_paginaActual")) : 1;
			int paginaIrA = request.getParameter("paginaIrA") != null && !request.getParameter("paginaIrA").equals("") ? 
					Integer.parseInt(request.getParameter("paginaIrA")) : 0;
			if(paginaIrA > 0) {
				paginaActual = paginaIrA;
			}
			int tamanioPagina = 10;
			int numeroRegistros = oDaoCita.numeroRegistrosCita(oConexion, filtros);
			int cantidadPaginas = (new BigDecimal(""+numeroRegistros).divide(new BigDecimal("10"),0,0).intValue()); 
			if(filtros.size()>0 && cantidadPaginas <= 1) {
				paginaActual = 0;
			}
			paginaActual = paginaActual >= 1 ? paginaActual : cantidadPaginas;
			int hasta = (paginaActual >= 1 ? paginaActual - 1: 1) * tamanioPagina;
        	hasta = hasta >= 10 ? hasta : 0;
        	
        	request.setAttribute("f_paginaActual", paginaActual);
        	request.setAttribute("paginaIrA", paginaIrA > 0 ? paginaIrA : "");
        	request.setAttribute("cantidadPaginas", new int [cantidadPaginas]);
        	request.setAttribute("numeroPaginas", cantidadPaginas);
			
			lista = oDaoCita.listarCitas(oConexion, filtros,hasta);
			
			ActionTipoCita oActionTipoCita = new ActionTipoCita();
			List listaTiposCitas = oActionTipoCita.listarTipoCitas();
			
			request.setAttribute("listaTiposCitas", listaTiposCitas);
			request.setAttribute("listaCitas", lista);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
        return parametros;
	}
	
	public Map guardarActualizarCita(Cita oCita) throws ClassNotFoundException, SQLException{
		Map datos = new HashMap();
		
		Conexion oConexion = new Conexion();
		oDaoCita.guardarActualizarCita(oConexion, oCita, datos);
		consultarCita(oCita.getIdCita(), datos);
		
		return datos;
	}
	
	public Map consultarCita(int idCita,Map parametros) throws ClassNotFoundException, SQLException{
		
		Conexion oConexion = new Conexion();
		Cita oCita = oDaoCita.consularCita(idCita, parametros, oConexion);
		parametros.put("oCita", oCita);
		
		ActionTipoCita oActionTipoCita = new ActionTipoCita();
		List listaTiposCitas = oActionTipoCita.listarTipoCitas();
		parametros.put("listaTiposCitas", listaTiposCitas);
		
		return parametros;
	}
	
	public Map eliminarCita(int idCita,Map parametros) throws ClassNotFoundException, SQLException{
		Conexion oConexion = new Conexion();
		oDaoCita.eliminarCita(idCita, parametros, oConexion);
		return parametros;
	}
	
	  public void exportar(HttpServletRequest request, String sRutaDirectorioJasper, Map parametros, OutputStream out, boolean maps, String formato) throws NumberFormatException, ClassNotFoundException, SQLException, ParseException{
	    	
		  Conexion oConexion = new Conexion();
			List lista = new ArrayList();
			List<Filtro> filtros = new ArrayList();
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			Filtro oFiltro = new Filtro();
			String numeroCita = request.getParameter("f_numeroCita");
			String idCliente = request.getParameter("idCliente");
			String tipoCita = request.getParameter("f_tipoCita");
			String estadoCita = request.getParameter("f_estadoCita");
			String sFechaCitaDesde = request.getParameter("f_fechaDesde");
			String sFechaCitaHasta = request.getParameter("f_fechaHasta");
			
			
			if(numeroCita != null && !numeroCita.equals("")) {
				oFiltro.setCampo("A.NUMERO_CITA");
				oFiltro.setOperador("LIKE");
				oFiltro.setValor(numeroCita+'%');
				filtros.add(oFiltro);
				
				request.setAttribute("f_numeroCita", numeroCita);
			}
			
			if(idCliente != null && !idCliente.equals("")) {
				oFiltro = new Filtro();
				oFiltro.setCampo("A.ID_CLIENTE");
				oFiltro.setOperador("LIKE");
				oFiltro.setValor(idCliente+'%');
				filtros.add(oFiltro);
				
				ActionCliente oActionCliente = new ActionCliente();
				Cliente oCliente = (Cliente) (oActionCliente.consultarCliente(Integer.parseInt(idCliente), parametros).get("oCliente"));
				
				String nombreCompletoCliente = oCliente.getPrimerNombreCliente() + " " + oCliente.getOtrosNombresCliente() + " " +
						oCliente.getPrimerApellidoCliente() + " " + oCliente.getSegundoApellidoCliente();
				
				request.setAttribute("f_nombreCompletoCliente", nombreCompletoCliente);
				request.setAttribute("f_numeroDocumentoCliente", oCliente.getNumeroDocumentoCliente());
				request.setAttribute("idCliente", idCliente);
			}
			
			if(tipoCita != null && !tipoCita.equals("")) {
				oFiltro = new Filtro();
				oFiltro.setCampo("A.ID_SERVICIO_CITA");
				oFiltro.setOperador("LIKE");
				oFiltro.setValor(tipoCita+'%');
				filtros.add(oFiltro);
				
				request.setAttribute("f_tipoCita", tipoCita);
			}
			
			if(estadoCita != null && !estadoCita.equals("")) {
				oFiltro = new Filtro();
				oFiltro.setCampo("A.ESTADO_CITA");
				oFiltro.setOperador("LIKE");
				oFiltro.setValor(estadoCita+'%');
				filtros.add(oFiltro);
				
				request.setAttribute("f_estadoCita", estadoCita);
			}
			
			if(!new Utilidades().esVacio(sFechaCitaDesde) && !new Utilidades().esVacio(sFechaCitaHasta)) {
				oFiltro = new Filtro();
				oFiltro.setCampo("A.FECHA_CITA");
				oFiltro.setOperador("BETWEEN");
				oFiltro.setValor(new java.sql.Date(sdf.parse(sFechaCitaDesde.replaceAll("-", "/")).getTime()));
				oFiltro.setValor2(new java.sql.Date(sdf.parse(sFechaCitaHasta.replaceAll("-", "/")).getTime()));
				filtros.add(oFiltro);
				
				request.setAttribute("f_fechaDesde", sFechaCitaDesde);
				request.setAttribute("f_fechaHasta", sFechaCitaHasta);
			}
			else {
				
				if(sFechaCitaDesde != null && !sFechaCitaDesde.equals("")) {
					oFiltro = new Filtro();
					oFiltro.setCampo("A.FECHA_CITA");
					oFiltro.setOperador(">=");
					oFiltro.setValor(new java.sql.Date(sdf.parse(sFechaCitaDesde.replaceAll("-", "/")).getTime()));
					filtros.add(oFiltro);
					
					request.setAttribute("f_fechaDesde", sFechaCitaDesde);
				}
				
				if(sFechaCitaHasta != null && !sFechaCitaHasta.equals("")) {
					oFiltro = new Filtro();
					oFiltro.setCampo("A.FECHA_CITA");
					oFiltro.setOperador("<=");
					oFiltro.setValor(new java.sql.Date(sdf.parse(sFechaCitaHasta.replaceAll("-", "/")).getTime()));
					filtros.add(oFiltro);
					
					request.setAttribute("f_fechaHasta", sFechaCitaHasta);
				}
			}
		  	
		  	try {
	            
		  		int paginaActual = request.getParameter("f_paginaActual") != null && !request.getParameter("f_paginaActual").equals("") ? 
						Integer.parseInt(request.getParameter("f_paginaActual")) : 1;
				int paginaIrA = request.getParameter("paginaIrA") != null && !request.getParameter("paginaIrA").equals("") ? 
						Integer.parseInt(request.getParameter("paginaIrA")) : 0;
				if(paginaIrA > 0) {
					paginaActual = paginaIrA;
				}
				int tamanioPagina = 10;
				int numeroRegistros = oDaoCita.numeroRegistrosCita(oConexion, filtros);
				int cantidadPaginas = (new BigDecimal(""+numeroRegistros).divide(new BigDecimal("10"),0,0).intValue()); 
				if(filtros.size()>0 && cantidadPaginas <= 1) {
					paginaActual = 0;
				}
				paginaActual = paginaActual >= 1 ? paginaActual : cantidadPaginas;
				int hasta = (paginaActual >= 1 ? paginaActual - 1: 1) * tamanioPagina;
	        	hasta = hasta >= 10 ? hasta : 0;
	        	
	        	request.setAttribute("f_paginaActual", paginaActual);
	        	request.setAttribute("paginaIrA", paginaIrA > 0 ? paginaIrA : "");
	        	request.setAttribute("cantidadPaginas", new int [cantidadPaginas]);
	        	request.setAttribute("numeroPaginas", cantidadPaginas);
				
				lista = oDaoCita.listarCitas(oConexion, filtros,hasta);
				
				ActionTipoCita oActionTipoCita = new ActionTipoCita();
				List listaTiposCitas = oActionTipoCita.listarTipoCitas();
				request.setAttribute("listaTiposCitas", listaTiposCitas);
				
				request.setAttribute("listaTiposCitas", listaTiposCitas);
				request.setAttribute("listaCitas", lista);
				
	    		List listaCitas = oDaoCita.listarCitas(oConexion,filtros,100000);
	    		
				GenerarReporte.exportar(sRutaDirectorioJasper+"/reporteCitas.jasper", listaCitas, parametros, out, maps, formato);
			} catch (JRException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
