/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.DaoMunicipio;
import entity.Filtro;
import entity.Municipio;
import Utils.Conexion;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jeferson Palacios Torres | Correo: jefferson308@hotmail.com
 */
public class ActionMunicipio {
    
    private DaoMunicipio daoMunicipio = new DaoMunicipio();
    private Municipio oMunicipio = new Municipio();
    Conexion oConexion = new Conexion();
    
    public Municipio consultarUsuario(String codigoMunicipio, Map parametros) throws ClassNotFoundException, SQLException{
        oMunicipio = daoMunicipio.consultarMunicipio(codigoMunicipio, parametros, oConexion);
        return oMunicipio;
    }
    
    public List listarMunicipio(HttpServletRequest request) throws ClassNotFoundException, SQLException{
        List listaMunicipio = new ArrayList();
        List<Filtro> filtros = new ArrayList();
		
		Filtro oFiltro = new Filtro();
		String codigoMunicipio = request.getParameter("f_codigoMunicipio");
		String nombreMunicipio= request.getParameter("f_nombreMunicipio");
		
		if(codigoMunicipio != null && !codigoMunicipio.equals("")) {
			oFiltro.setCampo("A.CODIGO_MUNICIPIO");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(codigoMunicipio+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_codigoMunicipio", codigoMunicipio);
		}
		
		if(nombreMunicipio != null && !nombreMunicipio.equals("")) {
			oFiltro = new Filtro();
			oFiltro.setCampo("A.NOMBRE_MUNICIPIO");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(nombreMunicipio+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_nombreMunicipio", nombreMunicipio);
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
			int numeroRegistros = daoMunicipio.numeroRegistrosMunicipio(oConexion, filtros);
			int cantidadPaginas = (new BigDecimal(""+numeroRegistros).divide(new BigDecimal("10"),0,0).intValue()); 
			paginaActual = paginaActual >= 1 ? paginaActual : cantidadPaginas;
			int hasta = (paginaActual >= 1 ? paginaActual - 1: 1) * tamanioPagina;
        	hasta = hasta >= 10 ? hasta : 0;
        	
        	request.setAttribute("f_paginaActual", paginaActual);
        	request.setAttribute("paginaIrA", paginaIrA > 0 ? paginaIrA : "");
        	request.setAttribute("cantidadPaginas", new int [cantidadPaginas]);
        	request.setAttribute("numeroPaginas", cantidadPaginas);
        		
        listaMunicipio = daoMunicipio.listarMunicipio(oConexion,filtros,hasta);
        } catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
        return listaMunicipio;
    }
    
}
