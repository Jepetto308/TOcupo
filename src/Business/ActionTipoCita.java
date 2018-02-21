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

import entity.TipoCita;
import entity.Filtro;
import net.sf.jasperreports.engine.JRException;
import DAO.DaoTipoCita;
import Utils.Conexion;
import Utils.GenerarReporte;;

public class ActionTipoCita {
	private DaoTipoCita oDaoTipoCita = new DaoTipoCita();
	
	public List listarTipoCitas(/*HttpServletRequest request*/){
		Conexion oConexion = new Conexion();
		List lista = new ArrayList();
		List<Filtro> filtros = new ArrayList();
		
		Filtro oFiltro = new Filtro();
//		String numeroDocumento = request.getParameter("f_numeroDocumento");
//		String nombreTipoCita = request.getParameter("f_nombreTipoCita");
//		
//		if(numeroDocumento != null && !numeroDocumento.equals("")) {
//			oFiltro.setCampo("A.NUMERO_IDENTIFICACION_CLIENTE");
//			oFiltro.setOperador("LIKE");
//			oFiltro.setValor(numeroDocumento+'%');
//			filtros.add(oFiltro);
//			
//			request.setAttribute("f_numeroDocumento", numeroDocumento);
//		}
//		
//		if(nombreTipoCita != null && !nombreTipoCita.equals("")) {
//			oFiltro = new Filtro();
//			oFiltro.setCampo("CONCAT(A.PRIMER_NOMBRE_CLIENTE,' ',A.OTROS_NOMBRES_CLIENTE)");
//			oFiltro.setOperador("LIKE");
//			oFiltro.setValor(nombreTipoCita+'%');
//			filtros.add(oFiltro);
//			
//			request.setAttribute("f_nombreTipoCita", nombreTipoCita);
//		}
		
        try {
        	
//			int paginaActual = request.getParameter("f_paginaActual") != null && !request.getParameter("f_paginaActual").equals("") ? 
//					Integer.parseInt(request.getParameter("f_paginaActual")) : 1;
//			int paginaIrA = request.getParameter("paginaIrA") != null && !request.getParameter("paginaIrA").equals("") ? 
//					Integer.parseInt(request.getParameter("paginaIrA")) : 0;
//			if(paginaIrA > 0) {
//				paginaActual = paginaIrA;
//			}
//			int tamanioPagina = 10;
//			int numeroRegistros = oDaoTipoCita.numeroRegistrosTipoCita(oConexion, filtros);
//			int cantidadPaginas = (new BigDecimal(""+numeroRegistros).divide(new BigDecimal("10"),0,0).intValue()); 
//			paginaActual = paginaActual >= 1 ? paginaActual : cantidadPaginas;
//			int hasta = (paginaActual >= 1 ? paginaActual - 1: 1) * tamanioPagina;
//        	hasta = hasta >= 10 ? hasta : 0;
//        	
//        	request.setAttribute("f_paginaActual", paginaActual);
//        	request.setAttribute("paginaIrA", paginaIrA > 0 ? paginaIrA : "");
//        	request.setAttribute("cantidadPaginas", new int [cantidadPaginas]);
//        	request.setAttribute("numeroPaginas", cantidadPaginas);
			
			lista = oDaoTipoCita.listarTipoCitas(oConexion, filtros,2);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
        return lista;
	}
	
	public Map guardarActualizarTipoCita(TipoCita oTipoCita) throws ClassNotFoundException, SQLException{
		Map datos = new HashMap();
		
		Conexion oConexion = new Conexion();
		oDaoTipoCita.guardarActualizarTipoCita(oConexion, oTipoCita, datos);
		datos.put("oTipoCita", oTipoCita);
		
		return datos;
	}
	
	public Map consultarTipoCita(int idTipoCita,Map parametros) throws ClassNotFoundException, SQLException{
		Map datos = new HashMap();
		
		Conexion oConexion = new Conexion();
		TipoCita oTipoCita = oDaoTipoCita.consularTipoCita(idTipoCita, parametros, oConexion);
		datos.put("oTipoCita", oTipoCita);
		
		return datos;
	}
	
	public Map eliminarTipoCita(int idTipoCita,Map parametros) throws ClassNotFoundException, SQLException{
		Conexion oConexion = new Conexion();
		oDaoTipoCita.eliminarTipoCita(idTipoCita, parametros, oConexion);
		return parametros;
	}
	
	  public void exportar(String sRutaDirectorioJasper, Map parametros, OutputStream out, boolean maps, String formato){
	    	
		  	HttpServletRequest request = (HttpServletRequest) parametros.get("request");
		  	List<Filtro> filtros = new ArrayList();
			
			Filtro oFiltro = new Filtro();
		  	
		  	try {
	            
	    		Conexion oConexion = new Conexion();
	    		List listaTipoCitas = oDaoTipoCita.listarTipoCitas(oConexion,filtros,1);
	    		
				GenerarReporte.exportar(sRutaDirectorioJasper+"/reporteTipoCitas.jasper", listaTipoCitas, parametros, out, maps, formato);
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
