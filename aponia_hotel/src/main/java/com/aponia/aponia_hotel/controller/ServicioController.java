package com.aponia.aponia_hotel.controller;

import com.aponia.aponia_hotel.entities.servicios.Servicio;
import com.aponia.aponia_hotel.service.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService service;

    public ServicioController(ServicioService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("servicios", service.listar());
        return "servicios/list"; // templates/servicios/list.html
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        var s = new Servicio();
        s.setActivo(true);
        s.setPublico(true);
        s.setPrecioBaseCurrency("COP"); // valor por defecto
        model.addAttribute("servicio", s);
        return "servicios/form"; // templates/servicios/form.html
    }

    @PostMapping
    public String crear(@ModelAttribute Servicio servicio) {
        // si usas CHAR(36) en DB, genera un UUID string:
        if (servicio.getId() == null || servicio.getId().isBlank()) {
            servicio.setId(UUID.randomUUID().toString());
        }
        service.crear(servicio);
        return "redirect:/servicios";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable String id, Model model) {
        model.addAttribute("servicio", service.obtener(id));
        return "servicios/form";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable String id, @ModelAttribute Servicio servicio) {
        servicio.setId(id);
        service.actualizar(servicio);
        return "redirect:/servicios";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable String id) {
        service.eliminar(id);
        return "redirect:/servicios";
    }
}
