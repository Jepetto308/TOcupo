/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Conexion {
	
    private static Connection cnx = null;
    private String url = "localhost";
    private static String database = "datapets";
    private String user = "root";
    private String password = "jepetto987";
    
   public Connection openConexion() throws SQLException, ClassNotFoundException {
      if (cnx == null) {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://"+url+"/"+database, user, password);
//            System.out.println("Conectado a " + url + " .....Ok");
         } catch (SQLException ex) {
            throw new SQLException(ex);
         } catch (ClassNotFoundException ex) {
            throw new ClassCastException(ex.getMessage());
         }
      }
      return cnx;
   }
   public Connection closeConexion() {
       try {
           if(cnx != null){
               cnx.close();
           }
//           System.out.println("Cerrando conexion a " + url + " .....Ok");
       } catch (SQLException ex) {
           System.out.println(ex);
       }
       cnx = null;
       return cnx;
   }
   
//   public  void main (String[] args){
//        try {
//            obtener();
//        } catch (SQLException ex) {
//            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//   }
    
   
}
