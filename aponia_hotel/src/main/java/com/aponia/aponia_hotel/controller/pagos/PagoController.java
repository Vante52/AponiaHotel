package com.aponia.aponia_hotel.controller.pagos;

import com.aponia.aponia_hotel.entities.pagos.Pago;
import com.aponia.aponia_hotel.service.pagos.PagoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService service;

    public PagoController(PagoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pagos", service.listar());
        return "pagos/list";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        Pago pago = new Pago();
        pago.setFecha(LocalDateTime.now());
        pago.setEstado("pendiente");
        pago.setTipo("pago_parcial");
        model.addAttribute("pago", pago);
        return "pagos/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Pago pago) {
        if (pago.getId() == null || pago.getId().isBlank()) {
            pago.setId(UUID.randomUUID().toString());
        }
        service.crear(pago);
        return "redirect:/pagos";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable String id, Model model) {
        model.addAttribute("pago", service.obtener(id));
        return "pagos/form";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable String id, @ModelAttribute Pago pago) {
        pago.setId(id);
        service.actualizar(pago);
        return "redirect:/pagos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable String id) {
        service.eliminar(id);
        return "redirect:/pagos";
    }

    @PostMapping("/{id}/completar")
    public String completar(@PathVariable String id) {
        service.completarPago(id);
        return "redirect:/pagos";
    }
}