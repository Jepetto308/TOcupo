/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entity.Cita;
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
public class DaoCita {
	
    public void guardarActualizarCita(Conexion oConexion, Cita cita, Map datos) throws SQLException, ClassNotFoundException{
      try{
         PreparedStatement consulta = null;
        
         //Sentencia de insert into que viene desde la base de datos
         String sqlInsertCita = "INSERT IGNORE INTO CITAS(NUMERO_CITA, FECHA_CITA, ID_SERVICIO_CITA, ESTADO_CITA, FECHA_CREACION, USUARIO_CREACION, ID_CLIENTE) VALUES (?, ?, ?, ?, ?, ?, ?)";         
         String sqlUpdateCita = "UPDATE CITAS SET NUMERO_CITA = ?, FECHA_CITA = ?, ID_SERVICIO_CITA = ?, ESTADO_CITA = ?, ID_CLIENTE = ? WHERE ID_CITA = ?";
         
         //Parametros (get) que se obtienen de la clase cita.java
         if(cita.getIdCita() == 0){
            consulta = oConexion.openConexion().prepareStatement(sqlInsertCita.toString());
            
            consulta.setString(1, cita.getNumeroCita());
            consulta.setTimestamp(2, cita.getFechaCita());
            consulta.setInt(3, cita.getIdServicioCita());
            consulta.setString(4, cita.getEstadoCita());
            consulta.setTimestamp(5, cita.getFechaCreacion());
            consulta.setString(6, cita.getUsuarioCreacion());
            consulta.setInt(7, cita.getIdCliente());
            
            cita.setIdCita(lastIdCita(oConexion.openConexion()));
            datos.put("okMensaje", "Registro ingresado exitosamente");
         }else{
        	 consulta = oConexion.openConexion().prepareStatement(sqlUpdateCita.toString());
        	 consulta.setString(1, cita.getNumeroCita());
             consulta.setTimestamp(2, cita.getFechaCita());
             consulta.setInt(3, cita.getIdServicioCita());
             consulta.setString(4, cita.getEstadoCita());
             consulta.setInt(5, cita.getIdCliente());
             
             consulta.setInt(6, cita.getIdCita());
             datos.put("okMensaje", "Registro actualizado exitosamente");
         }
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }finally{
    	  oConexion.closeConexion();
      }
   }
    
