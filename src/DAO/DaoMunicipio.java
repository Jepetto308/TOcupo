/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entity.Filtro;
import entity.Municipio;

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
public class DaoMunicipio {
	
     
    public List listarMunicipio(Conexion oConexion,List<Filtro> filtros,int hasta) throws SQLException, ClassNotFoundException{
        List<Municipio> listaMunicipio = new ArrayList();
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
            ResultSet rs = s.executeQuery ("SELECT A.CODIGO_MUNICIPIO, A.NOMBRE_MUNICIPIO FROM Municipios A "+where+" ORDER BY A.NOMBRE_MUNICIPIO"+limit);
            while (rs.next()) 
            {       
                 Municipio oMunicipio = new Municipio();
                 oMunicipio.setCodigoMunicipio(rs.getString(1));
                 oMunicipio.setNombreMunicipio(rs.getString(2));
                                  
                listaMunicipio.add(oMunicipio);                
            }
        } catch (SQLException ex) {
            System.err.println("Error listando los Municipio");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return listaMunicipio;
    }
    
    public int numeroRegistrosMunicipio(Conexion oConexion, List<Filtro> filtros) throws SQLException, ClassNotFoundException{
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
            ResultSet rs = s.executeQuery ("SELECT COUNT(1) FROM Municipios A "+where);
            while (rs.next())  
            {       
            	registros = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println("Error obteniendo el numero de registros, Tabla: Municipios");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
			return registros;
        }
        
    }
    
    
    public Municipio consultarMunicipio(String codigoMunicipio,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        Municipio oMunicipio = new Municipio();
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT CODIGO_Municipio, NOMBRE_Municipio FROM Municipios WHERE CODIGO_Municipio = '"+codigoMunicipio+"'");
            while (rs.next()) 
            {       
            	oMunicipio.setCodigoMunicipio(rs.getString(1));
            	oMunicipio.setNombreMunicipio(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.err.println("Error consultando el Municipio con codigo: "+codigoMunicipio);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return oMunicipio;
    }
    
}
