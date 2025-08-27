package com.aponia.aponia_hotel.controller.auth;

import com.aponia.aponia_hotel.entities.usuarios.Usuario;
import com.aponia.aponia_hotel.service.auth.RegistroAppService;
import com.aponia.aponia_hotel.service.usuarios.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;
    private final RegistroAppService registroAppService;

    public AuthController(UsuarioService usuarioService,
                          RegistroAppService registroAppService) {
        this.usuarioService = usuarioService;
        this.registroAppService = registroAppService;
    }

    // ====== LOGIN (igual) ======
    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "logout", required = false) String logout, Model model) {
        if (logout != null) model.addAttribute("ok", "Sesión cerrada correctamente");
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {

        Optional<Usuario> opt = usuarioService.obtenerPorEmail(email);

        if (opt.isPresent() && matchesPlain(password, opt.get().getPasswordHash())) {
            session.setAttribute("AUTH_USER_ID", opt.get().getId());
            session.setAttribute("AUTH_USER_EMAIL", opt.get().getEmail());
            session.setAttribute("AUTH_USER_ROLE", opt.get().getRol());
            return "redirect:/home/dashboard";
        }

        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

    private boolean matchesPlain(String rawPassword, String storedPasswordHash) {
        return storedPasswordHash != null && storedPasswordHash.equals(rawPassword);
    }

    // ====== REGISTRO ======

    @GetMapping("/register")
    public String registerForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("nombreCompleto") String nombreCompleto,
                           @RequestParam(value = "telefono", required = false) String telefono,
                           Model model,
                           HttpSession session) {
        try {
            Usuario u = registroAppService.registrarCliente(email, password, nombreCompleto, telefono);

            // Autologin
            session.setAttribute("AUTH_USER_ID", u.getId());
            session.setAttribute("AUTH_USER_EMAIL", u.getEmail());
            session.setAttribute("AUTH_USER_ROLE", u.getRol());

            return "redirect:/home/dashboard";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            return "auth/register";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "No se pudo registrar: " + e.getMessage());
            return "auth/register";
        }
    }
}
