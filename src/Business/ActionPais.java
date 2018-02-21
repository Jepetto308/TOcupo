/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.DaoPais;
import DAO.DaoUsuario;
import entity.Usuario;
import entity.Filtro;
import entity.Pais;
import Utils.Conexion;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jeferson Palacios Torres | Correo: jefferson308@hotmail.com
 */
public class ActionPais {
    
    private DaoPais daoPais = new DaoPais();
    private Pais oPais = new Pais();
    Conexion oConexion = new Conexion();
    
    public Pais consultarUsuario(String codigoPais, Map parametros) throws ClassNotFoundException, SQLException{
        oPais = daoPais.consultarPais(codigoPais, parametros, oConexion);
        return oPais;
    }
    
    public List listarPais(HttpServletRequest request) throws ClassNotFoundException, SQLException{
        List listaPais = new ArrayList();
        List<Filtro> filtros = new ArrayList();
		
		Filtro oFiltro = new Filtro();
		String codigoPais = request.getParameter("f_codigoPais");
		String nombrePais= request.getParameter("f_nombrePais");
		
		if(codigoPais != null && !codigoPais.equals("")) {
			oFiltro.setCampo("A.CODIGO_PAIS");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(codigoPais+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_codigoPais", codigoPais);
		}
		
		if(nombrePais != null && !nombrePais.equals("")) {
			oFiltro = new Filtro();
			oFiltro.setCampo("A.NOMBRE_PAIS");
			oFiltro.setOperador("LIKE");
			oFiltro.setValor(nombrePais+'%');
			filtros.add(oFiltro);
			
			request.setAttribute("f_nombrePais", nombrePais);
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
			int numeroRegistros = daoPais.numeroRegistrosPais(oConexion, filtros);
			int cantidadPaginas = (new BigDecimal(""+numeroRegistros).divide(new BigDecimal("10"),0,0).intValue()); 
			paginaActual = paginaActual >= 1 ? paginaActual : cantidadPaginas;
			int hasta = (paginaActual >= 1 ? paginaActual - 1: 1) * tamanioPagina;
        	hasta = hasta >= tamanioPagina ? hasta : 0;
        	
        	request.setAttribute("f_paginaActual", paginaActual);
        	request.setAttribute("paginaIrA", paginaIrA > 0 ? paginaIrA : "");
        	request.setAttribute("cantidadPaginas", new int [cantidadPaginas]);
        	request.setAttribute("numeroPaginas", cantidadPaginas);
        
        listaPais = daoPais.listarPais(oConexion,filtros,hasta);
        } catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
        return listaPais;
    }
    
}
