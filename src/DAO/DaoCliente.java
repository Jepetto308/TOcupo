/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entity.Cliente;
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
public class DaoCliente {
	
    public void guardarActualizarCliente(Conexion oConexion, Cliente cliente, Map datos) throws SQLException, ClassNotFoundException{
      try{
         PreparedStatement consulta = null;
        
         //Sentencia de insert into que viene desde la base de datos
         String sqlInsertCliente = "INSERT IGNORE INTO CLIENTES(CODIGO_TIPO_DOCUMENTO_CLIENTE, NUMERO_IDENTIFICACION_CLIENTE, PRIMER_NOMBRE_CLIENTE, OTROS_NOMBRES_CLIENTE, PRIMER_APELLIDO_CLIENTE, SEGUNDO_APELLIDO_CLIENTE, RAZON_SOCIAL_CLIENTE, TELEFONO_CLIENTE, CELULAR_CLIENTE, DIRECCION_CLIENTE, EMAIL_CLIENTE, FAX_CLIENTE, CODIGO_PAIS, CODIGO_MUNICIPIO, FECHA_CREACION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";         
         String sqlUpdateCliente = "UPDATE CLIENTES SET CODIGO_TIPO_DOCUMENTO_CLIENTE = ?, NUMERO_IDENTIFICACION_CLIENTE = ?, PRIMER_NOMBRE_CLIENTE = ?, OTROS_NOMBRES_CLIENTE = ?, PRIMER_APELLIDO_CLIENTE = ?, SEGUNDO_APELLIDO_CLIENTE = ?, RAZON_SOCIAL_CLIENTE = ?, TELEFONO_CLIENTE = ?, CELULAR_CLIENTE = ?, DIRECCION_CLIENTE = ?, EMAIL_CLIENTE = ?, FAX_CLIENTE = ?, CODIGO_PAIS = ?, CODIGO_MUNICIPIO = ?, FECHA_CREACION = ? WHERE ID_CLIENTE = ?";
         
         //Parametros (get) que se obtienen de la clase cliente.java
         if(cliente.getIdCliente() == 0){
            consulta = oConexion.openConexion().prepareStatement(sqlInsertCliente.toString());
            consulta.setString(1, cliente.getTipoDocumentoCliente());
            consulta.setInt(2, cliente.getNumeroDocumentoCliente());
            consulta.setString(3, cliente.getPrimerNombreCliente());
            
            consulta.setString(4, cliente.getOtrosNombresCliente());
            consulta.setString(5, cliente.getPrimerApellidoCliente());
            consulta.setString(6, cliente.getSegundoApellidoCliente());
            consulta.setString(7, cliente.getRazonSocialCliente());
            consulta.setString(8, cliente.getTelefonoCliente());
            consulta.setString(9, cliente.getCelularCliente());
            consulta.setString(10, cliente.getDireccionCliente());
            consulta.setString(11, cliente.getEmailCliente());
            consulta.setString(12, cliente.getFaxCliente());
            consulta.setString(13, cliente.getCodigoPais());
            consulta.setString(14, cliente.getCodigoMunicipio());
            consulta.setTimestamp(15, cliente.getFechaCreacion());
            
            cliente.setIdCliente(lastIdCliente(oConexion.openConexion()));
            datos.put("okMensaje", "Registro ingresado exitosamente");
         }else{
        	 consulta = oConexion.openConexion().prepareStatement(sqlUpdateCliente.toString());
             consulta.setString(1, cliente.getTipoDocumentoCliente());
             consulta.setInt(2, cliente.getNumeroDocumentoCliente());
             consulta.setString(3, cliente.getPrimerNombreCliente());
             
             consulta.setString(4, cliente.getOtrosNombresCliente());
             consulta.setString(5, cliente.getPrimerApellidoCliente());
             consulta.setString(6, cliente.getSegundoApellidoCliente());
             consulta.setString(7, cliente.getRazonSocialCliente());
             consulta.setString(8, cliente.getTelefonoCliente());
             consulta.setString(9, cliente.getCelularCliente());
             consulta.setString(10, cliente.getDireccionCliente());
             consulta.setString(11, cliente.getEmailCliente());
             consulta.setString(12, cliente.getFaxCliente());
             consulta.setString(13, cliente.getCodigoPais());
             consulta.setString(14, cliente.getCodigoMunicipio());
             consulta.setTimestamp(15, cliente.getFechaCreacion());
             
             consulta.setInt(16, cliente.getIdCliente());
             datos.put("okMensaje", "Registro actualizado exitosamente");
         }
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }finally{
    	  oConexion.closeConexion();
      }
   }
    
