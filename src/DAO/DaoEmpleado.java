/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entity.Empleado;
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
public class DaoEmpleado {
	
    public void guardarActualizarEmpleado(Conexion oConexion, Empleado oEmpleado, Map datos) throws SQLException, ClassNotFoundException{
      try{
         PreparedStatement consulta = null;
        
         //Sentencia de insert into que viene desde la base de datos
         String sqlInsertEmpleado = "INSERT IGNORE INTO EmpleadoS(CODIGO_TIPO_DOCUMENTO_EMPLEADO, NUMERO_IDENTIFICACION_EMPLEADO, PRIMER_NOMBRE_EMPLEADO, OTROS_NOMBRES_EMPLEADO, PRIMER_APELLIDO_EMPLEADO, SEGUNDO_APELLIDO_EMPLEADO, TELEFONO_EMPLEADO, CELULAR_EMPLEADO, DIRECCION_EMPLEADO, EMAIL_EMPLEADO, FAX_EMPLEADO, CODIGO_PAIS, CODIGO_MUNICIPIO, FECHA_CREACION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";         
         String sqlUpdateEmpleado = "UPDATE EMPLEADOS SET CODIGO_TIPO_DOCUMENTO_EMPLEADO = ?, NUMERO_IDENTIFICACION_EMPLEADO = ?, PRIMER_NOMBRE_EMPLEADO = ?, OTROS_NOMBRES_EMPLEADO = ?, PRIMER_APELLIDO_EMPLEADO = ?, SEGUNDO_APELLIDO_EMPLEADO = ?, TELEFONO_EMPLEADO = ?, CELULAR_EMPLEADO = ?, DIRECCION_EMPLEADO = ?, EMAIL_EMPLEADO = ?, FAX_EMPLEADO = ?, CODIGO_PAIS = ?, CODIGO_MUNICIPIO = ?, FECHA_CREACION = ? WHERE ID_EMPLEADO = ?";
         
         //Parametros (get) que se obtienen de la clase Empleado.java
         if(oEmpleado.getIdEmpleado() == 0){
            consulta = oConexion.openConexion().prepareStatement(sqlInsertEmpleado.toString());
            consulta.setString(1, oEmpleado.getTipoDocumentoEmpleado());
            consulta.setString(2, oEmpleado.getNumeroDocumentoEmpleado());
            consulta.setString(3, oEmpleado.getPrimerNombreEmpleado());
            
            consulta.setString(4, oEmpleado.getOtrosNombresEmpleado());
            consulta.setString(5, oEmpleado.getPrimerApellidoEmpleado());
            consulta.setString(6, oEmpleado.getSegundoApellidoEmpleado());
            consulta.setString(7, oEmpleado.getTelefonoEmpleado());
            consulta.setString(8, oEmpleado.getCelularEmpleado());
            consulta.setString(9, oEmpleado.getDireccionEmpleado());
            consulta.setString(10, oEmpleado.getCorreoEmpleado());
            consulta.setString(11, oEmpleado.getFaxEmpleado());
            consulta.setString(12, oEmpleado.getCodigoPais());
            consulta.setString(13, oEmpleado.getCodigoMunicipio());
            consulta.setTimestamp(14, oEmpleado.getFechaCreacion());
            
            oEmpleado.setIdEmpleado(lastIdEmpleado(oConexion.openConexion()));
            datos.put("okMensaje", "Registro ingresado exitosamente");
         }else{
        	 consulta = oConexion.openConexion().prepareStatement(sqlUpdateEmpleado.toString());
             consulta.setString(1, oEmpleado.getTipoDocumentoEmpleado());
             consulta.setString(2, oEmpleado.getNumeroDocumentoEmpleado());
             consulta.setString(3, oEmpleado.getPrimerNombreEmpleado());
             
             consulta.setString(4, oEmpleado.getOtrosNombresEmpleado());
             consulta.setString(5, oEmpleado.getPrimerApellidoEmpleado());
             consulta.setString(6, oEmpleado.getSegundoApellidoEmpleado());
             consulta.setString(7, oEmpleado.getTelefonoEmpleado());
             consulta.setString(8, oEmpleado.getCelularEmpleado());
             consulta.setString(9, oEmpleado.getDireccionEmpleado());
             consulta.setString(10, oEmpleado.getCorreoEmpleado());
             consulta.setString(11, oEmpleado.getFaxEmpleado());
             consulta.setString(12, oEmpleado.getCodigoPais());
             consulta.setString(13, oEmpleado.getCodigoMunicipio());
             consulta.setTimestamp(14, oEmpleado.getFechaCreacion());
             
             consulta.setInt(15, oEmpleado.getIdEmpleado());
             datos.put("okMensaje", "Registro actualizado exitosamente");
         }
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }finally{
    	  oConexion.closeConexion();
      }
   }
    
