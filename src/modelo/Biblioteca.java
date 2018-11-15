/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Sandra
 */
public class Biblioteca {
    private int id;
    private String tipo;
    private String nombre;
    private String direccion;
    private String cod_postal;
    private String telefono;
    private String web;
    private String email;
    private String catalogo;

    public Biblioteca() {
    }

    public Biblioteca(String tipo, String nombre, String direccion, String cod_postal, String telefono, String web, String email, String catalogo) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cod_postal = cod_postal;
        this.telefono = telefono;
        this.web = web;
        this.email = email;
        this.catalogo = catalogo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }

    @Override
    public String toString() {
        return "\n\t\t\tTipo: " + tipo 
                + "\n\t\t\tNombre: " + nombre 
                + "\n\t\t\tDirección: " + direccion 
                + "\n\t\t\tCod_postal=: " + cod_postal 
                + "\n\t\t\tTeléfono: " + telefono 
                + "\n\t\t\tWeb: " + web 
                + "\n\t\t\tEmail: " + email 
                + "\n\t\t\tCatálogo: " + catalogo;
    }
    
    
}
