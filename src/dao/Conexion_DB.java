/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sandra
 */
public class Conexion_DB {
    public Connection AbrirConexion(){
        Connection con=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String urlOdbc="jdbc:mysql://localhost:3306/directoriobibliotecas";
            con=java.sql.DriverManager.getConnection(urlOdbc, "root", "");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion_DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public void CerrarConexion(Connection con) throws Exception{
        if(con!=null) try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha sido imposible establecer la conexión "+ex.getMessage());
            //Logger.getLogger(Conexion_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
