package com.aponia.aponia_hotel.controller.habitaciones;

import com.aponia.aponia_hotel.entities.habitaciones.HabitacionTipo;
import com.aponia.aponia_hotel.service.HabitacionTipoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@Controller
@RequestMapping("/habitaciones-tipos")
public class HabitacionTipoController {

    private final HabitacionTipoService service;

    public HabitacionTipoController(HabitacionTipoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tiposHabitacion", service.listar());
        return "habitaciones-tipos/list";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        HabitacionTipo tipo = new HabitacionTipo();
        tipo.setActiva(true);
        tipo.setPrecioPorNoche(BigDecimal.ZERO);
        tipo.setAforoMaximo(1);
        model.addAttribute("tipoHabitacion", tipo);
        return "habitaciones-tipos/form";
    }

    @PostMapping
    public String crear(@ModelAttribute HabitacionTipo tipoHabitacion) {
        if (tipoHabitacion.getId() == null || tipoHabitacion.getId().isBlank()) {
            tipoHabitacion.setId(UUID.randomUUID().toString());
        }
        service.crear(tipoHabitacion);
        return "redirect:/habitaciones-tipos";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable String id, Model model) {
        model.addAttribute("tipoHabitacion", service.obtener(id));
        return "habitaciones-tipos/form";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable String id, @ModelAttribute HabitacionTipo tipoHabitacion) {
        tipoHabitacion.setId(id);
        service.actualizar(tipoHabitacion);
        return "redirect:/habitaciones-tipos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable String id) {
        service.eliminar(id);
        return "redirect:/habitaciones-tipos";
    }
}