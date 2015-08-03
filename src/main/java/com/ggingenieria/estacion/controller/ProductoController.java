package com.ggingenieria.estacion.controller;

import com.ggingenieria.estacion.modelos.*;
import com.ggingenieria.estacion.DAO.DAO;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

    @RequestMapping("/listadoProducto")
    public ArrayList<Producto> getListado() {
        return DAO.getInstance().getProductos();
    }

    @RequestMapping("/producto/listado/{empresaId}/{puntos}")
    public ArrayList<Producto> getListadoPorEmpresa(@PathVariable int empresaId,@PathVariable int puntos) {
        Empresa e = DAO.getInstance().getEmpresa(empresaId);
        ArrayList<Producto> newList = new ArrayList<Producto>();
        for(Producto p: DAO.getInstance().getProductoPorEmpresa(e)){
            if(p.getPuntos() <= puntos && p.getPuntos() > 0){
                newList.add(p);
            }
        }
        return newList;
    }

    @RequestMapping("/agregarProducto")
    public Producto agregar(@RequestBody Producto producto) {
        if (producto.getProductoId() == 0) {

            DAO.getInstance().add(producto);
        } else {
            DAO.getInstance().update(producto);
        }
        return producto;
    }

    @RequestMapping("/borrarProducto")
    public void borrar(@RequestBody Producto producto) {
        Producto u = DAO.getInstance().getProducto(producto.getProductoId());
        DAO.getInstance().delete(u);
    }
    
    @RequestMapping("/producto/{id}")
    public Producto listado(@PathVariable int id) {
        Producto p = DAO.getInstance().getProducto(id);
        return p;
    }
}
