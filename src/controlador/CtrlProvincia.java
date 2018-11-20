/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static controlador.CtrlDom.getElementEtiqueta;
import java.util.ArrayList;
import modelo.Biblioteca;
import modelo.Municipio;
import modelo.Provincia;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Sandra
 */
public class CtrlProvincia extends CtrlDom {

    static final String ET_PROVINCIA = "PROVINCIA";
    static final String ET_CODPROVINCIA = "COD_PROVINCIA";
    static final String ET_NUMPROVINCIA = "NOM_PROVINCIA";
    static final String ET_MUNICIPIOS = "MUNICIPIOS";
    static final String ET_MUNICIPIO = "MUNICIPIO";
    static final String ET_CODMUNICIPIO = "COD_MUNICIPIO";
    static final String ET_BIBLIOTECAS = "BIBLIOTECAS";
    static final String ET_BIBLIOTECA = "BIBLIOTECA";
    static final String ET_TIPO = "TIPO";
    static final String ET_NOMBRE = "NOMBRE";
    static final String ET_DIRECCION = "DIRECCION";
    static final String ET_CODPOSTAL = "COD_POSTAL";
    static final String ET_TELEFONO = "TELEFONO";
    static final String ET_WEB = "WEB";
    static final String ET_EMAIL = "EMAIL";
    static final String ET_CATALOGO = "CATALOGO";

    //LEER PROVINCIA-MUNICIPIO-BIBLIOTECA
    public static Provincia leer(Element elemProvincia) {
        Provincia provincia = null;
        provincia = leerProvincia(elemProvincia);
        return provincia;
    }

    private static Provincia leerProvincia(Element elemProvincia) {
        Provincia provincia = new Provincia();

        String cod = getValorEtiqueta(ET_CODPROVINCIA, elemProvincia);
        cod = cod.replaceAll(" ", "");
        cod = cod.replaceAll("\t", "");
        cod = cod.replaceAll("\n", "");
        provincia.setCod_provincia(Integer.parseInt(cod));

        String nom = getValorEtiqueta(ET_NUMPROVINCIA, elemProvincia);
        nom = nom.trim();
        nom = nom.replaceAll("\t", "");
        nom = nom.replaceAll("\n", "");
        provincia.setNom_provincia(nom);

        Element elemMunicipio = getElementEtiqueta(ET_MUNICIPIOS, elemProvincia);
        provincia.setMunicipios(leerMunicipios(elemMunicipio));

        return provincia;
    }

    private static ArrayList<Municipio> leerMunicipios(Element elemMunicipios) {
        NodeList nMunicipios = elemMunicipios.getChildNodes();
        ArrayList<Municipio> listaMunicipios = new ArrayList<>();

        for (int i = 0; i < nMunicipios.getLength(); i++) {
            Municipio municip = new Municipio();
            if (nMunicipios.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element elemMunicip = (Element) nMunicipios.item(i);

                String cod = getValorEtiqueta(ET_CODMUNICIPIO, elemMunicip);
                cod = cod.replaceAll(" ", "");
                cod = cod.replaceAll("\t", "");
                cod = cod.replaceAll("\n", "");
                municip.setCod_municipio(Integer.parseInt(cod));

                Element elemBibliotecas = getElementEtiqueta(ET_BIBLIOTECAS, elemMunicip);
                municip.setBibliotecas(leerBibliotecas(elemBibliotecas));
                listaMunicipios.add(municip);
            }
        }
        return listaMunicipios;
    }

    //LEER BIBLIOTECA-MUNICIPIO-PROVINCIA
    private static ArrayList<Biblioteca> leerBibliotecas(Element elemBibliotecas) {
        ArrayList<Biblioteca> biblios = new ArrayList<>();
        NodeList listaBiblioteca = elemBibliotecas.getChildNodes();

        for (int i = 0; i < listaBiblioteca.getLength(); i++) {

            if (listaBiblioteca.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Biblioteca b = new Biblioteca();
                Element elemBiblio = (Element) listaBiblioteca.item(i);

                String tipo = getValorEtiqueta(ET_TIPO, elemBiblio);
                tipo = tipo.trim();
                tipo = tipo.replaceAll("\t", "");
                tipo = tipo.replaceAll("\n", "");
                b.setTipo(tipo);
                
                String nombre = getValorEtiqueta(ET_NOMBRE, elemBiblio);
                nombre = nombre.trim();
                nombre = nombre.replaceAll("\t", "");
                nombre = nombre.replaceAll("\n", "");
                b.setNombre(nombre);
                
                String direccion = getValorEtiqueta(ET_DIRECCION, elemBiblio);
                direccion = direccion.trim();
                direccion = direccion.replaceAll("\t", "");
                direccion = direccion.replaceAll("\n", "");
                b.setDireccion(direccion);
                
                String codpostal = getValorEtiqueta(ET_CODPOSTAL, elemBiblio);
                codpostal = codpostal.trim();
                codpostal = codpostal.replaceAll("\t", "");
                codpostal = codpostal.replaceAll("\n", "");
                b.setCod_postal(codpostal);
                
                String telefono = getValorEtiqueta(ET_TELEFONO, elemBiblio);
                telefono = telefono.trim();
                telefono = telefono.replaceAll("\t", "");
                telefono = telefono.replaceAll("\n", "");
                b.setTelefono(telefono);
                
                String web = getValorEtiqueta(ET_WEB, elemBiblio);
                web = web.trim();
                web = web.replaceAll("\t", "");
                web = web.replaceAll("\n", "");
                b.setWeb(web);
                
                String mail = getValorEtiqueta(ET_EMAIL, elemBiblio);
                mail = mail.trim();
                mail = mail.replaceAll("\t", "");
                mail = mail.replaceAll("\n", "");
                b.setEmail(mail);
                
                String catalogo = getValorEtiqueta(ET_CATALOGO, elemBiblio);
                catalogo = catalogo.trim();
                catalogo = catalogo.replaceAll("\t", "");
                catalogo = catalogo.replaceAll("\n", "");
                b.setCatalogo(catalogo);
                

                biblios.add(b);
            }
        }
        return biblios;
    }

