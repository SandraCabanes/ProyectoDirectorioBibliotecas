/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Biblioteca;
import modelo.DirectorioBibliotecas;
import modelo.Municipio;
import modelo.Provincia;

/**
 *
 * @author Sandra
 */
public class DirectorioDAO {

    int cod_provincia, cod_municipio;

    public void cargarJDB(DirectorioBibliotecas directorio) {
        try {
            //CONEXION BASE DE DATOS
            Conexion_DB conexion_db = new Conexion_DB();
            System.out.println("Abrir conexión");
            Connection con = conexion_db.AbrirConexion();
            System.out.println("Conexión abierta");

            ProvinciaDAO provDAO = new ProvinciaDAO();
            MunicipioDAO munDAO = new MunicipioDAO();
            BibliotecaDAO biblioDAO = new BibliotecaDAO();

            for (int i = 0; i < directorio.size(); i++) {
                Provincia prov = new Provincia(directorio.get(i).getCod_provincia(), directorio.get(i).getNom_provincia());
                provDAO.inserta(con, prov);
                for (int j = 0; j < directorio.get(i).getMunicipios().size(); j++) {
                    Municipio muni = new Municipio(i + 1, directorio.get(i).getMunicipios().get(j).getCod_municipio());
                    munDAO.inserta(con, muni);
                    for (int k = 0; k < directorio.get(i).getMunicipios().get(j).getBibliotecas().size(); k++) {
                        Biblioteca biblio = new Biblioteca(j + 1, directorio.get(i).getMunicipios().get(j).getBibliotecas().get(k).getTipo(), directorio.get(i).getMunicipios().get(j).getBibliotecas().get(k).getNombre(), directorio.get(i).getMunicipios().get(j).getBibliotecas().get(k).getDireccion(), directorio.get(i).getMunicipios().get(j).getBibliotecas().get(k).getCod_postal(), directorio.get(i).getMunicipios().get(j).getBibliotecas().get(k).getTelefono(), directorio.get(i).getMunicipios().get(j).getBibliotecas().get(k).getWeb(), directorio.get(i).getMunicipios().get(j).getBibliotecas().get(k).getEmail(), directorio.get(i).getMunicipios().get(j).getBibliotecas().get(k).getCatalogo());
                        biblioDAO.inserta(con, biblio);
                    }
                }
            }

            //CERRAR CONEXION
            System.out.println("Cerrar conexión");

            conexion_db.CerrarConexion(con);
        } catch (Exception ex) {
            Logger.getLogger(DirectorioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
