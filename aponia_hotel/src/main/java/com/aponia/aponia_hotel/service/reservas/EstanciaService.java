package com.aponia.aponia_hotel.service.reservas;

import com.aponia.aponia_hotel.entities.reservas.Estancia;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EstanciaService {
    /**
     * Lista todas las estancias
     */
    List<Estancia> listar();

    /**
     * Lista las estancias por reserva
     */
    List<Estancia> listarPorReserva(String reservaId);

    /**
     * Crea una nueva estancia
     */
    Estancia crear(Estancia estancia);

    /**
     * Obtiene una estancia por su ID
     */
    Optional<Estancia> obtener(String id);

    /**
     * Actualiza una estancia existente
     */
    Estancia actualizar(Estancia estancia);

    /**
     * Elimina una estancia por su ID
     */
    void eliminar(String id);

    // Métodos deprecados o no utilizados
    // @deprecated Usar listarPorReserva() en su lugar
    // List<Estancia> findByReservaId(String reservaId);

    // @deprecated No implementado actualmente
    // List<Estancia> findByHabitacionAsignadaAndFechas(String habitacionId, LocalDate checkIn, LocalDate checkOut);
}