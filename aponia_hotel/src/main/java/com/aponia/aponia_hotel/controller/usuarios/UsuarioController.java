package com.aponia.aponia_hotel.controller.usuarios;

import com.aponia.aponia_hotel.entities.usuarios.Usuario;
import com.aponia.aponia_hotel.service.usuarios.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
        return "usuarios/list";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        Usuario usuario = new Usuario();
        usuario.setRol("cliente"); // Valor por defecto
        model.addAttribute("usuario", usuario);
        return "usuarios/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Usuario usuario) {
        if (usuario.getId() == null || usuario.getId().isBlank()) {
            usuario.setId(UUID.randomUUID().toString());
        }
        service.crear(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable String id, Model model) {
        model.addAttribute("usuario", service.obtener(id));
        return "usuarios/form";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable String id, @ModelAttribute Usuario usuario) {
        usuario.setId(id);
        service.actualizar(usuario);
        return "redirect:/usuarios";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable String id) {
        service.eliminar(id);
        return "redirect:/usuarios";
    }
}