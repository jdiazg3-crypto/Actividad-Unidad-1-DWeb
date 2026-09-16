package com.unicartagena.EquipoFutbolApp.controller;

import com.unicartagena.EquipoFutbolApp.model.Usuario;
import com.unicartagena.EquipoFutbolApp.repository.UsuarioRepository;
import com.unicartagena.EquipoFutbolApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/recuperar-clave")
    public String recuperarClaveForm() {
        return "recuperar-clave";
    }

    @PostMapping("/enviar-clave")
    public String enviarClave(@RequestParam("nombre") String nombre,
                              @RequestParam("correo") String correo,
                              Model model) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombre(nombre);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            try {
                emailService.enviarCorreoRecuperacion(correo, usuario.getNombre(), usuario.getClave());
                model.addAttribute("exito", "La clave ha sido enviada a su correo.");
            } catch (Exception e) {
                model.addAttribute("error", "Hubo un error al enviar el correo. Por favor intente más tarde.");
            }
        } else {
            model.addAttribute("error", "No existe un usuario con ese nombre.");
        }

        return "recuperar-clave";
    }
}