    public List listarCitas(Conexion oConexion, List<Filtro> filtros,int hasta) throws SQLException, ClassNotFoundException{
        List<Cita> listaCitas = new ArrayList();
        
        String where = "";
        String limit = " LIMIT 10 OFFSET "+hasta;
        if(hasta > 10000) {limit = "";};
        
        if(filtros.size()>0) {
        	for(int i=0;i<filtros.size();i++) {
        		Filtro oFiltro = (Filtro) filtros.get(i);
        		if(where.equals("")) {
        			where += " WHERE ";
        		}
        		where += oFiltro.getCampo() +" "+ oFiltro.getOperador() +" ";
        		if("LIKE".equalsIgnoreCase(oFiltro.getOperador()) || ">=".equalsIgnoreCase(oFiltro.getOperador()) || "<=".equalsIgnoreCase(oFiltro.getOperador())) {
        			where += "'"+oFiltro.getValor() + "' ";
        		}
        		else if("BETWEEN".equalsIgnoreCase(oFiltro.getOperador())) {
        			where += "'"+oFiltro.getValor()+"'" + " AND " + "'" + oFiltro.getValor2()+"'";
        		}
        		
        		if(filtros.size()>1 && (i+1 < filtros.size())) {
        			where += " "+ oFiltro.getEvaluador() +" ";
        		}
        				
        	}
        }
        
        try {
            PreparedStatement s = oConexion.openConexion().prepareStatement("SELECT A.ID_CITA, A.NUMERO_CITA, A.FECHA_CITA, A.ID_SERVICIO_CITA, A.ESTADO_CITA, A.FECHA_CREACION,"
            		+ " A.USUARIO_CREACION, A.ID_CLIENTE, CONCAT(B.PRIMER_NOMBRE_CLIENTE,' ',"
            		+ " B.OTROS_NOMBRES_CLIENTE,' ',B.PRIMER_APELLIDO_CLIENTE,' ',B.SEGUNDO_APELLIDO_CLIENTE) AS NOMBRE_COMPLETO,"
            		+ " B.NUMERO_IDENTIFICACION_CLIENTE, C.NOMBRE_TIPO_CITA"
            		+ " FROM CITAS A INNER JOIN CLIENTES B ON B.ID_CLIENTE = A.ID_CLIENTE INNER JOIN TIPOS_CITAS C ON C.ID_TIPO_CITA = A.ID_SERVICIO_CITA "+where+limit); 
            ResultSet rs = s.executeQuery(); 
            while (rs.next()) 
            {       
                 Cita oCita = new Cita();
                 oCita.setIdCita(rs.getInt(1));
                 oCita.setNumeroCita(rs.getString(2));
                 oCita.setFechaCita(rs.getTimestamp(3));
                 oCita.setIdServicioCita(rs.getInt(4));
                 oCita.setEstadoCita(rs.getString(5));
                 oCita.setFechaCreacion(rs.getTimestamp(6));
                 oCita.setUsuarioCreacion(rs.getString(7));
                 oCita.setIdCliente(rs.getInt(8));
                 oCita.setNombreCompletoCliente(rs.getString(9));
                 oCita.setNumeroDocumentoCliente(rs.getInt(10));
                 oCita.setNombreTipoCita(rs.getString(11));
                 
                listaCitas.add(oCita);                
            }
        } catch (SQLException ex) {
            System.err.println("Error listando las Citas");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return listaCitas;
    }
    
    
    public int numeroRegistrosCita(Conexion oConexion, List<Filtro> filtros) throws SQLException, ClassNotFoundException{
        List<Cita> listaCitas = new ArrayList();
        
        String where = "";
        int registros = 0;
        
        if(filtros.size()>0) {
        	for(int i=0;i<filtros.size();i++) {
        		Filtro oFiltro = (Filtro) filtros.get(i);
        		if(where.equals("")) {
        			where += " WHERE ";
        		}
        		where += oFiltro.getCampo() +" "+ oFiltro.getOperador() +" ";
        		if("LIKE".equalsIgnoreCase(oFiltro.getOperador()) || ">=".equalsIgnoreCase(oFiltro.getOperador()) || "<=".equalsIgnoreCase(oFiltro.getOperador())) {
        			where += "'"+oFiltro.getValor() + "' ";
        		}
        		else if("BETWEEN".equalsIgnoreCase(oFiltro.getOperador())) {
        			where += oFiltro.getValor() + " AND " + oFiltro.getValor2();
        		}
        		
        		if(filtros.size()>1 && (i+1 < filtros.size())) {
        			where += " "+ oFiltro.getEvaluador() +" ";
        		}
        				
        	}
        }
        
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT COUNT(1) FROM CITAS A "+where);
            
            while (rs.next()) 
            {       
            	registros++;
            }
        } catch (SQLException ex) {
            System.err.println("Error obteniendo el numero de registros, Tabla: Citas");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
			return registros;
        }
        
    }
    
    
    public Cita consularCita(int idCita,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        Cita oCita = new Cita();
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT A.ID_CITA, A.NUMERO_CITA, A.FECHA_CITA, A.ID_SERVICIO_CITA, A.ESTADO_CITA, A.FECHA_CREACION,"
            		+ " A.USUARIO_CREACION, A.ID_CLIENTE, CONCAT(B.PRIMER_NOMBRE_CLIENTE,' ',"
            		+ " B.OTROS_NOMBRES_CLIENTE,' ',B.PRIMER_APELLIDO_CLIENTE,' ',B.SEGUNDO_APELLIDO_CLIENTE) AS NOMBRE_COMPLETO,"
            		+ " B.NUMERO_IDENTIFICACION_CLIENTE"
            		+ " FROM CITAS A INNER JOIN CLIENTES B ON B.ID_CLIENTE = A.ID_CLIENTE"
            		+ " WHERE A.ID_CITA = "+idCita);
            while (rs.next()) 
            {       
            	oCita.setIdCita(rs.getInt(1));
                oCita.setNumeroCita(rs.getString(2));
                oCita.setFechaCita(rs.getTimestamp(3));
                oCita.setIdServicioCita(rs.getInt(4));
                oCita.setEstadoCita(rs.getString(5));
                oCita.setFechaCreacion(rs.getTimestamp(6));
                oCita.setUsuarioCreacion(rs.getString(7));
                oCita.setIdCliente(rs.getInt(8));
                oCita.setNombreCompletoCliente(rs.getString(9));
                oCita.setNumeroDocumentoCliente(rs.getInt(10));
                 
            }
        } catch (SQLException ex) {
            System.err.println("Error consultando la Cita con ID: "+idCita);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return oCita;
    }
    
    public void eliminarCita(int idCita,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            s.executeUpdate("DELETE FROM CITAS WHERE ID_CITA ="+idCita);
            parametros.put("okMensaje", "Registro eliminado exitosamente");
        } catch (SQLException ex) {
            System.err.println("Error eliminando el Cita con ID: "+idCita);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
    }
    
    private int lastIdCita(Connection conn) throws SQLException {
    	int id = 0;
    	try {
    		Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT MAX(ID_CITA) FROM CITAS");
			while (rs.next()) 
            { 
				id = rs.getInt(1);
            }
		} catch (SQLException e) {
			System.out.println("Error obteniendo el ultimo ID de Citas");
			e.printStackTrace();
		}finally {
			return id+1;
		}
    }
    
}
