/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entity.TipoCita;
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

/**
 *
 * @author USER
 */
public class DaoTipoCita {
	
    public void guardarActualizarTipoCita(Conexion oConexion, TipoCita cita, Map datos) throws SQLException, ClassNotFoundException{
      try{
         PreparedStatement consulta = null;
        
         //Sentencia de insert into que viene desde la base de datos
         String sqlInsertTipoCita = "INSERT IGNORE INTO TIPOS_CITAS(CODIGO_TIPO_CITA, NOMBRE_TIPO_CITA) VALUES (?, ?)";         
         String sqlUpdateTipoCita = "UPDATE TIPOS_CITAS SET CODIGO_TIPO_CITA = ?, NOMBRE_TIPO_CITA = ? WHERE ID_TIPO_CITA = ?";
         
         //Parametros (get) que se obtienen de la clase cita.java
         if(cita.getIdTipoCita() == 0){
            consulta = oConexion.openConexion().prepareStatement(sqlInsertTipoCita.toString());
            
            consulta.setString(1, cita.getCodigoTipoCita());
            consulta.setString(2, cita.getNombreTipoCita());
            
            cita.setIdTipoCita(lastIdTipoCita(oConexion.openConexion()));
            datos.put("okMensaje", "Registro ingresado exitosamente");
         }else{
        	 consulta = oConexion.openConexion().prepareStatement(sqlUpdateTipoCita.toString());
        	 consulta.setString(1, cita.getCodigoTipoCita());
             consulta.setString(2, cita.getNombreTipoCita());
             
             consulta.setInt(3, cita.getIdTipoCita());
             datos.put("okMensaje", "Registro actualizado exitosamente");
         }
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }finally{
    	  oConexion.closeConexion();
      }
   }
    
    public List listarTipoCitas(Conexion oConexion, List<Filtro> filtros,int hasta) throws SQLException, ClassNotFoundException{
        List<TipoCita> listaTipoCitas = new ArrayList();
        
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
            ResultSet rs = s.executeQuery ("SELECT A.ID_TIPO_CITA, A.CODIGO_TIPO_CITA, A.NOMBRE_TIPO_CITA"
            		+ " FROM TIPOS_CITAS A "+where+limit);
            while (rs.next()) 
            {       
                 TipoCita oTipoCita = new TipoCita();
                 oTipoCita.setIdTipoCita(rs.getInt(1));
                 oTipoCita.setCodigoTipoCita(rs.getString(2));
                 oTipoCita.setNombreTipoCita(rs.getString(3));
                 
                listaTipoCitas.add(oTipoCita);                
            }
        } catch (SQLException ex) {
            System.err.println("Error listando las TipoCitas");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return listaTipoCitas;
    }
    
    
    public int numeroRegistrosTipoCita(Conexion oConexion, List<Filtro> filtros) throws SQLException, ClassNotFoundException{
        List<TipoCita> listaTipoCitas = new ArrayList();
        
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
            		+ " FROM TIPOS_CITAS A "+where);
            
            while (rs.next()) 
            {       
            	registros++;
            }
        } catch (SQLException ex) {
            System.err.println("Error obteniendo el numero de registros, Tabla: TipoCitas");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
			return registros;
        }
        
    }
    
    
    public TipoCita consularTipoCita(int idTipoCita,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        TipoCita oTipoCita = new TipoCita();
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT A.ID_TIPO_CITA, A.CODIGO_TIP_CITA, A.NOMBRE_TIPO_CITA"
            		+ " FROM TIPOS_CITAS A WHERE A.ID_TIPO_CITA = "+idTipoCita);
            while (rs.next()) 
            {       
            	oTipoCita.setIdTipoCita(rs.getInt(1));
            	oTipoCita.setCodigoTipoCita(rs.getString(2));
            	oTipoCita.setNombreTipoCita(rs.getString(3));
            }
        } catch (SQLException ex) {
            System.err.println("Error consultando el TipoCita con ID: "+idTipoCita);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return oTipoCita;
    }
    
    public void eliminarTipoCita(int idTipoCita,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            s.executeUpdate("DELETE FROM TIPOS_CITAS WHERE ID_TIPO_CITA ="+idTipoCita);
            parametros.put("okMensaje", "Registro eliminado exitosamente");
        } catch (SQLException ex) {
            System.err.println("Error eliminando el TipoCitas con ID: "+idTipoCita);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
    }
    
    private int lastIdTipoCita(Connection conn) throws SQLException {
    	int id = 0;
    	try {
    		Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT MAX(ID_TIPO_) FROM TIPOS_CITAS");
			while (rs.next()) 
            { 
				id = rs.getInt(1);
            }
		} catch (SQLException e) {
			System.out.println("Error obteniendo el ultimo ID de TiposCitas");
			e.printStackTrace();
		}finally {
			return id+1;
		}
    }
    
}
