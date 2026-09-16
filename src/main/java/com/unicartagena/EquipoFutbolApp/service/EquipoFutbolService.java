package com.unicartagena.EquipoFutbolApp.service;

import com.unicartagena.EquipoFutbolApp.model.EquipoFutbol;
import com.unicartagena.EquipoFutbolApp.repository.EquipoFutbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoFutbolService {

    @Autowired
    private EquipoFutbolRepository repository;

    public List<EquipoFutbol> listarTodos() {
        return repository.findAll();
    }

    public EquipoFutbol buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public EquipoFutbol guardar(EquipoFutbol equipo) {
        return repository.save(equipo);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
    public List<EquipoFutbol> buscarPorPaisYCategoria(String pais, String categoria) {
        return repository.findByPaisAndCategoria(pais, categoria);
    }

    public List<EquipoFutbol> buscarPorGolesMayorA(Integer numGoles) {
        return repository.findByNumGolesGreaterThan(numGoles);
    }
}