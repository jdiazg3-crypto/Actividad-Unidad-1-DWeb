package com.unicartagena.EquipoFutbolApp.controller;

import com.unicartagena.EquipoFutbolApp.model.Usuario;
import com.unicartagena.EquipoFutbolApp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", service.listarTodos());
        return "usuarios/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", service.buscarPorId(id));
        return "usuarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario) {
        service.guardar(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/usuarios";
    }
    @GetMapping("/reporte-rol")
    public String reportePorRol(
            @RequestParam(required = false) String rol,
            Model model) {
        if (rol != null && !rol.isBlank()) {
            model.addAttribute("usuarios", service.buscarPorRol(rol));
        } else {
            model.addAttribute("usuarios", java.util.Collections.emptyList());
        }
        return "usuarios/reporte-rol";
    }

    @GetMapping("/reporte-nombre")
    public String reportePorNombre(
            @RequestParam(required = false) String nombre,
            Model model) {
        if (nombre != null && !nombre.isBlank()) {
            model.addAttribute("usuarios", service.buscarPorNombre(nombre));
        } else {
            model.addAttribute("usuarios", java.util.Collections.emptyList());
        }
        return "usuarios/reporte-nombre";
    }
}