package com.aponia.aponia_hotel.service.servicios;

import com.aponia.aponia_hotel.entities.servicios.ServicioDisponibilidad;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ServicioDisponibilidadService {
    /**
     * Lista todas las disponibilidades
     */
    List<ServicioDisponibilidad> listar();

    /**
     * Lista las disponibilidades de un servicio en una fecha con capacidad suficiente
     */
    List<ServicioDisponibilidad> listarDisponibles(String servicioId, LocalDate fecha, int capacidadRequerida);

    /**
     * Crea una nueva disponibilidad
     */
    ServicioDisponibilidad crear(ServicioDisponibilidad disponibilidad);

    /**
     * Obtiene una disponibilidad por su ID
     */
    Optional<ServicioDisponibilidad> obtener(String id);

    /**
     * Actualiza una disponibilidad existente
     */
    ServicioDisponibilidad actualizar(ServicioDisponibilidad disponibilidad);

    /**
     * Elimina una disponibilidad por su ID
     */
    void eliminar(String id);

    /**
     * Busca una disponibilidad específica por servicio, fecha y hora
     */
    Optional<ServicioDisponibilidad> buscarDisponibilidad(String servicioId, LocalDate fecha, LocalTime horaInicio);

    // Métodos deprecados o no utilizados
    // @deprecated Usar listarDisponibles() en su lugar
    // List<ServicioDisponibilidad> findByServicioIdAndFecha(String servicioId, LocalDate fecha);

    // @deprecated Usar buscarDisponibilidad() en su lugar
    // Optional<ServicioDisponibilidad> findByServicioAndFechaAndHora(String servicioId, LocalDate fecha, LocalTime horaInicio);
}