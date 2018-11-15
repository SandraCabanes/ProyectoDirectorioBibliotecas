/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Provincia;

/**
 *
 * @author Sandra
 */
public class ProvinciaDAO {
    public void actualiza(Connection con, Provincia provincia) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE provincia SET nom_provincia=? WHERE cod_provincia=?");
            stmt.setString(1, provincia.getNom_provincia());
            stmt.setString(2, provincia.getCod_provincia());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar la provincia " + ex.getMessage());
            //Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    public void elimina(Connection con, Provincia provincia) throws Exception{
        PreparedStatement stmt=null;
        
        try{
           stmt=con.prepareStatement("DELETE FROM provincia WHERE cod_provincia=?");
           stmt.setString(1, provincia.getCod_provincia());
           stmt.executeUpdate();
           
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Problemas al eliminar la provincia "+ex.getMessage());
        } finally{
           if(stmt!=null){
                stmt.close();
            }
        } 
    }
    public void inserta(Connection con, Provincia provincia) throws Exception{
        PreparedStatement stmt=null;
        
        try{
            stmt=con.prepareStatement("INSERT INTO provincia (cod_provincia, nom_provincia) VALUES(?,?)");
            
            stmt.setString(1, provincia.getCod_provincia());
            stmt.setString(2, provincia.getNom_provincia());
            
            stmt.executeUpdate();
        
        } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("Problemas al insertar provincia "+ex.getMessage());
        } finally{
           if(stmt!=null){
                stmt.close();
            }
        }
    }
    
    private void obtenProvinciaFila(ResultSet rs, Provincia provincia) throws Exception{
        provincia.setCod_provincia(rs.getString("cod_provincia"));
        provincia.setNom_provincia(rs.getString("nom_provincia"));
    }
    
    public Provincia findByCod(Connection con, Provincia provincia) throws Exception{
        
        PreparedStatement stmt=null;
        ResultSet rs=null;
        
        try{
         stmt = con.prepareStatement("SELECT * FROM provincia WHERE cod_provincia=?");
         stmt.setString(1, provincia.getNom_provincia());
         stmt.setString(2, provincia.getCod_provincia());
         rs = stmt.executeQuery();
            
            while(rs.next()){
                provincia=new Provincia();
                obtenProvinciaFila(rs, provincia);
            }
         
        }catch (SQLException ex){
           ex.printStackTrace();
           throw new Exception("problema al buscar por DNI "+ex.getMessage());
        }finally
        {
            if(rs!=null) rs.close();
            if(stmt!=null) stmt.close();
        }
        return provincia;
    }
}
