/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entity.PermisosUsuarios;
import entity.Filtro;
import entity.Permisos;

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
public class DaoPermisosUsuario {
	
    public void guardarActualizarPermisosUsuarios(Conexion oConexion, PermisosUsuarios permisosUsuario, Map datos) throws SQLException, ClassNotFoundException{
      try{
         PreparedStatement consulta = null;
        
         //Sentencia de insert into que viene desde la base de datos
         String sqlInsertPermisosUsuarios = "INSERT IGNORE INTO PERMISOS_USUARIOS(CODIGO_RECURSO, CODIGO_OPCION, LOGIN, REGLA) VALUES (?, ?, ?, ?)";         
//         String sqlUpdatePermisosUsuarios = "UPDATE PERMISOS_USUARIOS SET CODIGO_RECURSO = ?, CODIGO_OPCION = ?, LOGIN = ? WHERE LOGIN = ?";
         
         consulta = oConexion.openConexion().prepareStatement(sqlInsertPermisosUsuarios.toString());
            
         consulta.setString(1, permisosUsuario.getCodigoRecurso());
         consulta.setString(2, permisosUsuario.getCodigoOpcion());
         consulta.setString(3, permisosUsuario.getLogin());
         consulta.setString(4, permisosUsuario.getRegla());
            
         datos.put("okMensaje", "Registro ingresado exitosamente");
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }finally{
    	  oConexion.closeConexion();
      }
   }
    
    public List listarPermisosUsuarios(Conexion oConexion, String login,String modulo) throws SQLException, ClassNotFoundException{
        List<PermisosUsuarios> listaPermisosUsuarioss = new ArrayList();
        
        String where = new Utilidades().esVacio(modulo) ? "" : " WHERE A.CODIGO_RECURSO = '"+modulo+"'";
        
        try {
            PreparedStatement s = oConexion.openConexion().prepareStatement("SELECT A.CODIGO_RECURSO,A.CODIGO_OPCION,A.NOMBRE_OPCION,IF ((SELECT COUNT(1) FROM PERMISOS_USUARIOS B WHERE B.LOGIN = '"+login+"'"
            		+ " AND A.CODIGO_OPCION = B.CODIGO_OPCION AND A.CODIGO_RECURSO = B.CODIGO_RECURSO) = 1,'A','I') VALOR FROM OPCIONES_DISPONIBLES A"
            		+where+" ORDER BY A.NOMBRE_OPCION "); 
            ResultSet rs = s.executeQuery();
            while (rs.next()) 
            {       
                 PermisosUsuarios oPermisosUsuarios = new PermisosUsuarios();
                 oPermisosUsuarios.setCodigoRecurso(rs.getString(1));
                 oPermisosUsuarios.setCodigoOpcion(rs.getString(2));
                 oPermisosUsuarios.setNombreOpcion(rs.getString(3));
                 oPermisosUsuarios.setActivo(rs.getString(4));
                listaPermisosUsuarioss.add(oPermisosUsuarios);                
            }
        } catch (SQLException ex) {
            System.err.println("Error listando los PermisosUsuarios");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return listaPermisosUsuarioss;
    }
    
    public boolean tienePermisoUsuarioPorRecurso(Conexion oConexion, String login,String modulo) throws SQLException, ClassNotFoundException{
        int cantidadOpciones = 0;
        try {
            PreparedStatement s = oConexion.openConexion().prepareStatement("SELECT COUNT(1) FROM PERMISOS_USUARIOS B WHERE B.LOGIN = '"+login
            		+"' AND B.CODIGO_RECURSO = '"+modulo+"'"); 
            ResultSet rs = s.executeQuery();
            while (rs.next()) 
            {       
                 cantidadOpciones = rs.getInt(1);                
            }
        } catch (SQLException ex) {
            System.err.println("Error obteniendo el permiso por opcion en el modulo: "+modulo);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return cantidadOpciones > 0;
    }
    
    public boolean tienePermisoUsuarioPorOpcion(Conexion oConexion, String login,String modulo, String codigoOpcion) throws SQLException, ClassNotFoundException{
        int cantidadOpciones = 0;
        try {
            PreparedStatement s = oConexion.openConexion().prepareStatement("SELECT COUNT(1) FROM PERMISOS_USUARIOS B WHERE B.LOGIN = '"+login
            		+"' AND B.CODIGO_OPCION = '"+codigoOpcion+"' AND B.CODIGO_RECURSO = '"+modulo+"'"); 
            ResultSet rs = s.executeQuery();
            while (rs.next()) 
            {       
                 cantidadOpciones = rs.getInt(1);                
            }
        } catch (SQLException ex) {
            System.err.println("Error obteniendo el permiso por opcion en el modulo: "+modulo);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return cantidadOpciones > 0;
    }
    
    public List listarControles(Conexion oConexion, String login) throws SQLException, ClassNotFoundException{
        List<Permisos> listaControles = new ArrayList();
        
        String where = "";
        
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT A.CODIGO_RECURSO,A.CODIGO_OPCION, A.CODIGO_ROL FROM PERMISOS A");
            while (rs.next()) 
            {       
                 Permisos oPermisos = new Permisos();
                 oPermisos.setCodigoRecurso(rs.getString(1));
                 oPermisos.setCodigoOpcion(rs.getString(2));
                 oPermisos.setCodigoRol(rs.getString(3));
                 
                 listaControles.add(oPermisos);                
            }
        } catch (SQLException ex) {
            System.err.println("Error listando los PermisosUsuarios");
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
        return listaControles;
    }
    
    public void eliminarPermisosUsuarios(String login,String control,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            s.executeUpdate("DELETE FROM PERMISOS_USUARIOS WHERE LOGIN ='"+login+"'"+" AND CODIGO_RECURSO = '"+control+"'");
            parametros.put("okMensaje", "Registro eliminado exitosamente"); 
        } catch (SQLException ex) {
            System.err.println("Error eliminando el Permiso de Usuario con Login: "+login);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
    }
    
}
