package com.unicartagena.EquipoFutbolApp.service;

import com.unicartagena.EquipoFutbolApp.model.Usuario;
import com.unicartagena.EquipoFutbolApp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Usuario guardar(Usuario usuario) {
        if (usuario.getId() != null) {
            Usuario existente = repository.findById(usuario.getId()).orElse(null);
            if (usuario.getClave() == null || usuario.getClave().isBlank()) {
                usuario.setClave(existente.getClave()); // no cambia la clave si se deja en blanco
            } else {
                usuario.setClave(passwordEncoder.encode(usuario.getClave()));
            }
        } else {
            usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        }
        return repository.save(usuario);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}