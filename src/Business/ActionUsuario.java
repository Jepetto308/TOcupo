/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.DaoPermisosUsuario;
import DAO.DaoUsuario;
import entity.Empleado;
import entity.Filtro;
import entity.PermisosUsuarios;
import entity.Usuario;
import net.sf.jasperreports.engine.JRException;
import Utils.Conexion;
import Utils.GenerarReporte;
import Utils.Utilidades;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jeferson Palacios Torres | Correo: jefferson308@hotmail.com
 */
public class ActionUsuario {
    
    private DaoUsuario daoUsuario = new DaoUsuario();
    private Usuario oUsuario = new Usuario();
    Conexion oConexion = new Conexion();
    
    public Map insertarActualizarUsuario(Usuario oUsuario,HttpServletRequest request) throws FileNotFoundException, ClassNotFoundException, SQLException{
    	Map datos = new HashMap();
    	Connection conn = null;
    	
    	if(daoUsuario.existeUsuarioConNumeroIdentificacion(oUsuario.getIdUsuario())) {
    		conn = oConexion.openConexion();
    		daoUsuario.actualizarUsuario(oUsuario, conn,datos);
    	}else {
    		conn = oConexion.openConexion();
    		daoUsuario.insertarUsuario(oUsuario, conn,datos);
    	}
        ActionRoles oActionRol = new ActionRoles();
		datos.put("listaRoles", oActionRol.listarRoles(request));
		datos.put("oUsuario", oUsuario);
        
        return datos;
   }
    
    public Usuario consultarUsuario(String numeroIdentificacion) throws ClassNotFoundException, SQLException{
        Connection conn = oConexion.openConexion();
        oUsuario = daoUsuario.consultarUsuario(numeroIdentificacion, conn);
        
        return oUsuario;
    }
    
    public Map consultarUsuarioVista(int idUsuario,Map parametros, HttpServletRequest request) throws ClassNotFoundException, SQLException{
		Map datos = new HashMap();
		
		Conexion oConexion = new Conexion();
		Connection conn = oConexion.openConexion();
		oUsuario = daoUsuario.consultarUsuarioVista(idUsuario, conn);
		
		ActionRoles oActionRol = new ActionRoles();
		datos.put("listaRoles", oActionRol.listarRoles(request));
		
		datos.put("oUsuario", oUsuario);
		
		return datos;
	}
    