    //ESCRIBIR
    static void escribir(Provincia prov, Element elemDirectorio, Document doc) {
        Element elemProvincia = doc.createElement(ET_PROVINCIA);

        //Cod_provincia
        Element elemCod_prov = doc.createElement(ET_CODPROVINCIA);
        elemCod_prov.setTextContent(Integer.toString(prov.getCod_provincia()));
        //elemCod_prov.setTextContent(prov.getCod_provincia());
        elemProvincia.appendChild(elemCod_prov);

        //Num_provincia
        Element elemNum_prov = doc.createElement(ET_NUMPROVINCIA);
        elemNum_prov.setTextContent(prov.getNom_provincia());
        elemProvincia.appendChild(elemNum_prov);

        //Municipios
        Element elemMunicipios = doc.createElement(ET_MUNICIPIOS);
        escribirMunicipios(prov, doc, elemMunicipios);
        elemProvincia.appendChild(elemMunicipios);

        elemDirectorio.appendChild(elemProvincia);
    }

    private static void escribirMunicipios(Provincia prov, Document doc, Element elemMunicipios) {
        ArrayList<Municipio> listaMunicipios = prov.getMunicipios();

        for (int i = 0; i < listaMunicipios.size(); i++) {
            Municipio mun = listaMunicipios.get(i);
            Element elemMuni = doc.createElement(ET_MUNICIPIO);

            //Cod_municipio
            Element elemCod_mun = doc.createElement(ET_CODMUNICIPIO);
            elemCod_mun.setTextContent(Integer.toString(mun.getCod_municipio()));
            elemMuni.appendChild(elemCod_mun);

            //Bibliotecas
            Element elemBiblios = doc.createElement(ET_BIBLIOTECAS);
            escribirBibliotecas(mun, doc, elemBiblios);
            elemMuni.appendChild(elemBiblios);

            elemMunicipios.appendChild(elemMuni);
        }

    }

    private static void escribirBibliotecas(Municipio mun, Document doc, Element elemBiblios) {
        ArrayList<Biblioteca> listaBibliotecas = mun.getBibliotecas();

        for (int i = 0; i < listaBibliotecas.size(); i++) {
            Biblioteca biblio = listaBibliotecas.get(i);
            Element elemBiblio = doc.createElement(ET_BIBLIOTECA);

            //tipo
            Element elemTipo = doc.createElement(ET_TIPO);
            elemTipo.setTextContent(biblio.getTipo());
            elemBiblio.appendChild(elemTipo);

            //Nombre
            Element elemNombre = doc.createElement(ET_NOMBRE);
            elemNombre.setTextContent(biblio.getNombre());
            elemBiblio.appendChild(elemNombre);

            //Direccion
            Element elemDireccion = doc.createElement(ET_DIRECCION);
            elemDireccion.setTextContent(biblio.getDireccion());
            elemBiblio.appendChild(elemDireccion);

            //Cod_postal
            Element elemPostal = doc.createElement(ET_CODPOSTAL);
            elemPostal.setTextContent(biblio.getCod_postal());
            elemBiblio.appendChild(elemPostal);

            //Telefono
            Element elemTelefono = doc.createElement(ET_TELEFONO);
            elemTelefono.setTextContent(biblio.getTelefono());
            elemBiblio.appendChild(elemTelefono);

            //Web
            Element elemWeb = doc.createElement(ET_WEB);
            elemWeb.setTextContent(biblio.getWeb());
            elemBiblio.appendChild(elemWeb);

            //Mail
            Element elemCorreu = doc.createElement(ET_EMAIL);
            elemCorreu.setTextContent(biblio.getEmail());
            elemBiblio.appendChild(elemCorreu);

            //Catalogo
            Element elemCatalog = doc.createElement(ET_CATALOGO);
            elemCatalog.setTextContent(biblio.getCatalogo());
            elemBiblio.appendChild(elemCatalog);

            elemBiblios.appendChild(elemBiblio);
        }
    }
}
