package com.unicartagena.EquipoFutbolApp.repository;

import com.unicartagena.EquipoFutbolApp.model.EquipoFutbol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipoFutbolRepository extends JpaRepository<EquipoFutbol, Long> {
    List<EquipoFutbol> findByPaisAndCategoria(String pais, String categoria);
    List<EquipoFutbol> findByNumGolesGreaterThan(Integer numGoles);
}