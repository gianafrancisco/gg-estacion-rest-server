package com.ggingenieria.estacion.controller;

import com.ggingenieria.estacion.DAO.DAO;
import com.ggingenieria.estacion.DAO.DBSurtidor;
import com.ggingenieria.estacion.dbf.DbfSurtidor;
import com.ggingenieria.estacion.modelos.Surtidor;
import com.ggingenieria.estacion.modelos.SurtidorDato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SurtidorController {

    @Autowired
    private DBSurtidor dbSurtidor;


    @RequestMapping("/listadoSurtidor")
    public List listadoSurtidor() {
        return DAO.getInstance().getSurtidores();
    }

    @RequestMapping("/agregarSurtidor")
    public Surtidor agregarSurtidor(@RequestBody Surtidor surtidor) {
        if (surtidor.getSurtidorId() == 0) {
            DAO.getInstance().add(surtidor);

        } else {
            DAO.getInstance().update(surtidor);
        }
        return surtidor;
    }

    @RequestMapping("/borrarSurtidor")
    public void borrarSurtidor(@RequestBody Surtidor surtidor) {
        Surtidor u = DAO.getInstance().getSurtidor(surtidor.getSurtidorId());
        DAO.getInstance().delete(u);
    }

    @RequestMapping("/surtidor/leer/{id}")
    public SurtidorDato leer(@PathVariable int id) throws IOException, SQLException, ClassNotFoundException {

        SurtidorDato modelo;
        Surtidor surtidor = DAO.getInstance().getSurtidor(id);
        Map<String, Double> surtidores = dbSurtidor.getLecturaSurtidores(surtidor.getDireccionNodo());
        String key = Integer.toString(surtidor.getDireccionNodo());
        if (surtidores.get(key) != null) {
            modelo = new SurtidorDato(surtidores.get(key).intValue());
        } else {
            //modelo = new SurtidorDato((int) (Math.random() * 300 + 100));
            modelo = new SurtidorDato(0);
        }
        return modelo;
    }

    public void setDBSurtidor(DBSurtidor dbSurtidor) {
        this.dbSurtidor = dbSurtidor;
    }
}
