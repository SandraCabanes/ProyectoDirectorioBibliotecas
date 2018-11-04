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
    private String cod_provincia;
    private String num_provincia;
    private ArrayList<Municipio>municipios=new ArrayList<>();

    public Provincia(String cod_provincia, String num_provincia) {
        this.cod_provincia = cod_provincia;
        this.num_provincia = num_provincia;
    }

    public Provincia() {
    }

    public String getCod_provincia() {
        return cod_provincia;
    }

    public void setCod_provincia(String cod_provincia) {
        this.cod_provincia = cod_provincia;
    }

    public String getNum_provincia() {
        return num_provincia;
    }

    public void setNum_provincia(String num_provincia) {
        this.num_provincia = num_provincia;
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
                + "\n\tNum_provincia: " + num_provincia 
                + "\n\tMunicipios: " + municipios;
    }
     
     
}
