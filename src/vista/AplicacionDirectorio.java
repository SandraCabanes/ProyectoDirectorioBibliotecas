/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlDirectorio;
import dao.DirectorioDAO;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
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

        Document doc = null;
        File f1 = new File("directorioBibliotecasCV.xml");
        File f2 = new File("directorioBibliotecasCV2.xml");

        CtrlDirectorio ctrlDirect = new CtrlDirectorio();
        DirectorioBibliotecas directorio = null;

        DirectorioDAO direcDAO=null;
       
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

                    case 6:
                        direcDAO = new DirectorioDAO();
                        direcDAO.cargarJDB(directorio);

                        break;
                        
                    case 7:
                        direcDAO = new DirectorioDAO();
                        directorio=direcDAO.cargarClases();
                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción inválida");
                        break;
                }
            } while (op != 0);

            

    }

    private static void menu() {
        System.out.print("/*** DIRECTORIO BIBLIOTECAS COMUNIDAD VALENCIANA ***/\n"
                + "1. Recuperar (XML a DOM)\n"
                + "2. Leer (DOM a OBJETO)\n"
                + "3. Mostrar datos\n"
                + "4. Escribir (OBJETO a DOM)\n"
                + "5. Emmagatzemar (DOM a XML)\n"
                + "6. Añadir a la base de datos\n"
                + "7. Cargar clases de la base de datos\n"
                + "Elige una opción: ");
    }
}
