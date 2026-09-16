package com.unicartagena.EquipoFutbolApp.controller;

import com.unicartagena.EquipoFutbolApp.model.EquipoFutbol;
import com.unicartagena.EquipoFutbolApp.service.EquipoFutbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/equipos")
public class EquipoFutbolController {

    @Autowired
    private EquipoFutbolService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("equipos", service.listarTodos());
        return "equipos/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("equipo", new EquipoFutbol());
        return "equipos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("equipo", service.buscarPorId(id));
        return "equipos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EquipoFutbol equipo) {
        service.guardar(equipo);
        return "redirect:/equipos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/equipos";
    }
}