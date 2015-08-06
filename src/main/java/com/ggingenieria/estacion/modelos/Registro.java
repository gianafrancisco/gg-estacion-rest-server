/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggingenieria.estacion.modelos;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author francisco
 */
public class Registro implements Activable {
    private int registroId;
    private int usuarioId;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String permisos;
    private int surtidorId;
    private String descripcionSurtidor;
    private int direccionNodo;
    private int productoId;
    private String descripcionProducto;
    private int puntosCambiados;
    private int empresaId;
    private String nombreEmpresa;
    private double descuento;
    private int vencimiento;
    private int vehiculoId;
    private boolean autorizado;
    private String dominio;
    private String dominioMunicipal;
    private Date fechaRegistro;
    private Date fechaProximaVenta;
    private Date fechaVencimiento;
    private String accion;
    private Calendar calendar;

    public int getRegistroId() {
        return registroId;
    }

    public void setRegistroId(int registroId) {
        this.registroId = registroId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public int getSurtidorId() {
        return surtidorId;
    }

    public void setSurtidorId(int surtidorId) {
        this.surtidorId = surtidorId;
    }

    public String getDescripcionSurtidor() {
        return descripcionSurtidor;
    }

    public void setDescripcionSurtidor(String descripcionSurtidor) {
        this.descripcionSurtidor = descripcionSurtidor;
    }

    public int getDireccionNodo() {
        return direccionNodo;
    }

    public void setDireccionNodo(int direccionNodo) {
        this.direccionNodo = direccionNodo;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getPuntosCambiados() {
        return puntosCambiados;
    }

    public void setPuntosCambiados(int puntosCambiados) {
        this.puntosCambiados = puntosCambiados;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
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
        calendar = GregorianCalendar.getInstance();
        fechaRegistro = calendar.getTime();
        calendar.add(Calendar.DATE, vencimiento);
        fechaVencimiento = calendar.getTime();
        this.vencimiento = vencimiento;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getDominioMunicipal() {
        return dominioMunicipal;
    }

    public void setDominioMunicipal(String dominioMunicipal) {
        this.dominioMunicipal = dominioMunicipal;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    public Date getFechaProximaVenta() {
        return fechaProximaVenta;
    }

    public void setFechaProximaVenta(Date fechaProximaVenta) {
        this.fechaProximaVenta = fechaProximaVenta;
    }

    @Override
    public void setActivo(boolean activo) {

    }
}
