package com.aponia.aponia_hotel.service.servicios;

import com.aponia.aponia_hotel.entities.servicios.ServicioDisponibilidad;
import java.util.List;
import java.util.Optional;

public interface ServicioDisponibilidadService {
    List<ServicioDisponibilidad> listar();
    ServicioDisponibilidad crear(ServicioDisponibilidad disponibilidad);
    Optional<ServicioDisponibilidad> obtener(String id);
    ServicioDisponibilidad actualizar(ServicioDisponibilidad disponibilidad);
    void eliminar(String id);
    List<ServicioDisponibilidad> findByServicioId(String servicioId);
    List<ServicioDisponibilidad> findByServicioIdAndFecha(String servicioId, String fecha);
}