package com.ggingenieria.estacion.controller;

import com.ggingenieria.estacion.modelos.Usuario;
import com.ggingenieria.estacion.DAO.DAO;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @RequestMapping("/listadoUsuario")
    public List<Usuario> getListado() {
        return DAO.getInstance().getUsuarios();
    }

    @RequestMapping("/agregarUsuario")
    public Usuario agregarUsuario(@RequestBody Usuario usuario) {
        if (usuario.getUsuarioId() == 0) {
            DAO.getInstance().add(usuario);
        } else {
            DAO.getInstance().update(usuario);
        }
        return usuario;
    }

    @RequestMapping("/borrarUsuario")
    public void BorrarUsuario(@RequestBody Usuario usuario) {
        Usuario u = DAO.getInstance().getUsuario(usuario.getUsuarioId());
        DAO.getInstance().delete(u);
    }
}
