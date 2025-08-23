package com.aponia.aponia_hotel.service.pagos;

import com.aponia.aponia_hotel.entities.pagos.Pago;
import java.util.List;
import java.util.Optional;

public interface PagoService {
    /**
     * Lista todos los pagos
     */
    List<Pago> listar();

    /**
     * Lista los pagos por reserva
     */
    List<Pago> listarPorReserva(String reservaId);

    /**
     * Lista los pagos por reserva y estado
     */
    List<Pago> listarPorReservaYEstado(String reservaId, String estado);

    /**
     * Crea un nuevo pago
     */
    Pago crear(Pago pago);

    /**
     * Obtiene un pago por su ID
     */
    Optional<Pago> obtener(String id);

    /**
     * Actualiza un pago existente
     */
    Pago actualizar(Pago pago);

    /**
     * Elimina un pago por su ID
     */
    void eliminar(String id);

    Pago completarPago(String id);

    // Métodos deprecados o no utilizados
    // @deprecated Usar listarPorReserva() en su lugar
    // List<Pago> findByReservaId(String reservaId);

    // @deprecated Usar listarPorReservaYEstado() en su lugar
    // List<Pago> findByReservaIdAndEstado(String reservaId, String estado);
}