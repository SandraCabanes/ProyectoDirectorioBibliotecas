/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.BibliotecaDAO;
import dao.Conexion_DB;
import dao.MunicipioDAO;
import dao.ProvinciaDAO;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Biblioteca;
import modelo.Municipio;
import modelo.Provincia;

/**
 *
 * @author Sandra
 */
public class AplicacionDirectorioBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Conexion_DB conexion_db = new Conexion_DB();
            System.out.println("Abrir conexión");
            Connection con = conexion_db.AbrirConexion();
            System.out.println("Conexión abierta");

            ProvinciaDAO provDAO = new ProvinciaDAO();

            //Actualizar cliente
            Provincia p=new Provincia("1111", "Chequia");
            //provDAO.actualiza(con, p);
            
            
            //Añadir nuevo cliente
            Provincia p2 = new Provincia("1", "La terreta");
            //provDAO.inserta(con, p2);

            //Borrar cliente
            Provincia p3=new Provincia();
            p3.setCod_provincia("1111");
            //provDAO.elimina(con, p3);
            
             MunicipioDAO munDAO = new MunicipioDAO();

            //Actualizar cliente
            Municipio m=new Municipio("02");
            //munDAO.actualiza(con, p);
            
            
            //Añadir nuevo cliente
            Municipio m2 = new Municipio("01");
            munDAO.inserta(con, m2);

            //Borrar cliente
            Municipio m3=new Municipio();
            m3.setCod_municipio("1111");
            //munDAO.elimina(con, m3);
            
            
            BibliotecaDAO biblioDAO = new BibliotecaDAO();

            //Actualizar cliente
            Biblioteca b=new Biblioteca("2", "municipal", "carrer asd", "46800", "6666", "www.google.es", "asjda@kasjd.com", "catatlgoo");
            //biblioDAO.actualiza(con, b);
            
            
            //Añadir nuevo cliente
            Biblioteca b2=new Biblioteca("3", "municipal", "aveingutad", "4200", "777", "www.caralibrp.es", "asjda@kasjd.com", "catatlgoo");
            biblioDAO.inserta(con, b2);

            //Borrar cliente
            Provincia b3=new Provincia();
            p3.setCod_provincia("1111");
            //biblioDAO.elimina(con, b3);
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            /*Buscar un cliente por su DNI
            Cliente cli4=new Cliente();
            cli4.setDNI(25);
            cli4=cliDAO.findByDNI(con, cli4);
            System.out.println(cli4);
            
            //Buscar un cliente por su NICK
            Cliente cli5=new Cliente();
            cli5.setNick("peter");
            cli5=cliDAO.findByNick(con, cli5);
            System.out.println(cli5);
            
            //Obtener lista de clientes con DNI que empiecen igual
            int num=1;
            List<Cliente>listaClientesDNI=cliDAO.findByNumberDNIStart(con, num);
            System.out.println("\nLista de clientes cuyo DNI empieza por "+num);
            for (Cliente c : listaClientesDNI) {
                System.out.println(c);
            }*/
            
            
            System.out.println("Cerrar conexión");
            conexion_db.CerrarConexion(con);
        } catch (Exception ex) { 
            Logger.getLogger(AplicacionDirectorioBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Conexión cerrada");
    }
}
