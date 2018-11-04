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

        provincia.setCod_provincia(getValorEtiqueta(ET_CODPROVINCIA, elemProvincia));
        provincia.setNum_provincia(getValorEtiqueta(ET_NUMPROVINCIA, elemProvincia));

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

                municip.setCod_municipio(getValorEtiqueta(ET_CODMUNICIPIO, elemMunicip));
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
                b.setTipo(getValorEtiqueta(ET_TIPO, elemBiblio));
                b.setNombre(getValorEtiqueta(ET_NOMBRE, elemBiblio));
                b.setDireccion(getValorEtiqueta(ET_DIRECCION, elemBiblio));
                b.setCod_postal(getValorEtiqueta(ET_CODPOSTAL, elemBiblio));
                b.setTelefono(getValorEtiqueta(ET_TELEFONO, elemBiblio));
                b.setWeb(getValorEtiqueta(ET_WEB, elemBiblio));
                b.setEmail(getValorEtiqueta(ET_EMAIL, elemBiblio));
                b.setCatalogo(getValorEtiqueta(ET_CATALOGO, elemBiblio));

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
        elemCod_prov.setTextContent(prov.getCod_provincia());
        elemProvincia.appendChild(elemCod_prov);

        //Num_provincia
        Element elemNum_prov = doc.createElement(ET_NUMPROVINCIA);
        elemNum_prov.setTextContent(prov.getNum_provincia());
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
            elemCod_mun.setTextContent(mun.getCod_municipio());
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
