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
public class DirectorioBibliotecas extends ArrayList<Provincia>{
    
    public ArrayList<Provincia> getProvincias(){
        return this;
    }

   @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.getProvincias().size(); i++) {
            result += "\n" + this.getProvincias().get(i).toString();
        }
        return result;
    } 
}
