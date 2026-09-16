package com.unicartagena.EquipoFutbolApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoRecuperacion(String destino, String nombreUsuario, String clave) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destino);
        mensaje.setSubject("Recuperación de contraseña - Equipo Fútbol App");
        mensaje.setText("Hola " + nombreUsuario + ",\n\nTu clave de acceso es: " + clave + "\n\nPor favor, guárdala en un lugar seguro.");

        mailSender.send(mensaje);
    }
}