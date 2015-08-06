package com.ggingenieria.estacion.controller;

import com.ggingenieria.estacion.DAO.DAO;
import com.ggingenieria.estacion.modelos.Vehiculo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class VehiculoController {

    @RequestMapping("/listadoVehiculo")
    public ArrayList<Vehiculo> getListado() {
        return DAO.getInstance().getVehiculos();
    }

    @RequestMapping("/agregarVehiculo")
    public Vehiculo agregar(@RequestBody Vehiculo vehiculo) {
        if (vehiculo.getVehiculoId() == 0) {
            DAO.getInstance().add(vehiculo);
        } else {
            DAO.getInstance().update(vehiculo);
        }
        return vehiculo;
    }

    @RequestMapping("/borrarVehiculo")
    public void borrar(@RequestBody Vehiculo vehiculo) {
        Vehiculo u = DAO.getInstance().getVehiculo(vehiculo.getVehiculoId());
        DAO.getInstance().delete(u);
    }

    @RequestMapping("/vehiculo/leer")
    public Vehiculo leerPuerto() {
        Vehiculo u = DAO.getInstance().getVehiculo(14);
        return u;
    }
}