    public List listarUsuarios(HttpServletRequest request) throws ClassNotFoundException, SQLException{
        Connection conn = oConexion.openConexion();
        List listaUsuarios = new ArrayList();
        List<Filtro> filtros = new ArrayList();
		
		Filtro oFiltro = new Filtro();
		String login = request.getParameter("f_login");
		String identificacion = request.getParameter("f_identificacion");
		String codigoRol = request.getParameter("f_codigoRol");
		
		if(login != null && !login.equals("")) {
			oFiltro.setCampo("A.USERNAME");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(login+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_login", login);
		}
		
		if(identificacion != null && !identificacion.equals("")) {
			oFiltro = new Filtro();
			oFiltro.setCampo("A.numero_identificacion");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(identificacion+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_identificacion", identificacion);
		}
		
		if(codigoRol != null && !codigoRol.equals("")) {
			oFiltro = new Filtro();
			oFiltro.setCampo("A.codigo_rol");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(codigoRol+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_codigoRol", codigoRol);
		}
		
		int paginaActual = request.getParameter("f_paginaActual") != null && !request.getParameter("f_paginaActual").equals("") ? 
				Integer.parseInt(request.getParameter("f_paginaActual")) : 1;
		int paginaIrA = request.getParameter("paginaIrA") != null && !request.getParameter("paginaIrA").equals("") ? 
				Integer.parseInt(request.getParameter("paginaIrA")) : 0;
		if(paginaIrA > 0) {
			paginaActual = paginaIrA;
		}
		int tamanioPagina = 10;
		int numeroRegistros = daoUsuario.numeroRegistrosUsuario(conn, filtros);
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
		
		 listaUsuarios = daoUsuario.listarUsuarios(oConexion.openConexion(),filtros,hasta);
		 ActionRoles oActionRol = new ActionRoles();
		 request.setAttribute("listaRoles", oActionRol.listarRoles(request));
        
        return listaUsuarios;
    }
    
    public Map eliminarUsuario(int idUsuario,Map parametros) throws ClassNotFoundException, SQLException{
		Conexion oConexion = new Conexion();
		daoUsuario.eliminarUsuario(idUsuario, parametros, oConexion);
		return parametros;
	}
    
    public boolean existeUsuarioByNumeroIdentificacion(String numeroIdentificacion) throws ClassNotFoundException, SQLException{
        return daoUsuario.existeUsuarioConNumeroIdentificacion(numeroIdentificacion);
    }
    
    public boolean existeUsuarioByUsername(String username) throws ClassNotFoundException, SQLException{
        return daoUsuario.existeUsuarioConUsername(username);
    }
    
    public Usuario login(String username,String password) throws ClassNotFoundException, SQLException{
        Connection conn = oConexion.openConexion();
        oUsuario = daoUsuario.login(username, password, conn);
        return oUsuario;
    }   
    
    public void exportar(HttpServletRequest request,String sRutaDirectorioJasper, Map parametros, OutputStream out, boolean maps, String formato) throws ClassNotFoundException, SQLException{
    	
    	Connection conn = oConexion.openConexion();
        List listaUsuarios = new ArrayList();
        List<Filtro> filtros = new ArrayList();
		
		Filtro oFiltro = new Filtro();
		String login = request.getParameter("f_login");
		String identificacion = request.getParameter("f_identificacion");
		
		if(login != null && !login.equals("")) {
			oFiltro.setCampo("A.USERNAME");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(login+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_login", login);
		}
		
		if(identificacion != null && !identificacion.equals("")) {
			oFiltro = new Filtro();
			oFiltro.setCampo("A.numero_identificacion");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(identificacion+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_identificacion", identificacion);
		}
		
		int paginaActual = request.getParameter("f_paginaActual") != null && !request.getParameter("f_paginaActual").equals("") ? 
				Integer.parseInt(request.getParameter("f_paginaActual")) : 1;
		int paginaIrA = request.getParameter("paginaIrA") != null && !request.getParameter("paginaIrA").equals("") ? 
				Integer.parseInt(request.getParameter("paginaIrA")) : 0;
		if(paginaIrA > 0) {
			paginaActual = paginaIrA;
		}
		int tamanioPagina = 10;
		int numeroRegistros = daoUsuario.numeroRegistrosUsuario(conn, filtros);
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
		
		 listaUsuarios = daoUsuario.listarUsuarios(oConexion.openConexion(),filtros,1000000);
	  	
	  	try {
			GenerarReporte.exportar(sRutaDirectorioJasper+"/reporteUsuarios.jasper", listaUsuarios, parametros, out, maps, formato);
		} catch (JRException e) {
			e.printStackTrace();
		}
    }
    
    public List listaPermisosUsuario(HttpServletRequest request,String modulo,String user) throws ClassNotFoundException, SQLException {
    	List listaPermisosUsuario = new ArrayList();
    	DaoPermisosUsuario oDaoPermisosUsuario = new DaoPermisosUsuario();
    	
    	if(new Utilidades().esVacio(modulo)) {
    		return listaPermisosUsuario;
    	}
    	
    	listaPermisosUsuario = oDaoPermisosUsuario.listarPermisosUsuarios(oConexion, user,modulo);
    	request.setAttribute("modulo", modulo);
    	
    	return listaPermisosUsuario;
    	
    }
    
    public List listaPermisos(HttpServletRequest request) throws ClassNotFoundException, SQLException {
    	List listaPermisos = new ArrayList();
    	DaoPermisosUsuario oDaoPermisosUsuario = new DaoPermisosUsuario();
    	String user = request.getParameter("username");
    	
    	listaPermisos = oDaoPermisosUsuario.listarControles(oConexion, user);
    	
    	return listaPermisos;
    	
    }
    
    public Map insertarActualizarPermisosUsuario(List listaPermisos,String modulo,String login,HttpServletRequest request) throws FileNotFoundException, ClassNotFoundException, SQLException{
    	Map datos = new HashMap();
    	Connection conn = null;
    	DaoPermisosUsuario oDaoPermisosUsuario = new DaoPermisosUsuario();
    	
    	if(listaPermisos.size()>0) {
	    	oDaoPermisosUsuario.eliminarPermisosUsuarios(login,modulo, datos, oConexion);
	    	for (int i = 0; i < listaPermisos.size(); i++) {
				PermisosUsuarios oPermiso = (PermisosUsuarios) listaPermisos.get(i);
				if("I".equalsIgnoreCase(oPermiso.getActivo())) {
					continue;
				}
	    		oDaoPermisosUsuario.guardarActualizarPermisosUsuarios(oConexion, oPermiso, datos);
			}
    	}
        return datos;
   }
    
    public boolean validarEntrada(HttpServletRequest request,String modulo,String user) throws ClassNotFoundException, SQLException {
    	DaoPermisosUsuario oDaoPermisosUsuario = new DaoPermisosUsuario();
    	
    	if(new Utilidades().esVacio(modulo)) {
    		return false;
    	}
    	return oDaoPermisosUsuario.tienePermisoUsuarioPorRecurso(oConexion, user,modulo);
    	
    }
    
    public boolean validarEntradaPorOpcion(HttpServletRequest request,String modulo,String codigoOpcion,String user) throws ClassNotFoundException, SQLException {
    	DaoPermisosUsuario oDaoPermisosUsuario = new DaoPermisosUsuario();
    	
    	if(new Utilidades().esVacio(modulo)) {
    		return false;
    	}
    	return oDaoPermisosUsuario.tienePermisoUsuarioPorOpcion(oConexion, user,modulo,codigoOpcion);
    	
    }
    
}
