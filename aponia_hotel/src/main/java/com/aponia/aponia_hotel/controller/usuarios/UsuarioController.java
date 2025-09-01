package com.aponia.aponia_hotel.controller.usuarios;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aponia.aponia_hotel.entities.usuarios.Usuario;
import com.aponia.aponia_hotel.service.usuarios.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model, @ModelAttribute("ok") String ok, @ModelAttribute("error") String error) {
        model.addAttribute("usuarios", service.listar());
        return "usuarios/list";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        Usuario usuario = new Usuario();
        usuario.setRol(Usuario.UserRole.CLIENTE); 
        model.addAttribute("usuario", usuario);
        return "usuarios/form";
    }

    @PostMapping
public String crear(@ModelAttribute Usuario usuario,
                    @RequestParam(name = "password", required = false) String password,
                    RedirectAttributes ra) {
    try {
        System.out.println("[CREAR] IN >> id=" + usuario.getId() + " email=" + usuario.getEmail() + " rol=" + usuario.getRol() + " pass=" + password);

        if (usuario.getId() == null || usuario.getId().isBlank()) {
            usuario.setId(UUID.randomUUID().toString());
        }

        if (password == null || password.isBlank()) {
            ra.addFlashAttribute("error", "La contraseña es obligatoria al crear.");
            return "redirect:/usuarios/nuevo";
        }

        if (service.findByEmail(usuario.getEmail()).isPresent()) {
            ra.addFlashAttribute("error", "Ya existe un usuario con ese email.");
            return "redirect:/usuarios/nuevo";
        }

        usuario.setPasswordHash(password); // plain (tu login simple)
        Usuario saved = service.crear(usuario);

        System.out.println("[CREAR] OUT >> id=" + saved.getId());
        ra.addFlashAttribute("ok", "Usuario creado");
        return "redirect:/usuarios";

    } catch (Exception e) {
        e.printStackTrace();
        ra.addFlashAttribute("error", "No se pudo crear: " + e.getMessage());
        return "redirect:/usuarios/nuevo";
    }
}
    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable String id, Model model, RedirectAttributes ra) {
        return service.obtener(id).map(u -> {
            model.addAttribute("usuario", u);
            return "usuarios/form";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "No existe el usuario");
            return "redirect:/usuarios";
        });
    }

    @PostMapping("/{id}")
public String actualizar(@PathVariable String id,
                         @RequestParam String email,
                         @RequestParam Usuario.UserRole rol,
                         @RequestParam(name = "password", required = false) String password,
                         RedirectAttributes ra) {
    try {
        System.out.println("[ACTUALIZAR] IN >> id=" + id + " email=" + email + " rol=" + rol + " pass=" + password);

        var uOpt = service.obtener(id);
        if (uOpt.isEmpty()) {
            ra.addFlashAttribute("error", "No existe el usuario");
            return "redirect:/usuarios";
        }
        var u = uOpt.get();

        if (!u.getEmail().equals(email) && service.findByEmail(email).isPresent()) {
            ra.addFlashAttribute("error", "Ya existe un usuario con ese email.");
            return "redirect:/usuarios/" + id + "/editar";
        }

        u.setEmail(email);
        u.setRol(rol);

        if (password != null && !password.isBlank()) {
            u.setPasswordHash(password);
        }

        Usuario saved = service.actualizar(u);
        System.out.println("[ACTUALIZAR] OUT >> id=" + saved.getId());

        ra.addFlashAttribute("ok", "Usuario actualizado");
        return "redirect:/usuarios";

    } catch (Exception e) {
        e.printStackTrace();
        ra.addFlashAttribute("error", "No se pudo actualizar: " + e.getMessage());
        return "redirect:/usuarios/" + id + "/editar";
    }
}

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable String id, RedirectAttributes ra) {
        try {
            service.eliminar(id);
            ra.addFlashAttribute("ok", "Usuario eliminado");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "No se pudo eliminar: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }
}
