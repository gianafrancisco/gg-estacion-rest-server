package com.ggingenieria.estacion.controller;

import com.ggingenieria.estacion.dbf.DbfSurtidor;
import com.ggingenieria.estacion.modelos.*;
import com.ggingenieria.estacion.DAO.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurtidorController {

    @Autowired
    private DbfSurtidor dbfSurtidor;



    @RequestMapping("/listadoSurtidor")
    public ArrayList<Surtidor> getListado() {
        return DAO.getInstance().getSurtidores();
    }

    @RequestMapping("/agregarSurtidor")
    public Surtidor agregar(@RequestBody Surtidor surtidor) {
        if (surtidor.getSurtidorId() == 0) {
            DAO.getInstance().add(surtidor);

        } else {
            DAO.getInstance().update(surtidor);
        }
        return surtidor;
    }

    @RequestMapping("/borrarSurtidor")
    public void borrar(@RequestBody Surtidor surtidor) {
        Surtidor u = DAO.getInstance().getSurtidor(surtidor.getSurtidorId());
        DAO.getInstance().delete(u);
    }

    @RequestMapping("/surtidor/leer/{id}")
    public SurtidorDato leerDatosSurtidor(@PathVariable int id) throws IOException {

        SurtidorDato modelo = null;
        Surtidor surtidor = DAO.getInstance().getSurtidor(id);
        Map<String,Double> surtidores = dbfSurtidor.getLecturaSurtidores();
        String key = Integer.toString(surtidor.getDireccionNodo());
        if(surtidores.get(key) != null){
            modelo = new SurtidorDato(surtidores.get(key).intValue());
        }else{
            modelo = new SurtidorDato((int)(Math.random()*300 + 100));
        }
        return modelo;
    }

    public void setDbfSurtidor(DbfSurtidor dbfSurtidor) {
        this.dbfSurtidor = dbfSurtidor;
    }
}
