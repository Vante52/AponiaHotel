package com.aponia.aponia_hotel.service.reservas;

import com.aponia.aponia_hotel.entities.reservas.Reserva;
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

    void confirmarReserva(String id);

    Reserva cancelarReserva(String id);

    // Métodos deprecados o no utilizados
    // @deprecated Usar obtenerPorCodigo() en su lugar
    // Optional<Reserva> findByCodigo(String codigo);

    // @deprecated Usar listarPorCliente() en su lugar
    // List<Reserva> findByClienteId(String clienteId);
}