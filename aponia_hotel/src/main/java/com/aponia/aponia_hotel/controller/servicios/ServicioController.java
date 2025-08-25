package com.aponia.aponia_hotel.controller.servicios;

import com.aponia.aponia_hotel.entities.servicios.Servicio;
import com.aponia.aponia_hotel.service.servicios.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
        return "servicios/list";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        Servicio servicio = new Servicio();

        servicio.setPrecioPorPersona(BigDecimal.ZERO);
        servicio.setDuracionMinutos(0);
        model.addAttribute("servicio", servicio);
        return "servicios/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Servicio servicio) {
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

    @GetMapping("/cards")
    public String listarCards(Model model) {
        model.addAttribute("servicios", service.listar());
        return "servicios/cards";
    }
}