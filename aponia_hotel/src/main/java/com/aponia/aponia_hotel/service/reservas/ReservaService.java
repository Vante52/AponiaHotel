package com.aponia.aponia_hotel.service.reservas;

import com.aponia.aponia_hotel.entities.reservas.Reserva;
import java.util.List;
import java.util.Optional;

public interface ReservaService {
    List<Reserva> listar();
    Reserva crear(Reserva reserva);
    Optional<Reserva> obtener(String id);
    Reserva actualizar(Reserva reserva);
    void eliminar(String id);
    List<Reserva> findByClienteId(String clienteId);
    Optional<Reserva> findByCodigo(String codigo);
    void confirmarReserva(String id);
    void cancelarReserva(String id);
}