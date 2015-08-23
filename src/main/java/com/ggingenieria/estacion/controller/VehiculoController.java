package com.ggingenieria.estacion.controller;

import com.ggingenieria.estacion.DAO.DAO;
import com.ggingenieria.estacion.modelos.Vehiculo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VehiculoController {

    @RequestMapping("/listadoVehiculo")
    public List listadoVehiculo() {
        return DAO.getInstance().getVehiculos();
    }

    @RequestMapping("/agregarVehiculo")
    public Vehiculo agregarVehiculo(@RequestBody Vehiculo vehiculo) {
        if (vehiculo.getVehiculoId() == 0) {
            DAO.getInstance().add(vehiculo);
        } else {
            DAO.getInstance().update(vehiculo);
        }
        return vehiculo;
    }

    @RequestMapping("/borrarVehiculo")
    public void borrarVehiculo(@RequestBody Vehiculo vehiculo) {
        Vehiculo u = DAO.getInstance().getVehiculo(vehiculo.getVehiculoId());
        DAO.getInstance().delete(u);
    }

    @RequestMapping("/vehiculo/leer")
    public Vehiculo leer() {
        return DAO.getInstance().getVehiculo(14);
    }
}
