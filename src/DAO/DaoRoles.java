/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entity.Roles;
import entity.Filtro;

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
import Utils.Utilidades;

/**
 *
 * @author USER
 */
public class DaoRoles {
	
    public void guardarActualizarRoles(Conexion oConexion, Roles rol, Map datos) throws SQLException, ClassNotFoundException{
      try{
         PreparedStatement consulta = null;
        
         String sqlInsertRoles = "INSERT IGNORE INTO ROLES(CODIGO_ROL, NOMBRE_ROL, DESCRIPCION) VALUES (?, ?, ?)";         
         String sqlUpdateRoles = "UPDATE ROLES SET CODIGO_ROL = ?, NOMBRE_ROL = ?, DESCRIPCION = ? WHERE CODIGO_ROL = ?";
         
         List<Filtro> filtros = new ArrayList();
         Filtro oFiltro = new Filtro();
         oFiltro.setCampo("A.CODIGO_ROL");
         oFiltro.setOperador("LIKE");
         oFiltro.setValor(rol.getCodRol());
         filtros.add(oFiltro);
         int numeroRegistros = numeroRegistrosRoles(oConexion, filtros);
         
         if(numeroRegistros == 0){
            consulta = oConexion.openConexion().prepareStatement(sqlInsertRoles.toString());
            
            consulta.setString(1, rol.getCodRol());
            consulta.setString(2, rol.getNombreRol());
            consulta.setString(3, rol.getDescripcion());
            
            datos.put("okMensaje", "Registro ingresado exitosamente");
         }else{
        	 consulta = oConexion.openConexion().prepareStatement(sqlUpdateRoles.toString());
        	 consulta.setString(1, rol.getCodRol());
             consulta.setString(2, rol.getNombreRol());
             consulta.setString(3, rol.getDescripcion());;
             
             consulta.setString(4, rol.getCodRol());
             datos.put("okMensaje", "Registro actualizado exitosamente");
         }
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }finally{
    	  oConexion.closeConexion();
      }
   }
    
    public List listarRoles(Conexion oConexion, List<Filtro> filtros,int hasta) throws SQLException, ClassNotFoundException{
        List<Roles> listaRoless = new ArrayList();
        
        String where = "";
//        String limit = " LIMIT 10 OFFSET "+(hasta );
        String limit = "";
        
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
            ResultSet rs = s.executeQuery ("SELECT A.CODIGO_ROL, A.NOMBRE_ROL, A.DESCRIPCION"
            		+ " FROM ROLES A "+where+" ORDER BY A.NOMBRE_ROL "+limit);
            while (rs.next()) 
            {       
                 Roles oRoles = new Roles();
                 oRoles.setCodRol(rs.getString(1));
                 oRoles.setNombreRol(rs.getString(2));
                 oRoles.setDescripcion(rs.getString(3));
                 
                listaRoless.add(oRoles);                
            }
        } catch (SQLException ex) {
            System.err.println("Error listando los Roles");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return listaRoless;
    }
    
    
    public int numeroRegistrosRoles(Conexion oConexion, List<Filtro> filtros) throws SQLException, ClassNotFoundException{
        List<Roles> listaRoless = new ArrayList();
        
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
            ResultSet rs = s.executeQuery ("SELECT COUNT(1)"
            		+ " FROM ROLES A "+where);
            
            while (rs.next()) 
            {       
            	registros = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println("Error obteniendo el numero de registros, Tabla: Roles");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
			return registros;
        }
        
    }
    
    
    public Roles consularRoles(String codRol,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        Roles oRoles = new Roles();
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT A.CODIGO_ROL, A.NOMBRE_ROL, A.DESCRIPCION"
            		+ " FROM ROLES A WHERE A.CODIGO_ROL = '"+codRol+"'");
            while (rs.next()) 
            {       
                 oRoles.setCodRol(rs.getString(1));
                 oRoles.setNombreRol(rs.getString(2));
                 oRoles.setDescripcion(rs.getString(3));
                 
            }
        } catch (SQLException ex) {
            System.err.println("Error consultando el Roles con CODIGO: "+codRol);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return oRoles;
    }
    
    public void eliminarRoles(String idRoles,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            s.executeUpdate("DELETE FROM ROLES WHERE CODIGO_ROL ="+idRoles);
            parametros.put("okMensaje", "Registro eliminado exitosamente");
        } catch (SQLException ex) {
            System.err.println("Error eliminando el Rol con CODIGO: "+idRoles);
            System.out.println(ex.getMessage());
            parametros.put("okMensaje","Error eliminando el Rol con CODIGO: "+idRoles+"\n"+ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
    }
    
//    private int lastIdRoles(Connection conn) throws SQLException {
//    	int id = 0;
//    	try {
//    		Statement s = conn.createStatement();
//			ResultSet rs = s.executeQuery("SELECT MAX(ID_TIPO_) FROM TIPOS_CITAS");
//			while (rs.next()) 
//            { 
//				id = rs.getInt(1);
//            }
//		} catch (SQLException e) {
//			System.out.println("Error obteniendo el ultimo ID de TiposCitas");
//			e.printStackTrace();
//		}finally {
//			return id+1;
//		}
//    }
    
}