    public List listarEmpleados(Conexion oConexion, List<Filtro> filtros,int hasta) throws SQLException, ClassNotFoundException{
        List<Empleado> listaEmpleados = new ArrayList();
        
        String where = "";
        String limit = " LIMIT 10 OFFSET "+(hasta );
        
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
            ResultSet rs = s.executeQuery ("SELECT A.CODIGO_TIPO_DOCUMENTO_EMPLEADO, A.NUMERO_IDENTIFICACION_EMPLEADO, A.PRIMER_NOMBRE_EMPLEADO,"
            		+ " A.OTROS_NOMBRES_EMPLEADO, A.PRIMER_APELLIDO_EMPLEADO, A.SEGUNDO_APELLIDO_EMPLEADO, A.TELEFONO_EMPLEADO,"
            		+ " A.CELULAR_EMPLEADO, A.DIRECCION_EMPLEADO, A.EMAIL_EMPLEADO, A.FAX_EMPLEADO, A.CODIGO_PAIS, A.CODIGO_MUNICIPIO, A.FECHA_CREACION,"
            		+ " A.ID_EMPLEADO"
            		+ " FROM EMPLEADOS A "+where+limit);
            while (rs.next()) 
            {       
                 Empleado oEmpleado = new Empleado();
                 oEmpleado.setTipoDocumentoEmpleado(rs.getString(1));
                 oEmpleado.setNumeroDocumentoEmpleado(rs.getString(2));
                 oEmpleado.setPrimerNombreEmpleado(rs.getString(3));
                 oEmpleado.setOtrosNombresEmpleado(rs.getString(4));
                 oEmpleado.setPrimerApellidoEmpleado(rs.getString(5));
                 oEmpleado.setSegundoApellidoEmpleado(rs.getString(6));
                 oEmpleado.setTelefonoEmpleado(rs.getString(7));
                 oEmpleado.setCelularEmpleado(rs.getString(8));
                 oEmpleado.setDireccionEmpleado(rs.getString(9));
                 oEmpleado.setCorreoEmpleado(rs.getString(10));
                 oEmpleado.setFaxEmpleado(rs.getString(11));
                 oEmpleado.setCodigoPais(rs.getString(12));
                 oEmpleado.setCodigoMunicipio(rs.getString(13));
                 oEmpleado.setFechaCreacion(rs.getTimestamp(14));
                 
                 
                 oEmpleado.setIdEmpleado(rs.getInt(15));
                 
                listaEmpleados.add(oEmpleado);                
            }
        } catch (SQLException ex) {
            System.err.println("Error listando los Empleados");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return listaEmpleados;
    }
    
