package com.ggingenieria.webservices;

import com.ggingenieria.estacion.DAO.DAO;
import com.ggingenieria.estacion.modelos.Producto;
import com.ggingenieria.estacion.modelos.Surtidor;
import com.ggingenieria.estacion.modelos.Usuario;

/**
 * Created by francisco on 26/08/15.
 */
public class CreateDefault {

    public static void main(String... args){

        Usuario u = new Usuario();

        u.setActivo(true);
        u.setPermisos("Administrador");
        u.setNombreUsuario("admin");
        u.setClave("admin");
        DAO.getInstance().add(u);

        Producto p = new Producto();
        p.setPuntos(1);
        p.setActivo(true);
        p.setDescripcion("G.N.C");
        DAO.getInstance().add(p);

        Surtidor s = new Surtidor();
        s.setDescripcion("Surtidor 1");
        s.setActivo(true);
        s.setDireccionNodo(0);
        DAO.getInstance().add(s);

    }


}
