/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlDirectorio;
import dao.BibliotecaDAO;
import dao.Conexion_DB;
import dao.MunicipioDAO;
import dao.ProvinciaDAO;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import modelo.DirectorioBibliotecas;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Sandra
 */
public class AplicacionDirectorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Scanner teclado = new Scanner(System.in);
        int op;
        String cod_provincia, cod_municipio;

        Document doc = null;
        File f1 = new File("directorioBibliotecasCV.xml");
        File f2 = new File("directorioBibliotecasCV2.xml");

        CtrlDirectorio ctrlDirect = new CtrlDirectorio();
        DirectorioBibliotecas directorio = null;

        try {
        //CONEXION BASE DE DATOS
        Conexion_DB conexion_db = new Conexion_DB();
        System.out.println("Abrir conexión");
        Connection con = conexion_db.AbrirConexion();
        System.out.println("Conexión abierta");

        do {
            menu();
            op = teclado.nextInt();
            teclado.nextLine();

            switch (op) {
                case 1:
                    doc = ctrlDirect.recuperar(f1);
                    break;

                case 2:
                    directorio = ctrlDirect.leer(doc);

                    ProvinciaDAO provDAO = new ProvinciaDAO();
                    MunicipioDAO munDAO=new MunicipioDAO();
                    BibliotecaDAO biblioDAO=new BibliotecaDAO();
                    
                    for (int i = 0; i < directorio.size(); i++) {
                        provDAO.inserta(con, directorio.get(i));
                        for (int j = 0; j < directorio.get(i).getMunicipios().size(); j++) {
                            cod_provincia=directorio.get(i).getCod_provincia();
                            munDAO.inserta(con, directorio.get(i).getMunicipios().get(i), cod_provincia);
                            for (int k = 0; k < directorio.get(i).getMunicipios().get(i).getBibliotecas().size(); k++) {
                                cod_municipio=directorio.get(i).getMunicipios().get(i).getCod_municipio();
                                
                                biblioDAO.inserta(con, directorio.get(i).getMunicipios().get(i).getBibliotecas().get(i), cod_municipio);
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println(directorio);
                    break;

                case 4:
                    doc = ctrlDirect.deXMLaDOC();
                    ctrlDirect.escribir(doc);
                    break;

                case 5:
                    ctrlDirect.guardar(doc, f2);
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (op != 0);
        
        //CERRAR CONEXION
        System.out.println("Cerrar conexión");
        
            conexion_db.CerrarConexion(con);
        } catch (Exception ex) {
            Logger.getLogger(AplicacionDirectorio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void menu() {
        System.out.print("/*** DIRECTORIO BIBLIOTECAS COMUNIDAD VALENCIANA ***/\n"
                + "1. Recuperar (XML a DOM)\n"
                + "2. Leer (DOM a OBJETO)\n"
                + "3. Mostrar datos\n"
                + "4. Escribir (OBJETO a DOM)\n"
                + "5. Emmagatzemar (DOM a XML)\n"
                + "Elige una opción: ");
    }
}
