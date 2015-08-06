package com.ggingenieria.estacion.security;

/**
 * Created by francisco on 31/05/15.
 */

import com.ggingenieria.estacion.DAO.DAO;
import com.ggingenieria.estacion.modelos.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    public static final String ADMINISTRADOR = "Administrador";

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        Usuario user = DAO.getInstance().getUsuarioPorNombre(username);
        List<GrantedAuthority> authorities = null;
        User u = null;
        if (ADMINISTRADOR.equals(user.getPermisos())) {
            authorities =
                    buildUserAuthority(user.getPermisos());
            u = buildUserForAuthentication(user, authorities);
        }
        return u;

    }

    private User buildUserForAuthentication(Usuario user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getNombreUsuario(), user.getClave(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(String userRole) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        setAuths.add(new SimpleGrantedAuthority(userRole));

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

}