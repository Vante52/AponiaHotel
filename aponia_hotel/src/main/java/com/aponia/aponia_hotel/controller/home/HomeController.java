package com.aponia.aponia_hotel.controller.home;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aponia.aponia_hotel.entities.usuarios.Usuario;
import com.aponia.aponia_hotel.service.usuarios.ClientePerfilService;
import com.aponia.aponia_hotel.service.usuarios.UsuarioService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/home")
public class HomeController {


    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClientePerfilService clientePerfilService;
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("AUTH_USER_ID") == null) {
            return "redirect:/login";
        }
        // Puedes cargar aquí datos para cards rápidas, reservas, etc.
        return "app/dashboard";
    }

    // @GetMapping("/perfil")
    // public String perfil(HttpSession session, Model model) {
    //     if (session.getAttribute("AUTH_USER_ID") == null) {
    //         return "redirect:/login";
    //     }
    //     // Puedes cargar aquí datos para cards rápidas, reservas, etc.
    //     return "app/perfil";
    // }    
    @GetMapping("/user_info/{email}")
    public String userInfo(Model model, @PathVariable String email) {
        Optional<Usuario> opt = usuarioService.findByEmail(email);
        if (opt.isEmpty()) {
            return "redirect:/usuarios";
        }
        var u = opt.get();
        var uInfo = clientePerfilService.obtener(u.getId()).get();
        model.addAttribute("usuario", u);
        model.addAttribute("clientePerfil", uInfo);
        return "app/user_info";
    }
    
}