    public List listarClientes(Conexion oConexion, List<Filtro> filtros,int hasta) throws SQLException, ClassNotFoundException{
        List<Cliente> listaClientes = new ArrayList();
        
        String where = "";
        String limit = " LIMIT 10 OFFSET "+(hasta );
        if(hasta > 100000) {limit= "";};
        
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
            PreparedStatement s = oConexion.openConexion().prepareStatement("SELECT A.CODIGO_TIPO_DOCUMENTO_CLIENTE, A.NUMERO_IDENTIFICACION_CLIENTE, A.PRIMER_NOMBRE_CLIENTE,"
            		+ " A.OTROS_NOMBRES_CLIENTE, A.PRIMER_APELLIDO_CLIENTE, A.SEGUNDO_APELLIDO_CLIENTE, A.RAZON_SOCIAL_CLIENTE, A.TELEFONO_CLIENTE,"
            		+ " A.CELULAR_CLIENTE, A.DIRECCION_CLIENTE, A.EMAIL_CLIENTE, A.FAX_CLIENTE, A.CODIGO_PAIS, A.CODIGO_MUNICIPIO, A.FECHA_CREACION,A.ID_CLIENTE"
            		+ " FROM CLIENTES A "+where+limit); 
            ResultSet rs = s.executeQuery();
            while (rs.next()) 
            {       
                 Cliente oCliente = new Cliente();
                 oCliente.setTipoDocumentoCliente(rs.getString(1));
                 oCliente.setNumeroDocumentoCliente(rs.getInt(2));
                 oCliente.setPrimerNombreCliente(rs.getString(3));
                 oCliente.setOtrosNombresCliente(rs.getString(4));
                 oCliente.setPrimerApellidoCliente(rs.getString(5));
                 oCliente.setSegundoApellidoCliente(rs.getString(6));
                 oCliente.setRazonSocialCliente(rs.getString(7));
                 oCliente.setTelefonoCliente(rs.getString(8));
                 oCliente.setCelularCliente(rs.getString(9));
                 
                 
                 oCliente.setIdCliente(rs.getInt(16));
                 
                listaClientes.add(oCliente);                
            }
        } catch (SQLException ex) {
            System.err.println("Error listando los Clientes");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return listaClientes;
    }
    
    
    public int numeroRegistrosCliente(Conexion oConexion, List<Filtro> filtros) throws SQLException, ClassNotFoundException{
        List<Cliente> listaClientes = new ArrayList();
        
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
            ResultSet rs = s.executeQuery ("SELECT A.CODIGO_TIPO_DOCUMENTO_CLIENTE, A.NUMERO_IDENTIFICACION_CLIENTE, A.PRIMER_NOMBRE_CLIENTE,"
            		+ " A.OTROS_NOMBRES_CLIENTE, A.PRIMER_APELLIDO_CLIENTE, A.SEGUNDO_APELLIDO_CLIENTE, A.RAZON_SOCIAL_CLIENTE, A.TELEFONO_CLIENTE,"
            		+ " A.CELULAR_CLIENTE, A.DIRECCION_CLIENTE, A.EMAIL_CLIENTE, A.FAX_CLIENTE, A.CODIGO_PAIS, A.CODIGO_MUNICIPIO, A.FECHA_CREACION,A.ID_CLIENTE"
            		+ " FROM CLIENTES A "+where);
            
            while (rs.next()) 
            {       
            	registros++;
            }
        } catch (SQLException ex) {
            System.err.println("Error obteniendo el numero de registros, Tabla: Clientes");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
			return registros;
        }
        
    }
    
    
    public Cliente consularCliente(int idCliente,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        Cliente oCliente = new Cliente();
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT A.CODIGO_TIPO_DOCUMENTO_CLIENTE, A.NUMERO_IDENTIFICACION_CLIENTE, A.PRIMER_NOMBRE_CLIENTE, A.OTROS_NOMBRES_CLIENTE, A.PRIMER_APELLIDO_CLIENTE, A.SEGUNDO_APELLIDO_CLIENTE, A.RAZON_SOCIAL_CLIENTE, A.TELEFONO_CLIENTE, A.CELULAR_CLIENTE, A.DIRECCION_CLIENTE, A.EMAIL_CLIENTE, A.FAX_CLIENTE, A.CODIGO_PAIS, A.CODIGO_MUNICIPIO, A.FECHA_CREACION, B.NOMBRE_PAIS,C.NOMBRE_MUNICIPIO, A.ID_CLIENTE "
            		+ "FROM CLIENTES A LEFT OUTER JOIN PAISES B ON B.CODIGO_PAIS = A.CODIGO_PAIS LEFT OUTER JOIN MUNICIPIOS C ON C.CODIGO_MUNICIPIO = A.CODIGO_MUNICIPIO WHERE ID_CLIENTE ="+idCliente);
            while (rs.next()) 
            {       
                 oCliente.setTipoDocumentoCliente(rs.getString(1));
                 oCliente.setNumeroDocumentoCliente(rs.getInt(2));
                 oCliente.setPrimerNombreCliente(rs.getString(3));
                 oCliente.setOtrosNombresCliente(rs.getString(4));
                 oCliente.setPrimerApellidoCliente(rs.getString(5));
                 oCliente.setSegundoApellidoCliente(rs.getString(6));
                 oCliente.setRazonSocialCliente(rs.getString(7));
                 oCliente.setTelefonoCliente(rs.getString(8));
                 oCliente.setCelularCliente(rs.getString(9));
                 oCliente.setDireccionCliente(rs.getString(10));
                 oCliente.setEmailCliente(rs.getString(11));
                 oCliente.setFaxCliente(rs.getString(12));
                 oCliente.setCodigoPais(rs.getString(13));
                 oCliente.setCodigoMunicipio(rs.getString(14));
                 oCliente.setFechaCreacion(rs.getTimestamp(15));
                 oCliente.setNombrePais(rs.getString(16));
                 oCliente.setNombreMunicipio(rs.getString(17));
                 
                 oCliente.setIdCliente(rs.getInt(18));
                 
            }
        } catch (SQLException ex) {
            System.err.println("Error consultando el Cliente con ID: "+idCliente);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return oCliente;
    }
    
    public Cliente consularClientePorDocumento(int numeroDocumentoCliente,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        Cliente oCliente = new Cliente();
        try {
            PreparedStatement s = oConexion.openConexion().prepareStatement("SELECT A.CODIGO_TIPO_DOCUMENTO_CLIENTE, A.NUMERO_IDENTIFICACION_CLIENTE, A.PRIMER_NOMBRE_CLIENTE, A.OTROS_NOMBRES_CLIENTE, A.PRIMER_APELLIDO_CLIENTE, A.SEGUNDO_APELLIDO_CLIENTE, A.RAZON_SOCIAL_CLIENTE, A.TELEFONO_CLIENTE, A.CELULAR_CLIENTE, A.DIRECCION_CLIENTE, A.EMAIL_CLIENTE, A.FAX_CLIENTE, A.CODIGO_PAIS, A.CODIGO_MUNICIPIO, A.FECHA_CREACION, B.NOMBRE_PAIS,C.NOMBRE_MUNICIPIO, A.ID_CLIENTE "
            		+ "FROM CLIENTES A LEFT OUTER JOIN PAISES B ON B.CODIGO_PAIS = A.CODIGO_PAIS LEFT OUTER JOIN MUNICIPIOS C ON C.CODIGO_MUNICIPIO = A.CODIGO_MUNICIPIO WHERE A.NUMERO_IDENTIFICACION_CLIENTE ="+numeroDocumentoCliente);
            ResultSet rs = s.executeQuery();
            while (rs.next()) 
            {       
                 oCliente.setTipoDocumentoCliente(rs.getString(1));
                 oCliente.setNumeroDocumentoCliente(rs.getInt(2));
                 oCliente.setPrimerNombreCliente(rs.getString(3));
                 oCliente.setOtrosNombresCliente(rs.getString(4));
                 oCliente.setPrimerApellidoCliente(rs.getString(5));
                 oCliente.setSegundoApellidoCliente(rs.getString(6));
                 oCliente.setRazonSocialCliente(rs.getString(7));
                 oCliente.setTelefonoCliente(rs.getString(8));
                 oCliente.setCelularCliente(rs.getString(9));
                 oCliente.setDireccionCliente(rs.getString(10));
                 oCliente.setEmailCliente(rs.getString(11));
                 oCliente.setFaxCliente(rs.getString(12));
                 oCliente.setCodigoPais(rs.getString(13));
                 oCliente.setCodigoMunicipio(rs.getString(14));
                 oCliente.setFechaCreacion(rs.getTimestamp(15));
                 oCliente.setNombrePais(rs.getString(16));
                 oCliente.setNombreMunicipio(rs.getString(17));
                 
                 oCliente.setIdCliente(rs.getInt(18));
                 
            }
        } catch (SQLException ex) {
            System.err.println("Error consultando el Cliente con Documento: "+numeroDocumentoCliente);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return oCliente;
    }
    
    public void eliminarCliente(int idCliente,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            s.executeUpdate("DELETE FROM CLIENTES WHERE ID_CLIENTE ="+idCliente);
            parametros.put("okMensaje", "Registro eliminado exitosamente");
        } catch (SQLException ex) {
            System.err.println("Error eliminando el Cliente con ID: "+idCliente);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
    }
    
    private int lastIdCliente(Connection conn) throws SQLException {
    	int id = 0;
    	try {
    		Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT MAX(ID_CLIENTE) FROM clientes");
			while (rs.next()) 
            { 
				id = rs.getInt(1);
            }
		} catch (SQLException e) {
			System.out.println("Error obteniendo el ultimo ID de Clientes");
			e.printStackTrace();
		}finally {
			return id+1;
		}
    }
    
}
