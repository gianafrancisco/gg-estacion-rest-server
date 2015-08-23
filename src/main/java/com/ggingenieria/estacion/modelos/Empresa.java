/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggingenieria.estacion.modelos;

/**
 * @author francisco
 */
public class Empresa implements Activable {
    private int empresaId;
    private String nombre;
    private String localidad;
    private String numeroTelefono;
    private String nombreContacto;
    private String fechaAlta;
    private double descuento;
    private int vencimiento;
    private final int tiempoAutorizacion;
    private boolean activo;
    private String cuit;

    public Empresa() {
        tiempoAutorizacion = 8;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public int getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(int vencimiento) {
        this.vencimiento = vencimiento;
    }

    public int getTiempoAutorizacion() {
        return tiempoAutorizacion;
    }

    @Override
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
}
