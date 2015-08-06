/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggingenieria.test;

import com.ggingenieria.estacion.DAO.DAO;
import com.ggingenieria.estacion.modelos.Registro;

/**
 * @author mechi
 */
public class Test {
    public static void main(String[] args) {
        
        /*
        Usuario u = DAO.getInstance().getUsuario(1);
        System.out.println(u);
        
        for(Usuario u1: DAO.getInstance().getUsuarios()){
            System.out.println(u1);
        }
        */

        Registro r = new Registro();

        r.setAccion("VENTA");
        r.setApellido("Giana");
        r.setNombre("Francisco");
        r.setNombreUsuario("fgiana");
        r.setPermisos("Playero");
        r.setUsuarioId(1);

        r.setDescripcionProducto("Gas");
        r.setProductoId(1);

        r.setSurtidorId(1);
        r.setDescripcionSurtidor("Surtidor 1");
        r.setDireccionNodo(1);

        r.setEmpresaId(1);
        r.setNombreEmpresa("Minicipalidad La Falda");
        r.setDescuento(20.5);
        r.setVencimiento(90);

        r.setPuntosCambiados(200);

        r.setVehiculoId(1);
        r.setDominio("FFA999");
        r.setDominioMunicipal("FFA9991");

//        Calendar c1 = GregorianCalendar.getInstance();
//        r.setFechaRegistro(c1.getTime());
//        c1.add(Calendar.DATE, 30);
//        r.setFechaVencimiento(c1.getTime());

        DAO.getInstance().add(r);

    }
}
