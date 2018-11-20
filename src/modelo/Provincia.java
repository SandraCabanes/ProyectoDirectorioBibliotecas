/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Sandra
 */
public class Provincia {
    private int id_provincia;
    private int cod_provincia;
    private String nom_provincia;
    private ArrayList<Municipio>municipios=new ArrayList<>();

    public Provincia(int cod_provincia, String nom_provincia) {
        this.cod_provincia = cod_provincia;
        this.nom_provincia = nom_provincia;
    }

    public int getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(int id_provincia) {
        this.id_provincia = id_provincia;
    }

    

    public Provincia() {
    }

    public int getCod_provincia() {
        return cod_provincia;
    }

    public void setCod_provincia(int cod_provincia) {
        this.cod_provincia = cod_provincia;
    }

    public String getNom_provincia() {
        return nom_provincia;
    }

    public void setNom_provincia(String nom_provincia) {
        this.nom_provincia = nom_provincia;
    }

    public ArrayList<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(ArrayList<Municipio> municipios) {
        this.municipios = municipios;
    }
    
     public void imprimirMunicipios(){
        for (int i = 0; i < this.municipios.size(); i++) {
            System.out.println(this.municipios.get(i));
        }
    }

    @Override
    public String toString() {
        return "Provincia:\n" + "\tCod_provincia: " + cod_provincia 
                + "\n\tNum_provincia: " + nom_provincia 
                + "\n\tMunicipios: " + municipios;
    }
     
     
}
