package com.unicartagena.EquipoFutbolApp.service;

import com.unicartagena.EquipoFutbolApp.model.Usuario;
import com.unicartagena.EquipoFutbolApp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombre(nombre)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + nombre));

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getNombre())
                .password(usuario.getClave())
                .authorities(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toUpperCase()))
                .build();
    }
}