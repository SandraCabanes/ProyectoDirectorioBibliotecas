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
    private int id_municipio;
    private int id_provinciaAjena;
    private int cod_municipio;
    private ArrayList<Biblioteca>bibliotecas=new ArrayList<>();

    public Municipio(int id_provinciaAjena, int cod_municipio) {
        this.id_provinciaAjena = id_provinciaAjena;
        this.cod_municipio = cod_municipio;
    }

    public int getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(int id_municipio) {
        this.id_municipio = id_municipio;
    }

    public int getId_provinciaAjena() {
        return id_provinciaAjena;
    }

    public void setId_provinciaAjena(int id_provinciaAjena) {
        this.id_provinciaAjena = id_provinciaAjena;
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
