package com.aponia.aponia_hotel.service.pagos;

import com.aponia.aponia_hotel.entities.pagos.ResumenPago;
import java.util.List;
import java.util.Optional;

public interface ResumenPagoService {
    /**
     * Lista todos los resúmenes de pago
     */
    List<ResumenPago> listar();

    /**
     * Obtiene el resumen de pago de una reserva
     */
    Optional<ResumenPago> obtenerPorReserva(String reservaId);

    /**
     * Crea un nuevo resumen de pago
     */
    ResumenPago crear(ResumenPago resumenPago);

    /**
     * Obtiene un resumen de pago por su ID
     */
    Optional<ResumenPago> obtener(String id);

    /**
     * Actualiza un resumen de pago existente
     */
    ResumenPago actualizar(ResumenPago resumenPago);

    /**
     * Elimina un resumen de pago por su ID
     */
    void eliminar(String id);

    // Métodos deprecados o no utilizados
    // @deprecated Usar obtenerPorReserva() en su lugar
    // Optional<ResumenPago> findByReservaId(String reservaId);
}