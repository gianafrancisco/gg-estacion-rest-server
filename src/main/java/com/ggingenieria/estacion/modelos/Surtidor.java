/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggingenieria.estacion.modelos;

/**
 * @author francisco
 */
public class Surtidor implements Activable {
    private int surtidorId;
    private int direccionNodo;
    private String descripcion;
    private boolean activo;

    public int getSurtidorId() {
        return surtidorId;
    }

    public void setSurtidorId(int surtidorId) {
        this.surtidorId = surtidorId;
    }

    public int getDireccionNodo() {
        return direccionNodo;
    }

    public void setDireccionNodo(int direccionNodo) {
        this.direccionNodo = direccionNodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }
}
