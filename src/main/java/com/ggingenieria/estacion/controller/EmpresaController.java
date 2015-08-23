package com.ggingenieria.estacion.controller;

import com.ggingenieria.estacion.DAO.DAO;
import com.ggingenieria.estacion.modelos.Empresa;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmpresaController {

    @RequestMapping("/listadoEmpresa")
    public List listadoEmpresa() {
        return DAO.getInstance().getEmpresas();
    }

    @RequestMapping("/agregarEmpresa")
    public Empresa agregarEmpresa(@RequestBody Empresa empresa) {
        if (empresa.getEmpresaId() == 0) {
            DAO.getInstance().add(empresa);
        } else {
            DAO.getInstance().update(empresa);
        }
        return empresa;
    }

    @RequestMapping("/borrarEmpresa")
    public void borrarEmpresa(@RequestBody Empresa empresa) {
        Empresa u = DAO.getInstance().getEmpresa(empresa.getEmpresaId());
        DAO.getInstance().delete(u);
    }

    @RequestMapping("/empresa/{id}")
    public Empresa empresa(@PathVariable int id) {
        return DAO.getInstance().getEmpresa(id);
    }
}
