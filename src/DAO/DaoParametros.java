/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import entity.parametros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author WILMAR
 */
public class DaoParametros {
    
     public void guardar(Connection connexion, parametros parametros) throws SQLException{
      try{
         PreparedStatement consulta = null;
        
         //Sentencia de insert into que viene desde la base de datos
         String sqlInsertParametros = 
	"INSERT IGNORE"+
	"  INTO parametros("+
	"                CODIGO,"+
	"                VALOR,"+
	"                VALORES,"+

	

	"VALUES ("+
	"        ?,"+
	"        ?,"+
	"        ?,";

         
         //Parametros (get) que se obtienen de la clase cliente.java
         if(parametros.getCodigo() == ""){
            consulta = connexion.prepareStatement(sqlInsertParametros.toString());
            consulta.setString(1, parametros.getCodigo());
            consulta.setString(2, parametros.getValor());
            consulta.setString(3, parametros.getValores());
        
         }else{
             
         }
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
}
