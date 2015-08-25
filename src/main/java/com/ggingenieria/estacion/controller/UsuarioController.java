package com.ggingenieria.estacion.controller;

import com.ggingenieria.estacion.DAO.DAO;
import com.ggingenieria.estacion.modelos.Usuario;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsuarioController {

    public static final String PLAYERO = "Playero";

    @RequestMapping("/listadoUsuario")
    public List listadoUsuario() {
        return DAO.getInstance().getUsuarios();
    }

    @RequestMapping("/listadoPlayero")
    public List listadoPlayero() {
        List<Usuario> list = DAO.getInstance().getUsuarios();
        return list.stream().filter(u -> PLAYERO.equals(u.getPermisos()) == true).collect(Collectors.toList());

    }

    @RequestMapping("/agregarUsuario")
    public Usuario agregarUsuario(@RequestBody Usuario usuario) {
        if (usuario.getUsuarioId() == 0) {
            usuario.setPermisos("Playero");
            DAO.getInstance().add(usuario);
        } else {
            DAO.getInstance().update(usuario);
        }
        return usuario;
    }

    @RequestMapping("/borrarUsuario")
    public void borrarUsuario(@RequestBody Usuario usuario) {
        if(usuario.getPermisos().equals("Playero")) {
            Usuario u = DAO.getInstance().getUsuario(usuario.getUsuarioId());
            DAO.getInstance().delete(u);
        }
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
