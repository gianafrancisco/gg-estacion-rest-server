package com.ggingenieria.estacion.controller;

import com.ggingenieria.estacion.modelos.*;
import com.ggingenieria.estacion.DAO.DAO;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurtidorController {

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
    public SurtidorDato leerDatosSurtidor(@PathVariable int id) {
        return new SurtidorDato((int)(Math.random()*300 + 100));
    }
}
