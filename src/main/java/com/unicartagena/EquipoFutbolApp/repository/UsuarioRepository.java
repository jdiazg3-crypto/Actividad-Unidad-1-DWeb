package com.unicartagena.EquipoFutbolApp.repository;

import com.unicartagena.EquipoFutbolApp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}