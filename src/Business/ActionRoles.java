package Business;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import entity.Roles;
import entity.Filtro;
import net.sf.jasperreports.engine.JRException;
import DAO.DaoRoles;
import Utils.Conexion;
import Utils.GenerarReporte;;

public class ActionRoles {
	private DaoRoles oDaoRoles = new DaoRoles();
	
	public List listarRoles(HttpServletRequest request){
		Conexion oConexion = new Conexion();
		List lista = new ArrayList();
		List<Filtro> filtros = new ArrayList();
		
		Filtro oFiltro = new Filtro();
		String numeroDocumento = request.getParameter("f_numeroDocumento");
		String nombreRoles = request.getParameter("f_nombreRoles");
		
		if(numeroDocumento != null && !numeroDocumento.equals("")) {
			oFiltro.setCampo("A.NUMERO_IDENTIFICACION_CLIENTE");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(numeroDocumento+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_numeroDocumento", numeroDocumento);
		}
		
		if(nombreRoles != null && !nombreRoles.equals("")) {
			oFiltro = new Filtro();
			oFiltro.setCampo("CONCAT(A.PRIMER_NOMBRE_CLIENTE,' ',A.OTROS_NOMBRES_CLIENTE)");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(nombreRoles+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_nombreRoles", nombreRoles);
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
			int numeroRegistros = oDaoRoles.numeroRegistrosRoles(oConexion, filtros);
			int cantidadPaginas = (new BigDecimal(""+numeroRegistros).divide(new BigDecimal("10"),0,0).intValue()); 
			if(filtros.size() > 0 && cantidadPaginas <= 1) {
				paginaActual = 0;
			}
			paginaActual = paginaActual >= 1 ? paginaActual : cantidadPaginas;
			int hasta = (paginaActual >= 1 ? paginaActual - 1: 1) * tamanioPagina;
        	hasta = hasta >= 10 ? hasta : 0;
        	
        	request.setAttribute("f_paginaActual", paginaActual);
        	request.setAttribute("paginaIrA", paginaIrA > 0 ? paginaIrA : "");
        	request.setAttribute("cantidadPaginas", new int [cantidadPaginas]);
        	request.setAttribute("numeroPaginas", cantidadPaginas);
			
			lista = oDaoRoles.listarRoles(oConexion, filtros,hasta);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
        return lista;
	}
	
	public Map guardarActualizarRoles(Roles oRoles) throws ClassNotFoundException, SQLException{
		Map datos = new HashMap();
		
		Conexion oConexion = new Conexion();
		oDaoRoles.guardarActualizarRoles(oConexion, oRoles, datos);
		datos.put("oRoles", oRoles);
		
		return datos;
	}
	
	public Map consultarRoles(String codRol,Map parametros) throws ClassNotFoundException, SQLException{
		Map datos = new HashMap();
		
		Conexion oConexion = new Conexion();
		Roles oRoles = oDaoRoles.consularRoles(codRol, parametros, oConexion);
		datos.put("oRoles", oRoles);
		
		return datos;
	}
	
	public Map eliminarRoles(String idRoles,Map parametros) throws ClassNotFoundException, SQLException{
		Conexion oConexion = new Conexion();
		oDaoRoles.eliminarRoles(idRoles, parametros, oConexion);
		return parametros;
	}
	
	  public void exportar(String sRutaDirectorioJasper, Map parametros, OutputStream out, boolean maps, String formato){
	    	
		  	HttpServletRequest request = (HttpServletRequest) parametros.get("request");
		  	List<Filtro> filtros = new ArrayList();
			
			Filtro oFiltro = new Filtro();
			String numeroDocumento = request.getParameter("f_numeroDocumento");
			String nombreRoles = request.getParameter("f_nombreRoles");
			
			if(numeroDocumento != null && !numeroDocumento.equals("")) {
				oFiltro.setCampo("A.NUMERO_IDENTIFICACION_CLIENTE");
				oFiltro.setOperador("LIKE");
				oFiltro.setValor(numeroDocumento+'%');
				filtros.add(oFiltro);
				
				request.setAttribute("f_numeroDocumento", numeroDocumento);
			}
			
			if(nombreRoles != null && !nombreRoles.equals("")) {
				oFiltro = new Filtro();
				oFiltro.setCampo("CONCAT(A.PRIMER_NOMBRE_CLIENTE,' ',A.OTROS_NOMBRES_CLIENTE)");
				oFiltro.setOperador("LIKE");
				oFiltro.setValor(nombreRoles+'%');
				filtros.add(oFiltro);
				
				request.setAttribute("f_nombreRoles", nombreRoles);
			}
		  	
		  	try {
	            
	    		Conexion oConexion = new Conexion();
	    		List listaRoles = oDaoRoles.listarRoles(oConexion,filtros,1);
	    		
				GenerarReporte.exportar(sRutaDirectorioJasper+"/reporteRoles.jasper", listaRoles, parametros, out, maps, formato);
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
