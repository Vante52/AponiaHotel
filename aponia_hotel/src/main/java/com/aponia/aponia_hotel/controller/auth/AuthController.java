package com.aponia.aponia_hotel.controller.auth;

import com.aponia.aponia_hotel.entities.usuarios.Usuario;
import com.aponia.aponia_hotel.service.usuarios.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Mostrar formulario
    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "logout", required = false) String logout, Model model) {
        if (logout != null) model.addAttribute("ok", "Sesión cerrada correctamente");
        return "auth/login"; // <-- ruta de tu template (ajústalo al tuyo)
    }

    // Procesar login
    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {

        Optional<Usuario> opt = usuarioService.obtenerPorEmail(email);

        if (opt.isPresent() && matchesPlain(password, opt.get().getPasswordHash())) {
            // Guarda solo lo imprescindible en sesión
            session.setAttribute("AUTH_USER_ID", opt.get().getId());
            session.setAttribute("AUTH_USER_EMAIL", opt.get().getEmail());
            session.setAttribute("AUTH_USER_ROLE", opt.get().getRol()); // enum
            return "redirect:/"; // o "/servicios/cards"
        }

        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

    // ====== Modo simple: comparación plain (inseguro para producción) ======
    private boolean matchesPlain(String rawPassword, String storedPasswordHash) {
        // Para tu caso: "usuario = usuario y contraseña = contraseña"
        // Guarda el password en passwordHash tal cual (PLAIN) y comparamos iguales:
        return storedPasswordHash != null && storedPasswordHash.equals(rawPassword);
    }
}
