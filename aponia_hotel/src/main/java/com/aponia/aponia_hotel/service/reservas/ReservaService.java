package com.aponia.aponia_hotel.service.reservas;

import com.aponia.aponia_hotel.entities.reservas.Reserva;
import com.aponia.aponia_hotel.entities.reservas.Reserva.EstadoReserva;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservaService {
    /**
     * Lista todas las reservas
     */
    List<Reserva> listar();

    /**
     * Lista las reservas por cliente
     */
    List<Reserva> listarPorCliente(String clienteId);

    /**
     * Lista las reservas por estado
     */
    List<Reserva> listarPorEstado(EstadoReserva estado);

    /**
     * Lista las reservas activas (no canceladas ni completadas) por cliente
     */
    List<Reserva> listarReservasActivas(String clienteId);

    /**
     * Crea una nueva reserva
     */
    Reserva crear(Reserva reserva);

    /**
     * Obtiene una reserva por su ID
     */
    Optional<Reserva> obtener(String id);

    /**
     * Obtiene una reserva por su código
     */
    Optional<Reserva> obtenerPorCodigo(String codigo);

    /**
     * Actualiza una reserva existente
     */
    Reserva actualizar(Reserva reserva);

    /**
     * Elimina una reserva por su ID
     */
    void eliminar(String id);

    /**
     * Confirma una reserva y asigna habitaciones
     */
    Reserva confirmarReserva(String id);

    /**
     * Cancela una reserva
     */
    Reserva cancelarReserva(String id);

    /**
     * Marca una reserva como completada
     */
    Reserva completarReserva(String id);

    /**
     * Verifica si hay disponibilidad para las fechas y tipos de habitación solicitados
     */
    boolean verificarDisponibilidad(String tipoHabitacionId, LocalDate checkIn, LocalDate checkOut, int numeroHuespedes);

    /**
     * Calcula el total de una reserva (habitaciones + servicios)
     */
    double calcularTotalReserva(String id);
}