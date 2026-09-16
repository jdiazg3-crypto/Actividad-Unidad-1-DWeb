package com.unicartagena.EquipoFutbolApp.controller;

import com.unicartagena.EquipoFutbolApp.model.Usuario;
import com.unicartagena.EquipoFutbolApp.repository.UsuarioRepository;
import com.unicartagena.EquipoFutbolApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    // Inyectamos el encriptador que tienes configurado en SecurityConfig
    @Autowired
    private PasswordEncoder passwordEncoder;

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
                // 1. Generar una nueva clave aleatoria de 8 caracteres
                String nuevaClaveTemporal = UUID.randomUUID().toString().substring(0, 8);

                // 2. Encriptar esa nueva clave y guardarla en la base de datos
                usuario.setClave(passwordEncoder.encode(nuevaClaveTemporal));
                usuarioRepository.save(usuario); // Actualizamos el usuario

                // 3. Enviar la clave nueva (en texto plano) por correo
                emailService.enviarCorreoRecuperacion(correo, usuario.getNombre(), nuevaClaveTemporal);

                model.addAttribute("exito", "Se ha generado una nueva contraseña temporal y ha sido enviada a su correo.");
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("error", "Hubo un error al enviar el correo. Por favor intente más tarde.");
            }
        } else {
            model.addAttribute("error", "No existe un usuario con ese nombre.");
        }

        return "recuperar-clave";
    }
}