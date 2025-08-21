package com.aponia.aponia_hotel.service.pagos;

import com.aponia.aponia_hotel.entities.pagos.Pago;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PagoService {
    List<Pago> listar();
    Pago crear(Pago pago);
    Optional<Pago> obtener(String id);
    Pago actualizar(Pago pago);
    void eliminar(String id);
    List<Pago> findByReservaId(String reservaId);
    void completarPago(String id);
    BigDecimal obtenerTotalPagado(String reservaId);
}