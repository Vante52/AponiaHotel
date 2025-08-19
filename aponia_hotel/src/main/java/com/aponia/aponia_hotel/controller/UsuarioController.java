package com.aponia.aponia_hotel.controller;

import com.aponia.aponia_hotel.entities.Usuario;
import com.aponia.aponia_hotel.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", service.listar());
        return "usuarios/list";  // Thymeleaf: templates/usuarios/list.html
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Usuario usuario) {
        service.crear(usuario);
        return "redirect:/usuarios";
    }
}
