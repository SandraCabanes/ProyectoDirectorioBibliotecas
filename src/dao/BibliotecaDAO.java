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
import modelo.Biblioteca;
import modelo.Provincia;

/**
 *
 * @author Sandra
 */
public class BibliotecaDAO {

    public void actualiza(Connection con, Biblioteca biblioteca) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE biblioteca SET tipo=?, direccion=?, cod_postal=?, "
                    + "telefono=?, web=?, email=?, catalogo=? WHERE nombre=?");

            stmt.setString(1, biblioteca.getTipo());
            //stmt.setString(2, biblioteca.getNombre());
            stmt.setString(2, biblioteca.getDireccion());
            stmt.setString(3, biblioteca.getCod_postal());
            stmt.setString(4, biblioteca.getTelefono());
            stmt.setString(5, biblioteca.getWeb());
            stmt.setString(6, biblioteca.getEmail());
            stmt.setString(7, biblioteca.getCatalogo());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al actualizar la biblioteca " + ex.getMessage());
            //Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void elimina(Connection con, Biblioteca biblioteca) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM biblioteca WHERE nombre=?");
            stmt.setString(1, biblioteca.getNombre());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Problemas al eliminar la biblioteca " + ex.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void inserta(Connection con, Biblioteca biblioteca, String cod_municipio) throws Exception {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO biblioteca (cod_municipio, tipo, nombre, direccion, cod_postal, "
                    + "telefono, web, email, catalogo) VALUES(?, ?,?,?,?,?,?,?,?)");

            stmt.setString(1, cod_municipio);
            stmt.setString(2, biblioteca.getTipo());
            stmt.setString(3, biblioteca.getNombre());
            stmt.setString(4, biblioteca.getDireccion());
            stmt.setString(5, biblioteca.getCod_postal());
            stmt.setString(6, biblioteca.getTelefono());
            stmt.setString(7, biblioteca.getWeb());
            stmt.setString(8, biblioteca.getEmail());
            stmt.setString(9, biblioteca.getCatalogo());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Problemas al insertar biblioteca " + ex.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    private void obtenBiblioFila(ResultSet rs, Biblioteca biblio) throws Exception {
        biblio.setTipo(rs.getString("tipo"));
        biblio.setNombre(rs.getString("nombre"));
        biblio.setDireccion(rs.getString("direccion"));
        biblio.setCod_postal(rs.getString("cod_postal"));
        biblio.setTelefono(rs.getString("telefono"));
        biblio.setWeb(rs.getString("web"));
        biblio.setEmail(rs.getString("email"));
        biblio.setCatalogo(rs.getString("catalogo"));
    }

    public Biblioteca findByNombre(Connection con, Biblioteca biblio) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM biblioteca WHERE nombre=?");
            stmt.setString(1, biblio.getTipo());
            stmt.setString(2, biblio.getNombre());

            rs = stmt.executeQuery();

            while (rs.next()) {
                biblio = new Biblioteca();
                obtenBiblioFila(rs, biblio);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("problema al buscar por DNI " + ex.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return biblio;
    }
}
