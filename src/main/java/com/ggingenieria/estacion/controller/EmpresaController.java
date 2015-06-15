package com.ggingenieria.estacion.controller;

import com.ggingenieria.estacion.modelos.*;
import com.ggingenieria.estacion.DAO.DAO;
import java.util.ArrayList;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpresaController {

    @RequestMapping("/listadoEmpresa")
    public ArrayList<Empresa> getListado() {
        return DAO.getInstance().getEmpresas();
    }

    @RequestMapping("/agregarEmpresa")
    public Empresa agregar(@RequestBody Empresa empresa) {
        if (empresa.getEmpresaId() == 0) {
            DAO.getInstance().add(empresa);
        } else {
            DAO.getInstance().update(empresa);
        }
        return empresa;
    }

    @RequestMapping("/borrarEmpresa")
    public void borrar(@RequestBody Empresa empresa) {
        Empresa u = DAO.getInstance().getEmpresa(empresa.getEmpresaId());
        DAO.getInstance().delete(u);
    }

    @RequestMapping("/empresa/{id}")
    public Empresa get(@PathVariable int id) {
        Empresa e = DAO.getInstance().getEmpresa(id);
        return e;
    }
}
