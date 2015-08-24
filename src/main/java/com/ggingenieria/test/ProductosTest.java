/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggingenieria.test;

import com.ggingenieria.estacion.DAO.DAO;
import com.ggingenieria.estacion.modelos.Empresa;
import com.ggingenieria.estacion.modelos.Producto;

import java.util.List;


/**
 * @author mechi
 */
public class ProductosTest {
    public static void main(String[] args) {

        Empresa e = DAO.getInstance().getEmpresa(5);

        List<Producto> list = DAO.getInstance().getProductos();

        for (Producto p : list) {
            System.out.println(p.getDescripcion() + ": " + p.getPuntos());
        }
    }
}
