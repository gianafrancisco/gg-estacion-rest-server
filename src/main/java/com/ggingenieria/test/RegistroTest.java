/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ggingenieria.test;

import com.ggingenieria.estacion.DAO.DAO;
import com.ggingenieria.estacion.modelos.Registro;


/**
 *
 * @author mechi
 */
public class RegistroTest {
    public static void main(String []args){

        int puntos = 0;

        for(Registro r: DAO.getInstance().getRegistroVentas("2015-05-04 00:00:00", 5)){
            puntos +=r.getPuntosCambiados();
            System.out.println(r);
        }
        System.out.println(puntos);
    }
}
