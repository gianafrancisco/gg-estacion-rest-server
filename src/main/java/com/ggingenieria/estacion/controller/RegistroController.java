package com.ggingenieria.estacion.controller;

import com.ggingenieria.estacion.modelos.*;
import com.ggingenieria.estacion.DAO.DAO;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistroController {

    @RequestMapping("/registro/agregar")
    public HashMap<String,String> agregar(@RequestBody AgregarRegistro venta) {
        
        Usuario u=venta.getUsuario();
        Vehiculo v=venta.getVehiculo();
        Producto p=venta.getProducto();
        Empresa e = venta.getEmpresa();
        Surtidor s = venta.getSurtidor();
        SurtidorDato sd = venta.getLectura();

        HashMap <String,String> resp = new HashMap<String, String>();
        
        Registro r = new Registro();
        
        r.setAccion("VENTA");

        r.setApellido(u.getApellido());
        r.setNombre(u.getNombre());
        r.setNombreUsuario(u.getNombreUsuario());
        r.setPermisos(u.getPermisos());
        r.setUsuarioId(u.getUsuarioId());

        r.setDescripcionProducto(p.getDescripcion());
        r.setProductoId(p.getProductoId());

        r.setSurtidorId(s.getSurtidorId());
        r.setDescripcionSurtidor(s.getDescripcion());
        r.setDireccionNodo(s.getDireccionNodo());

        r.setEmpresaId(e.getEmpresaId());
        r.setNombreEmpresa(e.getNombre());
        r.setDescuento(e.getDescuento());
        r.setVencimiento(e.getVencimiento());

        r.setPuntosCambiados((int)(sd.getCarga() * (1 + (e.getDescuento()/100))));

        r.setVehiculoId(v.getVehiculoId());
        r.setDominio(v.getDominio());
        r.setDominioMunicipal(v.getDominioMunicipal());
        r.setAutorizado(v.isAutorizado());

        Calendar calendar = GregorianCalendar.getInstance();
        Date fechaActual = r.getFechaRegistro();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.HOUR, e.getTiempoAutorizacion());
        Date fechaProximaVenta = calendar.getTime();

        r.setFechaProximaVenta(fechaProximaVenta);

        v.setAutorizado(false);

        DAO.getInstance().add(r);
        DAO.getInstance().update(v);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        resp.put("fecha",sdf.format(r.getFechaProximaVenta()));

        return resp;
    }

    @RequestMapping("/registro/listaRegistro/{empresaId}/{fecha}")
    public boolean registros() {
        return true;
    }

    @RequestMapping("/registro/puntos/{empresaId}")
    public int puntosAcumulados(@PathVariable int empresaId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        String date = format.format(Calendar.getInstance().getTime());
        int puntos = 0;
        for(Registro r: DAO.getInstance().getRegistroVentas(date, empresaId)){
            puntos+=r.getPuntosCambiados();
        }
        for(Registro r: DAO.getInstance().getRegistroCambios(date, empresaId)){
            puntos-=r.getPuntosCambiados();
        }
        return puntos;
    }

    @RequestMapping("/registro/cambiarPuntos")
    public boolean cambiarPuntos(@RequestBody AgregarRegistro venta) {
        Usuario u=venta.getUsuario();
        Vehiculo v=venta.getVehiculo();
        Producto p=venta.getProducto();
        Empresa e = venta.getEmpresa();
        Surtidor s = venta.getSurtidor();

        Registro r = new Registro();

        r.setAccion("CAMBIO_DE_PUNTOS");

        r.setApellido(u.getApellido());
        r.setNombre(u.getNombre());
        r.setNombreUsuario(u.getNombreUsuario());
        r.setPermisos(u.getPermisos());
        r.setUsuarioId(u.getUsuarioId());

        r.setDescripcionProducto(p.getDescripcion());
        r.setProductoId(p.getProductoId());

        r.setSurtidorId(s.getSurtidorId());
        r.setDescripcionSurtidor(s.getDescripcion());
        r.setDireccionNodo(s.getDireccionNodo());

        r.setEmpresaId(e.getEmpresaId());
        r.setNombreEmpresa(e.getNombre());
        r.setDescuento(e.getDescuento());
        r.setVencimiento(e.getVencimiento());

        //p.setPuntosConDescuento((int) (p.getPuntos()*(1-e.getDescuento()/100)));

        r.setPuntosCambiados(p.getPuntosConDescuento());

        r.setVehiculoId(v.getVehiculoId());
        r.setDominio(v.getDominio());
        r.setDominioMunicipal(v.getDominioMunicipal());

        DAO.getInstance().add(r);

        return true;
    }

}
