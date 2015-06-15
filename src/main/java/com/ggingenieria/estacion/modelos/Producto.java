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
public class Producto {
    private int productoId;
    private int puntos;
    private int puntosConDescuento;
    private String descripcion;

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPuntosConDescuento() {
        return puntosConDescuento;
    }

    public void setPuntosConDescuento(int puntosConDescuento) {
        this.puntosConDescuento = puntosConDescuento;
    }
}