    public List listarEmpleadosSinPaginar(Conexion oConexion, List<Filtro> filtros) throws SQLException, ClassNotFoundException{
        List<Empleado> listaEmpleados = new ArrayList();
        
        String where = "";
        
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
            ResultSet rs = s.executeQuery ("SELECT A.CODIGO_TIPO_DOCUMENTO_EMPLEADO, A.NUMERO_IDENTIFICACION_EMPLEADO, A.PRIMER_NOMBRE_EMPLEADO,"
            		+ " A.OTROS_NOMBRES_EMPLEADO, A.PRIMER_APELLIDO_EMPLEADO, A.SEGUNDO_APELLIDO_EMPLEADO, A.TELEFONO_EMPLEADO,"
            		+ " A.CELULAR_EMPLEADO, A.DIRECCION_EMPLEADO, A.EMAIL_EMPLEADO, A.FAX_EMPLEADO, A.CODIGO_PAIS, A.CODIGO_MUNICIPIO, A.FECHA_CREACION,"
            		+ " A.ID_EMPLEADO"
            		+ " FROM EMPLEADOS A "+where);
            while (rs.next()) 
            {       
                 Empleado oEmpleado = new Empleado();
                 oEmpleado.setTipoDocumentoEmpleado(rs.getString(1));
                 oEmpleado.setNumeroDocumentoEmpleado(rs.getString(2));
                 oEmpleado.setPrimerNombreEmpleado(rs.getString(3));
                 oEmpleado.setOtrosNombresEmpleado(rs.getString(4));
                 oEmpleado.setPrimerApellidoEmpleado(rs.getString(5));
                 oEmpleado.setSegundoApellidoEmpleado(rs.getString(6));
                 oEmpleado.setTelefonoEmpleado(rs.getString(7));
                 oEmpleado.setCelularEmpleado(rs.getString(8));
                 oEmpleado.setDireccionEmpleado(rs.getString(9));
                 oEmpleado.setCorreoEmpleado(rs.getString(10));
                 oEmpleado.setFaxEmpleado(rs.getString(11));
                 oEmpleado.setCodigoPais(rs.getString(12));
                 oEmpleado.setCodigoMunicipio(rs.getString(13));
                 oEmpleado.setFechaCreacion(rs.getTimestamp(14));
                 
                 
                 oEmpleado.setIdEmpleado(rs.getInt(15));
                 
                listaEmpleados.add(oEmpleado);                
            }
        } catch (SQLException ex) {
            System.err.println("Error listando los Empleados");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return listaEmpleados;
    }
    
    
    public int numeroRegistrosEmpleado(Conexion oConexion, List<Filtro> filtros) throws SQLException, ClassNotFoundException{
        List<Empleado> listaEmpleados = new ArrayList();
        
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
            ResultSet rs = s.executeQuery ("SELECT COUNT(1) FROM EMPLEADOS A "+where);
            
            while (rs.next()) 
            {       
            	registros = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println("Error obteniendo el numero de registros, Tabla: Empleados");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
			return registros;
        }
        
    }
    
    
    public Empleado consularEmpleado(int idEmpleado,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        Empleado oEmpleado = new Empleado();
        try {
             String sql = "SELECT A.CODIGO_TIPO_DOCUMENTO_EMPLEADO, A.NUMERO_IDENTIFICACION_EMPLEADO, A.PRIMER_NOMBRE_EMPLEADO," + 
            		" A.OTROS_NOMBRES_EMPLEADO, A.PRIMER_APELLIDO_EMPLEADO, A.SEGUNDO_APELLIDO_EMPLEADO, A.TELEFONO_EMPLEADO," + 
            		" A.CELULAR_EMPLEADO, A.DIRECCION_EMPLEADO, A.EMAIL_EMPLEADO, A.FAX_EMPLEADO, A.CODIGO_PAIS, A.CODIGO_MUNICIPIO, A.FECHA_CREACION," + 
            		" B.NOMBRE_PAIS, C.NOMBRE_MUNICIPIO, A.ID_EMPLEADO"+
            		" FROM EmpleadoS A"+
            		" LEFT OUTER JOIN PAISES B ON B.CODIGO_PAIS = A.CODIGO_PAIS "+
            		" LEFT OUTER JOIN MUNICIPIOS C ON C.CODIGO_MUNICIPIO = A.CODIGO_MUNICIPIO WHERE ID_Empleado ="+idEmpleado;
             PreparedStatement ps = oConexion.openConexion().prepareStatement(sql); 
             ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {       
                 oEmpleado.setTipoDocumentoEmpleado(rs.getString(1));
                 oEmpleado.setNumeroDocumentoEmpleado(rs.getString(2));
                 oEmpleado.setPrimerNombreEmpleado(rs.getString(3));
                 oEmpleado.setOtrosNombresEmpleado(rs.getString(4));
                 oEmpleado.setPrimerApellidoEmpleado(rs.getString(5));
                 oEmpleado.setSegundoApellidoEmpleado(rs.getString(6));
                 oEmpleado.setTelefonoEmpleado(rs.getString(7));
                 oEmpleado.setCelularEmpleado(rs.getString(8));
                 oEmpleado.setDireccionEmpleado(rs.getString(9));
                 oEmpleado.setCorreoEmpleado(rs.getString(10));
                 oEmpleado.setFaxEmpleado(rs.getString(11));
                 oEmpleado.setCodigoPais(rs.getString(12));
                 oEmpleado.setCodigoMunicipio(rs.getString(13));
                 oEmpleado.setFechaCreacion(rs.getTimestamp(14));
                 oEmpleado.setNombrePais(rs.getString(15));
                 oEmpleado.setNombreMunicipio(rs.getString(16));
                 
                 oEmpleado.setIdEmpleado(rs.getInt(17));
                 
            }
        } catch (SQLException ex) {
            System.err.println("Error consultando el Empleado con ID: "+idEmpleado);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return oEmpleado;
    }
    
    public void eliminarEmpleado(int idEmpleado,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            s.executeUpdate("DELETE FROM Empleados WHERE ID_Empleado ="+idEmpleado);
            parametros.put("okMensaje", "Registro eliminado exitosamente");
        } catch (SQLException ex) {
            System.err.println("Error eliminando el Empleado con ID: "+idEmpleado);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
    }
    
    private int lastIdEmpleado(Connection conn) throws SQLException {
    	int id = 0;
    	try {
    		Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT MAX(ID_Empleado) FROM Empleados");
			while (rs.next()) 
            { 
				id = rs.getInt(1);
            }
		} catch (SQLException e) {
			System.out.println("Error obteniendo el ultimo ID de Empleados");
			e.printStackTrace();
		}finally {
			return id+1;
		}
    }
    
}
