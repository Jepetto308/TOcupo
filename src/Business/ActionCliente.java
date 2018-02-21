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

import entity.Cliente;
import entity.Filtro;
import net.sf.jasperreports.engine.JRException;
import DAO.DaoCliente;
import Utils.Conexion;
import Utils.GenerarReporte;;

public class ActionCliente {
	private DaoCliente oDaoCliente = new DaoCliente();
	
	public List listarClientes(HttpServletRequest request){
		Conexion oConexion = new Conexion();
		List lista = new ArrayList();
		List<Filtro> filtros = new ArrayList();
		
		Filtro oFiltro = new Filtro();
		String numeroDocumento = request.getParameter("f_numeroDocumento");
		String nombreCliente = request.getParameter("f_nombreCliente");
		
		if(numeroDocumento != null && !numeroDocumento.equals("")) {
			oFiltro.setCampo("A.NUMERO_IDENTIFICACION_CLIENTE");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(numeroDocumento+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_numeroDocumento", numeroDocumento);
		}
		
		if(nombreCliente != null && !nombreCliente.equals("")) {
			oFiltro = new Filtro();
			oFiltro.setCampo("CONCAT(A.PRIMER_NOMBRE_CLIENTE,' ',A.OTROS_NOMBRES_CLIENTE,' ',A.PRIMER_APELLIDO_CLIENTE,' ',A.SEGUNDO_APELLIDO_CLIENTE)");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(nombreCliente+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_nombreCliente", nombreCliente);
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
			int numeroRegistros = oDaoCliente.numeroRegistrosCliente(oConexion, filtros);
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
			
			lista = oDaoCliente.listarClientes(oConexion, filtros,hasta);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
        return lista;
	}
	
	public Map guardarActualizarCliente(Cliente oCliente) throws ClassNotFoundException, SQLException{
		Map datos = new HashMap();
		
		Conexion oConexion = new Conexion();
		oDaoCliente.guardarActualizarCliente(oConexion, oCliente, datos);
		datos.put("oCliente", oCliente);
		
		return datos;
	}
	
	public Map consultarCliente(int idCliente,Map parametros) throws ClassNotFoundException, SQLException{
		Map datos = new HashMap();
		
		Conexion oConexion = new Conexion();
		Cliente oCliente = oDaoCliente.consularCliente(idCliente, parametros, oConexion);
		datos.put("oCliente", oCliente);
		
		return datos;
	}
	
	public Map consultarClientePorDocumento(int numeroDocumentoCliente,Map parametros) throws ClassNotFoundException, SQLException{
		Map datos = new HashMap();
		
		Conexion oConexion = new Conexion();
		Cliente oCliente = oDaoCliente.consularClientePorDocumento(numeroDocumentoCliente, parametros, oConexion);
		datos.put("oCliente", oCliente);
		
		return datos;
	}
	
	public Map eliminarCliente(int idCliente,Map parametros) throws ClassNotFoundException, SQLException{
		Conexion oConexion = new Conexion();
		oDaoCliente.eliminarCliente(idCliente, parametros, oConexion);
		return parametros;
	}
	
	  public void exportar(String sRutaDirectorioJasper, Map parametros, OutputStream out, boolean maps, String formato){
	    	
		  	HttpServletRequest request = (HttpServletRequest) parametros.get("request");
		  	List<Filtro> filtros = new ArrayList();
			
			Filtro oFiltro = new Filtro();
			String numeroDocumento = request.getParameter("f_numeroDocumento");
			String nombreCliente = request.getParameter("f_nombreCliente");
			
			if(numeroDocumento != null && !numeroDocumento.equals("")) {
				oFiltro.setCampo("A.NUMERO_IDENTIFICACION_CLIENTE");
				oFiltro.setOperador("LIKE");
				oFiltro.setValor(numeroDocumento+'%');
				filtros.add(oFiltro);
				
				request.setAttribute("f_numeroDocumento", numeroDocumento);
			}
			
			if(nombreCliente != null && !nombreCliente.equals("")) {
				oFiltro = new Filtro();
				oFiltro.setCampo("CONCAT(A.PRIMER_NOMBRE_CLIENTE,' ',A.OTROS_NOMBRES_CLIENTE)");
				oFiltro.setOperador("LIKE");
				oFiltro.setValor(nombreCliente+'%');
				filtros.add(oFiltro);
				
				request.setAttribute("f_nombreCliente", nombreCliente);
			}
		  	
		  	try {
	            
	    		Conexion oConexion = new Conexion();
	    		List listaClientes = oDaoCliente.listarClientes(oConexion,filtros,100000000);
	    		
				GenerarReporte.exportar(sRutaDirectorioJasper+"/reporteClientes.jasper", listaClientes, parametros, out, maps, formato);
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
