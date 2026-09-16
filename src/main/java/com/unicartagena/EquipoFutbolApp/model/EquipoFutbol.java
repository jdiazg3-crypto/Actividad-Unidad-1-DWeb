package com.unicartagena.EquipoFutbolApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipo_futbol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipoFutbol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String eslogan;
    private String tecnico;
    private String pais;
    private String ciudad;
    private String categoria;
    private Integer numGoles;
    private Integer numPartidosJugados;
    private Integer numPartidosGanados;
    private Integer numCampeonatos;
    private Integer numExpulsiones;
    private Integer numEmpates;
}