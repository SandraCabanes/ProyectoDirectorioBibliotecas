/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static controlador.CtrlDom.deDOCaXML;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.TransformerException;
import modelo.DirectorioBibliotecas;
import modelo.Provincia;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Sandra
 */
public class CtrlDirectorio extends CtrlDom {
    static final String ET_DIRECTORIO = "DIRECTORIO_BIBLIOTECAS";
    File file = null;
    DirectorioBibliotecas directorioBibliotecas = null;

    //Constructores
    public CtrlDirectorio() {
        this.directorioBibliotecas = new DirectorioBibliotecas();
    }

    public CtrlDirectorio(DirectorioBibliotecas directBiblio) {
        this.directorioBibliotecas = directBiblio;
    }

    public CtrlDirectorio(DirectorioBibliotecas directBiblio, File file) {
        this.directorioBibliotecas = directBiblio;
        this.file = file;
    }

    //De XML A DOM
    
    public Document recuperar(File xmlFile) throws ParserConfigurationException, IOException, SAXException {
        Document doc = null;
        doc = deXMLaDOC(xmlFile);
        return doc;
    }
    
    public Document recuperar() throws ParserConfigurationException, IOException, SAXException {
        Document doc = null;
        doc = deXMLaDOC(); 
        return doc;
    }

    //De DOM a CLASE/OBJETO
    public DirectorioBibliotecas leer(Document doc) {
        
        Element elDirectorio = doc.getDocumentElement(); 

        NodeList listaProvincias = elDirectorio.getChildNodes();
        directorioBibliotecas.clear();

        for (int i = 0; i < listaProvincias.getLength(); i++) {
            if (listaProvincias.item(i).getNodeType() == Node.ELEMENT_NODE) {
                directorioBibliotecas.add(CtrlProvincia.leer((Element) listaProvincias.item(i)));
            }
        }
        return directorioBibliotecas;
    }
    
    //De CLASE/OBJETO a DOM
    public void escribir(Document doc){
        Element elemDirectorio=doc.createElement(ET_DIRECTORIO);
        doc.appendChild(elemDirectorio);
        
        for (Provincia prov : directorioBibliotecas) {
            CtrlProvincia.escribir(prov, elemDirectorio, doc);
        }
    }
   
    //De DOM a XML
     public void guardar() throws ParserConfigurationException, TransformerException{
        Document doc=null;
        doc=deXMLaDOC();
        escribir(doc);
        deDOCaXML(doc, file);
    }
    
    public void guardar(Document doc, File file) throws TransformerException{
        deDOCaXML(doc, file);
    }

    //Getters y Setters
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public DirectorioBibliotecas getDirectorio() {
        return directorioBibliotecas;
    }

    public void setDirectorio(DirectorioBibliotecas directBiblio) {
        this.directorioBibliotecas = directBiblio;
    }
}