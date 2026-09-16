# Proyecto Spring Boot - Gestión de Equipos de Fútbol

Este es un proyecto web MVC desarrollado con Spring Boot, Spring Data JPA, Thymeleaf y MySQL para la gestión de **Equipos de Fútbol** y **Usuarios**.

Cumple con todos los requisitos del Ejercicio 8 de la Unidad 1 de Desarrollo Web.

## Requisitos previos
- Java 17 o superior.
- Maven (incluido a través de `mvnw`).
- MySQL Server 8 o superior.
- Cuenta de correo configurada para SMTP (para probar recuperación de contraseña, es recomendable usar Mailtrap o una cuenta de Gmail con contraseñas de aplicación).

## Configuración de Base de Datos
1. Inicia tu servidor MySQL.
2. Ejecuta el script `ScriptSQL.sql` proporcionado en este repositorio para crear la base de datos `equipo_defutbol_db` y las tablas necesarias.
3. Configura tus credenciales en el archivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/equipo_defutbol_db?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=TuContraseña