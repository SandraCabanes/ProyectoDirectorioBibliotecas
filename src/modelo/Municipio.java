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
public class Municipio {
    private int cod_municipio;
    private ArrayList<Biblioteca>bibliotecas=new ArrayList<>();

    public Municipio(int cod_municipio) {
        this.cod_municipio = cod_municipio;
    }

    public Municipio() {
    }

    public int getCod_municipio() {
        return cod_municipio;
    }

    public void setCod_municipio(int cod_municipio) {
        this.cod_municipio = cod_municipio;
    }

    public ArrayList<Biblioteca> getBibliotecas() {
        return bibliotecas;
    }

    public void setBibliotecas(ArrayList<Biblioteca> bibliotecas) {
        this.bibliotecas = bibliotecas;
    }

    @Override
    public String toString() {
        return "\n\t\tCod_municipio: " + cod_municipio 
                + "\n\t\tBibliotecas: " + bibliotecas ;
    }
}
