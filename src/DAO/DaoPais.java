/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entity.Cliente;
import entity.Filtro;
import entity.Pais;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import Utils.Conexion;

/**
 *
 * @author USER
 */
public class DaoPais {
	
     
    public List listarPais(Conexion oConexion, List<Filtro> filtros,int hasta) throws SQLException, ClassNotFoundException{
        List<Pais> listaPais = new ArrayList();
        String where = "";
        String limit = " LIMIT 10 OFFSET "+(hasta);
        
        if(filtros.size()>0) {
        	for(int i=0;i<filtros.size();i++) {
        		Filtro oFiltro = (Filtro) filtros.get(i);
        		if(where.equals("")) {
        			where += " WHERE ";
        		}
        		where += oFiltro.getCampo() +" "+ oFiltro.getOperador() +" ";
        		if("LIKE".equalsIgnoreCase(oFiltro.getOperador())) {
        			where += "'"+oFiltro.getValor() + "' ";
        		}
        		if(filtros.size()>1 && (i+1 < filtros.size())) {
        			where += " AND ";
        		}
        	}
        }
        
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT A.CODIGO_PAIS, A.NOMBRE_PAIS FROM Paises A "+where+" ORDER BY A.NOMBRE_PAIS"+limit);
            while (rs.next()) 
            {       
                 Pais oPais = new Pais();
                 oPais.setCodigoPais(rs.getString(1));
                 oPais.setNombrePais(rs.getString(2));
                                  
                listaPais.add(oPais);                
            }
        } catch (SQLException ex) {
            System.err.println("Error listando los Pais");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return listaPais;
    }
    
    
    public int numeroRegistrosPais(Conexion oConexion, List<Filtro> filtros) throws SQLException, ClassNotFoundException{
        String where = "";
        int registros = 0;
        
        if(filtros.size()>0) {
        	for(int i=0;i<filtros.size();i++) {
        		Filtro oFiltro = (Filtro) filtros.get(i);
        		if(where.equals("")) {
        			where += " WHERE ";
        		}
        		where += oFiltro.getCampo() +" "+ oFiltro.getOperador() +" ";
        		if("LIKE".equalsIgnoreCase(oFiltro.getOperador())) {
        			where += "'"+oFiltro.getValor() + "' ";
        		}
        		if(filtros.size()>1 && (i+1 < filtros.size())) {
        			where += " AND ";
        		}
        	}
        }
        
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT COUNT(1) A FROM Paises A "+where);
            while (rs.next()) 
            {       
            	registros = rs.getInt(1); 
            }
        } catch (SQLException ex) {
            System.err.println("Error obteniendo el numero de registros, Tabla: Paises");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
			return registros;
        }
        
    }
    
    public Pais consultarPais(String codigoPais,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        Pais oPais = new Pais();
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT CODIGO_PAIS, NOMBRE_PAIS FROM Paises WHERE CODIGO_PAIS = '"+codigoPais+"'");
            while (rs.next()) 
            {       
            	oPais.setCodigoPais(rs.getString(1));
            	oPais.setNombrePais(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.err.println("Error consultando el Pais con codigo: "+codigoPais);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return oPais;
    }
    
}
