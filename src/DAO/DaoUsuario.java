/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import entity.Filtro;
import entity.Usuario;
import Utils.Conexion;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
/**
 *
 * @author ASUS
 */
public class DaoUsuario{
    
    Conexion oConexion = new Conexion();
    
    public boolean insertarUsuario(Usuario oUsuario,Connection conexion,Map datos) {
        FileInputStream fis = null;
        boolean exito = false;
        try {
            conexion.setAutoCommit(false);
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO usuarios (numero_identificacion, nombre, apellidos, username,"
                    + "telefono,foto_perfil,password,sexo,codigo_rol,estado) VALUES (?,?,?,?,?,?,?,?,?,?)");
           
//            File file = new File(oUsuario.getRutaImg());
//            try {
//				fis = new FileInputStream(file);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
            
            ps.setString(1, oUsuario.getNumeroIdentificacion());
            ps.setString(2, oUsuario.getNombre());
            ps.setString(3, oUsuario.getApellidos());
            ps.setString(4, oUsuario.getUsername());
            ps.setString(5, oUsuario.getTelefono());
//            ps.setBinaryStream(6,fis,fis != null?(int)file.length():0);
            ps.setBinaryStream(6,fis,0);
            ps.setString(7, oUsuario.getPassword());
            ps.setString(8, oUsuario.getSexo());
            ps.setString(9, oUsuario.getCodigoRol());
            ps.setString(10, oUsuario.getEstado());
            
            ps.executeUpdate();
            datos.put("okMessage", "Registro ingresado exitosamente!");
            conexion.commit();
            exito = true;
        } catch (SQLException ex) {
            try {
                conexion.setAutoCommit(false);
                conexion.rollback();
                System.err.println("Error insertando el Usuario: "+oUsuario.getNombre());
                System.out.println(ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                conexion.setAutoCommit(true);
                oConexion.closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exito;
    }
    
    public boolean actualizarUsuario(Usuario oUsuario,Connection conexion,Map datos) {
        FileInputStream fis = null;
        boolean exito = false;
        try {
            conexion.setAutoCommit(false);
            PreparedStatement ps = conexion.prepareStatement("UPDATE usuarios SET nombre = ?,apellidos = ?,telefono = ?, estado = ?, sexo = ? WHERE numero_identificacion = ?");
//            File file = new File(oUsuario.getRutaImg());
//            try {
//				fis = new FileInputStream(file);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//            
            ps.setString(1, oUsuario.getNombre());
            ps.setString(2, oUsuario.getApellidos());
            ps.setString(3, oUsuario.getTelefono());
            ps.setString(4,oUsuario.getEstado());
            ps.setString(5, oUsuario.getSexo());
            ps.setString(6, oUsuario.getNumeroIdentificacion());
            ps.executeUpdate();
            
            conexion.commit();
            datos.put("okMessage", "Registro actualizado exitosamente!");
            exito = true;
        } catch (SQLException ex) {
            try {
                conexion.rollback();
                System.err.println("Error actualizando el Usuario: "+oUsuario.getNombre());
                datos.put("okMessage", "Error actualizando el Usuario: "+oUsuario.getNombre());
                System.out.println(ex.getMessage());
            } catch (SQLException ex1) {
                Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                conexion.setAutoCommit(true);
                oConexion.closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exito;
    }
    
    public List listarUsuarios(Connection conexion, List<Filtro> filtros,int hasta){
        List listaUsuarios = new ArrayList();
        
        String where = "";
        String limit = " LIMIT 10 OFFSET "+(hasta );
        limit = hasta > 10000 ? "" : limit;
        
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
            PreparedStatement s = conexion.prepareStatement("SELECT A.numero_identificacion,A.nombre,A.apellidos,A.username,A.telefono,A.codigo_rol,B.nombre_rol, "
            		+" CONCAT(A.nombre,' ',A.apellidos) nombre_completo "
            		+ "FROM usuarios A INNER JOIN Roles B on A.codigo_rol = B.codigo_rol "+where+limit); 
            ResultSet rs = s.executeQuery();
            while (rs.next()) 
            {       
                 Usuario oUsuario = new Usuario();
                 oUsuario.setNumeroIdentificacion(rs.getString(1));
                 oUsuario.setNombre(rs.getString(2));
                 oUsuario.setApellidos(rs.getString(3));
                 oUsuario.setUsername(rs.getString(4));
                 oUsuario.setTelefono(rs.getString(5));
                 oUsuario.setCodigoRol(rs.getString(6));
                 oUsuario.setNombreRol(rs.getString(7));
                 oUsuario.setNombreCompleto(rs.getString(8));
                
                listaUsuarios.add(oUsuario);                
            }
        } catch (SQLException ex) {
            System.err.println("Error listando los Usuarios");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }finally{
        	oConexion.closeConexion();
        }
        return listaUsuarios;
    }
    
    public int numeroRegistrosUsuario(Connection conexion, List<Filtro> filtros){
        List listaUsuarios = new ArrayList();
        int registros = 0;
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
            Statement s = conexion.createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT COUNT(1) "
            		+ "FROM usuarios A INNER JOIN Roles B on A.codigo_rol = B.codigo_rol "+where);
            while (rs.next()) 
            {       
                 registros = rs.getInt(1);                
            }
        } catch (SQLException ex) {
            System.err.println("Error listando los Usuarios");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }finally{
        	oConexion.closeConexion();
        }
        return registros;
    }
    
    public Usuario consultarUsuario(String numeroIdentificacion,Connection conexion){
        Usuario oUsuario = new Usuario();
        try {
            Statement s = conexion.createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT A.numero_identificacion,A.nombre,A.apellidos,A.username,A.telefono,A.foto_perfil,A.sexo FROM usuarios A WHERE A.numero_identificacion = '"+numeroIdentificacion+"'");
            while (rs.next()) 
            { 
                 oUsuario.setNumeroIdentificacion(rs.getString(1));
                 oUsuario.setNombre(rs.getString(2));
                 oUsuario.setApellidos(rs.getString(3));
                 oUsuario.setUsername(rs.getString(4));
                 oUsuario.setTelefono(rs.getString(5));
                 
                 Blob blob = rs.getBlob(6);
                 byte[] data = blob.getBytes(1, (int)blob.length());
                 BufferedImage img = null;
                try {
                    img = ImageIO.read(new ByteArrayInputStream(data));
                    oUsuario.setFotoPerfil(img);
                } catch (IOException ex) {
                    Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                oUsuario.setSexo(rs.getString(7));
            }
        } catch (SQLException ex) {
            System.err.println("Error consultando el Usuario con Numero Identificacion: "+numeroIdentificacion);
            System.out.println(ex.getMessage());
        }finally{
        	oConexion.closeConexion();
        }
        return oUsuario;
    }
    
    public Usuario consultarUsuarioVista(int idUsuario,Connection conexion){
        Usuario oUsuario = new Usuario();
        try {
            Statement s = conexion.createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT A.numero_identificacion,A.nombre,A.apellidos,A.username,A.telefono,A.foto_perfil,A.sexo,A.estado,A.password FROM usuarios A WHERE A.numero_identificacion = '"+idUsuario+"'");
            while (rs.next()) 
            { 
                 oUsuario.setNumeroIdentificacion(rs.getString(1));
                 oUsuario.setNombre(rs.getString(2));
                 oUsuario.setApellidos(rs.getString(3));
                 oUsuario.setUsername(rs.getString(4));
                 oUsuario.setTelefono(rs.getString(5));
//                 
//                 Blob blob = rs.getBlob(6);
//                 byte[] data = blob.getBytes(1, (int)blob.length());
//                 BufferedImage img = null;
//                try {
//                    img = ImageIO.read(new ByteArrayInputStream(data));
//                    oUsuario.setFotoPerfil(img);
//                } catch (IOException ex) {
//                    Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
//                }
                
                oUsuario.setSexo(rs.getString(7));
                oUsuario.setEstado(rs.getString(8));
                oUsuario.setPassword(rs.getString(9));
            }
        } catch (SQLException ex) {
            System.err.println("Error consultando el Usuario con Numero ID: "+idUsuario);
            System.out.println(ex.getMessage());
        }finally{
        	oConexion.closeConexion();
        }
        return oUsuario;
    }
    
    public void eliminarUsuario(int idUsuario,Map parametros, Conexion oConexion) throws SQLException, ClassNotFoundException{
        try {
            Statement s = oConexion.openConexion().createStatement(); 
            s.executeUpdate("DELETE FROM Usuarios WHERE numero_identificacion ="+idUsuario);
            parametros.put("okMensaje", "Registro eliminado exitosamente");
        } catch (SQLException ex) {
            System.err.println("Error eliminando el Usuario con ID: "+idUsuario);
            System.out.println(ex.getMessage());
        }finally{
			oConexion.closeConexion();
        }
    }
    
    public boolean existeUsuarioConNumeroIdentificacion(String numeroIdentificacion) {
        
        String numeroDocumento = "";
        Conexion oConexion = new Conexion();
        try {
        	Connection conexion = oConexion.openConexion();
            Statement s = conexion.createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT A.numero_identificacion FROM usuarios A WHERE A.numero_identificacion = '"+numeroIdentificacion+"' LIMIT 1");
            while (rs.next()) 
            { 
                numeroDocumento = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("Error validando existencia del Usuario con Numero de identificacion: "+numeroIdentificacion);
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
        	oConexion.closeConexion();
        }
        return !"".equals(numeroDocumento);
    }
    
    public boolean existeUsuarioConUsername(String username) throws ClassNotFoundException, SQLException{
        
        String usuario = "";
        Conexion oConexion = new Conexion();
        Connection conexion = oConexion.openConexion();
        try {
            Statement s = conexion.createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT A.username FROM usuarios A WHERE A.username = '"+username+"' LIMIT 1");
            while (rs.next()) 
            { 
                usuario = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("Error validando existencia del Usuario con Usuario: "+username);
            System.out.println(ex.getMessage());
        }finally{
        	oConexion.closeConexion();
        }
        return !"".equals(usuario);
    }
    
    public Usuario login(String email,String password,Conexion conexion) throws ClassNotFoundException{
        Usuario oUsuario = new Usuario();
        try {
            Statement s = conexion.openConexion().createStatement(); 
            ResultSet rs = s.executeQuery ("CALL LOGIN('"+email+"','"+password+"');");
            while (rs.next()) 
            { 
            	
            	String mensajeInicial = rs.getString(1);
            	if("NO EXISTE".equalsIgnoreCase(mensajeInicial)) {
            		break;
            	}
                 oUsuario.setNumeroIdentificacion(rs.getString("NUMERO_DOCUMENTO"));
                 oUsuario.setNombre(rs.getString("NOMBRES"));
                 oUsuario.setApellidos(rs.getString("APELLIDOS"));
//                 oUsuario.setUsername(rs.getString(4));
                 oUsuario.setTelefono(rs.getString("TELEFONO"));
                 
//                 Blob blob = rs.getBlob(6);
//                 byte[] data = blob.getBytes(1, (int)blob.length());
//                 BufferedImage img = null;
//                try {
//                    img = ImageIO.read(new ByteArrayInputStream(data));
//                    oUsuario.setFotoPerfil(img);
//                } catch (IOException ex) {
//                    Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
//                }
                
//                oUsuario.setSexo(rs.getString(7));
//                oUsuario.setCodigoRol(rs.getString(8));
//                oUsuario.setNombreRol(rs.getString(9));
            }
        } catch (SQLException ex) {
            System.err.println("Error validando el Usuario con Usuario con Email: "+email);
            System.out.println(ex.getMessage());
        }finally{
        	conexion.closeConexion();
        }
        return oUsuario;
    }
    
}
