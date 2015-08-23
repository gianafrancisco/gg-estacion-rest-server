package com.ggingenieria.estacion.controller;

import com.ggingenieria.estacion.DAO.DAO;
import com.ggingenieria.estacion.modelos.*;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class RegistroController {

    @RequestMapping("/registro/agregar")
    public HashMap<String, String> agregar(@RequestBody AgregarRegistro venta) {

        Usuario u = venta.getUsuario();
        Vehiculo v = venta.getVehiculo();
        Producto p = venta.getProducto();
        Empresa e = venta.getEmpresa();
        Surtidor s = venta.getSurtidor();
        SurtidorDato sd = venta.getLectura();

        HashMap<String, String> resp = new HashMap<>();

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

        //r.setPuntosCambiados((int)(sd.getCarga() * (1 + (e.getDescuento()/100))));
        r.setPuntosCambiados((int) Math.ceil(sd.getCarga()));

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        resp.put("fecha", sdf.format(r.getFechaProximaVenta()));

        return resp;
    }

    @RequestMapping("/registro/listaRegistro/{empresaId}/{fecha}")
    public boolean listaRegistro() {
        return true;
    }

    @RequestMapping("/registro/puntos/{empresaId}")
    public int puntos(@PathVariable int empresaId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        String date = format.format(Calendar.getInstance().getTime());
        int puntos = 0;
        for (Registro r : DAO.getInstance().getRegistroVentas(date, empresaId)) {
            puntos += r.getPuntosCambiados();
        }
        for (Registro r : DAO.getInstance().getRegistroCambios(date, empresaId)) {
            puntos -= r.getPuntosCambiados();
        }
        return puntos;
    }

    @RequestMapping("/registro/cambiarPuntos")
    public Registro cambiarPuntos(@RequestBody AgregarRegistro venta) {
        Usuario u = venta.getUsuario();
        Vehiculo v = venta.getVehiculo();
        Producto p = venta.getProducto();
        Empresa e = venta.getEmpresa();
        Surtidor s = venta.getSurtidor();

        Registro r = new Registro();
        r.setAutorizado(true);

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

        p.setPuntosConDescuento(p.getPuntos());

        r.setPuntosCambiados(p.getPuntosConDescuento());

        r.setVehiculoId(v.getVehiculoId());
        r.setDominio(v.getDominio());
        r.setDominioMunicipal(v.getDominioMunicipal());

        DAO.getInstance().add(r);

        return r;
    }

    @RequestMapping("/registro/listado")
    public Map<String, Object> listado(@RequestBody Filtro filtro, @RequestParam(defaultValue = "20") int ipp, @RequestParam(defaultValue = "1") int p) {
        HashMap<String, Object> l = new HashMap<>();
        List<Registro> registros = DAO.getInstance().getRegistros(filtro, ipp, p);
        l.put("items", registros);
        l.put("totalItems", DAO.getInstance().getSize("Registro", filtro));

        return l;
    }

}
