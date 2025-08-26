package com.aponia.aponia_hotel.controller.servicios;

import com.aponia.aponia_hotel.entities.servicios.Servicio;
import com.aponia.aponia_hotel.service.servicios.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "servicios/list"; // -> templates/servicios/list.html
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        Servicio servicio = new Servicio();
        servicio.setPrecioPorPersona(BigDecimal.ZERO);
        servicio.setDuracionMinutos(0);
        model.addAttribute("servicio", servicio);
        return "servicios/form"; // -> templates/servicios/form.html (tu formulario)
    }

    @PostMapping
    public String crear(@ModelAttribute Servicio servicio, RedirectAttributes ra) {
        if (servicio.getId() == null || servicio.getId().isBlank()) {
            servicio.setId(UUID.randomUUID().toString());
        }
        service.crear(servicio);
        ra.addFlashAttribute("ok", "Servicio creado correctamente");
        return "redirect:/servicios";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable String id, Model model, RedirectAttributes ra) {
        Servicio s = service.obtener(id).orElse(null);
        if (s == null) {
            ra.addFlashAttribute("error", "El servicio no existe");
            return "redirect:/servicios";
        }
        model.addAttribute("servicio", s);
        return "servicios/form";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable String id, @ModelAttribute Servicio servicio, RedirectAttributes ra) {
        servicio.setId(id);
        service.actualizar(servicio);
        ra.addFlashAttribute("ok", "Servicio actualizado");
        return "redirect:/servicios";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable String id, RedirectAttributes ra) {
        service.eliminar(id);
        ra.addFlashAttribute("ok", "Servicio eliminado");
        return "redirect:/servicios";
    }

    @GetMapping("/cards")
    public String listarCards(Model model) {
        model.addAttribute("servicios", service.listar());
        return "servicios/cards"; // si también usas vista en tarjetas
    }

    @GetMapping("/{id}")
    public String detallePublico(@PathVariable String id, Model model, org.springframework.web.servlet.mvc.support.RedirectAttributes ra) {
        return service.obtener(id)
            .map(s -> {
                model.addAttribute("servicio", s);
                return "servicios/detalle";
            })
            .orElseGet(() -> {
                ra.addFlashAttribute("error", "El servicio no existe");
                return "redirect:/servicios/cards";
            });
    }

}
