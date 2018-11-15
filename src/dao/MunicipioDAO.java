/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Municipio;
/**
 *
 * @author Sandra
 */
public class MunicipioDAO {
     
    /*public void actualiza(Connection con, Municipio municipio) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE municipio SET cod_municipio=? WHERE cod_municipio=?");
            stmt.setString(1, municipio.getCod_municipio());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar el municipio " + ex.getMessage());
            //Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }*/
    
    public void elimina(Connection con, Municipio municipio) throws Exception{
        PreparedStatement stmt=null;
        
        try{
           stmt=con.prepareStatement("DELETE FROM municipio WHERE cod_municipio=?");
           stmt.setString(1, municipio.getCod_municipio());
           stmt.executeUpdate();
           
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Problemas al eliminar el municipio "+ex.getMessage());
        } finally{
           if(stmt!=null){
                stmt.close();
            }
        } 
    }
    public void inserta(Connection con, Municipio municipio, String cod_provincia) throws Exception{
        PreparedStatement stmt=null;
        
        try{
            stmt=con.prepareStatement("INSERT INTO municipio (cod_municipio, cod_provincia) VALUES(?,?)");
            
            stmt.setString(1, municipio.getCod_municipio());
            stmt.setString(2, cod_provincia);
            
            stmt.executeUpdate();
        
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Problemas al insertar municipio "+ex.getMessage());
        } finally{
           if(stmt!=null){
                stmt.close();
            }
        }
    }
}
