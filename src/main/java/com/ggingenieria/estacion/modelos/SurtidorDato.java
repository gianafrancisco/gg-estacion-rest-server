/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggingenieria.estacion.modelos;

/**
 *
 * @author francisco
 */
public class SurtidorDato {
    private double carga;

    public SurtidorDato(){
        
    }
    
    public SurtidorDato(double d) {
        this.carga = d;
    }

    public double getCarga() {
        return carga;
    }

    public void setCarga(double carga) {
        this.carga = carga;
    }

}
